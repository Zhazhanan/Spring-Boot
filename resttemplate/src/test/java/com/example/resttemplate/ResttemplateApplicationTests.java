package com.example.resttemplate;

import com.example.resttemplate.config.RestTemplateConfig;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResttemplateApplicationTests {

	@Autowired
	RestTemplate restTemplate;
	@Test
	public void contextLoads() {

		ResponseMsg object = restTemplate.getForObject("http://localhost:9000/api/jyidentity/users/123", ResponseMsg.class);
		System.out.println(object.toString());


		ResponseMsg responseMsg = restTemplate.postForObject("http://localhost:9000/api/jyidentity/groups/create", null, ResponseMsg.class);
		System.out.println(responseMsg.toString());
	}

}
