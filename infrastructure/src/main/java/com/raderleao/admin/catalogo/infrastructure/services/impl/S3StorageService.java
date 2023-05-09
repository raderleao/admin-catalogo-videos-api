package com.raderleao.admin.catalogo.infrastructure.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.raderleao.admin.catalogo.domain.video.Resource;
import com.raderleao.admin.catalogo.infrastructure.services.StorageService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class S3StorageService implements StorageService {

    private final String bucket;
    private final AmazonS3 s3Client;

    public S3StorageService(final String bucket, final AmazonS3 s3Client) {
        this.bucket = bucket;
        this.s3Client = s3Client;
    }

    @Override
    public void store(final String id, final Resource resource) {
        byte[] content = resource.content();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(resource.contentType());
        metadata.setContentMD5(resource.checksum());

        ByteArrayInputStream inputStream = new ByteArrayInputStream(content);

        s3Client.putObject(new PutObjectRequest(bucket, id, inputStream, metadata));
    }

    @Override
    public Optional<Resource> get(final String id) {

        return Optional.ofNullable(this.s3Client.getObject(bucket, id))
                .map(obj -> {
                            try {
                                return Resource.with(
                                        IOUtils.toByteArray(obj.getObjectContent()),
                                        obj.getObjectMetadata().getContentMD5(),
                                        obj.getObjectMetadata().getContentType(),
                                        obj.getKey()
                                );
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

    }

    @Override
    public List<String> list(final String prefix) {
        ListObjectsV2Result objectListing = s3Client.listObjectsV2(bucket, prefix);
        List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        return objectSummaries.stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll(final List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        List<DeleteObjectsRequest.KeyVersion> keys = ids.stream()
                .map(id -> new DeleteObjectsRequest.KeyVersion(id))
                .collect(Collectors.toList());

        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucket);
        deleteObjectsRequest.setKeys(keys);

        s3Client.deleteObjects(deleteObjectsRequest);
    }
}
