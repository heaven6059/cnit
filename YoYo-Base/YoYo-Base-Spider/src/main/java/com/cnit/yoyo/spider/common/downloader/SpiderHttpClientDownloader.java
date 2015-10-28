package com.cnit.yoyo.spider.common.downloader;

import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

/**
 * 爬虫抓取页面
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
public class SpiderHttpClientDownloader extends HttpClientDownloader {

	@Override
	protected HttpUriRequest getHttpUriRequest(Request request, Site site, Map<String, String> headers) {
        RequestBuilder requestBuilder = selectRequestMethod(request).setUri(request.getUrl());
        if (headers != null) {
            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
                requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
            }
        }
        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom()
                .setConnectionRequestTimeout(site.getTimeOut())
                .setSocketTimeout(site.getTimeOut())
                .setConnectTimeout(site.getTimeOut())
                .setCookieSpec(CookieSpecs.BEST_MATCH)
                .setCircularRedirectsAllowed(true)
                .setMaxRedirects(100);
		if (site.getHttpProxyPool().isEnable()) {
			HttpHost host = site.getHttpProxyFromPool();
			requestConfigBuilder.setProxy(host);
			request.putExtra(Request.PROXY, host);
		}
        requestBuilder.setConfig(requestConfigBuilder.build());
        return requestBuilder.build();
    }

}
