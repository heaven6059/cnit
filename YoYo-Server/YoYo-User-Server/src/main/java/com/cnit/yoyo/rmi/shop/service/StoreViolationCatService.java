package com.cnit.yoyo.rmi.shop.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.StoreViolationCatMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.shop.StoreViolationCat;
import com.cnit.yoyo.model.shop.StoreViolationCatExample;
import com.cnit.yoyo.model.shop.StoreViolationCatWithBLOBs;

@Service("storeViolationCatService")
public class StoreViolationCatService {
	
	private static final Log log = LogFactory.getLog(StoreViolationCatService.class);
	
    @Autowired 
    private StoreViolationCatMapper  storeViolationCatMapper;
    
    /**
     * @Title:  selectByParentId  
     * @Description:  TODO(这里用一句话描述这个方法的作用)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-2 下午3:11:30  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectByParentId(Object data) 
	{
		log.info("start[StoreViolationCatService.selectByParentId]");
		Integer parentId = JSONObject.fromObject(data).getInt("parentCatId");
//		StoreViolationCatExample example = new StoreViolationCatExample();
//		example.createCriteria().andParentIdEqualTo(parentId);
//		example.setOrderByClause("p_order");
//		List<StoreViolationCatWithBLOBs> list = storeViolationCatMapper.selectByExampleWithBLOBs(example);
		boolean exist = JSONObject.fromObject(data).getBoolean("exist");
		List<StoreViolationCatWithBLOBs> list = storeViolationCatMapper.selectByParentId(parentId,exist);
		log.info("end[StoreViolationCatService.selectByParentId]");
//		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONArray.toJSON(list));
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(list));
	}
    
    /**
     * @Title:  saveOrUpdateCate  
     * @Description:  TODO(保存或修改违规类型)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-2 下午5:12:03  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    public Object saveOrUpdateCate(Object data) throws Exception {
		log.info("start[StoreViolationCatService.saveOrUpdateCate]");
		StoreViolationCatWithBLOBs cate = (StoreViolationCatWithBLOBs) JSONObject.toBean(JSONObject.fromObject(data), StoreViolationCatWithBLOBs.class);
		StoreViolationCat parentCate = null;
		if(cate.getParentId()!=null&&cate.getParentId()!=0){
			parentCate = storeViolationCatMapper.selectByPrimaryKey(cate.getParentId());// 父级（用户选择的）
			if(parentCate!=null&&parentCate.getCatPath()!=null&&cate.getCatId()!=null&&cate.getCatId()!=0){
				if(parentCate.getCatPath().indexOf(cate.getCatId().toString())>=0){
					return new HeadObject(ErrorCode.IS_EXIST_CASCADE);
				}
			}
		}else{
			cate.setParentId(0);
		}
		//查询同名对象
		StoreViolationCatExample example = new StoreViolationCatExample();
		example.createCriteria().andCatNameEqualTo(cate.getCatName());
		List<StoreViolationCat> temp = storeViolationCatMapper.selectByExample(example);

		if (cate.getCatId() != null && cate.getCatId() != 0) {//修改对象
			if (temp != null && temp.size() > 0 && (!temp.get(0).getCatId().equals(cate.getCatId()))) {
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			//查询更新前的对象
			StoreViolationCatWithBLOBs cateFromDB = storeViolationCatMapper.selectByPrimaryKey(cate.getCatId());// 当前
			if(cateFromDB!=null){
				cate.setAddTime(cateFromDB.getAddTime());
				if(parentCate!=null){
					cate.setCatPath(parentCate.getCatPath() + parentCate.getCatId() + ",");
				}else{
					cate.setCatPath(",");
				}
				cate.setChildCount(cateFromDB.getChildCount());
				cate.setDisabled(cateFromDB.getDisabled());
				cate.setIsLeaf(cateFromDB.getIsLeaf());
				cate.setLastModify(new Date(System.currentTimeMillis()));
				storeViolationCatMapper.updateByPrimaryKeyWithBLOBs(cate);
				if (!cate.getParentId().equals(cateFromDB.getParentId())) {// 父级是否发生变化
					// 更新原来父级子类个数
					if (null != cateFromDB.getParentId() && cateFromDB.getParentId() != 0) {
						StoreViolationCatWithBLOBs updateParent = new StoreViolationCatWithBLOBs();
						updateParent.setCatId(cateFromDB.getParentId());
						StoreViolationCatExample example2 = new StoreViolationCatExample();
						example2.createCriteria().andParentIdEqualTo(cateFromDB.getParentId());
						updateParent.setChildCount(storeViolationCatMapper.countByExample(example2));
						if(updateParent.getChildCount()==0){
							updateParent.setIsLeaf(1);
						}
						storeViolationCatMapper.updateByPrimaryKeySelective(updateParent);
					}
					// 更新用户选择父类子类个数
					if (null != cate.getParentId() && cate.getParentId() != 0) {
						StoreViolationCatWithBLOBs updateParent = new StoreViolationCatWithBLOBs();
						updateParent.setCatId(cate.getParentId());
						StoreViolationCatExample example2 = new StoreViolationCatExample();
						example2.createCriteria().andParentIdEqualTo(cate.getParentId());
						updateParent.setChildCount(storeViolationCatMapper.countByExample(example2));
						if(updateParent.getChildCount()>=1){
							updateParent.setIsLeaf(0);
						}
						storeViolationCatMapper.updateByPrimaryKeySelective(updateParent);
					}
					//更新子集路径
					storeViolationCatMapper.updateCatPath(cateFromDB.getCatPath()+cateFromDB.getCatId()+",", cate.getCatPath()+cate.getCatId()+",");
				}
			}else{
				return new HeadObject(ErrorCode.NO_DATA);
			}
		} else {//新增对象
			if (temp != null && temp.size() > 0) {
				return new HeadObject(ErrorCode.IS_EXIST);
			}
			if (cate.getParentId() == null || cate.getParentId() == 0) {
				cate.setParentId(0);
				cate.setCatPath(",");
			} else {
				cate.setCatPath(parentCate.getCatPath() + parentCate.getCatId() + ",");
			}
			cate.setIsLeaf(1);
			cate.setAddTime(new Date(System.currentTimeMillis()));
			cate.setLastModify(new Date(System.currentTimeMillis()));
			storeViolationCatMapper.insertSelective(cate);
			if (cate.getParentId() != null && cate.getParentId() != 0) {
				// 更新用户选择父类子类个数
				StoreViolationCatWithBLOBs updateParent = new StoreViolationCatWithBLOBs();
				updateParent.setCatId(parentCate.getCatId());
				updateParent.setIsLeaf(0);
				StoreViolationCatExample example2 = new StoreViolationCatExample();
				example2.createCriteria().andParentIdEqualTo(updateParent.getCatId());
				updateParent.setChildCount(storeViolationCatMapper.countByExample(example2));
				storeViolationCatMapper.updateByPrimaryKeySelective(updateParent);
			}
		}
		log.info("end[StoreViolationCatService.saveOrUpdateCate]");
		return new HeadObject(ErrorCode.SUCCESS);
	}
    
    /**
     * @Title:  selectByCatId  
     * @Description:  TODO(根据主键查询违规类型对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-3 上午10:52:22  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectByCatId(Object data) 
	{
		log.info("start[StoreViolationCatService.selectByCatId]");
		StoreViolationCatWithBLOBs violationCat = storeViolationCatMapper.selectByPrimaryKey((Integer)data);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("cate", violationCat);
		log.info("end[StoreViolationCatService.selectByCatId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
	}
    
    /**
     * @Title:  delectByCatId  
     * @Description:  TODO(根据主键删除违规类型对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-3 下午2:24:54  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object delectByCatId(Object data){
    	log.info("start[StoreViolationCatService.delectByCatId]");
    	Integer catId = (Integer)data;
    	if(catId!=null&&catId!=0){
    		StoreViolationCatWithBLOBs violationCat = storeViolationCatMapper.selectByPrimaryKey(catId);
    		if(violationCat!=null){
    			List<StoreViolationCatWithBLOBs> childList = storeViolationCatMapper.selectByParentId(violationCat.getCatId(), true);
    			if(childList!=null&&childList.size()>=1){
    				return new HeadObject(ErrorCode.IS_EXIST_CASCADE);
    			}else{
    				storeViolationCatMapper.deleteByPrimaryKey(violationCat.getCatId());
        			//更新父级
        			if(violationCat.getParentId()!=null&&violationCat.getParentId()!=0){
        				StoreViolationCatWithBLOBs updateParent = new StoreViolationCatWithBLOBs();
            			updateParent.setCatId(violationCat.getParentId());
            			StoreViolationCatExample example2 = new StoreViolationCatExample();
            			example2.createCriteria().andParentIdEqualTo(violationCat.getParentId());
            			updateParent.setChildCount(storeViolationCatMapper.countByExample(example2));
            			if(updateParent.getChildCount()==0){
            				updateParent.setIsLeaf(1);
            			}
            			storeViolationCatMapper.updateByPrimaryKeySelective(updateParent);
        			}
    			}
//    			storeViolationCatMapper.deleteByPrimaryKey(violationCat.getCatId());
//    			//删除子集
//    			StoreViolationCatExample example = new StoreViolationCatExample();
//    			example.createCriteria().andCatPathLike(violationCat.getCatPath()+violationCat.getCatId()+",%");
//    			storeViolationCatMapper.deleteByExample(example);
//    			//更新父级
//    			if(violationCat.getParentId()!=null&&violationCat.getParentId()!=0){
//    				StoreViolationCatWithBLOBs updateParent = new StoreViolationCatWithBLOBs();
//        			updateParent.setCatId(violationCat.getParentId());
//        			StoreViolationCatExample example2 = new StoreViolationCatExample();
//        			example2.createCriteria().andParentIdEqualTo(violationCat.getParentId());
//        			updateParent.setChildCount(storeViolationCatMapper.countByExample(example2));
//        			if(updateParent.getChildCount()==0){
//        				updateParent.setIsLeaf(1);
//        			}
//        			storeViolationCatMapper.updateByPrimaryKeySelective(updateParent);
//    			}
    		}
    	}
		log.info("end[CategoryService.deleteVirtualCategoryByCatId]");
		return new HeadObject(ErrorCode.SUCCESS);
    }
    
    /**
     * @Title:  selectChild  
     * @Description:  TODO(查询除第一级分类外的违规分类)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-9 下午4:50:58  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectChild(Object data) 
	{
		log.info("start[StoreViolationCatService.selectChild]");
		List<StoreViolationCatWithBLOBs> list = storeViolationCatMapper.selectChild();
		log.info("end[StoreViolationCatService.selectChild]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(list));
	}
}
