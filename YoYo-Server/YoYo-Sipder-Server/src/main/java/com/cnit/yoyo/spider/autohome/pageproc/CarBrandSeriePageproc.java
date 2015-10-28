package com.cnit.yoyo.spider.autohome.pageproc;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;
import com.cnit.yoyo.spider.Config.Configuration;
import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.pipeline.CarTypeAttrPipeline;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;
import com.cnit.yoyo.spider.common.dto.autohome.vo.FactoryConfig;
import com.cnit.yoyo.spider.common.spider.SpiderHolder;
import com.cnit.yoyo.spider.common.utils.DateUtil;
import com.cnit.yoyo.spider.common.utils.HessianHelper;
import com.cnit.yoyo.spider.common.utils.HttpUtils;
import com.cnit.yoyo.spider.common.utils.SpiderUtil;
import com.cnit.yoyo.spider.common.utils.SpringContextHolder;


/**
 * 车品牌
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Component
public class CarBrandSeriePageproc extends AutohomePageProcessor {
	@Resource
	private PublicAutohomePageproc publicAutohomePageproc;
	/** 日志对象 */
	private static Logger LOG = LoggerFactory.getLogger(CarBrandSeriePageproc.class);
	@Override
	public void process(Page page) {
		
		Html html = page.getHtml();
		List<Selectable> nodes = html.$("div.uibox-con dl").nodes();
		List<String> carSerieUrls = new LinkedList<String>();
		List<CarInfo> brandCarList = new ArrayList<CarInfo>();
		List<CarInfo> factoryCarList = new ArrayList<CarInfo>();
		List<CarInfo> serieCarList = new ArrayList<CarInfo>();
		// 车品牌
		for (Selectable node : nodes) {
			List<Selectable> brandNodes = node.$("dt p").nodes();

			String brandUrl = brandNodes.get(0).$("a", "href").get();
			String brandId = SpiderUtil.fetechNumStr(brandUrl);
			brandId = Constant.CARBRAND_ID_PREFIX + brandId;
			String brandImg = brandNodes.get(0).$("a img", "src").get();
			String brandName = brandNodes.get(1).$("a", "text").get();
			Date currentDate = DateUtil.getCurrentDate();
			CarInfo brandCar = new CarInfo();
			brandCar.setId(brandId);
			brandCar.setPid("0");
			brandCar.setName(brandName);
			brandCar.setCreateTime(currentDate);
			brandCar.setUpdateTime(currentDate);
			// 同步的方式抓取品牌页面的官网地址
			Document result = null;
			try {
				result = Jsoup.parse(new URL(brandUrl),100000);
				Thread.sleep(2000);
			} catch (Exception e2) {
				LOG.error("抓取品牌页面的官网地址失败{}",e2.toString());
			}
			String brandRealUrl=null;
			if(result != null){
				Elements brandRealUrls=result.select("div .uibox-title-message a");
				if(brandRealUrls != null && brandRealUrls.size() > 0 ){
					brandRealUrl=brandRealUrls.get(0).attr("href");
				}
			}
//			SpiderHolder.getInstance(publicAutohomePageproc, brandUrl).run();
//			Html result = publicAutohomePageproc.getResult().get(0);
//			String brandRealUrl = result.$("div .uibox-title-message a","href").get();
			if(!StringUtils.isBlank(brandRealUrl)){
				brandUrl=brandRealUrl;
			}
			brandCar.setAutohomeUrl(brandUrl);
//			//上传图片
//			String remotePath=Configuration.getInstance().getConfigValue("images.serviceUrl", "http://10.255.8.17:8082/ImageService/remoting/ImageTest");
//			HessianInerface hInerface = HessianHelper.getInstance().createClient(remotePath);
//			try {
//				byte[] buffer = HessianHelper.getInstance().getRemoteBuffer(brandImg);
//				ImagesDTO imagesDTO=hInerface.uploadSingleFile(buffer,"mp");
//				brandImg=imagesDTO.getFileName();
//			} catch (Exception e1) {
//				LOG.error("图片保存失败{}",e1.toString());
//			}
			brandCar.setImgPath(brandImg);
			brandCarList.add(brandCar);

			// 车厂商
			List<Selectable> factoryNodes = node.$("dd h3").nodes();
			// 厂商下的车系
			List<Selectable> factorySerieNodes = node.$("dd ul").nodes();
			int i = 0;
			String factoryId = "";
			for (Selectable fsNode : factorySerieNodes) {
				if (CollectionUtils.isNotEmpty(factoryNodes) && null != factoryNodes.get(i)) {
					Selectable factoryNode = factoryNodes.get(i++);
					Selectable tmpNode = factoryNode.$("h3.rank-list-title");
					String factoryName = tmpNode.$("a", "text").get();
					String factoryLink = tmpNode.$("a", "href").get();
					factoryId = Constant.CARFACTORY_ID_PERFIX + SpiderUtil.fetechNumStr(factoryLink);
					CarInfo facCar = new CarInfo();
					facCar.setId(factoryId);
					facCar.setPid(brandId);
					facCar.setName(factoryName);
					facCar.setCreateTime(currentDate);
					facCar.setUpdateTime(currentDate);
					facCar.setAutohomeUrl(factoryLink);
					if(factoryName != null && factoryName.contains("(进口)")){
						facCar.setMadeId("2");
					}else{
						facCar.setMadeId("1");
					}
					facCar.setImgPath(brandCar.getImgPath());
					factoryCarList.add(facCar);
				}else{
					String brandUrl1=Constant.CARFACTORYURL;
					Map<String,String> param=new HashMap<String,String>();
					param.put("type", "3");
					param.put("value", obtainId(brandId));
					try {
						LOG.info("品牌id{},没找到厂商，通过查询下拉框的方式获取厂商",brandId);
						String htmlStr=HttpUtils.get(brandUrl1, param);
						String config = JSONObject.parseObject(htmlStr).getString("result").replace("\"", "'");
						FactoryConfig factoryConfig = JSONObject.parseObject(config, FactoryConfig.class);
						String factoryName="";
						if(factoryConfig != null){
							factoryId=Constant.CARFACTORY_ID_PERFIX +factoryConfig.getFactoryitems().get(0).getId().toString();
							factoryName=factoryConfig.getFactoryitems().get(0).getName();
						}
						LOG.info("品牌id{},找厂商结束{},休息0.2秒",brandId,factoryId);
						
						CarInfo facCar = new CarInfo();
						facCar.setId(factoryId);
						facCar.setPid(brandId);
						facCar.setName(factoryName);
						facCar.setCreateTime(currentDate);
						facCar.setUpdateTime(currentDate);
						facCar.setAutohomeUrl(null);
						if(factoryName != null && factoryName.contains("(进口)")){
							facCar.setMadeId("2");
						}else{
							facCar.setMadeId("1");
						}
						facCar.setImgPath(brandCar.getImgPath());
						factoryCarList.add(facCar);
						Thread.sleep(200);
					} catch (Exception e) {
						factoryId=Constant.CARFACTORY_ID_PERFIX_V+obtainId(brandId);
						CarInfo facCar = new CarInfo();
						facCar.setId(factoryId);
						facCar.setPid(brandId);
						facCar.setName(brandName+"(厂商)");
						facCar.setCreateTime(currentDate);
						facCar.setUpdateTime(currentDate);
						facCar.setAutohomeUrl(null);
						facCar.setMadeId("1");
						facCar.setImgPath(brandCar.getImgPath());
						factoryCarList.add(facCar);
						LOG.error("品牌id{},找厂商异常了,写到日志里。factoryId{}为虚拟出来的厂商,e:{}",brandId,factoryId,factoryId,e.getMessage());
					}
				}
				// 车系
				List<Selectable> serieNodes = fsNode.$("ul li").nodes();
				for (Selectable serieNode : serieNodes) {
					String serieName = serieNode.$("h4 a", "text").get();
					String serieUrl = serieNode.$("h4 a", "href").get();
					if (serieUrl.matches("^.+/\\d+/$")) {
						String serieId = SpiderUtil.fetechNumStr(serieUrl);
						serieUrl = "http://car.autohome.com.cn/config/series/" + serieId + ".html";
						carSerieUrls.add(serieUrl);

						// 车系对象创建
						CarInfo serieCar = new CarInfo();
						serieId = Constant.CARSERIE_ID_PREFIX + serieId;
						serieCar.setId(serieId);
						serieCar.setPid(StringUtils.isNotEmpty(factoryId) ? factoryId : brandId);
						serieCar.setName(serieName);
						serieCar.setCreateTime(currentDate);
						serieCar.setUpdateTime(currentDate);
						serieCar.setAutohomeUrl(serieUrl);
						serieCarList.add(serieCar);
					}
				}
			}
		}
		if (CollectionUtils.isNotEmpty(carSerieUrls)) {
			// 车品牌
			page.putField(Constant.PIPE_CAR_BRAND, brandCarList);
			// 车厂商
			page.putField(Constant.PIPE_CAR_FACTORY, factoryCarList);
			// 车系统
			page.putField(Constant.PIPE_CAR_SERIE, serieCarList);

			// 继续抓取,新启一个spider
			CarTypeAttrPageproc carTypePageproc = SpringContextHolder.getBean(CarTypeAttrPageproc.class);
			CarTypeAttrPipeline carTypePipeline = SpringContextHolder.getBean(CarTypeAttrPipeline.class);
			SpiderHolder.getInstance(carTypePageproc, carSerieUrls).addPipeline(carTypePipeline).runAsync();
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
	
}
