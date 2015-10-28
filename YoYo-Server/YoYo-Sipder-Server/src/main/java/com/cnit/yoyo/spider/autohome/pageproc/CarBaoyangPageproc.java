package com.cnit.yoyo.spider.autohome.pageproc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangAttr;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangInfo;
import com.cnit.yoyo.spider.common.enctype.Md5Util;

/**
 * 车型
 * @Author:yangyi  
 * @Date:2015年6月8日  
 * @Version:1.0.0
 */
@Component
public class CarBaoyangPageproc extends AutohomePageProcessor {

	@Override
	public void process(Page page) {
		Html html = page.getHtml();
		Document doc=html.getDocument();
		String urlLink=page.getUrl().toString();
		String carTypeId=null;
		Elements carTypeIds=doc.select("input[name=specid]");
		if(carTypeIds != null && carTypeIds.size() > 0 ){
			carTypeId=carTypeIds.get(0).attr("value");
		}
		Elements upKeepEles = doc.getElementById("maintaincontainer").getElementsByTag("tbody");
		//存在保养信息则解析
		if(upKeepEles != null && upKeepEles.size() > 0){
			//变速箱类型
			Elements gearBoxs=doc.select(".W_660 > .t_H > .floatLeft");
			String gearBox=parseGearBox(gearBoxs.text()).replaceAll(" ", "");
			//车
			Elements firstCarInfo=doc.select(".notes");
			String firstPlan=null;//首保公里
			String firstPlanMon=null;//首保月数
			String secondPlan=null;//二保
			String secondPlanMon=null;//二保月数
			String jiange=null;//间隔
			String jiangeMon=null;//间隔月数
			if(firstCarInfo != null && firstCarInfo.size() > 0){
				//（首保：5000公里/6个月 二保：10000公里/12个月                         间隔：5000公里/6个月）
					String upKeepPlan = firstCarInfo.text();
					String[] plan = upKeepPlan.split("二保：");
					firstPlan = plan[0].split("首保：")[1].split("公里/")[0];
					firstPlanMon = plan[0].split("首保：")[1].split("公里/")[1].replace("个月", "");
					String[] plan2 = plan[1].split("间隔：");
					secondPlan = plan2[0].trim().split("公里/")[0];
					secondPlanMon =plan2[0].trim().split("公里/")[1].replace("个月", "");
					jiange = plan2[1].substring(0,plan2[1].lastIndexOf("●更换")).split("公里/")[0];
					jiangeMon = plan2[1].substring(0,plan2[1].lastIndexOf("●更换")).split("公里/")[1].replace("个月", "");
			}
			//解析车系、车型
			String[] carAttrs=getCarAttrs(urlLink);
			String seriesId=carAttrs[0];//车系id
			carTypeId=carAttrs[1];//车型id
			String baoyangId=Md5Util.md5(String.valueOf(seriesId + carTypeId + gearBox));
			CarBaoyangInfo carBaoyangInfo = new CarBaoyangInfo();
			carBaoyangInfo.setCarSeriesId(seriesId);
			carBaoyangInfo.setCarTypeId(carTypeId);
			carBaoyangInfo.setId(baoyangId);
			carBaoyangInfo.setFirstUpKeep(firstPlan);
			carBaoyangInfo.setFirstUpKeepMon(firstPlanMon);
			carBaoyangInfo.setSecondUpKeep(secondPlan);
			carBaoyangInfo.setSecondUpKeepMon(secondPlanMon);
			carBaoyangInfo.setGearBox(gearBox);
			carBaoyangInfo.setInterval(jiange);
			carBaoyangInfo.setIntervalMon(jiangeMon);
			carBaoyangInfo.setCreateTime(new Date());
			carBaoyangInfo.setUpdateTime(new Date());
			//需要解析的保养数据
			Element upKeepEle = upKeepEles.get(0);
//			upKeepList = parseUpKeep(upKeepEle,urlLink,carBaoyangInfo);
			parseUpKeep(upKeepEle,urlLink,carBaoyangInfo);
			//解析出保养计划，有值则处理相应的数据，记录到数据库中
			if(carBaoyangInfo != null && carBaoyangInfo.getCarBaoyangAttrs() != null && carBaoyangInfo.getCarBaoyangAttrs().size() > 0){
				//车型
				page.putField(Constant.PIPE_CAR_BAOYANG_INFO, carBaoyangInfo);
				//车属性
				page.putField(Constant.PIPE_CAR_BAOYANG_ATTR, carBaoyangInfo.getCarBaoyangAttrs());
			}
		}
		
	}
	
	private String[] getCarAttrs(String urlLink){
		String[] attrs=new String[2];
		if(StringUtils.isNotBlank(urlLink)){
			String[] attrval=urlLink.split("_");
			attrs[0] = attrval[2];
			attrs[1] = attrval[3];
		}
		return attrs;
	}

	//解析保养计划
		private CarBaoyangInfo parseUpKeep(Element ele,String urlLink,CarBaoyangInfo carBaoyangInfo){
			String baoyangId=carBaoyangInfo.getId();
			Date date = new Date();
			Elements eles = ele.getElementsByTag("tr");
			if(eles.size() > 0){
				List<CarBaoyangAttr> attrList=new ArrayList<CarBaoyangAttr>();
 				Elements headEles = eles.get(0).getElementsByTag("td");
				List<String> mileageList = new ArrayList<String>();
				List<String> monthList = new ArrayList<String>();
				for(int i = 2; i < headEles.size(); i++){
					//5000<br>公里/<br>6<br>个月
					String headStr = headEles.get(i).html();
					Matcher matcher = Pattern.compile("(\\d+)").matcher(headStr);
					String month="",mileage="";
					if(matcher.find())
						mileage = matcher.group(1);
					if(matcher.find())
						month = matcher.group(1);
					mileageList.add(mileage);
					monthList.add(month);
				}
				
				for(int i = 1; i <eles.size(); i++){
					Elements elesTd = eles.get(i).getElementsByTag("td");
					String item = elesTd.get(0).html();
					String price = elesTd.get(1).html();
					//如果没有价格就默认为0
					if(!StringUtils.isNumeric(price)){
						price = "0";
					}
					if(elesTd.size() < monthList.size()){
						CarBaoyangAttr attr = new CarBaoyangAttr();
						attr.setBaoyangId(baoyangId);
						attr.setItem(item);
						attr.setGearBox(carBaoyangInfo.getGearBox());//变速箱名称
						attr.setMileage("");//里程数
						attr.setUpKeepCost("0");
						attr.setCreateTime(date);
						attr.setUpdateTime(date);
						String attrId=Md5Util.md5(String.valueOf(baoyangId + item + attr.getMileage()));
						attr.setId(attrId);
						attrList.add(attr);
					}else{
						for(int j = 2; j < elesTd.size(); j++){
							CarBaoyangAttr cuk = new CarBaoyangAttr();
							cuk.setBaoyangId(baoyangId);
							String status = elesTd.get(j).html();
							String upkeepStatus = "";
							if("总计（元）".equals(item)){
								status =  elesTd.get(j).getElementsByTag("span").get(0).html();
							}else{
								if("●".equals(status)){
									status = price;
									upkeepStatus = "0";
								}else if("○".equals(status)){
									status = "0";
									upkeepStatus = "1";
								}else{
									status = "";
								}
							}
							cuk.setCreateTime(date);
							cuk.setGearBox(carBaoyangInfo.getGearBox());
							cuk.setItem(item);
							cuk.setMileage(mileageList.get(j-2));
							cuk.setUpdateTime(date);
							cuk.setUpKeepCost(status);
							cuk.setUpKeepMonth(monthList.get(j-2));
							cuk.setUpKeepStatus(upkeepStatus);
							String attrId=Md5Util.md5(String.valueOf(baoyangId + item + cuk.getMileage()));
							cuk.setId(attrId);
							attrList.add(cuk);
						}
					}
				}
				carBaoyangInfo.setCarBaoyangAttrs(attrList);
			}
			return carBaoyangInfo;
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
	 * 解析变速箱名称
	 * @param s
	 * @return
	 */
	public String parseGearBox(String s) {
		return s.substring(s.lastIndexOf(">") + 1).trim();
	}
 
}
