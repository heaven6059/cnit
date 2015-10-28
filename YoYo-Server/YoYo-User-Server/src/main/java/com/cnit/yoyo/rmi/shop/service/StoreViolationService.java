package com.cnit.yoyo.rmi.shop.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.CompanyMapper;
import com.cnit.yoyo.dao.shop.EarnestMapper;
import com.cnit.yoyo.dao.shop.StoreMapper;
import com.cnit.yoyo.dao.shop.StoreViolationCatMapper;
import com.cnit.yoyo.dao.shop.StoreViolationMapper;
import com.cnit.yoyo.dao.shop.ViolationMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.shop.EarnestWithBLOBs;
import com.cnit.yoyo.model.shop.StoreViolation;
import com.cnit.yoyo.model.shop.StoreViolationCat;
import com.cnit.yoyo.model.shop.StoreViolationCatExample;
import com.cnit.yoyo.model.shop.StoreViolationExample;
import com.cnit.yoyo.model.shop.Violation;
import com.cnit.yoyo.model.shop.dto.EarnestDTO;
import com.cnit.yoyo.model.shop.dto.StoreViolationDTO;
import com.cnit.yoyo.model.shop.dto.StoreViolationQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("storeViolationService")
public class StoreViolationService {

	private static final Log log = LogFactory.getLog(StoreViolationService.class);
    
	@Autowired
	private StoreViolationMapper storeViolationMapper;
	@Autowired
	private StoreViolationCatMapper storeViolationCatMapper;
	@Autowired
	private ViolationMapper violationMapper;
	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private EarnestMapper earnestMapper;
	@Autowired
	private CompanyMapper companyMapper;
	
	/**
	 * @Title:  findSumEarnestByCompanyId  
	 * @Description:  TODO(根据companyId查询违规扣除的保证金总数)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-28 下午4:34:16  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object findSumEarnestByCompanyId(Object data) 
	{
		log.info("start[StoreViolationService.findSumEarnestByCompanyId]");
		Double sumEarnest = storeViolationMapper
				.selectSumEarnestByCompanyId((Long) data);
		log.info("end[StoreViolationService.findSumEarnestByCompanyId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),
				sumEarnest != null ? sumEarnest : 0);
	}
	
	/**
	 * @Title:  selectStoreViolation  
	 * @Description:  TODO(根据条件查询对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-5 下午6:23:55  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectStoreViolation(Object data) 
	{
		log.info("start[StoreViolationService.selectStoreViolation]");
		StoreViolationQryDTO dto = (StoreViolationQryDTO)data;
      	PageHelper.startPage(dto.getPage(), dto.getRows());
      	ResultPage<StoreViolationDTO> page = new ResultPage<StoreViolationDTO>(storeViolationMapper.selectByQryDto(dto));
		log.info("end[StoreViolationService.selectStoreViolation]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONObject.toJSON(page));
	}
	
	/**
	 * @Title:  deleteByIdList  
	 * @Description:  TODO(根据主键删除对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-8 下午1:18:31  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object deleteByIdList(Object data){
    	log.info("start[StoreViolationService.delectByIdList]");
    	List<Long> idList = (List<Long>)data;
    	if(idList!=null&&idList.size()>=1){
    		storeViolationMapper.updateDisabled(idList, 1);
    		StoreViolation storeViolation  = null;
    		Store store = new Store();
    		Date now = new Date(System.currentTimeMillis());
    		for(int i =0 ;i<idList.size();i++){
    			storeViolation = storeViolationMapper.selectByPrimaryKey(idList.get(i));
    			if(storeViolation!=null){
    				//撤销扣除保证金，分数撤销未实现？？删除操作是否撤销扣除保证金？？状态已完成和未完成是否都要撤销扣除保证金？？
    				List<EarnestDTO> earnestList = earnestMapper.selectByStoreViolationId(idList.get(i));
    				if(earnestList!=null && earnestList.size()>=1){
    					for(int j =0;j<earnestList.size();j++){
    						if(earnestList.get(j).getEarnestValue().doubleValue()<0){
    							earnestMapper.deleteByPrimaryKey(earnestList.get(j).getId());
    							companyMapper.decreEarnestById(earnestList.get(j).getCompanyId(), earnestList.get(j).getEarnestValue().doubleValue());
    						}
    					}
    				}
    				//根据分店id查询店铺违规最大值，不存在则恢复数据状态
    				List<StoreViolation> storeViolationList = storeViolationMapper.selectMaxViolationByParams(0, 0, 1, storeViolation.getStoreId(), null, null);
    				if(storeViolationList!=null && storeViolationList.size()>=1){
						store.setStoreId(storeViolationList.get(0).getStoreId());
						if(storeViolationList.get(0).getGoodsEndtime()!=null && storeViolationList.get(0).getGoodsEndtime().after(now)){
							store.setLimitGoods("1");
						}else{
							store.setLimitGoods("0");
						}
						if(storeViolationList.get(0).getGoodsdownEndtime()!=null && storeViolationList.get(0).getGoodsdownEndtime().after(now)){
							store.setLimitGoodsdown("1");
						}else{
							store.setLimitGoodsdown("0");
						}
						if(storeViolationList.get(0).getNewsEndtime()!=null && storeViolationList.get(0).getNewsEndtime().after(now)){
							store.setLimitNews("1");
							store.setLimitNewsValue(storeViolationList.get(0).getNewsValue());
						}else{
							store.setLimitNews("0");
							store.setLimitNewsValue(null);
						}
						if(storeViolationList.get(0).getStoreEndtime()!=null && storeViolationList.get(0).getStoreEndtime().after(now)){
							store.setLimitStore("1");
						}else{
							store.setLimitStore("0");
						}
						if(storeViolationList.get(0).getStoredownEndtime()!=null && storeViolationList.get(0).getStoredownEndtime().after(now)){
							store.setLimitStoredown("1");
						}else{
							store.setLimitStoredown("0");
						}
						if(storeViolationList.get(0).getSalesEndtime()!=null && storeViolationList.get(0).getSalesEndtime().after(now)){
							store.setLimitSales("1");
						}else{
							store.setLimitSales("0");
						}
						storeMapper.updateByPrimaryKeySelective(store);
    				}else{
    					store.setStoreId(storeViolation.getStoreId());
            			store.setLimitGoods("0");
            			store.setLimitGoodsdown("0");
            			store.setLimitNews("0");
            			store.setLimitStore("0");
            			store.setLimitStoredown("0");
            			store.setLimitSales("0");
            			storeMapper.updateByPrimaryKeySelective(store);
    				}
    			}
    		}
    	}
		log.info("end[StoreViolationService.delectByIdList]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
    }
	
	/**
	 * @Title:  saveStoreViolation  
	 * @Description:  TODO(保存店铺违规对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-9 下午6:05:42  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object saveStoreViolation(Object data){
    	log.info("start[StoreViolationService.saveStoreViolation]");
    	JSONObject jsonObject = JSONObject.fromObject(data);
    	List<Integer> storeId = (List<Integer>) jsonObject.get("storeId");
    	List<Integer> catId = (List<Integer>) jsonObject.get("catId"); 
    	String remark = jsonObject.getString("remark");
    	String loginName = jsonObject.getString("loginName");
    	Long now = System.currentTimeMillis();
    	Date date = new Date(now);
    	Store store = null;
    	StoreViolation storeViolation = new StoreViolation();
		storeViolation.setAddTime(date);
		storeViolation.setDisabled(0);
		storeViolation.setdOrder(0);
		storeViolation.setLastModify(date);
		storeViolation.setRemark(remark);
		storeViolation.setStatus(0);
		for(int j = 0 ; j<catId.size() ; j++){
			//查询违规类型
			StoreViolationCat storeViolationCat = storeViolationCatMapper.selectByPrimaryKey(catId.get(j)); 
			if(storeViolationCat!=null && storeViolationCat.getDisabled()==0){
				if(storeViolationCat.getParentId()!=0){
					storeViolation.setCatId(storeViolationCat.getCatId());
					storeViolation.setScore(storeViolationCat.getScore());
					//查询第一级违规类型的违规处理
					List<Violation> violationList = violationMapper.selectByCatId(Integer.parseInt(storeViolationCat.getCatPath().split(",")[1]));
					for(int i = 0 ; i<storeId.size() ; i++){
						store = storeMapper.selectByPrimaryKey(storeId.get(i).longValue());
						if(store!=null){
							storeViolation.setStoreId(store.getStoreId());
							storeViolation.setCompanyId(store.getCompanyId());
							if(violationList != null && violationList.size() >= 1){
								if(violationList.get(0).getGoodsDays()!=null&&violationList.get(0).getGoodsDays()>=1){
									storeViolation.setGoodsStarttime(storeViolation.getAddTime());
									storeViolation.setGoodsEndtime(new Date(now + (violationList.get(0).getGoodsDays()*24*60*60*1000)));
								}else{
									storeViolation.setGoodsStarttime(null);
									storeViolation.setGoodsEndtime(null);
								}
								if(violationList.get(0).getGoodsdownDays()!=null&&violationList.get(0).getGoodsdownDays()>=1){
									storeViolation.setGoodsdownStarttime(storeViolation.getAddTime());
									storeViolation.setGoodsdownEndtime(new Date(now + (violationList.get(0).getGoodsdownDays()*24*60*60*1000)));
								}else{
									storeViolation.setGoodsdownStarttime(null);
									storeViolation.setGoodsdownEndtime(null);
								}
								if(violationList.get(0).getNewsDays()!=null&&violationList.get(0).getNewsDays()>=1){
									storeViolation.setNewsStarttime(storeViolation.getAddTime());
									storeViolation.setNewsEndtime(new Date(now + (violationList.get(0).getNewsDays()*24*60*60*1000)));
								}else{
									storeViolation.setNewsStarttime(null);
									storeViolation.setNewsEndtime(null);
								}
								if(violationList.get(0).getStoreDays()!=null&&violationList.get(0).getStoreDays()>=1){
									storeViolation.setStoreStarttime(storeViolation.getAddTime());
									storeViolation.setStoreEndtime(new Date(now + (violationList.get(0).getStoreDays()*24*60*60*1000)));
								}else{
									storeViolation.setStoreStarttime(null);
									storeViolation.setStoreEndtime(null);
								}
								if(violationList.get(0).getStoredownDays()!=null&&violationList.get(0).getStoredownDays()>=1){
									storeViolation.setStoredownStarttime(storeViolation.getAddTime());
									storeViolation.setStoredownEndtime(new Date(now + (violationList.get(0).getStoredownDays()*24*60*60*1000)));
								}else{
									storeViolation.setStoredownStarttime(null);
									storeViolation.setStoredownEndtime(null);
								}
								if(violationList.get(0).getSalesDays()!=null&&violationList.get(0).getSalesDays()>=1){
									storeViolation.setSalesStarttime(storeViolation.getAddTime());
									storeViolation.setSalesEndtime(new Date(now + (violationList.get(0).getSalesDays()*24*60*60*1000)));
								}else{
									storeViolation.setSalesStarttime(null);
									storeViolation.setSalesEndtime(null);
								}
								storeViolation.setEarnest(violationList.get(0).getEarnestMoney());
								storeViolation.setNewsValue(violationList.get(0).getNewsDaysValue());
								storeViolation.setProcessed(1);
								storeMapper.updateByViolation(violationList.get(0),store);//更新分店
								
							}else{
								storeViolation.setGoodsStarttime(null);
								storeViolation.setGoodsEndtime(null);
								storeViolation.setGoodsdownStarttime(null);
								storeViolation.setGoodsdownEndtime(null);
								storeViolation.setNewsStarttime(null);
								storeViolation.setNewsEndtime(null);
								storeViolation.setStoreStarttime(null);
								storeViolation.setStoreEndtime(null);
								storeViolation.setStoredownStarttime(null);
								storeViolation.setStoredownEndtime(null);
								storeViolation.setSalesStarttime(null);
								storeViolation.setSalesEndtime(null);
								storeViolation.setEarnest(null);
								storeViolation.setNewsValue(null);
								storeViolation.setProcessed(0);
							}
							storeViolationMapper.insertSelective(storeViolation);
							if(violationList != null && violationList.size() >= 1){
								if(storeViolation.getEarnest()!=null&&storeViolation.getEarnest().doubleValue()>0){
									//扣除保证金，扣分未实现
									EarnestDTO earnest = new EarnestDTO();
									earnest.setAddtime(date);
									earnest.setCompanyId(storeViolation.getCompanyId());
									earnest.setEarnestValue(new BigDecimal(0-storeViolation.getEarnest().doubleValue()));
									earnest.setLastModify(date);
									earnest.setOperator(loginName);
									earnest.setReason(storeViolation.getId()+"违规："+storeViolationCat.getCatName());
									earnest.setRemark("违规："+storeViolationCat.getCatName());
									earnest.setSource(4);
									earnestMapper.insertSelective(earnest);
									companyMapper.decreEarnestById(storeViolation.getCompanyId(), storeViolation.getEarnest().doubleValue());
								}
							}
						}else{
//							System.out.println("该店铺不存在！");
							return new ResultObject(new HeadObject(ErrorCode.FAILURE,"所选分店不存在"));
						}
					}
				}else{
//					System.out.println("违规类型不可为一级分类");
					return new ResultObject(new HeadObject(ErrorCode.FAILURE,"违规类型选择有误"));
				}
			}else{
//				System.out.println("违规类型为空!");
				return new ResultObject(new HeadObject(ErrorCode.FAILURE,"违规类型选择有误"));
			}
		}
		log.info("end[StoreViolationService.saveStoreViolation]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
    }
	
}
