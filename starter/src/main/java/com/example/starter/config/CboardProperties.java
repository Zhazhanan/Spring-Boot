package com.example.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author WangKun
 * @create 2018-12-06
 * @desc
 **/
@ConfigurationProperties(prefix = "cboard.db")
public class CboardProperties {
    private Boolean schemaUpdate;

    public Boolean getSchemaUpdate() {
        return schemaUpdate;
    }

    public void setSchemaUpdate(Boolean schemaUpdate) {
        this.schemaUpdate = schemaUpdate;
    }
}
