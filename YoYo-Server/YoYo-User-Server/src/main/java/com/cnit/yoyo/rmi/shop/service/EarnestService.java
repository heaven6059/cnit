package com.cnit.yoyo.rmi.shop.service;

import java.text.SimpleDateFormat;
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
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.dto.CompanyDTO;
import com.cnit.yoyo.model.shop.dto.CompanyExperienceDTO;
import com.cnit.yoyo.model.shop.dto.EarnestDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("earnestService")
public class EarnestService {
	
	private static final Log log = LogFactory.getLog(EarnestService.class);
	    
	@Autowired
	private EarnestMapper earnestMapper;
	@Autowired
    private CompanyMapper companyMapper;
	
	/**
	 * @Title:  findByCompanyId  
	 * @Description:  TODO(根据companyId查询保证金日志)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-27 下午7:08:36  
	 * @version 1.0.0 
	 * @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object findByCompanyId(Object data) 
	{
		log.info("start[EarnestService.findByCompanyId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		PageHelper.startPage(jsonObject.getInt("pageIndex"),
				jsonObject.getInt("pageSize"));
		// 查询Earnest列表
		Long companyId = jsonObject.getLong("companyId");
//		List<EarnestDTO> earnestList = earnestMapper.selectByCompanyId(companyId);
		ResultPage<EarnestDTO> page = new ResultPage(earnestMapper.selectByCompanyId(companyId));
		if (page != null && page.getRows() != null && page.getRows().size() >= 1) 
		{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < page.getRows().size(); i++) 
			{
				if (page.getRows().get(i).getAddtime() != null) 
				{
					page.getRows().get(i).setAddDate(format.format(page.getRows().get(i).getAddtime()));
				}
				if (page.getRows().get(i).getExpiretime() != null) 
				{
					page.getRows().get(i).setExpireDate(format.format(page.getRows().get(i).getExpiretime()));
				}
				if (page.getRows().get(i).getLastModify() != null) 
				{
					page.getRows().get(i).setLastModifyDate(format.format(page.getRows().get(i).getLastModify()));
				}
				if (page.getRows().get(i).getEarnestValue() != null) 
				{
					page.getRows().get(i).setValue(
							page.getRows().get(i).getEarnestValue().doubleValue());
				}
			}
		}
		log.info("end[EarnestService.findByCompanyId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), page);
	}
	
	/**
	 * @Title:  findSumEarnestByCompanyId  
	 * @Description:  TODO(根据companyId查询保证金缴纳总额)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-4-29 上午9:29:16  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object findSumEarnestByCompanyId(Object data) 
	{
		log.info("start[EarnestService.findSumEarnestByCompanyId]");
		Double sumEarnest = earnestMapper
				.selectSumEarnestByCompanyId((Long) data);
		log.info("end[EarnestService.findSumEarnestByCompanyId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),
				sumEarnest != null ? sumEarnest : 0);
	}
	
	/**
	 * @Title:  findEarnestByCompanyId  
	 * @Description:  TODO(查询保证金余额)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-26 下午8:16:13  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object findEarnestByCompanyId(Object data) 
	{
		log.info("start[EarnestService.findEarnestByCompanyId]");
		Double sumEarnest = earnestMapper.selectEarnestByCompanyId((Long) data);
		log.info("end[EarnestService.findEarnestByCompanyId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), sumEarnest != null ? sumEarnest : 0);
	}
	
	
	/**
     * 
     *@description 添加保证金记录
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-1
     *@param data
     *@return
     */
    public Object saveEarnest(Object data) {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        EarnestDTO edto = (EarnestDTO) JSONObject.toBean(content, EarnestDTO.class);
        edto.setAddtime(new Date());
        edto.setLastModify(new Date());
        edto.setExpiretime(new Date());  //过期时间无效
        earnestMapper.insertSelective(edto);
        //修改店铺的经验值
        CompanyDTO dto = new CompanyDTO();
        dto.setExperience(0);
        dto.setEarnest(edto.getEarnestValue().doubleValue());
        dto.setCompanyId(Long.valueOf(edto.getCompanyId()));
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
    public Object findEarnest(Object data){
        HeadObject head = new HeadObject();
        ResultPage<EarnestDTO> page = null;
        JSONObject content = JSONObject.fromObject(data);
        EarnestDTO experienceDto = (EarnestDTO) JSONObject.toBean(content, EarnestDTO.class);
        PageHelper.startPage(experienceDto.getPage(), experienceDto.getRows());
        page = new ResultPage<EarnestDTO>( earnestMapper.findList(experienceDto));
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, JSONObject.fromObject(page));
   }

}
