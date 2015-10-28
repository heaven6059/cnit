package com.cnit.yoyo.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.base.validation.ValidationResult;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.painting.CarDamage;
import com.cnit.yoyo.model.painting.CarDamageDetail;
import com.cnit.yoyo.model.painting.CarDamageOfferDetail;
import com.cnit.yoyo.model.painting.PaintingOrders;
import com.cnit.yoyo.model.painting.dto.CarDamageQueryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.APICommonUtil;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.domain.ResultPage;

@Controller("myPaintingController")
@RequestMapping("/mypainting")
public class MyPaintingController extends BaseController {
    public static final int  pageRows = 10;   //默认每页的条数
	public final static Map<Integer, String> map = new HashMap<Integer, String>();  
	static {  
		map.put(0, "在线支付");
		map.put(1, "到店支付");
	}  
    
    @Autowired
    private RemoteService otherService;
    @Autowired
    private RemoteService memberService;
	
    @RequestMapping("/myPaintingList")
    @ResponseBody
    public Object myPaintingList(String data,HttpServletRequest request)
    {

        log.info("###########myPaintingList-->start-------------------");
        log.info("----------------------data:"+data+"-------------------------");

		HeadObject headObject = null;
		ResultObject resultObject = null;
		try {
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			Integer page = jsonData.get("page")==null?1:jsonData.getInt("page");   //不传页数，默认查询第一页
			Integer rows = jsonData.get("rows")==null?pageRows:jsonData.getInt("rows");  //没有传每页的数量，使用默认值
			String passStatus=jsonData.getString("passStatus")==null?"":jsonData.getString("passStatus");
			MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
			//设置远程查询参数
			CarDamageQueryDTO dto = new CarDamageQueryDTO();
			dto.setPage(page);
			dto.setRows(rows);
			dto.setPassStatus(passStatus);
			dto.setMemberId(Integer.valueOf(memberListDo.getMemberId()));
			
		    headObject = CommonHeadUtil.geneHeadObject("paintingService.findMyDamageList");
			resultObject = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject,dto));
			//图片地址转成httpURL
			String imgUrl = Configuration.getInstance().getConfigValue("images.url");
			ResultPage resultPage = (ResultPage) resultObject.getContent();
			List<CarDamage> list = resultPage.getRows();
			for(CarDamage cd:list){
				List<CarDamageDetail> deList = cd.getDetailList();
				for(CarDamageDetail cdd:deList){
					String[]pics = cdd.getPic().split(";");
					String[]nPics = new String[pics.length];
					int i=0;
					for(String pic:pics){
						nPics[i++] = imgUrl+pic;
					}
					cdd.setPic(StringUtils.join(nPics,";"));
				}
			}
			log.info("###########myPaintingList-->end-------------------");
			return resultObject;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return processExpction(e.getMessage());
		}
    }
    
    @RequestMapping("/myPaintingOffers")
    @ResponseBody
    public Object myPaintingOffers(String data,HttpServletRequest request)
    {
    	log.info("###########myPaintingOffers-->start");
    	log.info("----------------------data:"+data+"-------------------------");
    	 HeadObject headObject = null;
    	 ResultObject resultObject = null;
    	try{
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			String damageId = jsonData.getString("damageId");  
			
			//校验数据
			if(damageId==null||"".equals(damageId)){
				ValidationResult result = new ValidationResult();
				 Map<String,String> errorMsg = new HashMap<String,String>();
				 errorMsg.put("damageId", "不能为空");
				 result.setErrorMsg(errorMsg);
				 return elementErrors(headObject, result);
			}
    		
		    headObject = CommonHeadUtil.geneHeadObject("paintingService.findOffers");
		    Map<String,String> map = new HashMap<String, String>();
			map.put("damageId", damageId);
			resultObject = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject,map));
			log.info("###########myPaintingOffers-->end");
			return resultObject;
    	}catch(Exception e){
    		log.error(e.getMessage(),e);
    		return processExpction(e.getMessage());
    	}
    }
    
	@RequestMapping("/myPaintingChooseStore")
	@ResponseBody
    public Object myPaintingChooseStore(String data,HttpServletRequest request)
    {
		log.info("###########myPaintingChooseStore-->start");
		log.info("----------------------data:"+data+"-------------------------");
    	String area = "广东";
		 HeadObject headObject = null;
		 ResultObject resultObject = null;
    	try{
			//获取请求参数
			JSONObject jsonData = JSONObject.fromObject(data);
			String damageId = jsonData.getString("damageId");   
	    	String companyId = jsonData.getString("companyId");
	    	area = jsonData.getString("area")==null?area:jsonData.getString("area");
	    	
			//校验数据
			if(damageId==null||"".equals(damageId)){
				ValidationResult result = new ValidationResult();
				 Map<String,String> errorMsg = new HashMap<String,String>();
				 errorMsg.put("damageId", "不能为空");
				 result.setErrorMsg(errorMsg);
				 return elementErrors(headObject, result);
			}
			if(companyId==null||"".equals(companyId)){
				ValidationResult result = new ValidationResult();
				 Map<String,String> errorMsg = new HashMap<String,String>();
				 errorMsg.put("companyId", "不能为空");
				 result.setErrorMsg(errorMsg);
				 return elementErrors(headObject, result);
			}
	    	
	    	Map<String,String> map = new HashMap<String, String>();
	    	map.put("companyId", companyId);
	    	map.put("area", area);
	    	Map<String,String> map2 = new HashMap<String, String>();
	    	map2.put("companyId", companyId);
	    	map2.put("damageId", damageId);
	        headObject = CommonHeadUtil.geneHeadObject("companyService.selectStoreByArea");
	        HeadObject headObject2 = CommonHeadUtil.geneHeadObject("paintingService.findOffers");
	        resultObject = new ResultObject();
	        ResultObject resultObject2 = new ResultObject();
	        
			 resultObject = (ResultObject) memberService.doServiceByServer(new RequestObject(headObject,map));
			 resultObject2 = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject2,map2));
			 Map<String, Object> resultMap = (Map<String, Object>)resultObject.getContent();
			 List<Store> storeList = (List<Store>)resultMap.get("storeList");
			 String issueType = (String)resultMap.get("issueType");
			 
			 JSONObject result = new JSONObject();
			 
			 result.put("storeList", storeList);
			 result.put("area", area);
			 result.put("issueType", issueType);
			 result.put("offerList", resultObject2.getContent());
			 
			 log.info("###########myPaintingChooseStore-->end");
			 return result;
    	}catch(Exception e){
    		log.error(e.getMessage(),e);
    		return processExpction(e.getMessage());
    	}
    }
	
    @RequestMapping("/myPaintingSubmitOrder")
    @ResponseBody
    public Object myPaintingSubmitOrder(String data,HttpServletRequest request) {
    	 log.info("###########myPaintingSubmitOrder-->start");
    	 log.info("----------------------data:"+data+"-------------------------");
		 HeadObject headObject = null;
		 ResultObject resultObject = null;
    	try{
			//获取请求参数
			JSONObject jsonData = JSONObject.fromObject(data);
			MemberListDo memberListDo = APICommonUtil.getMemberListDo((String)request.getAttribute("sessionid"));
			String memberId = memberListDo.getMemberId();
			Integer accountId = memberListDo.getAccountId();
			
			Integer carDamageId = jsonData.getInt("carDamageId");  
			Integer storeId = jsonData.getInt("storeId");
		    String storeName = jsonData.getString("storeName");
		    Integer carDamageOfferId = jsonData.getInt("carDamageOfferId");
		    String consumptionTime = jsonData.getString("consumptionTime");
		    Integer paymentId = jsonData.getInt("paymentId");
		    String payed = jsonData.getString("payed");
		    String name = jsonData.getString("name");
		    String phone = jsonData.getString("phone");
		    String markText = jsonData.getString("markText");
			
			PaintingOrders order = new PaintingOrders();
			if(null != memberId){
				order.setMemberId(Integer.valueOf(memberId));
			}
			if(null != accountId){
				order.setAccountId(accountId);
			}
			order.setId(Long.valueOf(generateOrderNum(request)));
			order.setStoreId(storeId);
			order.setStoreName(storeName);
			order.setCarDamageOfferId(carDamageOfferId);
			order.setConsumptionTime(formatDate(consumptionTime,"yyyy-MM-dd"));
			order.setPaymentId(paymentId);
			order.setPayment(map.get(paymentId));
			order.setCarDamageId(carDamageId);
			Date now = new Date();
			order.setCreatetime(now);
			order.setLastModified(now);
			order.setStatus("create");
			order.setPayed(new BigDecimal(payed));
			order.setName(name);
			order.setPhone(phone);
			order.setMarkText(markText);
			headObject = CommonHeadUtil.geneHeadObject( "paintingService.checkExist");
		    resultObject = new ResultObject();
		    
			Map<String , Integer> map1 = new HashMap<String , Integer>();
			map1.put("damageId", carDamageId);
			map1.put("memberId", Integer.valueOf(memberId));
			resultObject = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject,map1));
				
			Integer count = (Integer)resultObject.getContent();
			if(count !=-1 && count > 0){
				return new ResultObject(new HeadObject(ErrorCode.FAILURE,"请勿重复提交订单，如需重新下单请先取消已下的订单！"));
			}
		    headObject = CommonHeadUtil.geneHeadObject("paintingService.submitOrder");
		    
			 synchronized (this) {
				 resultObject = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject,order));
				 if(resultObject.getRetCode().equals(ErrorCode.FAILURE)){
					 return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,msg:'订单提交失败，"+resultObject.getHead().getRetMsg()+"'}"));
				 }
			 }
			 log.info("###########myPaintingSubmitOrder-->end");
		    	return resultObject;
    	}catch(Exception e){
    		log.error(e.getMessage(),e);
    		return processExpction(e.getMessage());
    	}
    }
    
    @RequestMapping("/myPaintingOrderDetail")
    @ResponseBody
    public Object myPaintingOrderDetail(String data,HttpServletRequest request)
    {
    	log.info("###########myPaintingOrderDetail-->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject("paintingService.findOrderByDamageId");
        ResultObject resultObject = null;
        try 
        {
        	 //获取参数
        	JSONObject jsonData = JSONObject.fromObject(data);
        	String reqdamageId = jsonData.getString("damageId");
        	if(StringUtils.isEmpty(reqdamageId)){
                return processExpction("damageId不能为空！");
        	}
        	Integer damageId= Integer.parseInt(reqdamageId);
        	resultObject = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject,damageId));
            request.setAttribute("order", resultObject.getContent());
            
			//图片地址转成httpURL
			String imgUrl = Configuration.getInstance().getConfigValue("images.url");
			PaintingOrders paintingOrders = (PaintingOrders) resultObject.getContent();
			if(paintingOrders!=null){
					//消费日期格式化
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					Date date = paintingOrders.getConsumptionTime();
					paintingOrders.setConsumptionTimeStr(date==null?"":df.format(date));
					
					if(paintingOrders != null && paintingOrders.getDamageOfferList() != null){
						List<CarDamageOfferDetail> list = paintingOrders.getDamageOfferList();
						for(CarDamageOfferDetail cd:list){
							CarDamageDetail cdd = cd.getCarDamageDetail();
							String[]pics = cdd.getPic().split(";");
							String[]nPics = new String[pics.length];
							int i=0;
							for(String pic:pics){
								nPics[i++] = imgUrl+pic;
							}
							cdd.setPic(StringUtils.join(nPics,";"));
						}
					}
			}
			log.info("###########myPaintingSubmitOrder-->end");
        	return resultObject;
        } catch (Exception e) {
        	log.error(e.getMessage(),e);
            return processExpction(e.getMessage());
        }
    }
    
    @RequestMapping("/myPaintingCancleOrder")
    @ResponseBody
    public Object myPaintingCancleOrder(String data,HttpServletRequest request){
    	log.info("###########myPaintingCancleOrder-->start");
		HeadObject headObject = CommonHeadUtil.geneHeadObject("paintingService.modifyOrder");
		ResultObject resultObject = null;
		try {
	       	 //获取参数
	       	JSONObject jsonData = JSONObject.fromObject(data);
	       	String orderId = jsonData.getString("orderId");
	       	
	       	if(StringUtils.isEmpty(orderId)){
	       		return processExpction("orderId不能为空！");
	       	}
			
			JSONObject obj = new JSONObject();
			obj.put("orderId", orderId);
			obj.put("type", 1);
			resultObject = (ResultObject) otherService.doServiceByServer(new RequestObject(headObject,obj));
			log.info("###########myPaintingCancleOrder-->end");
			return resultObject;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		    return processExpction(e.getMessage());
		}
    }
    
    /**
     * 生成订单号
     * @return
     */
    private String generateOrderNum(HttpServletRequest request) {
//    	整车 999 + 来源= 年（xx）月（xx）日（xx）+ XXXX
//    	配件 888
//    	保养 777
//    	精品 666
//    	其他 555
//    	钣金 333  
//    	10：web前端  11：web管理平台  20：appAndroid 21:appIOS
    	StringBuilder sb = new StringBuilder("333");
    	sb.append(checkFrom(request));
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date()).replaceAll("-", "").substring(2);
		sb.append(date);
    	return sb.toString();
    }
    
    /**
     * @Title: checkFromMobile 
     * @Description: TODO(判断是否是移动设备访问) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-18
     * @version 1.0.0 
     * @param request
     * @param @return    
     * @return boolean    返回类型 
     * @throws
     */
    private String checkFrom(HttpServletRequest request) 
	{
		// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
		// 字符串在编译时会被转码一次,所以是 "\\b"
		// \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
		String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"
				+ "|windows (phone|ce)|blackberry"
				+ "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
				+ "|laystation portable)|nokia|fennec|htc[-_]"
				+ "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
		String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"
				+ "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		if (null == userAgent) 
		{
			userAgent = "";
		}

		// 移动设备正则匹配：手机端、平板
		Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
		Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

		// 匹配
		Matcher matcherPhone = phonePat.matcher(userAgent);
		Matcher matcherTable = tablePat.matcher(userAgent);
		if (matcherPhone.find() || matcherTable.find()) 
		{
			if (userAgent.contains("android")) 
			{
				return "20";
			} else {
				return "21";
			}
		} else {
			return "10";
		}
	} 
    
    private Date formatDate(String dateStr, String format)
    {
    	SimpleDateFormat simpleDateFormat  =   new  SimpleDateFormat(format); 
    	Date date = new Date();
    	try {
    		simpleDateFormat.parse(dateStr);
		} catch (ParseException e1) {
			log.error(e1.getMessage());
			e1.printStackTrace();
		}
    	return date;
    }
}
