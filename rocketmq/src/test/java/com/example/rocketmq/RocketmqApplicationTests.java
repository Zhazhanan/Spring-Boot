package com.example.rocketmq;

import com.example.rocketmq.config.bean.MQConsumer;
import com.example.rocketmq.config.bean.MQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RocketmqApplicationTests {

    @Autowired
    MQProducer rocketMQProducer;
    @Autowired
    MQConsumer rocketMQConsumer;

    @Test
    public void contextLoads() {
        Message msg = null;
        try {
            String ms = "{\"antifraudMatch\":\"0\",\"application\":[{\"createTime\":\"2019-06-10 10:16:18\",\"appProductType\":\"PTL170500105\",\"appProductName\":\"薪悦贷\",\"applicationType\":\"LOAN\",\"customerManagerName\":\"陶倩\",\"decision\":\"质检通过-审查中\",\"auditStatus\":\"1700\",\"isEms\":\"否\",\"applicationNumber\":130154740357,\"minAppAmount\":30000,\"loanType\":\"非循环贷\",\"province\":\"河北省\",\"applyPeriod\":\"24\",\"customerManagerMobilePhone\":\"17731605275\",\"assignmentVersion\":\"v2\",\"acceptStoreName\":\"廊坊\",\"manageStoreName\":\"廊坊\",\"creditStart\":\"2019-06-11 14:05:59\",\"acceptStoreCode\":\"301002004\",\"customerChannel\":\"借悦\",\"loanPurpose\":\"3\",\"areaOrgId\":\"30102\",\"customerSource\":\"借悦\",\"monthPayment\":0,\"customerServiceMobilePhone\":\"17731605275\",\"maxAppAmount\":30000,\"manageStoreCode\":\"301002004\",\"familyIsKnown\":\"是\",\"customerServiceCode\":\"11037385\",\"customerManagerCode\":\"11037385\",\"customerServiceName\":\"陶倩\"}],\"applicationNumber\":\"130154740357\",\"bankCardsInfo\":[{\"accountName\":\"招商银行\",\"bankReservedPhone\":\"13916148895\",\"isLoanType\":\"是\",\"isReceiveType\":\"是\",\"bankCardAccount\":\"6214830135922982\",\"bankName\":\"黄梁金\",\"bankCode\":\"308\"}],\"caller\":\"1\",\"channel\":\"JY01\",\"contactInfo\":[{\"conRelation\":\"朋友\",\"conPhone\":\"13549925804\",\"conName\":\"二黄梁金\",\"contactType\":\"紧急联系人\"},{\"conRelation\":\"配偶\",\"conPhone\":\"13654260753\",\"conName\":\"四黄梁金\",\"contactType\":\"紧急联系人\"},{\"conRelation\":\"母亲\",\"conPhone\":\"13815077387\",\"conName\":\"一黄梁金\",\"contactType\":\"家庭联系人\"},{\"conRelation\":\"同事\",\"conPhone\":\"13467601220\",\"conName\":\"三黄梁金\",\"contactType\":\"工作联系人\"}],\"customerBasicInfo\":[{\"cardId\":\"150623198805254283\",\"birthday\":\"1989-12-03\",\"sex\":\"M\",\"hometownAddr\":\"北京市大兴区\",\"cardEndT\":\"2018-05-22\",\"cardType\":\"身份证\",\"nation\":\"汉\",\"telphone\":\"13916148895\",\"customerSource\":\"借悦\",\"isLong\":\"否\",\"currentAddr\":\"北京市大兴区\",\"mobilePhone\":\"13916148895\",\"age\":29,\"cardStartT\":\"2008-05-22\",\"custName\":\"飞司一\"}],\"decisionsType\":\"CT01\",\"flowSource\":\"1\",\"housePropertyInfo\":[{\"houseMonthlyPayment\":0,\"houseType\":\"全款房\",\"houseProRightRate\":\"97\"}],\"jobBasicInfo\":[{\"jAddrAreacode\":\"110101\",\"jDetailAddr\":\"北京市北京市东城区银河SOHO572\",\"jName\":\"北京京邦达贸易 都匀第一分\",\"jAddr\":\"银河SOHO572\",\"jPhoneAreaCode\":\"934\",\"jPhone\":\"96040490\",\"seniority\":0,\"jEnterT\":\"2018-10-13\"}],\"operateLog\":[{\"id\":69935337394,\"operateTime\":\"2019-06-11 14:12:38\",\"statusName\":\"质检通过-审查中\",\"applicationNumber\":130154740357,\"operateUserName\":\"王浩\",\"operateUserId\":10033493,\"operateStatus\":\"1700\"},{\"id\":69935337390,\"operateTime\":\"2019-06-10 10:16:18\",\"statusName\":\"已提交-待质检\",\"applicationNumber\":130154740357,\"operateUserId\":1,\"operateStatus\":\"1200\",\"operateContent\":\"还款易进件待质检\"},{\"id\":69935337391,\"operateTime\":\"2019-06-11 14:05:43\",\"statusName\":\"已提交-质检中\",\"applicationNumber\":130154740357,\"operateUserName\":\"王浩\",\"operateUserId\":10033493,\"operateStatus\":\"1300\",\"operateContent\":\"领取任务, 交叉质检中\"},{\"id\":69935337392,\"operateTime\":\"2019-06-11 14:05:59\",\"statusName\":\"质检通过\",\"applicationNumber\":130154740357,\"operateUserName\":\"王浩\",\"operateUserId\":10033493,\"operateStatus\":\"1600\"}],\"queryType\":\"INS01SCORE01\"}";
            msg = new Message("TOPIC_LOANCREDIT_ANTIFRAUD_JC5WK", "TAG_ANTIFRAUD_SUBMISSION", ms.getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult send = rocketMQProducer.sendMessage(msg);
            System.out.println("------------------------------------------------" + send.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
