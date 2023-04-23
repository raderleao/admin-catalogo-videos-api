package com.raderleao.admin.catalogo.infrastructure.configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.raderleao.admin.catalogo.infrastructure.configuration.properties.s3.AmazonS3Properties;
import com.raderleao.admin.catalogo.infrastructure.configuration.properties.s3.S3StorageProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"development", "production"})
public class S3CloudConfig {

    @Bean
    @ConfigurationProperties("amazon.s3")
    public AmazonS3Properties amazonS3Properties() {
        return new AmazonS3Properties();
    }

    @Bean
    @ConfigurationProperties("amazon.s3.storage.catalogo-videos")
    public S3StorageProperties s3StorageProperties() {
        return new S3StorageProperties();
    }

    @Bean
    public AmazonS3 amazonS3Client(
            final AmazonS3Properties cloudConfig,
            final S3StorageProperties storageConfig
    ) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
                cloudConfig.getAccessKey(),
                cloudConfig.getSecretKey()
        );

        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setConnectionTimeout(storageConfig.getConnectTimeout());
        clientConfiguration.setSocketTimeout(storageConfig.getReadTimeout());
        clientConfiguration.setMaxErrorRetry(storageConfig.getRetryMaxAttempts());

        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(storageConfig.getRegion())
                .withClientConfiguration(clientConfiguration);

        return builder.build();
    }

}