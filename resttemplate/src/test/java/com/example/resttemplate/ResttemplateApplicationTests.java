package com.example.resttemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.resttemplate.config.RestTemplateConfig;
import org.apache.http.client.methods.HttpGet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResttemplateApplicationTests {

	@Autowired
	HttpService httpService;
	@Autowired
	private RestTemplate restTemplate;
	@Test
	public void contextLoads() {

//		String url = "http://audit/audit/modules/lnadRauReconsider/checkIsReconsider/v1/%s";
//		url = String.format(url, "123123");
//		System.out.println(url);
//		ResponseMsg object = restTemplate.getForObject(url, ResponseMsg.class);

//		ResponseMsg object = restTemplate.getForObject("http://localhost:9000/api/jyidentity/users/123", ResponseMsg.class);
//		System.out.println(object.toString());


//		ResponseMsg responseMsg = restTemplate.postForObject("http://localhost:9000/api/jyidentity/groups/create", null, ResponseMsg.class);
//		System.out.println(responseMsg.toString());
	}

	class Requcet{
		String userId;
		List<String> taskIds;

		public Requcet() {
		}

		public Requcet(String userId, List<String> taskIds) {
			this.userId = userId;
			this.taskIds = taskIds;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public List<String> getTaskIds() {
			return taskIds;
		}

		public void setTaskIds(List<String> taskIds) {
			this.taskIds = taskIds;
		}
	}
	@Test
	public void test() throws Exception {
		String url = "http://192.168.70.86:9000/api/jytask/action/setAssignee/v1";
		Map<String, Object> map = new HashMap<>();
		List list = new ArrayList<>();

		map.put("userId", "userId");
		map.put("taskIds", list);

		Requcet requcet = new Requcet();
		HashMap s = httpService.sendHttpPost(url, requcet, HashMap.class);
		System.out.println(s);
	}

}
