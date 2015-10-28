package com.cnit.yoyo.spider.autohome.pageproc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.constants.DepartmentConstant;
import com.cnit.yoyo.spider.autohome.pipeline.CarTypeAttrPipeline;
import com.cnit.yoyo.spider.autohome.service.CarInfoService;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;
import com.cnit.yoyo.spider.common.dto.autohome.vo.FactoryConfig;
import com.cnit.yoyo.spider.common.spider.SpiderHolder;
import com.cnit.yoyo.spider.common.utils.DateUtil;
import com.cnit.yoyo.spider.common.utils.HttpUtils;
import com.cnit.yoyo.spider.common.utils.SpiderUtil;
import com.cnit.yoyo.spider.common.utils.SpringContextHolder;


/**
 * 车系别
 * @Author:yangyi  
 * @Date:2015年6月16日  
 * @Version:1.0.0
 */
@Component
public class CarFactoryScopePageproc extends AutohomePageProcessor {
	@Resource
	private CarInfoService	carInfoService;
	@Resource
	private PublicAutohomePageproc publicAutohomePageproc;
	Boolean lastTag=false;
	/** 日志对象 */
	private static Logger LOG = LoggerFactory.getLogger(CarFactoryScopePageproc.class);
	
	private Boolean getFactoryScope(String url,Html html,List<CarInfo> factoryScopeList,int i){
		
		if(!lastTag){
			String countryNum=subCountryNum(url);
			LOG.info("抓取第"+i+"页的数据开始>>>");
			List<Selectable> nodes = html.$("body li").nodes();
			//页面返回有值
			if(nodes != null && nodes.size() > 0){
				for(Selectable node : nodes){
					String serieUrl=node.$("div .ul-pic a","href").get();
					if(StringUtils.isBlank(serieUrl)){
						LOG.info("国家"+countryNum+"一共有"+i+"页<<<");
						lastTag=true;
						return lastTag;
					}
					String serieId=Constant.CARSERIE_ID_PREFIX+obtainId(serieUrl);
					//查询车系数据
					CarInfo parm=new CarInfo();
					parm.setId(serieId);
					CarInfo resultCarInfo=carInfoService.queryByKey(parm);
					if(resultCarInfo != null){
						//根据车系查询厂家数据
						CarInfo parm1=new CarInfo();
						parm1.setId(resultCarInfo.getPid());
						CarInfo result=carInfoService.queryByKey(parm1);
						if(result != null){
							Integer scopeId=null;
							if("1".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_CHINA;
							}
							if("2".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_GERMANY;
							}
							if("3".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_JAPAN;
							}
							if("4".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_USA;
							}
							if("5".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_KOREA;
							}
							if("6".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_FRANCE;
							}
							if("7".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_BRITISH;
							}
							if("8".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_ITALY;
							}
							if("9".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_SWEDEN;
							}
							if("11".equals(countryNum)){
								scopeId=DepartmentConstant.PIPE_CZECH;
							}
							result.setScopeId(scopeId);
							factoryScopeList.add(result);
							i++;
							lastTag=false;
							// 同步的方式抓取下个分页
							String nextUrl=url.split("page=")[0]+"page="+i+url.split("page=")[1].substring(1,url.split("page=")[1].length()-1);
							SpiderHolder.getInstance(publicAutohomePageproc, nextUrl).run();
							Html nextHtml = publicAutohomePageproc.getResult().get(0);
							getFactoryScope(nextUrl, nextHtml, factoryScopeList, i);
						}
					}
				}
			}else{
				LOG.info("国家"+countryNum+"一共有"+i+"页<<<");
				lastTag=true;
				return lastTag;
			}
		}
		return lastTag;
	}
	@Override
	public void process(Page page) {
		List<CarInfo> factoryScopeList=new ArrayList<CarInfo>();
		int i=0;
		lastTag=false;
		Html html = page.getHtml();
		String url= page.getUrl().toString();
		if(getFactoryScope(url, html, factoryScopeList,i)){
			if (CollectionUtils.isNotEmpty(factoryScopeList)) {
				// 车厂商系别
				page.putField(Constant.PIPE_CAR_FACTORY_SCOPE, factoryScopeList);
			}
		}
	}
	
	/**
	 * 从url http://www.autohome.com.cn/8888/options.html 中截取出ID8888
	 * 
	 * @param url
	 * @return
	 */
	private String obtainId(String url) {
		Matcher matcher = Pattern.compile("(\\d+)").matcher(url);
		String id = null;
		if (matcher.find()) {
			id = matcher.group(1);
		}
		return id;
	}
	
	/**
	 * 从url http://www.autohome.com.cn/8888/options.html 中截取出ID8888
	 * 
	 * @param url
	 * @return
	 */
	private String obtainId1(String url) {
		Matcher matcher = Pattern.compile("(\\d+)").matcher(url);
		String id = null;
		if (matcher.find()) {
			id = matcher.group(1);
		}
		return id;
	}

	/**
	 * 截取当前页面
	 * @Description:
	 * @param url
	 * @return
	 */
	private String subPageNum(String url){
	    Pattern p = Pattern.compile("page=(.*)&price");  
	    Matcher m = p.matcher(url);  
	    String num = null;
	    while(m.find()){  
	    	num=m.group(1);  
	    }
	    return num;
	}
	
	/**
	 * 截取国家标识
	 * @Description:
	 * @param url
	 * @return
	 */
	private String subCountryNum(String url){
	    Pattern p = Pattern.compile("country=(.*)&deliveryCapacity");  
	    Matcher m = p.matcher(url);  
	    String num = null;
	    while(m.find()){  
	    	num=m.group(1);  
	    }
	    return num;
	}
}
