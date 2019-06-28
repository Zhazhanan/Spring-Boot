package com.example.esjob;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.example.esjob.config.ElasticJobHandler;
import com.example.esjob.config.SimElasticJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsJobApplicationTests {

    @Autowired
    private ZookeeperRegistryCenter zookeeperRegistryCenter;

    @Autowired
    private ElasticJobHandler jobHandler;

    @Test
    public void contextLoads() throws InterruptedException {
//        SimElasticJob myElasticJob = new SimElasticJob();
//        // 定义作业核心配置
//        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("demoSimpleJob", "0/10 * * *  ?", 10).build();
//        // 定义SIMPLE类型配置
//        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, SimElasticJob.class.getCanonicalName());
//        // 定义Lite作业根配置
//        LiteJobConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).build();
//        JobScheduler jobScheduler = new JobScheduler(zookeeperRegistryCenter, simpleJobRootConfig);
//        jobScheduler.init();

        jobHandler.addJob("SimpleJob", "*/5 * * * * ?", 10,"1");
        Thread.sleep(100000);

    }

}
