package com.raderleao.admin.catalogo.infrastructure.configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.raderleao.admin.catalogo.infrastructure.configuration.properties.s3.S3StorageProperties;
import com.raderleao.admin.catalogo.infrastructure.configuration.properties.storage.StorageProperties;
import com.raderleao.admin.catalogo.infrastructure.services.StorageService;
import com.raderleao.admin.catalogo.infrastructure.services.impl.S3StorageService;
import com.raderleao.admin.catalogo.infrastructure.services.local.InMemoryStorageService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class StorageConfig {

    @Bean
    @ConfigurationProperties(value = "storage.catalogo-videos")
    public StorageProperties storageProperties() {
        return new StorageProperties();
    }

    @Bean
    @Profile({"development", "test-integration", "test-e2e"})
    public StorageService localStorageAPI() {
        return new InMemoryStorageService();
    }

    @Bean
    @ConditionalOnMissingBean
    public StorageService s3StorageAPI(
            final S3StorageProperties props,
            final AmazonS3 s3Client
    ) {
        return new S3StorageService(props.getBucket(), s3Client);
    }
}
