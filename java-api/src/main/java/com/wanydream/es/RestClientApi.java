package com.wanydream.es;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpHost;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.IOUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

public class RestClientApi {

	private static final Logger logger = LogManager.getLogger(RestClientApi.class);

	public static void main(String[] args) {
		RestClient client = null;
		try {
			client = RestClient
					.builder(new HttpHost("10.30.0.41", 9200, "http"), new HttpHost("10.30.0.42", 9800, "http"))
					.build();

			Response response = client.performRequest("GET", "_cluster/health?pretty");
			logger.info("sync demo 1 :" + IOUtils.toString(new InputStreamReader(response.getEntity().getContent())));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null)
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
