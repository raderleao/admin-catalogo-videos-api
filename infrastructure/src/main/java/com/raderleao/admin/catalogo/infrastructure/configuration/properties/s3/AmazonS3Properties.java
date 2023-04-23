package com.raderleao.admin.catalogo.infrastructure.configuration.properties.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class AmazonS3Properties implements InitializingBean {

    private static final Logger log =
            LoggerFactory.getLogger(AmazonS3Properties.class);

    private String accessKey;

    private String secretKey;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public void afterPropertiesSet() {
        log.debug(toString());
    }

    @Override
    public String toString() {
        return "AmazonS3Properties{" +
                "access-key='" + accessKey + '\'' +
                '}';
    }
}
