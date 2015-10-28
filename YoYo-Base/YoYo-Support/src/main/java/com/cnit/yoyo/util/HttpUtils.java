package com.cnit.yoyo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnit.yoyo.util.sms.SmsReturnMsg;


/**
 * Description:HTTP请求
 * @author  wanghb
 * @version V1.0 
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public class HttpUtils {
	private static Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
	
	/** contentType post方式 */
	public static final String CONTENT_TYPE_POST = "application/json" ;
	
	
	/**
     * post请求(json请求方式)
     * @param url
     * @param reqStr
     * @param timeout 超时时间，秒
     * @return
     * @throws IOException
     */
    public static String post(String url, String reqStr, int timeout) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        //设置超时时间
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        String retVal = null;
        try {
            HttpPost httppost = new HttpPost(url);
            LOG.info("api请求url>>>:"+url);
            LOG.info("api请求参数>>>:"+reqStr);
            StringEntity s = new StringEntity(reqStr,"UTF-8");
            s.setContentType(CONTENT_TYPE_POST);//发送json数据需要设置contentType
            httppost.setEntity(s);
            long startTime = System.currentTimeMillis();
            HttpResponse response=httpclient.execute(httppost);
            long endTime = System.currentTimeMillis();
            int statusCode = response.getStatusLine().getStatusCode();
            LOG.info("api调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
            if(statusCode == HttpStatus.SC_OK){
            	retVal=EntityUtils.toString(response.getEntity());
            	LOG.info("api请求返回内容>>>:"+retVal);
            }else{
            	LOG.error("api请求返回失败>>>:"+response.getStatusLine());
            }
        } catch (IOException e) {
        	//发生网络异常
        	throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    
    /**
     * post请求 ，超时默认10秒
     * @param url
     * @param params
     * @return
     * @throws Exception 
     */

    public static String postForm(String url, Map<String, String> params) throws Exception {
        return postForm(url, params, 10);
    }
    /**
     * post请求
     * @param url
     * @param params
     * @param timeout 超时时间，秒
     * @return
     * @throws Exception 
     */
    public static String postForm(String url, Map<String, String> params, int timeout) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", timeout * 1000);
        httpclient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        String retVal = "";
        try {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    formparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
            HttpPost httppost = new HttpPost(url);
            httppost.setEntity(entity);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            retVal = new String(httpclient.execute(
                    httppost, responseHandler));
        } catch (IOException e) {
            throw e;
        } catch (Exception ex){
        	throw ex;
        }finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    /**
     * get请求
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, String> params) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setIntParameter("http.socket.timeout", 100000);
        String retVal = "";
        try {
            List<NameValuePair> qparams = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Map.Entry<String, String> param : params.entrySet()) {
                    qparams.add(new BasicNameValuePair(param.getKey(), param.getValue()));
                }
            }
            String paramstr = URLEncodedUtils.format(qparams, HTTP.UTF_8);
            if (StringUtils.isNotEmpty(paramstr)) {
                url = url + "?" + paramstr;
            }
            HttpGet httpget = new HttpGet(url);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            retVal = httpclient.execute(httpget, responseHandler);
        } catch (IOException e) {
            throw e;
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return retVal;
    }
    /**
     * 异步get
     * @param url
     * @param params
     * @throws Exception
     */
    public void asynGet(String url, Map<String, String> params) throws Exception {
        /*HttpClientConnection  conn = null;
        try {
            URI uri = new URI(url);
            String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
            String host = uri.getHost() == null ? "localhost" : uri.getHost();
            int port = uri.getPort();
            if (port == -1) {
                if (scheme.equalsIgnoreCase("http")) {
                    port = 80;
                } else if (scheme.equalsIgnoreCase("https")) {
                    port = 443;
                }
            }
            
            conn = new HttpClientConnection(host, port);
            GetRequest request = null;
            if (params == null) {
                request = new GetRequest(url);
            } else {
                org.xlightweb.NameValuePair[] nv = new org.xlightweb.NameValuePair[params.size()];
                int i = 0;
                for (Map.Entry<String, String> param : params.entrySet()) {
                    nv[i] = new org.xlightweb.NameValuePair(param.getKey(), URLEncoder.encode(param.getValue(), "UTF-8"));
                    i ++;
                }
                request = new GetRequest(url, nv);
            }
            conn.send(request);
        } catch (Exception e) {
            throw e;
        } finally {
            conn.close();
        }*/
    }
    
    public static void main(String[] args) throws IOException{
    	String url="http://60.209.7.25:2975/smsapi/SendMsg";
    	Map<String,String> map =new HashMap<String, String>();
    	map.put("un", "yytt");
    	map.put("pw", "1234");
    	map.put("pc", "18664975981");
    	map.put("msg", "【优信 卡】随 机 码：996214，请按照网站屏幕提示，进行密 码重 置");
    	try {
			String ret=get(url, map);
			SmsReturnMsg retVO=SmsReturnMsg.getResponse(ret);
			System.out.println(retVO.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}