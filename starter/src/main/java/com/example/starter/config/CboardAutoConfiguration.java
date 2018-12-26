package com.example.starter.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author WangKun
 * @create 2018-12-06
 * @desc
 **/
@Configuration
@EnableConfigurationProperties(CboardProperties.class)
public class CboardAutoConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(CboardAutoConfiguration.class);

    @Autowired
    private CboardProperties cboardProperties;
    @Autowired
    private ApplicationContext ctx;

    @Bean
    public void dbInit() throws IOException {
        Boolean schemaUpdate = cboardProperties.getSchemaUpdate();
        if (schemaUpdate != null && schemaUpdate == true) {
                LOGGER.info("---------------------------dbInit::{}");
        }
    }

    private InputStream getConfigStream() throws IOException {
        Resource resource = ctx.getResource("");
        InputStream is = resource.getInputStream();
        return is;
    }

    public static byte[] readInputStream(InputStream inputStream, String inputStreamName) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[16 * 1024];
        try {
            int bytesRead = inputStream.read(buffer);
            while (bytesRead != -1) {
                outputStream.write(buffer, 0, bytesRead);
                bytesRead = inputStream.read(buffer);
            }
        } catch (Exception e) {
            throw new RuntimeException("Couldn't read input stream " + inputStreamName, e);
        } finally {
            inputStream.close();
        }
        return outputStream.toByteArray();
    }
}
