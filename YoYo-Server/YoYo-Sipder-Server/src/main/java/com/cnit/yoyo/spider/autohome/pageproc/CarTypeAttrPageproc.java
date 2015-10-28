package com.cnit.yoyo.spider.autohome.pageproc;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.dto.ImagesDTO;
import com.cnit.yoyo.image.HessianInerface;
import com.cnit.yoyo.spider.Config.Configuration;
import com.cnit.yoyo.spider.Config.Constants;
import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.pipeline.CarBaoyangPipeline;
import com.cnit.yoyo.spider.autohome.pipeline.CarTypeAttrPipeline;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarAttr;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarColor;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.ColorInfo;
import com.cnit.yoyo.spider.common.dto.autohome.vo.ColorAttr;
import com.cnit.yoyo.spider.common.dto.autohome.vo.ColorAttr.Specitem;
import com.cnit.yoyo.spider.common.dto.autohome.vo.ColorAttr.Specitem.Coloritem;
import com.cnit.yoyo.spider.common.dto.autohome.vo.ConfigAttr;
import com.cnit.yoyo.spider.common.dto.autohome.vo.ConfigAttr.Paramtypeitem;
import com.cnit.yoyo.spider.common.dto.autohome.vo.ConfigAttr.Paramtypeitem.Paramitem;
import com.cnit.yoyo.spider.common.dto.autohome.vo.OptionAttr;
import com.cnit.yoyo.spider.common.dto.autohome.vo.OptionAttr.Configtypeitem;
import com.cnit.yoyo.spider.common.dto.autohome.vo.OptionAttr.Configtypeitem.Configitem;
import com.cnit.yoyo.spider.common.dto.autohome.vo.Valueitem;
import com.cnit.yoyo.spider.common.enctype.Md5Util;
import com.cnit.yoyo.spider.common.spider.SpiderHolder;
import com.cnit.yoyo.spider.common.utils.DateUtil;
import com.cnit.yoyo.spider.common.utils.HessianHelper;
import com.cnit.yoyo.spider.common.utils.SpringContextHolder;

/**
 * 车型
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Component
public class CarTypeAttrPageproc extends AutohomePageProcessor {
	private static Logger LOG = LoggerFactory.getLogger(CarTypeAttrPageproc.class);

	@Override
	public void process(Page page) {
		Html html = page.getHtml();
		String htmlStr = html.get();
		if (!htmlStr.contains("var keyLink = ")) {
			return;
		}
		String url = page.getUrl().get();
		String serieId = obtainId(url);
		serieId = Constant.CARSERIE_ID_PREFIX + serieId;
		// String keyLink = StringUtils.substringBetween(htmlStr,
		// "var keyLink = ", "};").concat("}");
		// JSONObject keyLinkJObj =
		// JSONObject.parseObject(keyLink).getJSONObject("result");
		// String dealerPrices = StringUtils.substringBetween(htmlStr,
		// "var dealerPrices = ", "};").concat("}");
		// JSONObject dealerPricesJObj =
		// JSONObject.parseObject(dealerPrices).getJSONObject("result");
		String config = StringUtils.substringBetween(htmlStr, "var config = ", "};").concat("}");
		ConfigAttr confAttr = JSONObject.parseObject(
				JSONObject.parseObject(config).getString("result").replace("\"", "'"), ConfigAttr.class);
		String option = StringUtils.substringBetween(htmlStr, "var option = ", "};").concat("}");
		OptionAttr optionAttr = JSONObject.parseObject(
				JSONObject.parseObject(option).getString("result").replace("\"", "'"), OptionAttr.class);
		// 外观颜色
		String outColor = StringUtils.substringBetween(htmlStr, "var color = ", "};").concat("}");
		ColorAttr outColorAttr = JSONObject.parseObject(
				JSONObject.parseObject(outColor).getString("result").replace("\"", "'"), ColorAttr.class);
		// 内饰颜色
		String inColorStr = StringUtils.substringBetween(htmlStr, "var innerColor = ", "};");
		ColorAttr inColorAttr = null;
		if (StringUtils.isNotEmpty(inColorStr)) {
			String inColor = inColorStr.concat("}");
			inColorAttr = JSONObject.parseObject(
					JSONObject.parseObject(inColor).getString("result").replace("\"", "'"), ColorAttr.class);
		}

		// 车型集合
		List<CarInfo> typeCarList = new ArrayList<CarInfo>();
		List<CarAttr> carAttrList = new ArrayList<CarAttr>();
		Date currentDate = DateUtil.getCurrentDate();

		int childOrderIndex = 1;
		int parentOrderIndex = 1;

		// 设置属性项 config
		List<Paramtypeitem> paramtypeitems = confAttr.getParamtypeitems();
		for (Paramtypeitem param : paramtypeitems) {// 遍历项
			String itemname = param.getName();
			Boolean isNotProcList = true; // 是否已经处理了属性项

			List<Paramitem> items = param.getParamitems();
			for (Paramitem item : items) {// 遍历属性
				String valuename = item.getName();
				List<Valueitem> valueitems = item.getValueitems();

				for (Valueitem valueitem : valueitems) {// 遍历多个车型的属性值
					Long carTypeId = valueitem.getSpecid();
					String level=null;
					String value = valueitem.getValue();
					// 处理车属性
					CarAttr carAttr = new CarAttr();
					String md5 = Md5Util.md5(String.valueOf(carTypeId + itemname + valuename));
					carAttr.setId(md5);
					carAttr.setCarTypeId(carTypeId);
					carAttr.setName(valuename);
					carAttr.setParentOrderIndex(parentOrderIndex);
					carAttr.setChildOrderIndex(childOrderIndex);
					if (StringUtils.equalsIgnoreCase("暂无报价", value)) {
						value="0";
					}
					if (StringUtils.equalsIgnoreCase("厂商指导价(元)", valuename)) {
						if(value.contains("万")){
							value=getMoney(value);
						}
					}
					carAttr.setVal(value);
					carAttr.setType(getValueType(value));
					carAttrList.add(carAttr);
					
					if (StringUtils.equalsIgnoreCase("级别", valuename)) {
						level=value;
					}
					// 处理车型
					if (StringUtils.equalsIgnoreCase("车型名称", valuename)) {
						CarInfo typeCar = new CarInfo();
						typeCar.setAutohomeUrl("http://www.autohome.com.cn/spec/" + carTypeId);
						typeCar.setId(Constant.CARTYPE_ID_PREFIX + carTypeId);
						typeCar.setPid(serieId);
						typeCar.setName(value);
						typeCar.setCreateTime(currentDate);
						typeCar.setUpdateTime(currentDate);
						String year=getYear(value);
						typeCar.setYear(year);
						if(StringUtils.isNotBlank(level)){
							typeCar.setLevel(level);
						}
						
						// 同步的方式抓取车型图片数据
						String carTypeUrl="http://www.autohome.com.cn/spec/"+carTypeId+"/";
						Document result = null;
						try {
							result = Jsoup.parse(new URL(carTypeUrl),100000);
							Thread.sleep(2000);
						} catch (Exception e2) {
							LOG.error("抓取品牌页面的官网地址失败{}",e2.toString());
						}
						String cartypeRealUrl=null;
						//车型图片
						String imageUrl=null;
						if(result != null){
							Elements cartypeRealUrls=result.select("div .row .cardetail-pic a img");
							if(cartypeRealUrls != null && cartypeRealUrls.size() > 0 ){
								cartypeRealUrl=cartypeRealUrls.get(0).attr("src");
							}
						}
//						//上传图片
//						String remotePath=Configuration.getInstance().getConfigValue("images.serviceUrl", "http://10.255.8.17:8082/ImageService/remoting/ImageTest");
//						HessianInerface hInerface = HessianHelper.getInstance().createClient(remotePath);
//						try {
//							byte[] buffer = HessianHelper.getInstance().getRemoteBuffer(cartypeRealUrl);
//							ImagesDTO imagesDTO=hInerface.uploadSingleFile(buffer,"mp");
//							imageUrl=imagesDTO.getFileName();
//						} catch (Exception e1) {
//							LOG.error("图片保存失败{}",e1.toString());
//						}
//						if(imageUrl != null){
//							typeCar.setImgPath(imageUrl);
//						}
						typeCar.setImgPath(cartypeRealUrl);
						typeCarList.add(typeCar);
					}
					if (isNotProcList) {
						// 处理车属性项集
						CarAttr carAttrParent = new CarAttr();
						md5 = Md5Util.md5(String.valueOf(carTypeId + itemname));
						carAttrParent.setId(md5);
						carAttrParent.setCarTypeId(carTypeId);
						carAttrParent.setName(itemname);
						carAttrParent.setParentOrderIndex(parentOrderIndex);
						carAttrList.add(carAttrParent);
					}

				}
				isNotProcList = false;
				childOrderIndex++;
			}
			parentOrderIndex++;
		}

		// 设置属性项 option
		List<Configtypeitem> configtypeitems = optionAttr.getConfigtypeitems();
		for (Configtypeitem param : configtypeitems) {// 遍历项
			String itemname = param.getName();
			Boolean isNotProcList = true; // 是否已经处理了属性项

			List<Configitem> items = param.getConfigitems();
			for (Configitem item : items) {// 遍历属性
				String valuename = item.getName();
				List<Valueitem> valueitems = item.getValueitems();
				for (Valueitem valueitem : valueitems) {// 遍历属性值
					Long carTypeId = valueitem.getSpecid();
					String value = valueitem.getValue();

					// 处理车属性
					CarAttr carAttr = new CarAttr();
					String md5 = Md5Util.md5(String.valueOf(carTypeId + itemname + valuename));
					carAttr.setId(md5);
					carAttr.setCarTypeId(carTypeId);
					carAttr.setName(valuename);
					carAttr.setParentOrderIndex(parentOrderIndex);
					carAttr.setChildOrderIndex(childOrderIndex);
					if (StringUtils.equalsIgnoreCase("暂无报价", value)) {
						value="0";
					}
					if (StringUtils.equalsIgnoreCase("厂商指导价(元)", valuename)) {
						if(value.contains("万")){
							value=getMoney(value);
						}
					}
					carAttr.setVal(value);
					carAttr.setType(getValueType(value));
					carAttrList.add(carAttr);

					if (isNotProcList) {
						// 处理车属性项集
						CarAttr carAttrParent = new CarAttr();
						md5 = Md5Util.md5(String.valueOf(carTypeId + itemname));
						carAttrParent.setId(md5);
						carAttrParent.setCarTypeId(carTypeId);
						carAttrParent.setName(itemname);
						carAttrParent.setParentOrderIndex(parentOrderIndex);
						carAttrList.add(carAttrParent);
					}
				}
				isNotProcList = false;
				childOrderIndex++;
			}
			parentOrderIndex++;
		}

		Map<Long, ColorInfo> colorMap = new HashMap<Long, ColorInfo>();
		List<CarColor> carColorList = new ArrayList<CarColor>();
		// 外观颜色
		List<Specitem> specitems = outColorAttr.getSpecitems();
		for (Specitem specitem : specitems) {
			Long carTypeId = specitem.getSpecid(); // 车型id

			List<Coloritem> coloritems = specitem.getColoritems();
			for (Coloritem coloritem : coloritems) {
				Long colorId = coloritem.getId();
				String name = coloritem.getName();
				String value = coloritem.getValue();
				Integer picnum = coloritem.getPicnum();
				Integer clubpicnum = coloritem.getClubpicnum();

				ColorInfo colorInfo = new ColorInfo(colorId, name, value, picnum, clubpicnum);
				colorMap.put(colorId, colorInfo);

				// 车型与颜色的关系
				CarColor carColor = new CarColor();
				carColor.setCarTypeId(carTypeId);
				carColor.setColorId(colorId);
				carColor.setIsInner(false);
				carColorList.add(carColor);
			}
		}

		// 内饰颜色
		if (null != inColorAttr) {
			specitems = inColorAttr.getSpecitems();
			for (Specitem specitem : specitems) {
				Long carTypeId = specitem.getSpecid(); // 车型id

				List<Coloritem> coloritems = specitem.getColoritems();
				for (Coloritem coloritem : coloritems) {
					Long colorId = coloritem.getId();
					String name = coloritem.getName();
					String value = coloritem.getValue();
					Integer picnum = coloritem.getPicnum();
					Integer clubpicnum = coloritem.getClubpicnum();

					ColorInfo colorInfo = new ColorInfo(colorId, name, value, picnum, clubpicnum);
					colorMap.put(colorId, colorInfo);

					// 车型与颜色的关系
					CarColor carColor = new CarColor();
					carColor.setCarTypeId(carTypeId);
					carColor.setColorId(colorId);
					carColor.setIsInner(true);
					carColorList.add(carColor);

				}
			}
		}
		//车型数据不为空时，继续遍历车保养数据
		if (CollectionUtils.isNotEmpty(typeCarList)) {
			//车型
			page.putField(Constant.PIPE_CAR_TYPE, typeCarList);
			//车属性
			page.putField(Constant.PIPE_CAR_ATTR, carAttrList);
			//车颜色
			page.putField(Constant.PIPE_COLOR_INFO, colorMap.values());
			//车系和车颜色关联表
			page.putField(Constant.PIPE_CAR_COLOR, carColorList);
			
			//车保养url集合，用于抓取保养数据
			List<String> carBaoyangUrls = new LinkedList<String>();
			for(CarInfo typeCar : typeCarList){
				//车型id
				String typeCarId=obtainId(typeCar.getId());
				//车系id
				String seriesCarId=obtainId(typeCar.getPid());
				//变速箱id
				String engineId=Configuration.getInstance().getConfigValue(Constants.getStringName(typeCarId), "0");
				//车保养url地址
				String baoyangUrl = "http://car.autohome.com.cn/Baoyang/detail_"+engineId+"_"+seriesCarId+"_"+typeCarId+"_0_0_0.html";
				//不包含null字段
				if(baoyangUrl != null && !baoyangUrl.contains("null")){
					carBaoyangUrls.add(baoyangUrl);
				}
			}

			// 继续抓取,新启一个保养数据的spider
			CarBaoyangPageproc carBaoyangPageproc = SpringContextHolder.getBean(CarBaoyangPageproc.class);
			CarBaoyangPipeline carBaoyangPipeline = SpringContextHolder.getBean(CarBaoyangPipeline.class);
			SpiderHolder.getInstance(carBaoyangPageproc, carBaoyangUrls).addPipeline(carBaoyangPipeline).runAsync();
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
	 * 风骏3 2011款 2.4L商务版 豪华型大双排,找出2011
	 * @param url
	 * @return
	 */
	private String getYear(String url){
		return url.substring(url.indexOf("款")-4, url.indexOf("款"));
	}
	
	/**
	 * 1.58万~22万,找出1.58
	 * @param str
	 */
	private String getMoney(String str){
		return str.substring(0, str.indexOf("万"));
	}
	
	//参数数据类型：STR-字符串 INT-整形 BOL-布尔型 DEC-浮点型  LIST-列表
	/**
	 * 获取值类型
	 * @param str
	 * @return
	 */
	private String getValueType(String str){
		String DEC_Rule="\\d+\\.\\d+";
		String INT_Rule="[0-9]*";
		String BOL_Rule="●-○";
		String STR_Rule="-";
		if(StringUtils.isBlank(str) || str.trim().equals("") || str.trim().equals("●") || str.trim().equals("-") || str.trim().equals("○")){
			return "BOL";
		}
		if(str.matches(DEC_Rule)){
			return "DEC";
		}
		if(str.matches(INT_Rule)){
			return "INT";
		}
		if(str.trim().contains(STR_Rule)){
			return "STR";
		}
		return "STR";
	}

	public static void main(String[] args){
		String url="314";
		String str=new CarTypeAttrPageproc().getValueType(url);
		System.out.println(str);
	}
}
