package com.example.esjob.config;

import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Description
 * <br> Created by WangKun on 2019/06/28
 * <br>
 **/
@Configuration
public class JobConfig {

//    @Resource
//    private DataSource dataSource;


    @Value("${regCenter.serverList}")
    private String serverlists;
    @Value("${regCenter.namespace}")
    private String namespace;

    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter zookeeperRegistryCenter() {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverlists, namespace));
    }

//    @Bean
//    public JobEventConfiguration jobEventConfiguration() {
//        return new JobEventRdbConfiguration(dataSource);
//    }

    /**
     * 配置任务监听器
     *
     * @return
     */
    @Bean
    public ElasticJobListener elasticJobListener() {
        return new MyElasticJobListener();
    }


    public class MyElasticJobListener implements ElasticJobListener {
        private final Logger logger = LoggerFactory.getLogger(MyElasticJobListener.class);
        private long beginTime = 0;

        @Override
        public void beforeJobExecuted(ShardingContexts shardingContexts) {
            beginTime = System.currentTimeMillis();
            logger.info("===>{}" + shardingContexts.getJobName() + " JOB BEGIN TIME: {} <===");
        }

        @Override
        public void afterJobExecuted(ShardingContexts shardingContexts) {
            long endTime = System.currentTimeMillis();
            logger.info("===>{} " + shardingContexts.getJobName() + "JOB END TIME: {},TOTAL CAST: {} <===");
        }
    }

   /* @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final SimpleJob simpleJob,
                                           @Value("${stockJob.cron}") final String cron,
                                           @Value("${stockJob.shardingTotalCount}") final int shardingTotalCount,
                                           @Value("${stockJob.shardingItemParameters}") final String shardingItemParameters) {
        return new SpringJobScheduler(simpleJob, regCenter(), getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters));
    }

    *//**
     * @Description 任务配置类
     *//*
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass,
                                                         final String cron,
                                                         final int shardingTotalCount,
                                                         final String shardingItemParameters) {
        return LiteJobConfiguration
                .newBuilder(
                        new SimpleJobConfiguration(
                                JobCoreConfiguration.newBuilder(
                                        jobClass.getName(), cron, shardingTotalCount)
                                        .shardingItemParameters(shardingItemParameters)
                                        .build()
                                , jobClass.getCanonicalName()
                        )
                )
                .overwrite(true)
                .build();

    }*/
}
