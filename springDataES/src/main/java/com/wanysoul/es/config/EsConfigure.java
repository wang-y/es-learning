package com.wanysoul.es.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfigure implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

	private static Logger logger = LogManager.getLogger(EsConfigure.class);

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String clusterNodes;

	@Value("${spring.data.elasticsearch.cluster-name}")
	private String clusterName;

	@Value("${spring.data.elasticsearch.sniff}")
	private boolean sniff;

	private TransportClient client;

	private PreBuiltTransportClient preBuiltTransportClient;

	@Override
	public void destroy() throws Exception {
		try {
			logger.info("Closing elasticSearch client");
			if (client != null) {
				client.close();
			}
		} catch (final Exception e) {
			logger.error("Error closing ElasticSearch client: ", e);
		}
	}

	@Override
	public TransportClient getObject() throws Exception {
		return client;
	}

	@Override
	public Class<TransportClient> getObjectType() {
		return TransportClient.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		buildClient();
	}

	protected void buildClient() {
		try {
			preBuiltTransportClient = new PreBuiltTransportClient(settings());
			if (!"".equals(clusterNodes)) {
				for (String nodes : clusterNodes.split(",")) {
					String InetSocket[] = nodes.split(":");
					String Address = InetSocket[0];
					Integer port = Integer.valueOf(InetSocket[1]);
					preBuiltTransportClient
							.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(Address), port));
				}
				client = preBuiltTransportClient;
			}
		} catch (UnknownHostException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 初始化默认的client
	 */
	private Settings settings() {
		Settings settings = Settings.builder().put("cluster.name", clusterName).put("client.transport.sniff", sniff) // 嗅探内网集群状态并自动发现新加入集群的机器
				.build();
		return settings;
	}
}
