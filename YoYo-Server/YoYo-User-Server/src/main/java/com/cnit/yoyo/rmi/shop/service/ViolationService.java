package com.cnit.yoyo.rmi.shop.service;

import java.util.Date;
import java.util.List;
import java.util.jar.JarOutputStream;

import javax.enterprise.inject.New;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.StoreViolationCatMapper;
import com.cnit.yoyo.dao.shop.ViolationMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.BaseQryDTO;
import com.cnit.yoyo.model.car.CarFactoryScope;
import com.cnit.yoyo.model.car.dto.CarFactoryScopeQryDTO;
import com.cnit.yoyo.model.shop.StoreViolationCat;
import com.cnit.yoyo.model.shop.StoreViolationCatExample;
import com.cnit.yoyo.model.shop.StoreViolationCatWithBLOBs;
import com.cnit.yoyo.model.shop.Violation;
import com.cnit.yoyo.model.shop.ViolationExample;
import com.cnit.yoyo.model.shop.dto.ViolationDTO;
import com.cnit.yoyo.model.shop.dto.ViolationQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @ClassName: ViolationService  
 * @Description: TODO(店铺违规--违规处理)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-6-3 下午5:08:19  
 * @version 1.0.0
 */
@Service("violationService")
public class ViolationService {

	private static final Log log = LogFactory.getLog(ViolationService.class);
	
    @Autowired 
    private ViolationMapper violationMapper;
    
    @Autowired 
    private StoreViolationCatMapper violationCatMapper;
	
    /**
     * @Title:  selectViolation  
     * @Description:  TODO(查询违规处理)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-3 下午5:15:25  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectViolation(Object data) 
	{
		log.info("start[ViolationService.selectViolation]");
//		List<Violation> list = violationMapper.selectByExample(new ViolationExample());
		ViolationQryDTO dto = (ViolationQryDTO)data;
      	PageHelper.startPage(dto.getPage(), dto.getRows());
      	ResultPage<ViolationDTO> page = new ResultPage<ViolationDTO>(violationMapper.selectByQryDto(dto));
		log.info("end[ViolationService.selectViolation]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page));
	}
    
    /**
     * @Title:  saveOrUpdateViolation  
     * @Description:  TODO(保存或更新违规处理)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-4 下午5:57:21  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object saveOrUpdateViolation(Object data) 
	{
		log.info("start[ViolationService.saveViolation]");
		HeadObject headObject = null;
		Violation violation = (Violation) JSONObject.toBean(JSONObject.fromObject(data), Violation.class);
		//判断违规类型是否存在
		StoreViolationCat cate = violationCatMapper.selectByPrimaryKey(violation.getCatId());
		if(cate!=null){
			//判断该违规类型是否已配置
			ViolationExample example = new ViolationExample();
			example.createCriteria().andCatIdEqualTo(violation.getCatId());
			List<Violation> list = violationMapper.selectByExample(example);
			if(violation.getViolationId()!=null&&violation.getViolationId()>0){//更新
				if(list!=null&&list.size()>=1){
					for(int i = 0 ; i<list.size() ; i++){
						if(!list.get(i).getViolationId().equals(violation.getViolationId())){
							headObject = new HeadObject(ErrorCode.IS_EXIST, "该违规类型的违规处理已存在");
						}
					}
				}
				//更新
				if(headObject==null){
					violation.setDisabled(null);
					violation.setdOrder(null);
					violation.setAddTime(null);
					violation.setLastModify(new Date(System.currentTimeMillis()));
					violationMapper.updateByPrimaryKeySelective(violation);
					headObject = new HeadObject(ErrorCode.SUCCESS);
				}
			}else{//新增
				if(!(list!=null&&list.size()>=1)){
					violation.setAddTime(new Date(System.currentTimeMillis()));
					violation.setLastModify(violation.getAddTime());
					violation.setDisabled(0);
					violation.setdOrder(0);
					violationMapper.insertSelective(violation);
					headObject = new HeadObject(ErrorCode.SUCCESS);
				}else{
					//该违规类型的违规处理已存在
					headObject = new HeadObject(ErrorCode.IS_EXIST, "该违规类型的违规处理已存在");
				}
			}
		}else{
			//违规类型不存在
			headObject = new HeadObject(ErrorCode.NO_DATA, "该违规类型不存在");
		}
		log.info("end[ViolationService.saveViolation]");
		return headObject;
	}
    
    /**
     * @Title:  deleteByIdList  
     * @Description:  TODO(根据主键删除对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-5 上午11:14:52  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object deleteByIdList(Object data){
    	log.info("start[ViolationService.delectByIdList]");
		ViolationExample example = new ViolationExample();
		example.createCriteria().andViolationIdIn((List<Integer>)data);
		violationMapper.deleteByExample(example);
		log.info("end[ViolationService.delectByIdList]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
    }
    
}
