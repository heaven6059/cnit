package com.cnit.yoyo.rmi.shop.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.dao.shop.CompanyMapper;
import com.cnit.yoyo.dao.shop.EarnestMapper;
import com.cnit.yoyo.dao.shop.StoreMapper;
import com.cnit.yoyo.dao.shop.StoreViolationCatMapper;
import com.cnit.yoyo.dao.shop.StoreViolationMapper;
import com.cnit.yoyo.dao.shop.ViolationMapper;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.shop.StoreViolation;
import com.cnit.yoyo.model.shop.StoreViolationCat;
import com.cnit.yoyo.model.shop.StoreViolationExample;
import com.cnit.yoyo.model.shop.Violation;
import com.cnit.yoyo.model.shop.dto.EarnestDTO;

/**
 * @ClassName: ViolationTask  
 * @Description: TODO(违规处理任务类)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-6-26 上午10:22:46  
 * @version 1.0.0
 */
@Service
public class ViolationTask {

	public static final Logger log = LoggerFactory.getLogger(ViolationTask.class);

	@Autowired
	private StoreViolationMapper storeViolationMapper;
	@Autowired
	private ViolationMapper violationMapper;
	@Autowired
	private StoreViolationCatMapper storeViolationCatMapper;
	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private EarnestMapper earnestMapper;
	@Autowired
	private CompanyMapper companyMapper;

	/**
	 * 处理商品违规
	 * @Description:
	 * @param param
	 */
	public void dealStoreViolation(Object data) {
		long start = System.currentTimeMillis();
		try {
			this.updateUnProcessed();
			this.updateProcessed();
		} catch (Exception e) {
			log.error(e.toString());
		}
		long end = System.currentTimeMillis();
		log.info("违规处理时间：-->> " + (end - start) + " 毫秒");
	}
	
	/**
	 * @Title:  updateProcessed  
	 * @Description:  TODO(更新已处理的店铺违规)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-26 下午2:06:26  
	 * @version 1.0.0 
	 * @param 
	 * @return void  返回类型 
	 * @throws
	 */
	private void updateProcessed(){
//		Integer count = storeViolationMapper.selectMaxViolationCountByParams(0, 0, 1, 1);
		Integer count = storeViolationMapper.selectStoreIdCountByParams(0, 0, 1);
//		System.out.println("已处理违规数量："+count);
		if(count>=1){
			Integer pageSize = 100;
			Integer pageCount = 0;
			if(count%pageSize==0){
				pageCount = count/pageSize;
			}else{
				pageCount = count/pageSize + 1;
			}
			//分页查询已处理违规列表
			List<StoreViolation> storeViolationList = null;
			Store store = new Store();
//			StoreViolation storeViolation = new StoreViolation();
			Date now = new Date(System.currentTimeMillis());
			Integer status = 1;
			for(int i =0 ; i < pageCount ; i++){
				storeViolationList = storeViolationMapper.selectMaxViolationByParams(0, 0, 1, null, i*pageSize, pageSize);
				if(storeViolationList!=null && storeViolationList.size()>=1){
					for(int j =0 ; j<storeViolationList.size(); j++){
						status = 1;
						store.setStoreId(storeViolationList.get(j).getStoreId());
						if(storeViolationList.get(j).getGoodsEndtime()!=null && storeViolationList.get(j).getGoodsEndtime().after(now)){
							store.setLimitGoods("1");
							status = 0;
						}else{
							store.setLimitGoods("0");
						}
						if(storeViolationList.get(j).getGoodsdownEndtime()!=null && storeViolationList.get(j).getGoodsdownEndtime().after(now)){
							store.setLimitGoodsdown("1");
							status = 0;
						}else{
							store.setLimitGoodsdown("0");
						}
						if(storeViolationList.get(j).getNewsEndtime()!=null && storeViolationList.get(j).getNewsEndtime().after(now)){
							store.setLimitNews("1");
							store.setLimitNewsValue(storeViolationList.get(j).getNewsValue());
							status = 0;
						}else{
							store.setLimitNews("0");
							store.setLimitNewsValue(null);
						}
						if(storeViolationList.get(j).getStoreEndtime()!=null && storeViolationList.get(j).getStoreEndtime().after(now)){
							store.setLimitStore("1");
							status = 0;
						}else{
							store.setLimitStore("0");
						}
						if(storeViolationList.get(j).getStoredownEndtime()!=null && storeViolationList.get(j).getStoredownEndtime().after(now)){
							store.setLimitStoredown("1");
							status = 0;
						}else{
							store.setLimitStoredown("0");
						}
						if(storeViolationList.get(j).getSalesEndtime()!=null && storeViolationList.get(j).getSalesEndtime().after(now)){
							store.setLimitSales("1");
							status = 0;
						}else{
							store.setLimitSales("0");
						}
						storeMapper.updateByPrimaryKeySelective(store);
						if(status == 1){
							storeViolationMapper.updateStatusByStoreId(storeViolationList.get(j).getStoreId().intValue(), 1);
						}
					}
				}
			}
		}
	}
	
	/**
	 * @Title:  updateUnProcessed  
	 * @Description:  TODO(更新未处理的店铺违规)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-25 下午6:45:53  
	 * @version 1.0.0 
	 * @param @param storeViolation
	 * @return void  返回类型 
	 * @throws
	 */
	private void updateUnProcessed(){
		Integer count = storeViolationMapper.selectMaxViolationCountByParams(0, 0, 0);
//		System.out.println("未处理数量："+count);
		if(count>=1){
			Integer pageSize = 100;
			Integer pageCount = 0;
			if(count%pageSize==0){
				pageCount = count/pageSize;
			}else{
				pageCount = count/pageSize + 1;
			}
			//分页查询未处理的店铺违规列表
			List<StoreViolation> storeViolationList = null;
			Store store = new Store();
			StoreViolation storeViolation = new StoreViolation();
			Date now = new Date(System.currentTimeMillis());
			for(int i =0 ; i < pageCount ; i++){
				storeViolationList = storeViolationMapper.selectByParams(0, 0, 0, i*pageSize, pageSize);
				if(storeViolationList!=null && storeViolationList.size()>=1){
					for(int j =0 ; j<storeViolationList.size(); j++){
						StoreViolationCat storeViolationCat = storeViolationCatMapper.selectByPrimaryKey(storeViolationList.get(j).getCatId());
						if(storeViolationCat!=null && storeViolationCat.getDisabled()==0){
							if(storeViolationCat.getParentId()!=0){
								storeViolation.setId(storeViolationList.get(j).getId());
								storeViolation.setScore(storeViolationCat.getScore());
								//查询第一级违规类型的违规处理
								List<Violation> violationList = violationMapper.selectByCatId(Integer.parseInt(storeViolationCat.getCatPath().split(",")[1]));
//								Store store = storeMapper.selectByPrimaryKey(storeViolation.getStoreId());
								store.setStoreId(storeViolationList.get(j).getStoreId());
//								if(store!=null){
									if(violationList != null && violationList.size() >= 1){
//										System.out.println("storeId...."+store.getStoreId()+"...storeviolationId..."+storeViolation.getId()+"....violationid..."+violationList.get(0).getViolationId());
										if(violationList.get(0).getGoodsDays()!=null&&violationList.get(0).getGoodsDays()>=1){
											storeViolation.setGoodsStarttime(now);
											storeViolation.setGoodsEndtime(new Date(now.getTime() + (violationList.get(0).getGoodsDays()*24*60*60*1000)));
										}else{
											storeViolation.setGoodsStarttime(null);
											storeViolation.setGoodsEndtime(null);
										}
										if(violationList.get(0).getGoodsdownDays()!=null&&violationList.get(0).getGoodsdownDays()>=1){
											storeViolation.setGoodsdownStarttime(now);
											storeViolation.setGoodsdownEndtime(new Date(now.getTime() + (violationList.get(0).getGoodsdownDays()*24*60*60*1000)));
										}else{
											storeViolation.setGoodsdownStarttime(null);
											storeViolation.setGoodsdownEndtime(null);
										}
										if(violationList.get(0).getNewsDays()!=null&&violationList.get(0).getNewsDays()>=1){
											storeViolation.setNewsStarttime(now);
											storeViolation.setNewsEndtime(new Date(now.getTime() + (violationList.get(0).getNewsDays()*24*60*60*1000)));
											storeViolation.setNewsValue(violationList.get(0).getNewsDaysValue());
										}else{
											storeViolation.setNewsStarttime(null);
											storeViolation.setNewsEndtime(null);
											storeViolation.setNewsValue(null);
										}
										if(violationList.get(0).getStoreDays()!=null&&violationList.get(0).getStoreDays()>=1){
											storeViolation.setStoreStarttime(now);
											storeViolation.setStoreEndtime(new Date(now.getTime() + (violationList.get(0).getStoreDays()*24*60*60*1000)));
										}else{
											storeViolation.setStoreStarttime(null);
											storeViolation.setStoreEndtime(null);
										}
										if(violationList.get(0).getStoredownDays()!=null&&violationList.get(0).getStoredownDays()>=1){
											storeViolation.setStoredownStarttime(now);
											storeViolation.setStoredownEndtime(new Date(now.getTime() + (violationList.get(0).getStoredownDays()*24*60*60*1000)));
										}else{
											storeViolation.setStoredownStarttime(null);
											storeViolation.setStoredownEndtime(null);
										}
										if(violationList.get(0).getSalesDays()!=null&&violationList.get(0).getSalesDays()>=1){
											storeViolation.setSalesStarttime(now);
											storeViolation.setSalesEndtime(new Date(now.getTime() + (violationList.get(0).getSalesDays()*24*60*60*1000)));
										}else{
											storeViolation.setSalesStarttime(null);
											storeViolation.setSalesEndtime(null);
										}
										storeViolation.setEarnest(violationList.get(0).getEarnestMoney());
										storeViolation.setProcessed(1);
										storeMapper.updateByViolation(violationList.get(0),store);//更新分店
										storeViolationMapper.updateByPrimaryKeySelective(storeViolation);
										if(storeViolation.getEarnest()!=null&&storeViolation.getEarnest().doubleValue()>0){
											//扣除保证金，扣分未实现
											EarnestDTO earnest = new EarnestDTO();
											earnest.setAddtime(now);
											earnest.setCompanyId(storeViolationList.get(j).getCompanyId());
											earnest.setEarnestValue(new BigDecimal(0-storeViolation.getEarnest().doubleValue()));
											earnest.setLastModify(now);
											earnest.setOperator("");
											earnest.setReason(storeViolation.getId()+"违规："+storeViolationCat.getCatName());
											earnest.setRemark("违规："+storeViolationCat.getCatName());
											earnest.setSource(4);
											earnestMapper.insertSelective(earnest);
											companyMapper.decreEarnestById(storeViolationList.get(j).getCompanyId(), storeViolation.getEarnest().doubleValue());
										}
									}
//								}
							}
						}
					}
				}
			}
		}
	}
	

}
