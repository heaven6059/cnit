package com.cnit.yoyo.rmi.activity.service;

import java.text.SimpleDateFormat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.activity.CouponsApplyBrandMapper;
import com.cnit.yoyo.dao.activity.CouponsMapper;
import com.cnit.yoyo.dao.activity.CouponsPicShipMapper;
import com.cnit.yoyo.dao.activity.SalesRuleGoodsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.exception.BusinessException;
import com.cnit.yoyo.model.activity.Coupons;
import com.cnit.yoyo.model.activity.CouponsApplyBrand;
import com.cnit.yoyo.model.activity.CouponsExample;
import com.cnit.yoyo.model.activity.CouponsPicShip;
import com.cnit.yoyo.model.activity.SalesRuleGoodsWithBLOBs;
import com.cnit.yoyo.model.activity.dto.CouponsApplyBrandDTO;
import com.cnit.yoyo.model.activity.dto.CouponsDTO;
import com.cnit.yoyo.model.member.dto.RolesDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 
 * @ClassName: CouponsService
 * @Description: 优惠券
 * @author xiaox
 * @date 2015-4-20 下午1:11:07
 */
@Service("couponsService")
public class CouponsService {
	public static final Logger log = LoggerFactory.getLogger(CouponsService.class);
	
	@Autowired
	private CouponsMapper couponsMapper;
	
	@Autowired
	private SalesRuleGoodsMapper salesRuleGoodsMapper;
	
	@Autowired
    private CouponsApplyBrandMapper couponsApplyBrandMapper;
	
	@Autowired
	private CouponsPicShipMapper couponsPicShipMapper;
	
	@Autowired
	private RemoteService searchClientService;
	
	
	/**
	 * 
	 *@description 店铺查找所有优惠券
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-4-21
	 *@param data
	 *@return
	 */
	public Object findList(Object data){
	    HeadObject head = new HeadObject();
        List<Coupons> list = null;
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) data;
            CouponsDTO dto = new CouponsDTO();
            dto.setCompanyId(Long.parseLong(map.get("companyId").toString()));
            dto.setStoreId(Long.parseLong(map.get("storeId").toString()));
            dto.setType(Integer.parseInt(map.get("type").toString()));
            list = this.couponsMapper.selectCouponsList(dto);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
        	log.error(e.getMessage());
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head, list);
	}
	
	/**
	 * 
	 *@description 获取类别数据，带分页
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-4-23
	 *@param data
	 *@return
	 */
	public Object findListPage(Object data){
        HeadObject head = new HeadObject();
        ResultPage<Coupons> page = null;
        try{
            JSONObject content = JSONObject.fromObject(data);
            CouponsDTO dto = new CouponsDTO();
            PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
            page = new ResultPage<Coupons>(this.couponsMapper.selectCouponsList(dto));
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }  
        JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new com.cnit.yoyo.util.JsonDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(BigDecimal.class, new com.cnit.yoyo.util.JsonDecimalValueProcessor());
		JSONObject json = JSONObject.fromObject(page, jsonConfig);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
   }
   
	
	/**
	 * 
	 *@description 根据id查找优惠券信息
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-4-21
	 *@param data
	 *@return
	 */
	public Object findById(Object data){
        HeadObject head = new HeadObject();
        CouponsDTO dto = null;
        try {
            Integer id = (Integer) data;
            dto = this.couponsMapper.findById(id);
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head, dto);
    }
	public Object findPicById(Object data) {
		List<CouponsPicShip> list = couponsPicShipMapper.selectByCpnsId((Integer) data);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
	}
	/**
	 * 更新优惠券数量
	 * @Description: 
	 * @param data
	 * @return
	 */
	public Object updateCouponsNum(Object data) throws Exception{
		HeadObject head = new HeadObject();
		try {
            CouponsDTO dto =com.alibaba.fastjson.JSONObject.parseObject(data.toString(), CouponsDTO.class);
            Coupons resultDto=couponsMapper.selectByPrimaryKey(dto.getCpnsId());
//            BeanUtils.copyProperties(dto, coupons);
            if(null!=dto.getOnlineQuantity()&&dto.getOnlineQuantity()!=0){
            	if(resultDto.getOnlineGenQuantity()+dto.getOnlineQuantity()>resultDto.getOnlineLimit()){
            		 head.setRetCode(ErrorCode.THE_NUMBER_OVER);
            		 return new ResultObject(head);
            	}
            	resultDto.setOnlineGenQuantity(resultDto.getOnlineGenQuantity()+dto.getOnlineQuantity());
            }
            couponsMapper.updateByPrimaryKeySelective(resultDto);
            CouponsDTO couponsDTO=couponsMapper.findById(dto.getCpnsId());
            HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.addCoupons");
            resultDto.setFromTime(DateUtils.dateToString(couponsDTO.getFromTime(), DateUtils.NORMALSS_DATE_PATTERN));
            resultDto.setToTime(DateUtils.dateToString(couponsDTO.getToTime(), DateUtils.NORMALSS_DATE_PATTERN));
			searchClientService.doServiceByServer(new RequestObject(headObject, JSON.toJSONString(resultDto)));
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage());
            head.setRetCode(ErrorCode.FAILURE);
            throw new Exception(e);
        }
        return new ResultObject(head);
	}
	/**
	 * 
	 *@description 更新优惠券
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-4-21
	 *@return
	 */
	public Object updateCoupons(Object data){
        HeadObject head = new HeadObject();
        try {
            JSONObject content = JSONObject.fromObject(data);
            CouponsDTO dto = (CouponsDTO) JSONObject.toBean(content,CouponsDTO.class);
            int cpnsId = dto.getCpnsId();
            //1.先更新商品规则
            SalesRuleGoodsWithBLOBs rule = new SalesRuleGoodsWithBLOBs();
            BeanUtils.copyProperties(dto, rule);
            rule.setName("优惠券规则-"+dto.getCpnsName());
            rule.setStatus(dto.getCpnsStatus());
            rule.setFromTime(DateUtils.getDate(dto.getFromTimeStr(),DateUtils.NORMALSS_DATE_PATTERN));
            rule.setToTime(DateUtils.getDate(dto.getToTimeStr(),DateUtils.NORMALSS_DATE_PATTERN));
            salesRuleGoodsMapper.updateByPrimaryKeySelective(rule);
            
            
            //2.更新优惠券
            Coupons coupons = new Coupons();
            BeanUtils.copyProperties(dto, coupons);
            coupons.setRuleId(rule.getRuleId());
            if (!dto.getCpnsPrefix().equals("B")) {
				CouponsExample example=new CouponsExample();
				example.createCriteria().andCpnsPrefixEqualTo(dto.getCpnsPrefix());
				List<Coupons> list=couponsMapper.selectByExample(example);
				if (null!=list && list.size()>0) {
					if(!dto.getCpnsId().equals(list.get(0).getCpnsId())){
						head.setRetCode(ErrorCode.IS_EXIST);
						return new ResultObject(head);
					}
				}
			}else{
				coupons.setCpnsPrefix(null);
			}
            couponsMapper.updateByPrimaryKeySelective(coupons);
            //更新索引库
            CouponsDTO couponsDTO=couponsMapper.findById(dto.getCpnsId());
            HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.addCoupons");
            coupons.setFromTime(DateUtils.dateToString(couponsDTO.getFromTime(), DateUtils.NORMALSS_DATE_PATTERN));
            coupons.setToTime(DateUtils.dateToString(couponsDTO.getToTime(), DateUtils.NORMALSS_DATE_PATTERN));
			searchClientService.doServiceByServer(new RequestObject(headObject, JSON.toJSONString(coupons)));
            
            couponsPicShipMapper.deleteByCpnsId(cpnsId);
            String picIds = dto.getPicIds();
			String[] pics = picIds.split(",");
			if(pics != null) {
				int len = pics.length;
				if(len > 0) {
					for(int i=0;i<len;i++) {
						long picId = Long.parseLong(pics[i]);
						CouponsPicShip record = new CouponsPicShip();
						record.setCpnsId(cpnsId);
						record.setPictureId(picId);
						couponsPicShipMapper.insert(record);
					}
				}
			}
			
			
          //3.先删除再插入优惠券的适用范围
            couponsApplyBrandMapper.batchDelete(coupons.getCpnsId());
            String catAndBrand = dto.getCatAndbrandIds();
            List<CouponsApplyBrand> list = new ArrayList<CouponsApplyBrand>();
            CouponsApplyBrand  temp = new CouponsApplyBrand();
            if(StringUtils.isNotBlank(catAndBrand)){
                String [] catandBrandIds = catAndBrand.split(",");
                String[] str = null;
                for(int i=0;i<catandBrandIds.length; i++ ) {
                    str = catandBrandIds[i].split("\\|");
                    temp = new CouponsApplyBrand();
                    temp.setCouponsId(coupons.getCpnsId());
                    temp.setCatId(Integer.valueOf(str[0]));
                    temp.setBrandId(Integer.valueOf(str[1]));
                    list.add(temp);
                }
            }
            if(list.size()>0){
                couponsApplyBrandMapper.batchInsert(list);
            }
            
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head);
    }
	public Object updateCouponsByStatus(Object data){
        HeadObject head = new HeadObject();
        try {
        	JSONObject content = JSONObject.fromObject(data);
            Integer couponId=content.getInt("couponId");
            String opt=content.getString("opt");
            Coupons coupons= couponsMapper.selectByPrimaryKey(couponId);
            if(opt.equals("enable")){
            	coupons.setCpnsStatus("1");
            }else{
            	coupons.setCpnsStatus("0");
            	HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.deleteCoupons");
    			searchClientService.doServiceByServer(new RequestObject(headObject, JSON.toJSONString(coupons)));
            }
            couponsMapper.updateByPrimaryKeySelective(coupons);
            if(opt.equals("enable")){
            	CouponsDTO couponsDTO=couponsMapper.findById(coupons.getCpnsId());
            	coupons.setFromTime(DateUtils.dateToString(couponsDTO.getFromTime(), DateUtils.NORMALSS_DATE_PATTERN));
            	coupons.setToTime(DateUtils.dateToString(couponsDTO.getToTime(), DateUtils.NORMALSS_DATE_PATTERN));
            	HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.addCoupons");
    			searchClientService.doServiceByServer(new RequestObject(headObject, JSON.toJSONString(coupons)));
            }
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            log.error(e.getMessage());
            head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head);
    }
	/**
	 * 
	 * @Description:保存优惠券 
	 * @author xiaox
	 * @date 2015-4-20 下午1:13:54
	 */
	public Object saveCoupons(Object data) throws BusinessException {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			CouponsDTO dto = (CouponsDTO) JSONObject.toBean(content,CouponsDTO.class);
			
			//1.先添加商品规则
			SalesRuleGoodsWithBLOBs rule = new SalesRuleGoodsWithBLOBs();
			BeanUtils.copyProperties(dto, rule);
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		    Date fromDate = sdf.parse(dto.getFromTimeStr()); 
		    Date toDate = sdf.parse(dto.getToTimeStr());
			rule.setFromTime(fromDate);
			rule.setToTime(toDate);
			rule.setCreateTime(new Date());
			rule.setName("优惠券规则-"+dto.getCpnsName());
			rule.setStatus(dto.getCpnsStatus());
			salesRuleGoodsMapper.insertSelective(rule);
			
			//2.添加优惠券
			Coupons coupons = new Coupons();
			BeanUtils.copyProperties(dto, coupons);
			coupons.setRuleId(rule.getRuleId());
			coupons.setCreateTime(new Date());
			coupons.setBigPic(dto.getBigPic());
			
			if (!dto.getCpnsPrefix().equals("B")) {
				CouponsExample example=new CouponsExample();
				example.createCriteria().andCpnsPrefixEqualTo(dto.getCpnsPrefix());
				List<Coupons> list=couponsMapper.selectByExample(example);
				if (null!=list && list.size()>0) {
					head.setRetCode(ErrorCode.IS_EXIST);
					return new ResultObject(head);
				}
			}else {
				Long temp = new Date().getTime();
				coupons.setCpnsPrefix("B"+temp.toString());
			}
			
			couponsMapper.insertSelective(coupons);
			
			int cpnsId = coupons.getCpnsId();
			String picIds = dto.getPicIds();
			String[] pics = picIds.split(",");
			if(pics != null) {
				int len = pics.length;
				if(len > 0) {
					for(int i=0;i<len;i++) {
						long picId = Long.parseLong(pics[i]);
						CouponsPicShip record = new CouponsPicShip();
						record.setCpnsId(cpnsId);
						record.setPictureId(picId);
						couponsPicShipMapper.insert(record);
					}
				}
			}
			
			//3.插入优惠券的适用范围
			String catAndBrand = dto.getCatAndbrandIds();
			List<CouponsApplyBrand> list = new ArrayList<CouponsApplyBrand>();
			CouponsApplyBrand  temp = new CouponsApplyBrand();
			if(StringUtils.isNotBlank(catAndBrand)){
			    String [] catandBrandIds = catAndBrand.split(",");
			    String[] str = null;
			    for(int i=0;i<catandBrandIds.length; i++ ) {
			        str = catandBrandIds[i].split("\\|");
			        temp = new CouponsApplyBrand();
			        temp.setCouponsId(coupons.getCpnsId());
			        temp.setCatId(Integer.valueOf(str[0]));
    			    temp.setBrandId(Integer.valueOf(str[1]));
    			    list.add(temp);
			    }
			}
			if(list.size()>0){
			    couponsApplyBrandMapper.batchInsert(list);
			}
			if(coupons.getCpnsStatus().equals("1")){
				HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.addCoupons");
				coupons.setFromTime(dto.getFromTimeStr());
				coupons.setToTime(dto.getToTimeStr());
				searchClientService.doServiceByServer(new RequestObject(headObject, JSON.toJSONString(coupons)));
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
			throw new BusinessException(ErrorCode.SPE001);
		}
		return new ResultObject(head);
	}
	
	
	/**
	 * 
	 *@description 删除优惠券
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-4-21
	 *@throws BusinessException
	 */
	public Object deleteCoupons(Object data) throws BusinessException {
        HeadObject head = new HeadObject();
        try {
            JSONObject content = JSONObject.fromObject(data);
            Coupons coupons= couponsMapper.selectByPrimaryKey(content.getInt("couponId"));
            salesRuleGoodsMapper.deleteByPrimaryKey(content.getInt("ruleId"));
            couponsMapper.deleteByPrimaryKey(content.getInt("couponId"));
            //更新索引库
            HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.deleteCoupons");
			searchClientService.doServiceByServer(new RequestObject(headObject, JSON.toJSONString(coupons)));
            head.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
        	log.error("删除优惠券异常："+e);
            head.setRetCode(ErrorCode.FAILURE);
            throw new BusinessException(ErrorCode.SPE001);
        }
        return new ResultObject(head);
    }
	
	/**
	 * @Title:  selectByStoreId  
	 * @Description:  TODO(根据店铺id查询优惠券)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-5 下午1:40:07  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectByStoreId(Object data){
        log.info("start[CouponsService.selectByStoreId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		System.out.println("service..."+data);
		Integer  pageIndex = jsonObject.getInt("pageIndex");
		Integer pageSize = jsonObject.getInt("pageSize");
		PageHelper.startPage(pageIndex, pageSize);
		Long storeId = jsonObject.getLong("storeId");
		CouponsExample example = new CouponsExample();
        example.createCriteria().andStoreIdEqualTo(storeId);
        example.createCriteria().andIssueTypeEqualTo("1");
        example.createCriteria().andCpnsStatusEqualTo("1");
        example.createCriteria().andDisabledEqualTo("0");
		ResultPage<RolesDTO> page = new ResultPage(couponsMapper.selectByExample(example));
		log.info("end[CouponsService.selectByStoreId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject(page));
   }
	
	
	/**
	 * 
	 *@description 获得优惠券的使用范围
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-7-13
	 */
   public Object getCatAndBrand(Object data) throws BusinessException {
    HeadObject head = new HeadObject();
    List<CouponsApplyBrandDTO> list = couponsApplyBrandMapper.findByCouponsId((Integer)data);
    head.setRetCode(ErrorCode.SUCCESS);
    return new ResultObject(head,list);
  }
}