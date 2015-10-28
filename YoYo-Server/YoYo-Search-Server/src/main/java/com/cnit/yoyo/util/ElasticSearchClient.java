package com.cnit.yoyo.util;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * @Description:
 * @author wanghb
 * @date 2015年5月4日
 * @Copyright 2015 cnit
 * @version V1.0.0
 */
public class ElasticSearchClient {

	private String clusterName;
	private String elasticUrl;
	private Integer elasticPort;

	public Client getClient() {
		//设置client.transport.sniff为true来使客户端去嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中，这样做的好处是一般不用手动设置集群里所有集群的ip到连接客户端，
		//它会自动帮你添加，并且自动发现新加入集群的机器
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", clusterName).put("client.transport.sniff", true).build();
		Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress(elasticUrl, elasticPort));
		return client;
	}

	public static void main(String[] args) {
		// Client client = getClient();
		// QueryBuilder query = QueryBuilders.matchQuery("name", "44");
		// SearchResponse response =
		// client.prepareSearch("products").setTypes("productList").setQuery(query).setFrom(0).setSize(60).execute().actionGet();
		// SearchHits shs = response.getHits();
		// for (SearchHit hit : shs) {
		// System.out.println("分数(score):" + hit.getScore() + ", 业务描述(desc):" +
		// hit.getSource().get("desc"));
		// }
		// client.close();
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getElasticUrl() {
		return elasticUrl;
	}

	public void setElasticUrl(String elasticUrl) {
		this.elasticUrl = elasticUrl;
	}

	public Integer getElasticPort() {
		return elasticPort;
	}

	public void setElasticPort(Integer elasticPort) {
		this.elasticPort = elasticPort;
	}

}
