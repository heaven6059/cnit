/**
 * 文 件 名   :  PaintingService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-5-5 下午6:27:15
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.painting.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.DocFlavor.STRING;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.Constant;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.constant.OrderConstant;
import com.cnit.yoyo.constant.OrderConstant.OrderLogBehavior;
import com.cnit.yoyo.dao.painting.CarDamageDetailMapper;
import com.cnit.yoyo.dao.painting.CarDamageMapper;
import com.cnit.yoyo.dao.painting.CarDamageOfferDetailMapper;
import com.cnit.yoyo.dao.painting.CarDamageOfferMapper;
import com.cnit.yoyo.dao.painting.CarPartMapper;
import com.cnit.yoyo.dao.painting.PaintingOrdersMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.OrderLog;
import com.cnit.yoyo.model.painting.CarDamage;
import com.cnit.yoyo.model.painting.CarDamageDetail;
import com.cnit.yoyo.model.painting.CarDamageOffer;
import com.cnit.yoyo.model.painting.CarDamageOfferDetail;
import com.cnit.yoyo.model.painting.CarPart;
import com.cnit.yoyo.model.painting.PaintingOrders;
import com.cnit.yoyo.model.painting.dto.CarDamageQueryDTO;
import com.cnit.yoyo.model.painting.dto.PaintingOrderDTO;
import com.cnit.yoyo.reship.model.ReshipDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;


/**
 *@description 钣金喷漆services
 *@detail <功能详细描述>
 *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
 *@version 1.0.0
 */
@Service("paintingService")
public class PaintingService {
    
    public static final Logger logger = LoggerFactory.getLogger(PaintingService.class);
    
    @Autowired
    private CarPartMapper carPartMapper;
    @Autowired
    private CarDamageDetailMapper carDamageDetailMapper;
    @Autowired
    private CarDamageMapper carDamageMapper;
    @Autowired
    private CarDamageOfferMapper carDamageOfferMapper;
    @Autowired
    private CarDamageOfferDetailMapper carDamageOfferDetailMapper;
    @Autowired
    private PaintingOrdersMapper paintingOrdersMapper;
    
    @Autowired
    private RemoteService orderService;
    
	public final static Map<String, String> LogMap = new HashMap<String, String>();  
	static {  
		LogMap.put("create", "创建订单");
		LogMap.put("payments", "支付订单");
		LogMap.put("refunds", "申请退款");
		LogMap.put("finish", "订单完成");
		LogMap.put("reship", "申请退货");		
		LogMap.put("cancel", "取消订单");
	}  
	
    /**买家查看自己的钣金受损单列表
     * @param data
     * @return
     */
    public Object findMyDamageList(Object data)
    {
		logger.info("PaintingService.findMyDamageList>>>>>>>>>start");
        HeadObject head = new HeadObject();
        ResultPage<CarDamage> result= new ResultPage<CarDamage>();
        try 
        {
            CarDamageQueryDTO qryDTO=(CarDamageQueryDTO)data;
            int pageNum = qryDTO.getPage();
            if(pageNum <1)
            {
            	pageNum =1;
            }
            int pageSize = qryDTO.getRows()<=0?GlobalStatic.DEFAULT_PAGE_SIZE_10:qryDTO.getRows();
            //手动存入起始页和每页显示个数
            qryDTO.setPage((pageNum-1)*pageSize);
            qryDTO.setRows(pageSize);
            
            //查看受损单总数
            int total = carDamageMapper.selectCount(qryDTO);
            //当前页数
            int pages = this.getPages(total,pageSize);
            
            //分页查询受损单列表
            List<CarDamage> list= carDamageMapper.selectMyList(qryDTO);
            int currPageSize = list.size();
            
            result.setRows(list);
            result.setPageIndex(pageNum);//起始页
            result.setPageSize(pageSize);//页面大小
            result.setTotal(total);//总数
            result.setPages(pages);//总页数
            result.setCurrPageSize(currPageSize);//当前页
            head.setRetCode(ErrorCode.SUCCESS);
        } 
        catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
		logger.info("PaintingService.findMyDamageList>>>>>>>>>end");
        return new ResultObject(head, result);
    }
    
    /**
     *@description 查找所有的汽车部位
     *@detail <方法详细描述>
     *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
     *@version 1.0.0
     *@data 2015-5-6
     *@return
     */
    public Object findList(Object data)
    {
        logger.info("PaintingService.findList>>>>>>>>>start");
        HeadObject head = new HeadObject();
        List<CarPart> list = null;
        try 
        {
            list = this.carPartMapper.selectAllparts();
            head.setRetCode(ErrorCode.SUCCESS);
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        logger.info("PaintingService.findList>>>>>>>>>end");
        return new ResultObject(head, list);
    }
    
    /**
     *@description 保存受损部位
     *@detail <方法详细描述>
     *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
     *@version 1.0.0
     *@data 2015-5-12
     *@param data
     *@return
     */
    public Object saveCarParts(Object data)
    {
        logger.info(">>>>>>>>>>>PaintingService.saveCarParts-->start");
        JSONObject content = JSONObject.fromObject(data);
        JSONArray parts = content.getJSONArray("catParts");
        Integer memberId = content.optInt("memberId");
        Integer accountId = content.optInt("accountId");
        CarDamage carDamage = new CarDamage();
        carDamage.setMemberId(memberId);
        carDamage.setAccountId(accountId);
        carDamage.setCreatetime(new Date());
        List<CarDamageDetail> list = new ArrayList<CarDamageDetail>();
        HeadObject head = new HeadObject();
        try
        {
            carDamageMapper.insertSelective(carDamage);
            Integer carDamageMapperId = carDamage.getId();
            for (Object object : parts) 
            {
                JSONObject json = JSONObject.fromObject(object);
                CarDamageDetail obj1 = new CarDamageDetail();
                obj1.setCarPartId(json.optInt("car_part_id"));
                obj1.setPic(json.optString("pic"));
                obj1.setRemark(json.optString("remark"));
                obj1.setCreatetime(new Date());
                obj1.setCarDamageId(carDamageMapperId);
                list.add(obj1);
            }
            carDamageDetailMapper.batchInsert(list);
            head.setRetCode(ErrorCode.SUCCESS);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        logger.info(">>>>>>>>>>PaintingService.saveCarParts-->end");
        return head;
    }
    
    /**
     *@description 查询受损单
     *@detail 给商家展示所有提交的受损单
     *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
     *@version 1.0.0
     *@data 2015-5-12
     *@param data
     *@return
     */
    public Object findDetailList(Object data)
    {
        logger.info("###########PaintingService.findDetailList-->start");
        HeadObject head = new HeadObject();
        ResultPage<CarDamage> result= new ResultPage<CarDamage>();
        try 
        {
            CarDamageQueryDTO qryDTO=(CarDamageQueryDTO)data;
            Boolean canSee = qryDTO.getCanSee();
            if(null != canSee && canSee==true){
            	String[] arr = {"1","3"};
            	qryDTO.setPassStatusArr(arr);
            	qryDTO.setPassStatus(null);
            }
            int pageNum = qryDTO.getPage();
            if(pageNum <1){
            	pageNum =1;
            }
            int pageSize = qryDTO.getRows()<=0?GlobalStatic.DEFAULT_PAGE_SIZE_10:qryDTO.getRows();
            
            qryDTO.setPage((pageNum-1)*pageSize);
            qryDTO.setRows(pageSize);
            List<CarDamage> list= carDamageMapper.selectList(qryDTO);
            Integer companyId = qryDTO.getCompanyId();
            //商铺已报价的受损单
            List<Integer> offeredlist = carDamageOfferMapper.selectByCompanyId(companyId);
            for (Integer carDamageId : offeredlist) {
				for (CarDamage carDamage : list) {
					if(carDamage.getId().equals(carDamageId)) 
					{
						carDamage.setOffered(true); 
						break;
					}
				}
			}
            int total = carDamageMapper.selectCount(qryDTO);
            int pages = this.getPages(total,pageSize);
            int currPageSize = list.size();

            /*public ResultPage(List<T> rows) {
                if (rows instanceof Page) {
                    Page page = (Page) rows;
                    this.pageIndex = page.getPageNum();
                    this.pageSize = page.getPageSize();页面大小
                    this.total = page.getTotal();总数
                    this.pages = page.getPages();总页数
                    this.rows = page;
                    this.currPageSize = page.size();
                }
            }*/
            result.setRows(list);
            result.setPageIndex(pageNum);
            result.setPageSize(pageSize);
            result.setTotal(total);
            result.setPages(pages);
            result.setCurrPageSize(currPageSize);
            head.setRetCode(ErrorCode.SUCCESS);
        } 
        catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        logger.info("###########PaintingService.findDetailList-->end");
        return new ResultObject(head,result);
    }
    
    /**
     *@description 根据ID查询汽车受损单
     *@detail <方法详细描述>
     *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
     *@version 1.0.0
     *@data 2015-5-14
     *@param data
     *@return
     */
    public Object findDetailById(Object data) 
    {
        logger.info("###########PaintingService.findDetailById-->start");
        HeadObject head = new HeadObject();
        List<CarDamageDetail> carDamageDetailList = new ArrayList<CarDamageDetail>();
        List<CarDamageOfferDetail> carDamageOfferDetailList = new ArrayList<CarDamageOfferDetail>();
    	Map<String, Object> map = new HashMap<String,Object>();
    	ResultObject resultObject = new ResultObject();
    	CarDamageOffer carDamageOffer = new CarDamageOffer();
        try 
        {
        	JSONObject obj = JSONObject.fromObject(data);
        	int damageId = obj.optInt("id");
        	Integer companyId = obj.optInt("companyId");
        	String edit = obj.optString("edit");
        	logger.info("damageId:"+damageId+",edit:"+edit);
        	if("1".equals(edit))
        	{
        		carDamageDetailList = carDamageDetailMapper.selectByDamageId(damageId);
            	map.put("carDamageDetailList", carDamageDetailList);
        	}
        	else if("0".equals(edit))
        	{
        		carDamageOfferDetailList = carDamageOfferDetailMapper.selectByDamageId(damageId,companyId);
        		carDamageOffer = carDamageOfferMapper.selectOffer(damageId,companyId);
            	map.put("carDamageOfferDetailList",carDamageOfferDetailList);
            	map.put("carDamageOffer",carDamageOffer);
        	}
            head.setRetCode(ErrorCode.SUCCESS);
            resultObject.setHead(head);
            resultObject.setContent(map);
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            head.setRetCode(ErrorCode.FAILURE);
        }
        logger.info("###########PaintingService.findDetailById-->end");
        return resultObject;
    }
    
    /**
     *@description 买家对受损单报价
     *@detail <方法详细描述>
     *@author <a href="zjcai@chinacnit.com">蔡志杰</a>
     *@version 1.0.0
     *@data 2015-5-16
     *@param data
     *@return
     */
    public Object saveOffer(Object data)
    {
        logger.info("###########PaintingService.saveOffer-->start");
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        int companyId = content.optInt("companyId");
        BigDecimal totalprice = BigDecimal.valueOf(content.optDouble("totalprice"));
        int carDamageId = content.optInt("carDamageId");
        CarDamageOffer carDamageOffer = new CarDamageOffer();
        carDamageOffer.setCompanyId(companyId);
        carDamageOffer.setTotalprice(totalprice);
        carDamageOffer.setCarDamageId(carDamageId);
        carDamageOffer.setCreatetime(new Date());
        Integer carDamageOfferId = null ;
        try
        {
            carDamageOfferMapper.insertSelective(carDamageOffer);
            carDamageOfferId = carDamageOffer.getId();
            System.err.println("*******"+carDamageOfferId);
        }catch (Exception e) {
            logger.error(e.getMessage());
            head.setRetCode(ErrorCode.FAILURE);
        }
        
        JSONArray rows = content.getJSONArray("rows");
        @SuppressWarnings("unchecked")
        List<CarDamageOfferDetail> list = (List<CarDamageOfferDetail>)JSONArray.toCollection(rows, CarDamageOfferDetail.class);
        for (CarDamageOfferDetail carDamageOfferDetail : list) {
            carDamageOfferDetail.setCarDamageOfferId(carDamageOfferId);
            carDamageOfferDetail.setCarDamageId(carDamageId);
            carDamageOfferDetail.setCreatetime(new Date());
        }
        if (list.size() > 0) {
            try
            {
                carDamageOfferDetailMapper.batchInsert(list);
                head.setRetCode(ErrorCode.SUCCESS);
            }catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage());
                head.setRetCode(ErrorCode.FAILURE);
            }
        }
        logger.info("###########PaintingService.saveOffer-->end");
        return head;
    }
    
    /**
     * 获取总页数
     * @param total
     * @param size
     * @return
     */
    private int getPages(long total,int size){
    	if(total >0 && size>0){
    		long pagesize = total/size;
    		if(pagesize <1){
    			pagesize = 1;
    		}else if(total%size != 0){
    			pagesize++;
    		}
    		return (int)pagesize;
    	}else{
    		return 1;
    	}
	}
    
    /**
     * 修改验证状态
     * @param data
     * @return
     */
    public Object updataPassStatus(Object data){
    	logger.info("##############PaintingService.updataPassStatus--->Start");
        HeadObject head = new HeadObject();
    	@SuppressWarnings("unchecked")
		Map<String, String> map = (HashMap<String, String>)data;
    	try{
    		carDamageMapper.updatePassStatus(map);
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		e.printStackTrace();
    		head.setRetCode(ErrorCode.FAILURE);
		}
    	logger.info("##############PaintingService.updataPassStatus--->end");
    	return new ResultObject(head);
    }
    
    /**
     * 查看受损单报价
     * @param data
     * @return
     */
    public Object findOffers(Object data)
    {
       	logger.info("##############PaintingService.findOffers--->Start");
        HeadObject head = new HeadObject();
        Map<String, String> map = (HashMap<String, String>)data;
        Integer damageId = Integer.valueOf(map.get("damageId"));
        Integer companyId = null;
        if(null != map.get("companyId"))
        {
        	companyId = Integer.valueOf(map.get("companyId"));
        }
        CarDamageQueryDTO dto = new CarDamageQueryDTO();
        dto.setCompanyId(companyId);
        dto.setCarDamageId(damageId);
        List<CarDamageOffer> list = new ArrayList<CarDamageOffer>();
    	try{
    		list = carDamageOfferMapper.selectOffers(dto);
        	head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		logger.error(e.getMessage());
    		e.printStackTrace();
    		head.setRetCode(ErrorCode.FAILURE);
		}
    	logger.info("##############PaintingService.findOffers--->end");
    	return new ResultObject(head,list);
    }
    
    /**
     * 提交钣金订单
     * @param data
     * @return
     */
    public Object submitOrder(Object data)
    {
    	logger.info("##############PaintingService.submitOrder--->Start");
        HeadObject head = new HeadObject();
        PaintingOrders order = (PaintingOrders)data;
        PaintingOrders _order = new  PaintingOrders();
        Long id = null;
        try
        {
        	int i = 0;
			do {
				id = Long.valueOf(order.getId() + fourNumber());
				_order = paintingOrdersMapper.selectByPrimaryKey(id);
				i++;
			}while(_order != null && i< 15000);
			if(_order != null){
				return new HeadObject(ErrorCode.FAILURE,"今日订单量已达上限");
			}
			order.setId(id);
        	paintingOrdersMapper.insertSelective(order);
        	Map<String,String> map = new HashMap<String, String>();
        	map.put("id", order.getCarDamageId()+"");
        	map.put("passStatus", "3");
        	carDamageMapper.updatePassStatus(map);
        	head.setRetCode(ErrorCode.SUCCESS);
        }
        catch (Exception e)
        {
        	logger.error(e.getMessage());
        	e.printStackTrace();
        	head.setRetCode(ErrorCode.FAILURE);
        	head.setRetMsg(e.getMessage());
        }
    	logger.info("##############PaintingService.submitOrder--->end");
    	return new ResultObject(head, id);
    }
    
    public Object findOrderByDamageId(Object data)
    {
    	logger.info("##############PaintingService.findOrderByDamageId--->Start");
        HeadObject head = new HeadObject();
        Integer id = (Integer)data;
        PaintingOrders order = new PaintingOrders();
        try
        {
        	order = paintingOrdersMapper.selectByDamageId(id);
        	head.setRetCode(ErrorCode.SUCCESS);
        }
        catch (Exception e)
        {
        	logger.error(e.getMessage());
        	e.printStackTrace();
        	head.setRetCode(ErrorCode.FAILURE);
        	head.setRetMsg(e.getMessage());
        }
    	logger.info("##############PaintingService.findOrderByDamageId--->end");
    	return new ResultObject(head, order);
    }
    
    /**
     * 查询订单详情
     * @param data
     * @return
     */
    public Object findOrderByOrderId(Object data)
    {
    	logger.info("##############PaintingService.findOrderByOrderId--->Start");
        HeadObject head = new HeadObject();
        Long id = (Long)data;
        PaintingOrders order = new PaintingOrders();
        try
        {
        	order = paintingOrdersMapper.selectByPrimaryKey(id);
        	head.setRetCode(ErrorCode.SUCCESS);
        }
        catch (Exception e)
        {
        	logger.error(e.getMessage());
        	e.printStackTrace();
        	head.setRetCode(ErrorCode.FAILURE);
        	head.setRetMsg(e.getMessage());
        }
    	logger.info("##############PaintingService.findOrderByOrderId--->end");
    	return new ResultObject(head, order);
    }
    
    /**
     * 查询订单列表
     * @param data
     * @return
     */
	public Object findOrderList(Object data){
    	logger.info("##############PaintingService.findOrderList--->Start");
        HeadObject head = new HeadObject();
        PaintingOrderDTO dto = (PaintingOrderDTO)data;
        
    	if((null == dto.getPageNum() || dto.getPageNum() ==0)&& dto.getPage() !=0){
			dto.setPageNum(dto.getPage());
		}
		if((null == dto.getPageSize() || dto.getPageSize() ==0) && dto.getRows() !=0){
			dto.setPageSize(dto.getRows());
		}

        int pageNum = (null == dto.getPageNum()? 0:dto.getPageNum());
		int pageSize = dto.getPageSize();
		
        ResultPage<String> result=null;
		PageHelper.startPage(pageNum,pageSize);

		try{
			result = new ResultPage<String>(paintingOrdersMapper.selectOrderIdsList(dto));
			if(result.getRows().size()>0){
				dto.setIds(result.getRows());
				result.setRows(paintingOrdersMapper.selectList(dto));
			}else{
				result.setRows(null);
			}
	    	head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
        	e.printStackTrace();
			logger.error(e.toString());
        	head.setRetCode(ErrorCode.FAILURE);
		}
    	logger.info("##############PaintingService.findOrderList--->end");
    	return new ResultObject(head, result);
    }
    
    /**
     * 生成4位随机数
     * @return
     */
    private String fourNumber(){
		Random random = new Random();
		String rd = random.nextInt(10000)+"";
		int len = 4 - rd.length();
		if(len > 0){
			for(int i=0; i<len; i++){
				rd = "0"+rd;
			}
		}
		return rd;
    }
    
    /**
     * 修改订单状态
     * @param data
     * @return
     */
    public Object modifyOrder(Object data){
    	logger.info("##############PaintingService.modifyOrder--->Start");
        HeadObject head = new HeadObject();
    	JSONObject obj = JSONObject.fromObject(data);
    	String orderId = obj.optString("orderId");
    	String oldStatus = obj.optString("oldStatus");
    	
    	String[] idArr = (orderId).split(",");
		List<Long> ids = new ArrayList<Long>();
		for (String id : idArr) {
			ids.add(Long.valueOf(id));
		}
    	int type = obj.optInt("type");
    	PaintingOrders order = new PaintingOrders();
    	order.setIds(ids);
    	//1、取消订单2、删除订单3、还原订单4、彻底删除订单5、订单支付成功
    	//6、确认订单7、安装中8、已完成，9退款
    	if(type == 1){
    		obj.put("type","cancel");
    		order.setStatus("dead");
    		order.setPayStatus("");
    	}else if(type == 2){
    		order.setDisabled("1");
    	}else if(type == 3){
    		order.setDisabled("0");
    	}else if(type == 4){
    		order.setDisabled("2");
    	}else if(type == 5){
    		obj.put("type","payments");
    		order.setPayStatus("1");
    		order.setStatus("unconfirm");
    	}else if(type == 6){
    		obj.put("type","confirm");
    		order.setStatus("uninstall");
    	}else if(type == 7){
    		obj.put("type","install");
    		order.setPayStatus("1");
    		order.setStatus("install");
    	}else if(type == 8){
    		obj.put("type","finish");
    		order.setStatus("finish");
    	}
		order.setLastModified(new Date());
		try{
			if(StringUtils.isNotBlank(oldStatus)){
				List<String> statusList = paintingOrdersMapper.selectStatus(ids);
				List<String> oldStatusList = Arrays.asList(oldStatus.split(","));
				if(!oldStatusList.equals(statusList)){
					return new ResultObject(new HeadObject(ErrorCode.VALIDATE_ELEMENT_ERROR));
				}
			}
			paintingOrdersMapper.updateStatus(order);
			
			if(StringUtils.isNotBlank(obj.optString("type"))){
				addOrderLog(obj);
			}

			if(type == 1){
				if(StringUtils.isNotBlank(oldStatus)){
					List<String> statusList = paintingOrdersMapper.selectStatus(ids);
					List<String> oldStatusList = Arrays.asList(oldStatus.split(","));
					if(!oldStatusList.equals(statusList)){
						return new ResultObject(new HeadObject(ErrorCode.VALIDATE_ELEMENT_ERROR));
					}
				}
				paintingOrdersMapper.updateStatus(order);
				//取消订单，设置受损单未成交状态
				if(type == 1){
					Map<String,String> map = new HashMap<String, String>();
		        	map.put("id", orderId+"");
		        	map.put("passStatus", "1");
		        	carDamageMapper.updatePassStatusByOrderId(map);
				}
				
				//是否付款
//	        	ReshipDTO dto = new ReshipDTO();
//	        	dto.setOrderId(obj.optLong("orderId"));
//	        	dto.setOpId(obj.optInt("opId"));
//	        	dto.setOpName(obj.optString("opName"));
//	    		HeadObject headObject = CommonHeadUtil.geneHeadObject("reshipService.applyRefunds");
//	        	orderService.doServiceByServer(new RequestObject(headObject, dto));
	        	head.setRetCode(ErrorCode.DOING);
	        	//跳转到退款流程
	        	return new ResultObject(head);
			}
	    	head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
        	e.printStackTrace();
			logger.error(e.toString());
        	head.setRetCode(ErrorCode.FAILURE);
        	return new ResultObject(head);
		}
    	logger.info("##############PaintingService.modifyOrder--->end");
    	return new ResultObject(head);
    }
    
    public Object updateOrder(Object data){
    	logger.info("##############PaintingService.updateOrder--->start");
    	HeadObject head = new HeadObject();
    	PaintingOrders obj = (PaintingOrders)data;
    	try{
        	paintingOrdersMapper.updateByPrimaryKeySelective(obj);
        	head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		e.printStackTrace();
    		logger.error(e.toString());
    		head.setRetCode(ErrorCode.FAILURE);
		}
    	logger.info("##############PaintingService.updateOrder--->end");
    	return new ResultObject(head);
    }
    
	/**
	 * @Title:  addOrderLog  
	 * @Description: 添加日志
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-13 下午4:27:47  
	 * @version 1.0.0 
	 * @param @param obj
	 * @param @throws Exception
	 * @return void  返回类型 
	 * @throws
	 */
	private void addOrderLog(JSONObject obj) throws Exception{
		OrderLog orderLog=new OrderLog();
		orderLog.setOrderId(obj.optLong("orderId"));
		orderLog.setBillType("order");
		orderLog.setOpId(obj.optInt("opId"));
		orderLog.setOpName(obj.optString("opName"));
		//设置订单退款状态
		orderLog.setBehavior(obj.optString("type"));
		orderLog.setLogText(LogMap.get(obj.optString("type")));
		
		HeadObject headObject = CommonHeadUtil.geneHeadObject("orderLogService.addOrderLog");
    	orderService.doServiceByServer(new RequestObject(headObject, orderLog));
	}
	
    /**
     * 判断是否某个受损单是否已下单
     * @param data
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object checkExist(Object data){
    	logger.info("##############PaintingService.checkExist--->Start");
        HeadObject head = new HeadObject();
		Map<String, Integer> map = (Map<String, Integer>)data;
		int count = -1;
		try{
			count = paintingOrdersMapper.selectByOrderAndMem(map);
        	head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
			logger.error(e.toString());
        	head.setRetCode(ErrorCode.FAILURE);
		}
    	logger.info("##############PaintingService.checkExist--->end");
    	return new ResultObject(head, count);
    }
    
    /**
     * 查询卖家是否有钣金喷漆权限
     * @param data
     * @return
     */
    public Object checkPermission(Object data){
    	logger.info("##############PaintingService.checkPermission--->Start");
        HeadObject head = new HeadObject();
        Long companyId = (Long)data;
		int count = -1;
		try{
			count = paintingOrdersMapper.selectPermission(companyId);
        	head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
			logger.error(e.toString());
        	head.setRetCode(ErrorCode.FAILURE);
		}
    	logger.info("##############PaintingService.checkPermission--->end");
    	return new ResultObject(head, count);
    }
    
	/**
	 * 非分页查询订单列表
	 * @param data
	 * @return
	 */
	public Object findExportOrderList(Object data){
		logger.info("##############PaintingService.findExportOrderList--->Start");
        HeadObject head = new HeadObject();
        PaintingOrderDTO dto = (PaintingOrderDTO)data;
        String josnArr= "";
		try{
			List<String> list = paintingOrdersMapper.selectList(dto);
			josnArr = com.alibaba.fastjson.JSON.toJSONString(list);
	    	head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
        	e.printStackTrace();
			logger.error(e.toString());
        	head.setRetCode(ErrorCode.FAILURE);
		}
    	logger.info("##############PaintingService.findExportOrderList--->end");
    	return new ResultObject(head, josnArr);
	}
}
