package com.wanysoul.es.web;

import javax.annotation.Resource;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Resource
	private TransportClient client;
	
	@RequestMapping(value="/test")
	public void test(){
		GetResponse response = client.prepareGet("us", "user", "1").get();
		System.out.println(response.getSourceAsString()); ;
	}

}
