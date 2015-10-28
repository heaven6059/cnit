package com.cnit.yoyo.task;  

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnit.yoyo.autohome.model.AutohomeCarInfoDTO;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.app.Ttoken;
import com.cnit.yoyo.model.car.Car;
import com.cnit.yoyo.model.car.CarDept;
import com.cnit.yoyo.model.car.CarSpiderCompare;
import com.cnit.yoyo.model.car.dto.CarFactoryDTO;
import com.cnit.yoyo.model.goods.Brand;
import com.cnit.yoyo.model.spider.AutohomeCarAttr;
import com.cnit.yoyo.model.spider.vo.CarConfigVO;
import com.cnit.yoyo.model.spider.vo.SpiderDataCompareVO;
import com.cnit.yoyo.model.spider.vo.SpiderDataTipsVO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.rmi.spider.service.AutohomeCarDataService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.JsonDateValueProcessor;
import com.cnit.yoyo.util.JsonDecimalValueProcessor;
import com.cnit.yoyo.util.domain.ResultPage;

/**
 * 汽车之家爬虫对比线程
 * @Author:yangyi  
 * @Date:2015年8月5日  
 * @Version:1.0.0
 */
@Component("autohomeCarDataTask")
public class AutohomeCarDataTask {
	public static final Logger log = LoggerFactory.getLogger(AutohomeCarDataTask.class);
	@Autowired
	private RemoteService  otherService;
	@Autowired
	private RemoteService  itemService;
	@Autowired
	AutohomeCarDataService autohomeCarDataService;
	/**
	 * 对比项分类
	 */
	public static final String CARDATA_CB="CB";//车品牌
	public static final String CARDATA_CF="CF";//车厂商
	public static final String CARDATA_CS="CS";//车系别
	public static final String CARDATA_CT="CT";//车型
	//对比数据
	private List<CarSpiderCompare> compareList=new ArrayList<CarSpiderCompare>();
	//分页对象
	private ResultPage page=new ResultPage();
	
	/**
	 * @Description:组装对比集合实体
	 * @param list
	 * @param type
	 * @return
	 */
	private CarSpiderCompare getCarSpiderCompareData(AutohomeCarInfoDTO list,String type){
		CarSpiderCompare spiderCompare=new CarSpiderCompare();
		SpiderDataTipsVO afterTipsCompare=new SpiderDataTipsVO();
		afterTipsCompare.setName(list.getName());
		afterTipsCompare.setUrl(list.getAutohomeUrl());
		SpiderDataCompareVO afterValueCompare=new SpiderDataCompareVO();
		afterValueCompare.setId(list.getId());
		afterValueCompare.setName(list.getName());
		afterValueCompare.setPid(list.getPid());
		afterValueCompare.setUrl(list.getAutohomeUrl());
		afterValueCompare.setCreateTime(list.getUpdateTime());
		afterValueCompare.setLevel(list.getLevel());
		afterValueCompare.setMadeId(list.getMadeId());
		afterValueCompare.setScopeId(list.getScopeId());
		afterValueCompare.setYear(list.getYear());
		spiderCompare.setAfterTips(fromObjectToString(afterTipsCompare).toString());
		spiderCompare.setAfterValue(fromObjectToString(afterValueCompare).toString());
		spiderCompare.setSpiderValId(list.getId());
		spiderCompare.setType(type);
		spiderCompare.setCompareType("add");
		spiderCompare.setStatus(0);
		spiderCompare.setCreateTime(new Date());
		return spiderCompare;
	}
	
	/**
	 * @Description:组装车型对比集合实体
	 * @param list
	 * @param type
	 * @return
	 */
	private CarSpiderCompare getCarTypeSpiderCompareData(AutohomeCarInfoDTO autohomeCarInfo){
		CarSpiderCompare spiderCompare=new CarSpiderCompare();
		SpiderDataTipsVO afterTipsCompare=new SpiderDataTipsVO();
		afterTipsCompare.setName(autohomeCarInfo.getName());
		afterTipsCompare.setUrl(autohomeCarInfo.getAutohomeUrl());
		afterTipsCompare.setDetailConfig(autohomeCarInfo.getAutohomeCarAttrs());
		SpiderDataCompareVO afterValueCompare=new SpiderDataCompareVO();
		afterValueCompare.setId(autohomeCarInfo.getId());
		afterValueCompare.setName(autohomeCarInfo.getName());
		afterValueCompare.setPid(autohomeCarInfo.getPid());
		afterValueCompare.setUrl(autohomeCarInfo.getAutohomeUrl());
		afterValueCompare.setCreateTime(autohomeCarInfo.getUpdateTime());
		afterValueCompare.setLevel(autohomeCarInfo.getLevel());
		afterValueCompare.setMadeId(autohomeCarInfo.getMadeId());
		afterValueCompare.setScopeId(autohomeCarInfo.getScopeId());
		afterValueCompare.setYear(autohomeCarInfo.getYear());
		afterValueCompare.setDetailConfig(autohomeCarInfo.getAutohomeCarAttrs());
		spiderCompare.setAfterTips(fromObjectToString(afterTipsCompare).toString());
		spiderCompare.setAfterValue(fromObjectToString(afterValueCompare).toString());
		spiderCompare.setSpiderValId(autohomeCarInfo.getId());
		spiderCompare.setType(CARDATA_CS);
		spiderCompare.setCompareType("add");
		spiderCompare.setStatus(0);
		spiderCompare.setCreateTime(new Date());
		return spiderCompare;
	}
	
	/**
	 * @Description:分页查询并同步爬虫数据
	 */
	@SuppressWarnings("unchecked")
	private void syncSpiderData(){
		try {
			// 查询汽车之家车品牌，车厂商，车系，车型关联数据(组合数据)
			ResultObject resultObject = (ResultObject) autohomeCarDataService.queryAutohomeCarDataList(page);
			page = (ResultPage<AutohomeCarInfoDTO>)resultObject.getContent();
			log.info("当前页码:"+page.getPageIndex()+",页面大小:"+page.getPageSize()+",当前页数量:"+page.getCurrPageSize()+",总记录数:"+page.getTotal()+",总页数:"+page.getPages());
			if (page != null && page.getRows().size() > 0) {
				List<AutohomeCarInfoDTO> resultList = page.getRows();
				// List<AutohomeCarInfoDTO> resultList=(List<AutohomeCarInfoDTO>)resultObject.getContent();
				if (!resultList.isEmpty()) {
					for (AutohomeCarInfoDTO list : resultList) {
						if (list.getDataType().equals(CARDATA_CB)) {
							if (StringUtils.isBlank(list.getAutohomeId())) {
								// 新增的数据
								CarSpiderCompare addCarSpiderCompare = getCarSpiderCompareData(list, CARDATA_CB);
								compareList.add(addCarSpiderCompare);
							} else {
								// 对比之前的数据,如果一致则不管，否则插入对比表数据
								HeadObject headObject1 = CommonHeadUtil.geneHeadObject(null, "2000020109-22");
								ResultObject resultObject1 = itemService.doService(new RequestObject(headObject1, list));
								// 返回结果
								if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
									Brand retObj = (Brand) resultObject1.getContent();
									if (retObj != null) {
										CarSpiderCompare spiderCompare = new CarSpiderCompare();
										boolean isChange = false;
										SpiderDataTipsVO beforeTipsCompare = new SpiderDataTipsVO();
										SpiderDataTipsVO afterTipsCompare = new SpiderDataTipsVO();
										// 比较爬虫名称
										if (!retObj.getBrandName().equals(list.getName())) {
											beforeTipsCompare.setName(retObj.getBrandName());
											afterTipsCompare.setName(list.getName());
											isChange = true;
										}
										// 比较爬虫链接url
										if (!retObj.getBrandUrl().equals(list.getAutohomeUrl())) {
											beforeTipsCompare.setUrl(retObj.getBrandUrl());
											afterTipsCompare.setUrl(list.getAutohomeUrl());
											isChange = true;
										}
										if (isChange) {
											// 变动前数据
											SpiderDataCompareVO beforeValueCompare = new SpiderDataCompareVO();
											beforeValueCompare.setId(retObj.getBrandId().toString());
											beforeValueCompare.setName(retObj.getBrandName());
											beforeValueCompare.setPid("0");
											beforeValueCompare.setUrl(retObj.getBrandUrl());
											// 变动后数据
											SpiderDataCompareVO afterValueCompare = new SpiderDataCompareVO();
											afterValueCompare.setId(list.getId());
											afterValueCompare.setName(list.getName());
											afterValueCompare.setPid(list.getPid());
											afterValueCompare.setUrl(list.getAutohomeUrl());
											afterValueCompare.setCreateTime(list.getCreateTime());
											spiderCompare.setBeforeTips(fromObjectToString(beforeTipsCompare));
											spiderCompare.setAfterTips(fromObjectToString(afterTipsCompare));
											spiderCompare.setBeforeValue(fromObjectToString(beforeValueCompare));
											spiderCompare.setAfterValue(fromObjectToString(afterValueCompare));
											spiderCompare.setSpiderValId(list.getId());
											spiderCompare.setType(CARDATA_CB);
											spiderCompare.setCompareType("update");
											spiderCompare.setStatus(0);
											spiderCompare.setCreateTime(new Date());
											compareList.add(spiderCompare);
										}
									}
								}
							}
							// cbList.add(list);
						}
						if (list.getDataType().equals(CARDATA_CF)) {
							if (StringUtils.isBlank(list.getAutohomeId())) {
								// 新增的数据
								CarSpiderCompare addCarSpiderCompare = getCarSpiderCompareData(list, CARDATA_CF);
								compareList.add(addCarSpiderCompare);
							} else {
								// 对比之前的数据,如果一致则不管，否则插入对比表数据
								HeadObject headObject1 = CommonHeadUtil.geneHeadObject(null, "010801-10");
								ResultObject resultObject1 = itemService.doService(new RequestObject(headObject1, list));
								// 返回结果
								if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
									CarFactoryDTO retObj = (CarFactoryDTO) resultObject1.getContent();
									if (retObj != null) {
										CarSpiderCompare spiderCompare = new CarSpiderCompare();
										boolean isChange = false;
										SpiderDataTipsVO beforeTipsCompare = new SpiderDataTipsVO();
										SpiderDataTipsVO afterTipsCompare = new SpiderDataTipsVO();
										// 比较爬虫名称
										if (!retObj.getFactoryName().equals(list.getName())) {
											beforeTipsCompare.setName(retObj.getFactoryName());
											afterTipsCompare.setName(list.getName());
											isChange = true;
										}
										// 比较爬虫链接url
										if (!retObj.getLinkUrl().equals(list.getAutohomeUrl())) {
											if(StringUtils.isNotBlank(list.getAutohomeUrl()) && !"null".equals(list.getAutohomeUrl())){
												beforeTipsCompare.setUrl(retObj.getLinkUrl());
												afterTipsCompare.setUrl(list.getAutohomeUrl());
												isChange = true;
											}
										}
										if (isChange) {
											// 变动前数据
											SpiderDataCompareVO beforeValueCompare = new SpiderDataCompareVO();
											beforeValueCompare.setId(retObj.getFactoryId().toString());
											beforeValueCompare.setName(retObj.getFactoryName());
											beforeValueCompare.setPid(retObj.getBrandId() == null ? "" : retObj.getBrandId().toString());
											beforeValueCompare.setUrl(retObj.getLinkUrl());
											beforeValueCompare.setCreateTime(retObj.getCreatetime());
											// 变动后数据
											SpiderDataCompareVO afterValueCompare = new SpiderDataCompareVO();
											afterValueCompare.setId(list.getId());
											afterValueCompare.setName(list.getName());
											afterValueCompare.setPid(list.getPid());
											afterValueCompare.setUrl(list.getAutohomeUrl());
											afterValueCompare.setCreateTime(list.getCreateTime());
											spiderCompare.setBeforeTips(fromObjectToString(beforeTipsCompare));
											spiderCompare.setAfterTips(fromObjectToString(afterTipsCompare));
											spiderCompare.setBeforeValue(fromObjectToString(beforeValueCompare));
											spiderCompare.setAfterValue(fromObjectToString(afterValueCompare));
											spiderCompare.setSpiderValId(list.getId());
											spiderCompare.setType(CARDATA_CF);
											spiderCompare.setCompareType("update");
											spiderCompare.setStatus(0);
											spiderCompare.setCreateTime(new Date());
											compareList.add(spiderCompare);
										}
									}
								}
							}
						}
						if (list.getDataType().equals(CARDATA_CS)) {
							if (StringUtils.isBlank(list.getAutohomeId())) {
								// 新增的数据
								CarSpiderCompare addCarSpiderCompare = getCarSpiderCompareData(list, CARDATA_CS);
								compareList.add(addCarSpiderCompare);
							} else {
								// 对比之前的数据,如果一致则不管，否则插入对比表数据
								HeadObject headObject1 = CommonHeadUtil.geneHeadObject(null, "2000020117-08");
								ResultObject resultObject1 = itemService.doService(new RequestObject(headObject1, list));
								// 返回结果
								if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
									CarDept retObj = (CarDept) resultObject1.getContent();
									if (retObj != null) {
										CarSpiderCompare spiderCompare = new CarSpiderCompare();
										boolean isChange = false;
										SpiderDataTipsVO beforeTipsCompare = new SpiderDataTipsVO();
										SpiderDataTipsVO afterTipsCompare = new SpiderDataTipsVO();
										// 比较爬虫名称
										if (!retObj.getCarDeptName().equals(list.getName())) {
											beforeTipsCompare.setName(retObj.getCarDeptName());
											afterTipsCompare.setName(list.getName());
											isChange = true;
										}
										if (isChange) {
											// 变动前数据
											SpiderDataCompareVO beforeValueCompare = new SpiderDataCompareVO();
											beforeValueCompare.setId(retObj.getCarDeptId().toString());
											beforeValueCompare.setName(retObj.getCarDeptName());
											beforeValueCompare.setPid(retObj.getFactoryid() == null ? "" :retObj.getFactoryid().toString());
											// 变动后数据
											SpiderDataCompareVO afterValueCompare = new SpiderDataCompareVO();
											afterValueCompare.setId(list.getId());
											afterValueCompare.setName(list.getName());
											afterValueCompare.setPid(list.getPid());
											afterValueCompare.setUrl(list.getAutohomeUrl());
											afterValueCompare.setCreateTime(list.getCreateTime());
											spiderCompare.setBeforeTips(fromObjectToString(beforeTipsCompare));
											spiderCompare.setAfterTips(fromObjectToString(afterTipsCompare));
											spiderCompare.setBeforeValue(fromObjectToString(beforeValueCompare));
											spiderCompare.setAfterValue(fromObjectToString(afterValueCompare));
											spiderCompare.setSpiderValId(list.getId());
											spiderCompare.setType(CARDATA_CS);
											spiderCompare.setCompareType("update");
											spiderCompare.setStatus(0);
											spiderCompare.setCreateTime(new Date());
											compareList.add(spiderCompare);
										}
									}
								}
							}
							// csList.add(list);
						}
						if (list.getDataType().equals(CARDATA_CT)) {
							ResultObject resultObject1 = (ResultObject) autohomeCarDataService.selectCarInfoIncludeCarAttr(list.getId());
							AutohomeCarInfoDTO autohomeCarInfo=(AutohomeCarInfoDTO) resultObject1.getContent();
							if (StringUtils.isBlank(list.getAutohomeId())) {
								// 新增的数据
								CarSpiderCompare addCarSpiderCompare = getCarTypeSpiderCompareData(autohomeCarInfo);
								compareList.add(addCarSpiderCompare);
							} else {
								// 对比之前的数据,如果一致则不管，否则插入对比表数据
								HeadObject headObject2 = CommonHeadUtil.geneHeadObject(null, "2000020112-12");
								ResultObject resultObject2 = itemService.doService(new RequestObject(headObject2, list));
								// 返回结果
								if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
									Car retObj = (Car) resultObject2.getContent();
									if (retObj != null) {
										CarSpiderCompare spiderCompare = new CarSpiderCompare();
										boolean isChange = false;
										SpiderDataTipsVO beforeTipsCompare = new SpiderDataTipsVO();
										SpiderDataTipsVO afterTipsCompare = new SpiderDataTipsVO();
										// 比较爬虫名称
										if (!retObj.getCarName().equals(autohomeCarInfo.getName())) {
											beforeTipsCompare.setName(retObj.getCarName());
											afterTipsCompare.setName(autohomeCarInfo.getName());
											isChange = true;
										}
										//对比后的值
										List afterValueList=new ArrayList<>();
										//比较属性项
										if(retObj.getCarConfigWithBLOBs() != null){
											List<CarConfigVO> carConfigList=getDTOList(retObj.getCarConfigWithBLOBs().getConfigs(),CarConfigVO.class);
											if(!carConfigList.isEmpty()){
												for(CarConfigVO carConfig:carConfigList){
													for(AutohomeCarAttr attr :autohomeCarInfo.getAutohomeCarAttrs()){
														if(carConfig.getDisplayName().equals(attr.getName())){
															CarConfigVO afterConfig=new CarConfigVO();
															afterConfig.setDisplayName(attr.getName());
															afterConfig.setConfigValue(attr.getVal());
															afterValueList.add(afterConfig);
															//属性项对上后，检查属性值是否有变化
															if(!carConfig.getConfigValue().equals(attr.getVal())){
																if("厂商指导价(元)".equals(carConfig.getDisplayName())){
																	if(!carConfig.getConfigValue().contains(attr.getVal())){
																		//有变化，则加入表中
																		beforeTipsCompare.addDetailConfigs(carConfig);
																		afterTipsCompare.addDetailConfigs(afterConfig);
																	}
																}else{
																	//有变化，则加入表中
																	beforeTipsCompare.addDetailConfigs(carConfig);
																	afterTipsCompare.addDetailConfigs(afterConfig);
																}
															}
														}
													}
												}
											}
										}
										if(beforeTipsCompare.getDetailConfig() != null && beforeTipsCompare.getDetailConfig().size() > 0){
											isChange = true;
										}
										if (isChange) {
											// 变动前数据
											SpiderDataCompareVO beforeValueCompare = new SpiderDataCompareVO();
											beforeValueCompare.setId(retObj.getCarId().toString());
											beforeValueCompare.setName(retObj.getCarName());
											beforeValueCompare.setPid(retObj.getCarDeptId() == null ? "" :retObj.getCarDeptId().toString());
											beforeValueCompare.setDetailConfig(getDTOList(retObj.getCarConfigWithBLOBs().getConfigs(),CarConfigVO.class));
											// 变动后数据
											SpiderDataCompareVO afterValueCompare = new SpiderDataCompareVO();
											afterValueCompare.setId(list.getId());
											afterValueCompare.setName(list.getName());
											afterValueCompare.setPid(list.getPid());
											afterValueCompare.setUrl(list.getAutohomeUrl());
											afterValueCompare.setCreateTime(list.getCreateTime());
											afterValueCompare.setDetailConfig(afterValueList);
											spiderCompare.setBeforeTips(fromObjectToString(beforeTipsCompare));
											spiderCompare.setAfterTips(fromObjectToString(afterTipsCompare));
											spiderCompare.setBeforeValue(fromObjectToString(beforeValueCompare));
											spiderCompare.setAfterValue(fromObjectToString(afterValueCompare));
											spiderCompare.setSpiderValId(list.getId());
											spiderCompare.setType(CARDATA_CT);
											spiderCompare.setCompareType("update");
											spiderCompare.setStatus(0);
											spiderCompare.setCreateTime(new Date());
											compareList.add(spiderCompare);
										}
									}
								}
							}
						}
					}
				}
				
				//执行删除原对比表数据，并插入数据
				if(!compareList.isEmpty()){
					//执行数据同步操作
					log.info("执行第"+page.getPageIndex()+"页请求开始》》》");
					log.info("请求数据:"+compareList);
					HeadObject headObject1 = CommonHeadUtil.geneHeadObject(null, "2000020112-11");
					ResultObject resultObject1 = itemService.doService(new RequestObject(headObject1, compareList));
					log.info("执行第"+page.getPageIndex()+"页请求结束《《《");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("同步数据异常>>>{}",e.toString());
		}
	}
	
	/**
	 * 从一个JSON数组得到一个java对象集合，形如： 
	 * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...] 
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static List getDTOList(String jsonString, Class clazz){  
	   JSONArray array = JSONArray.fromObject(jsonString);  
	   Object[] obj = new Object[array.size()];  
	        for(int i = 0; i < array.size(); i++){  
	             JSONObject jsonObject = array.getJSONObject(i);  
	            obj[i] = JSONObject.toBean(jsonObject, clazz);  
	        }  
	   return Arrays.asList(obj);
	}
	
	/**
	 * @Description:格式化输出json
	 * @param obj
	 * @return
	 */
	private String fromObjectToString(Object obj){
		JsonConfig jsonConfig = new JsonConfig();  
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(BigDecimal.class, new JsonDecimalValueProcessor());
	    PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object object, String fieldName, Object fieldValue) {
            return null == fieldValue;
        }};
	    jsonConfig.setJsonPropertyFilter(filter);
	    return JSONObject.fromObject(obj, jsonConfig).toString();
	}
	
	/**
	 * @Description:同步爬虫数据入口
	 */
	public void syncCompareSpiderData(){
		log.info("同步爬虫数据开始>>>>>");
		try {
			page.setPageSize(10);
			do{
				if(page.getPageIndex() == 0){
					page.setPageIndex(page.getPageIndex()+1);
				}
				log.info("执行第"+page.getPageIndex()+"页数据开始>>>>");
				syncSpiderData();
				log.info("执行第"+page.getPageIndex()+"页数据结束<<<<");
				page.setPageIndex(page.getPageIndex()+1);
			}while(page.getPages() >= page.getPageIndex());
		log.info("同步爬虫数据结束<<<<<");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("同步爬虫数据异常>>>{}",e.toString());
		}
	}
	
}
