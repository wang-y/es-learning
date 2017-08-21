package com.wanydream.es;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class TransportClientApi {

	public static void main(String[] args) {
		TransportClient client = null;
		try {
			// 设置集群名称
			Settings settings = Settings.builder().put("cluster.name", "ori_test_es").build();
			// 创建连接通道
			client = new PreBuiltTransportClient(settings)
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.30.0.41"), 9300))
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("10.30.0.42"), 9300));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (client != null) {
				client.close();
			}
		}

	}
}
