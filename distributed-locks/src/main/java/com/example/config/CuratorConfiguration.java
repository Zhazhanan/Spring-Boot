package com.example.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CuratorConfiguration {

    @Value("${curator.retryCount}")
    private int retryCount;

    @Value("${curator.elapsedTimeMs}")
    private int elapsedTimeMs;

    @Value("${curator.connectString}")
    private String connectString;

    @Value("${curator.sessionTimeoutMs}")
    private int sessionTimeoutMs;

    @Value("${curator.connectionTimeoutMs}")
    private int connectionTimeoutMs;

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework() {
//        return CuratorFrameworkFactory.newClient(
//                connectString,
//                sessionTimeoutMs,
//                connectionTimeoutMs,
//                new RetryNTimes(retryCount, elapsedTimeMs));

       /* RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 1);
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString(host)
                        .sessionTimeoutMs(sessionTimeout)
                        .connectionTimeoutMs(connectTimeout)
                        .retryPolicy(retryPolicy)
                        *//*.aclProvider(new ACLProvider() {
                            private List<ACL> acl ;
                            @Override
                            public List<ACL> getDefaultAcl() {
                                if(acl ==null) {
                                    ArrayList<ACL> acl = ZooDefs.Ids.CREATOR_ALL_ACL;
                                    acl.clear();
                                    acl.add(new ACL(ZooDefs.Perms.ALL, new Id("auth", "admin:admin")));
                                    this.acl = acl;
                                }
                                return acl;
                            }
                            @Override
                            public List<ACL> getAclForPath(String s) {
                                return acl;
                            }
                        })*//*
//                        .namespace(namespace)
                        .build();
        client.start();
        client.getConnectionStateListenable().addListener(new ZookeeperConnectionListener(host,maxRetry,sessionTimeout,connectTimeout,namespace));
        return client;*/

        return CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .sessionTimeoutMs(sessionTimeoutMs)  // 会话超时时间
                .connectionTimeoutMs(connectionTimeoutMs) // 连接超时时间
                .retryPolicy(new RetryNTimes(retryCount, elapsedTimeMs))
                .namespace("base") // 包含隔离名称
                .build();
    }
}
