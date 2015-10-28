package com.cnit.yoyo.search.service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.ClientConfig;
import io.searchbox.client.config.ClientConstants;

import java.util.LinkedHashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 搜索引擎客户端
 *@author wanghb
 *@date 2015-04-30
 *@version 1.0.0
 */
@Configuration
public class SpringConfiguration {

	public @Bean
	ClientConfig clientConfig() {
//		String connectionUrl = "http://10.255.8.92:9200";
		String connectionUrl = "http://10.10.7.221:9200";
//		String connectionUrl = "http://10.255.8.17:9200";
		ClientConfig clientConfig = new ClientConfig();
		LinkedHashSet<String> servers = new LinkedHashSet<String>();
		servers.add(connectionUrl);
		clientConfig.getServerProperties().put(ClientConstants.SERVER_LIST, servers);
		clientConfig.getClientFeatures().put(ClientConstants.IS_MULTI_THREADED, false);
		return clientConfig;
	}

	public @Bean
	JestClient jestClient() {
		JestClientFactory factory = new JestClientFactory();
		factory.setClientConfig(clientConfig());
		return factory.getObject();
	}
}
