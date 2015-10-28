package com.cnit.yoyo.rmi.shop.service;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.shop.CompanyExperienceMapper;
import com.cnit.yoyo.dao.shop.CompanyMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.dto.CompanyDTO;
import com.cnit.yoyo.model.shop.dto.CompanyExperienceDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("companyExperienceService")
public class CompanyExperienceService {
	
	private static final Log log = LogFactory.getLog(CompanyExperienceService.class);
	    
	
    @Autowired
    private CompanyExperienceMapper companyExperienceMapper;
	
    @Autowired
    private CompanyMapper companyMapper;
	
	/**
	 * 
	 *@description 添加经验值记录
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-1
	 *@param data
	 *@return
	 */
	public Object saveExperience(Object data) {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        CompanyExperienceDTO experienceDto = (CompanyExperienceDTO) JSONObject.toBean(content, CompanyExperienceDTO.class);
        experienceDto.setModifyTime(new Date());
        companyExperienceMapper.insertSelective(experienceDto);
        //修改店铺的经验值
        CompanyDTO dto = new CompanyDTO();
        dto.setEarnest(0d);
        dto.setExperience(experienceDto.getExperience());
        dto.setCompanyId(Long.valueOf(experienceDto.getCompanyId()));
        companyMapper.updateExperienceAndEarnest(dto);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
    }
	
	/**
	 * 
	 *@description 查找记录
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-1
	 *@param data
	 *@return
	 */
	public Object findList(Object data){
        HeadObject head = new HeadObject();
        ResultPage<CompanyExperienceDTO> page = null;
        JSONObject content = JSONObject.fromObject(data);
        CompanyExperienceDTO experienceDto = (CompanyExperienceDTO) JSONObject.toBean(content, CompanyExperienceDTO.class);
        PageHelper.startPage(experienceDto.getPage(), experienceDto.getRows());
        page = new ResultPage<CompanyExperienceDTO>( companyExperienceMapper.findList(experienceDto));
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, JSONObject.fromObject(page));
   }

}
