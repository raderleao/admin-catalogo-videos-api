package com.raderleao.admin.catalogo.infrastructure.services.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.raderleao.admin.catalogo.domain.Fixture;
import com.raderleao.admin.catalogo.domain.video.VideoMediaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


public class S3StorageServiceAPITest {

    private S3StorageService target;

    private AmazonS3 storage;

    private String bucket = "catalogo-videos";

    @BeforeEach
    public void setUp() {
        this.storage = mock(AmazonS3.class);
        this.target = new S3StorageService(bucket, storage);
    }

    @Test
    public void givenValidResource_whenCallsStore_shouldStoreIt() throws IOException {
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.THUMBNAIL);
        final var expectedId = expectedResource.name();

        final InputStream inputStream = new ByteArrayInputStream(expectedResource.content());
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(expectedResource.contentType());

        final S3Object s3Object = new S3Object();
        s3Object.setBucketName(bucket);
        s3Object.setKey(expectedId);
        s3Object.setObjectContent(inputStream);
        s3Object.setObjectMetadata(metadata);

        doReturn(s3Object).when(storage).getObject(eq(bucket), eq(expectedId));

        this.target.store(expectedId, expectedResource);

        final var capturer = ArgumentCaptor.forClass(PutObjectRequest.class);

        verify(storage, times(1)).putObject(capturer.capture());

        final var actualPutObjectRequest = capturer.getValue();
        final var actualInputStream = actualPutObjectRequest.getInputStream();
        final var actualMetadata = actualPutObjectRequest.getMetadata();

        Assertions.assertEquals(bucket, actualPutObjectRequest.getBucketName());
        Assertions.assertEquals(expectedId, actualPutObjectRequest.getKey());
        Assertions.assertEquals(expectedResource.contentType(), actualMetadata.getContentType());
        // Verifique outras propriedades de metadados, se necessário

        // Verifique o conteúdo do objeto
        byte[] actualContent = new byte[expectedResource.content().length];
        actualInputStream.read(actualContent, 0, expectedResource.content().length);
        Assertions.assertArrayEquals(expectedResource.content(), actualContent);
    }

    @Test
    public void givenResource_whenCallsGet_shouldRetrieveIt() throws IOException {
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.THUMBNAIL);
        final var expectedId = expectedResource.name();

        final InputStream inputStream = new ByteArrayInputStream(expectedResource.content());
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(expectedResource.contentType());
        metadata.setContentMD5(expectedResource.checksum());

        final S3Object s3Object = new S3Object();
        s3Object.setBucketName(bucket);
        s3Object.setKey(expectedId);
        s3Object.setObjectContent(inputStream);
        s3Object.setObjectMetadata(metadata);

        doReturn(s3Object).when(storage).getObject(eq(bucket), eq(expectedId));

        final var actualContent = target.get(expectedId).get();

        Assertions.assertEquals(expectedResource.checksum(), actualContent.checksum());
        Assertions.assertEquals(expectedResource.name(), actualContent.name());
        Assertions.assertArrayEquals(expectedResource.content(), actualContent.content());
        Assertions.assertEquals(expectedResource.contentType(), actualContent.contentType());
    }

    @Test
    public void givenInvalidResource_whenCallsGet_shouldRetrieveEmpty() {
        final var expectedResource = Fixture.Videos.resource(VideoMediaType.THUMBNAIL);
        final var expectedId = expectedResource.name();

        doReturn(null).when(storage).getObject(eq(bucket), eq(expectedId));

        final var actualContent = target.get(expectedId);

        Assertions.assertTrue(actualContent.isEmpty());
    }

    @Test
    public void givenPrefix_whenCallsList_shouldRetrieveAll() {
        final var video = Fixture.Videos.resource(VideoMediaType.VIDEO);
        final var banner = Fixture.Videos.resource(VideoMediaType.BANNER);
        final var expectedIds = List.of(video.name(), banner.name());

        // Criar mocks dos objetos necessários
        ListObjectsV2Result objectListing = mock(ListObjectsV2Result.class);
        S3ObjectSummary objectSummary1 = mock(S3ObjectSummary.class);
        S3ObjectSummary objectSummary2 = mock(S3ObjectSummary.class);

        // Configurar comportamento dos mocks
        when(storage.listObjectsV2(eq(bucket), eq("it"))).thenReturn(objectListing);
        when(objectListing.getObjectSummaries()).thenReturn(Arrays.asList(objectSummary1, objectSummary2));
        when(objectSummary1.getKey()).thenReturn(video.name());
        when(objectSummary2.getKey()).thenReturn(banner.name());

        // Chamar o método list e verificar o resultado
        List<String> actualContent = target.list("it");

        Assertions.assertTrue(
                expectedIds.size() == actualContent.size()
                        && expectedIds.containsAll(actualContent)
        );
    }

    @Test
    public void givenResource_whenCallsDeleteAll_shouldEmptyStorage() {
        final var expectedIds = List.of("item1", "item2");

        final var capturer = ArgumentCaptor.forClass(DeleteObjectsRequest.class);

        target.deleteAll(expectedIds);

        verify(storage, times(1)).deleteObjects(capturer.capture());

        final var actualIds = capturer.getValue().getKeys().stream()
                .map(DeleteObjectsRequest.KeyVersion::getKey)
                .toList();

        Assertions.assertTrue(expectedIds.size() == actualIds.size() && actualIds.containsAll(expectedIds));
    }
}