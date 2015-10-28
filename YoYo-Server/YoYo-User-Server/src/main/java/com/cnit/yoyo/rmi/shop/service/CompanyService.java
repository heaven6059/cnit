package com.cnit.yoyo.rmi.shop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.member.ClerkMapper;
import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.dao.shop.CompanyCheckCatMapper;
import com.cnit.yoyo.dao.shop.CompanyMapper;
import com.cnit.yoyo.dao.shop.ShopBrandMapper;
import com.cnit.yoyo.dao.shop.ShopScopeMapper;
import com.cnit.yoyo.dao.shop.StoreMapper;
import com.cnit.yoyo.dao.system.SiteRoleMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Company;
import com.cnit.yoyo.model.goods.CompanyAndStoreBean;
import com.cnit.yoyo.model.goods.CompanyWithBLOBs;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.goods.dto.CompanyDTO;
import com.cnit.yoyo.model.member.Clerks;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.shop.ShopGrade;
import com.cnit.yoyo.model.shop.ShopScope;
import com.cnit.yoyo.model.shop.dto.ShopDetailInfoDTO;
import com.cnit.yoyo.model.system.SiteRole;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description 店铺service
 * @version 1.0.0
 */
@Service("companyService")
public class CompanyService {
	private static final Log log = LogFactory.getLog(CompanyService.class);
	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private StoreMapper storeMapper;

	@Autowired
	private ShopBrandMapper shopBrandMapper;

	@Autowired
	private ShopScopeMapper shopScopeMapper;

    @Autowired
    private ClerkMapper clerksMapper;
    @Autowired
    private SiteRoleMapper siteRoleMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private CompanyCheckCatMapper companyCheckCatMapper;
    
    @Autowired
    private RemoteService itemService;

	/**
	 * 
	 * @Title: saveShop
	 * @Description: 保存店铺
	 * @param @param data
	 * @param @return 设定文件
	 * @author xiaox
	 * @date 2015-3-24 下午2:22:30
	 */
	public Object saveShop(Object data) {
		JSONObject content = JSONObject.fromObject(data);
		Store store = new Store();
		CompanyDTO company = (CompanyDTO) JSONObject.toBean(content.getJSONObject("company"), CompanyDTO.class);
	    company.setLastModify(DateUtils.getCurrentDate(null));  //在sql中转换为date
	    company.setApplyTime(company.getLastModify());
	    if(company.getApproved().equals("-1")){ //提交了基本信息，但没有提交图片资料，状态为删除
	        company.setDisabled("1");
	        //store.setDisabled(company.getDisabled());
	    }
		companyMapper.insertSelective(company);

		
		store.setAccountId(company.getAccountId());
		store.setCompanyName(company.getCompanyName());
		store.setShopName(company.getShopName());
		store.setStoreIdcard(company.getStoreIdcard());
		store.setStoreIdcardname(company.getStoreIdcardname());
		store.setStoreName(company.getStoreName());
		store.setTel(company.getTel());
		store.setLastModify(new Date());
		store.setArea(company.getCompanyCarea());  //首个入驻分店默认是公司联系地址
		store.setAddr(company.getCompanyCaddr());
		store.setAreaIds(company.getCompanyCareaIds());
		store.setCompanyId(company.getCompanyId());
		storeMapper.insertSelective(store);

		/*2015.06.09 去掉品牌申请// 店铺品牌 2015.03.24 xiaox
		ShopBrandWithBLOBs record = new ShopBrandWithBLOBs();
		if (company.getBrandId() == 0) { // 不是选择已有品牌，而是新申请品牌
			record.setBrandName(company.getBankName());
			record.setBrandKeywords(company.getBrandAlias());
			record.setStatus(GlobalStatic.NEW_BRAND_USER);
			record.setType(GlobalStatic.NEW_BRAND_USER);
		} else { // 选择已有的品牌
			record.setStatus(GlobalStatic.NEW_BRAND_USER); // 状态：待审核
			record.setType(GlobalStatic.OLD_BRAND_USER);
			record.setBrandId(company.getBrandId());
		}
		record.setCompanyId(company.getCompanyId());
		shopBrandMapper.insertSelective(record);*/
		// 插入店铺的经营范围
		JSONArray arr = content.getJSONArray("goodCateIds");
		ShopScope scope = new ShopScope();
		scope.setCompanyId(company.getCompanyId());
		for (int i = 0; i < arr.size(); i++) {
			scope.setCatId(arr.getInt(i));
			shopScopeMapper.insertSelective(scope);
		}
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), company.getCompanyId());

	}

	// 更新图片的路径
	public Object updateShopData(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			CompanyDTO company = (CompanyDTO) JSONObject.toBean(content, CompanyDTO.class);
			company.setDisabled("0");
			companyMapper.updateShopById(company);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			head.setRetCode(ErrorCode.FAILURE);
		}

		return head;
	}
	
	/**
	 * 查找主店名称和ID作为页面下拉框数据
	 * ssd
	 * @param data
	 * @return
	 */
	public Object findSelect(Object data){
   	 HeadObject head = new HeadObject();
   	 List<Company> list = null;
        try{
            list =  companyMapper.findSelect();
            head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){    
       	 e.printStackTrace();
            head.setRetCode(ErrorCode.FAILURE);
        }       
        //JSONArray ja = JSONArray.fromObject(list);
        return new ResultObject(head, list);
   }

	/**
	 * 
	 * @Title: updateShop
	 * @Description: 更新部分属性(店铺基本信息功能)
	 * @param @param data
	 * @param @return 设定文件
	 * @author xiaox
	 * @date 2015-3-20 上午10:37:55
	 */
	public Object updateShopInfo(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			CompanyWithBLOBs company = (CompanyWithBLOBs) JSONObject.toBean(content, CompanyWithBLOBs.class);

			companyMapper.updateShopInfoById(company);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage());
			head.setRetCode(ErrorCode.FAILURE);
		}

		return new ResultObject(head);
	}

	/**
	 * 
	 * @Title: findByIdcard
	 * @Description: 根据法人身份证号码查询店铺
	 * @param @param data
	 * @param @return 设定文件
	 * @author xiaox
	 * @date 2015-3-19 下午4:45:09
	 */
	public Object findByIdcard(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
 			CompanyDTO dto = new CompanyDTO();
 			dto.setCompanyIdcard(content.getString("idcard"));
 			System.out.println(content.get("companyId").equals("null"));
 			if(!content.get("companyId").equals("null")){
 			    dto.setCompanyId(content.getLong("companyId"));
 			}
			int count = companyMapper.findByIdcard(dto);
			if (count == 0) {
				head.setRetCode(ErrorCode.SUCCESS);
			} else {
				head.setRetCode(ErrorCode.IS_EXIST);
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			head.setRetCode(ErrorCode.FAILURE);
		}

		return new ResultObject(head);
	}

	/**
	 * 
	 * @Title: findById
	 * @Description: 根据店铺id查找店铺
	 * @param @param data
	 * @param @return 设定文件
	 * @author xiaox
	 * @date 2015-3-19 下午4:46:31
	 */
	public Object findById(Object data) {
		HeadObject head = new HeadObject();
		CompanyDTO dto = null;
		try {
			JSONObject content = JSONObject.fromObject(data);
			dto = companyMapper.findById(content.getLong("companyId"));

			head.setRetCode(ErrorCode.SUCCESS);

		} catch (Exception e) {
			log.error(e.getMessage());
			head.setRetCode(ErrorCode.FAILURE);
		}

		return new ResultObject(head, JSONObject.fromObject(dto));
	}

	/**
	 * 
	 * @description 根据店铺id查找店铺的等级：单店，集团
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-15
	 * @param data
	 * @return
	 */
	public Object findGradeById(Object data) {
		HeadObject head = new HeadObject();
		ShopGrade dto = null;
		try {
			JSONObject content = JSONObject.fromObject(data);
			dto = companyMapper.findGradeById(content.getLong("companyId"));

			head.setRetCode(ErrorCode.SUCCESS);

		} catch (Exception e) {
			log.error(e.getMessage());
			head.setRetCode(ErrorCode.FAILURE);
		}

		return new ResultObject(head, JSONObject.fromObject(dto));
	}

	/**
	 * 
	 * @description 根据公司ID查询公司及分店信息
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-15
	 * @param data
	 * @return
	 */
	public CompanyAndStoreBean selectCompanyAndStoreById(Object data) {
		CompanyAndStoreBean bean = companyMapper.selectCompanyAndStoreById((Long) data);
		return bean;
	}
	
	/**
	 * 
	 *@description 根据店铺等级查找店铺
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-5-26
	 *@param data
	 *@return
	 */
	public Object findCompanyByGradeId(Object data){
	    Integer [] shopGradeIds =  (Integer[]) data;
	    return companyMapper.findCompanyByGradeId(shopGradeIds);
	}
	
	/**
	 * 
	 *@description 根据条件查询店铺列表
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-5-27
	 *@param data
	 *@return
	 */
	public Object findShopListByParam(Object data) {
        HeadObject head = new HeadObject();
        ResultPage<CompanyDTO> page = null;
        JSONObject content = JSONObject.fromObject(data);
        CompanyDTO company = (CompanyDTO) JSONObject.toBean(content, CompanyDTO.class);
        PageHelper.startPage(company.getPage(), company.getRows());
        page = new ResultPage<CompanyDTO>(companyMapper.findShopListByParam(company));
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head,JSONObject.fromObject(page));
    }
	
	/**
	 * 
	 *@description 查找店铺的详情
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-5-29
	 *@param data
	 *@return
	 */
	public Object findShopDetailByCompanyId(Object data){
	    HeadObject head = new HeadObject();
	    CompanyDTO dto = null;
        dto = companyMapper.findShopDetailByCompanyId((Integer)data);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, JSONObject.fromObject(dto));
	}
	
	/**
	 * 
	 *@description 批量更新店铺审核状态
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-1
	 *@param data
	 *@return
	 */
   public Object updateShop(Object data) {
        HeadObject head = new HeadObject();
        @SuppressWarnings("unchecked")
        Map<String,Object> map = (Map<String,Object>)data;
        Integer[] ids = (Integer[]) map.get("ids");
        Integer[] accountIds = (Integer[]) map.get("accountIds");
        String[] issueTypes = (String[]) map.get("issueTypes");
        Integer[] goodCategory = (Integer[]) map.get("goodCategory");
        String approved = map.get("approved").toString();
        if(goodCategory!=null){ //需要审核商品
            map.put("isCheck",1);
        }else{
            map.put("isCheck",0);
        }
        if(approved.equals("2")){ //审核不通过,删除，TODO 邮件通知
            map.put("disabled", "1");
            //同时删除分店
            storeMapper.deleteAllByCompanyId(ids);
        }else{
            map.put("disabled", "0");
        }
        companyMapper.updateShop(map);
        //给新店铺赋值角色：查找店铺角色id,与会员id
        if((GlobalStatic.SHOP_CHECK_PASS).equals(approved)){
            SiteRole companyRole = siteRoleMapper.findByRolename(GlobalStatic.ROLE_SHOP_COMPANY);  //集团
            SiteRole storeRole = siteRoleMapper.findByRolename(GlobalStatic.ROLE_SHOP_STORE);    //单店
            List<Store> store=null;
            Clerks clerk = null;
            List<Member> memberList = null;
            Member member = new Member();
            for(int i=0;i<ids.length;i++){
                store = storeMapper.findShopByCompanyId(Long.valueOf(ids[i])); //获得分店id
                if(store!=null && store.size()>0){
                    clerk = new Clerks();
                    member.setAccountId(accountIds[i]);
                    memberList = memberMapper.findByMember(member);  //查找会员id
                    if(memberList!=null &&memberList.size()>0){
                        clerk.setMemberId(memberList.get(0).getMemberId().longValue());
                    }
                    clerk.setStoreId(store.get(0).getStoreId());
                    clerk.setCommanyId(Long.valueOf(ids[i]));
                    if((GlobalStatic.SHOP_TYPE_COMPANY).equals(issueTypes[i])){
                        clerk.setRoleId(Long.valueOf(companyRole.getRoleId()));
                    }else{
                        clerk.setRoleId(Long.valueOf(storeRole.getRoleId()));
                    }
                    clerk.setType(GlobalStatic.SHOP_TYPE); //店主
                    clerk.setLastModify(DateUtils.getCurrentDate(null));
                    clerksMapper.insertSelective(clerk);
                }
                
                //插入该店铺需要商品审核的分类id
                if(goodCategory!=null){
                    Map<String,Object> cmap = new HashMap<String,Object>();
                    cmap.put("companyId", ids[i]);
                    cmap.put("goodCategory",goodCategory);
                    companyCheckCatMapper.save(cmap);
                }
            }
        }
       
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
    }
   
   /**
    * 
    *@description 删除
    *@detail <方法详细描述>
    *@author <a href="xiaoxiang@cnit.com">肖湘</a>
    *@version 1.0.0
    *@data 2015-6-1
    *@param data
    *@return
    */
   public Object deleteShop(Object data) {
       HeadObject head = new HeadObject();
       @SuppressWarnings("unchecked")
       Map<String,Object> map = (Map<String,Object>)data;
       //1.先删除店铺商品是否需要审核的表记录
       companyCheckCatMapper.deleteByCompanyIds((Integer[])map.get("ids"));
       //2.删除店主的卖家角色（不能进入卖家中心）
       clerksMapper.deleteByCompanyId((Integer[])map.get("ids"));
       //3.下架所有商品
       HeadObject headObject = CommonHeadUtil.geneHeadObject("goodsService.downGoodsByCompanyId");
       itemService.doServiceByServer(new RequestObject(headObject,(Integer[])map.get("ids")));
       companyMapper.deleteShop(map);
       head.setRetCode(ErrorCode.SUCCESS);
       return new ResultObject(head);
   }
   
   /**
	 * 根据公司ID及地区查询分店列表
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object selectStoreByArea(Object data){
		log.info("CompanyService.selectStoreByArea》》》》》》》》》》》》start");
	    HeadObject head = new HeadObject();
		Map<String,String> map = (HashMap<String, String>)data;
		Long companyId = Long.valueOf(map.get("companyId"));
		CompanyDTO dto = new CompanyDTO();
		dto.setCompanyId(companyId);
		List<Store> list = new ArrayList<Store>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try{
			ShopGrade grade = companyMapper.findGradeById(companyId);
			String issueType = grade.getIssueType();
			//如果是单店就去除地域限制，直接将分店查出来
			if("0".equals(issueType)){
				dto.setCompanyArea("");
			}else{
				dto.setCompanyArea(map.get("area"));
			}
			list = companyMapper.selectStore(dto);
			resultMap.put("storeList", list);
			resultMap.put("issueType", issueType);
			resultMap.put("area", map.get("area"));
		}catch(Exception e){
			log.error(e);
			head.setRetCode(ErrorCode.FAILURE);
		}
		head.setRetCode(ErrorCode.SUCCESS);
		log.info("CompanyService.selectStoreByArea》》》》》》》》》》》》end");
		return new ResultObject(head,resultMap);
	}
	
	/**
	 * 
	 *@description 更新店铺有效期
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-8
	 *@param data
	 *@return
	 */
     public Object updateShopTime(Object data) {
        HeadObject head = new HeadObject();
        @SuppressWarnings("unchecked")
        Map<String,Object> map = (Map<String,Object>)data;
        companyMapper.updateShopTime(map);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
     }
     
     /**
      * 
      *@description 查找店铺商品是否需要审核
      *@detail <方法详细描述>
      *@author <a href="xiaoxiang@cnit.com">肖湘</a>
      *@version 1.0.0
      *@data 2015-6-11
      *@param data
      *@return
      */
     public Object findCheckById(Object data) {
         HeadObject head = new HeadObject();
         CompanyDTO dto = null;
         JSONObject content = JSONObject.fromObject(data);
         dto = companyMapper.findCheckById(content.getLong("companyId"));
         head.setRetCode(ErrorCode.SUCCESS);
         return new ResultObject(head, JSONObject.fromObject(dto));
     }
     
 	/**
 	  * @description <b>获取店铺首页信息</b>
 	  * @author 王鹏
 	  * @version 1.0.0
 	  * @data 2015-6-10
 	  * @param @param data
 	  * @param @return
 	  * @return Object
 	*/
 	public Object findShopIndexInfo(Object data) {
 		HeadObject head = new HeadObject();
 		CompanyDTO dto = null;
 		try {
 			dto = companyMapper.findById((Long) data);
 			head.setRetCode(ErrorCode.SUCCESS);
 		} catch (Exception e) {
 			log.error(e.getMessage());
 			head.setRetCode(ErrorCode.FAILURE);
 		}
 		return new ResultObject(head, dto);
 	}
 	
 	/**
 	 * 
 	 *@description 查询店铺是否禁止发布商品
 	 *@detail <方法详细描述>
 	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 	 *@version 1.0.0
 	 *@data 2015-6-12
 	 *@param data
 	 *@return
 	 */
 	public Object findStoreForbidden(Object data){
 	   HeadObject head = new HeadObject();
       int isForbidden = companyMapper.findStoreForbidden(Long.valueOf(data.toString()));
       head.setRetCode(ErrorCode.SUCCESS);
       return new ResultObject(head, isForbidden);
 	}
 	
 	/**
 	 * 
 	 *@description 更新店铺所有的信息
 	 *@detail <方法详细描述>
 	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 	 *@version 1.0.0
 	 *@data 2015-6-15
 	 *@param data
 	 *@return
 	 */
 	public Object updateShopAllInfo(Object data) {
 	   HeadObject head = new HeadObject();
 	   JSONObject content = JSONObject.fromObject(data);
 	   //更新店铺
       CompanyDTO company = (CompanyDTO) JSONObject.toBean(content.getJSONObject("company"), CompanyDTO.class);
       companyMapper.updateShopAllInfo(company);
       //更新分店,店铺信息不能更新，多个分店不好处理
     /*  Store store = new Store();
       store.setAccountId(company.getAccountId());
       store.setCompanyName(company.getCompanyName());
       store.setShopName(company.getShopName());
       store.setStoreIdcard(company.getStoreIdcard());
       store.setStoreIdcardname(company.getStoreIdcardname());
       store.setStoreName(company.getStoreName());
       store.setTel(company.getTel());
       store.setLastModify(DateUtils.parser(company.getLastModify()));
       store.setCompanyId(company.getCompanyId());
       storeMapper.insertSelective(store);
       
       */
    // 更新店铺的经营范围，先删除
       shopScopeMapper.deleteByCompanyId(company.getCompanyId());
       JSONArray arr = content.getJSONArray("goodCateIds");
       ShopScope scope = new ShopScope();
       scope.setCompanyId(company.getCompanyId());
       for (int i = 0; i < arr.size(); i++) {
           scope.setCatId(arr.getInt(i));
           shopScopeMapper.insertSelective(scope);
       }
       head.setRetCode(ErrorCode.SUCCESS);
       return new ResultObject(head);
 	    
 	}
 	
 	/**
 	 * @Title:  selectByPrimaryKey  
 	 * @Description:  TODO(根据主键查询集团对象)  
 	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 	 * @date 2015-6-23 下午1:46:17  
 	 * @version 1.0.0 
 	 * @param @param data
 	 * @param @return
 	 * @return Object  返回类型 
 	 * @throws
 	 */
 	public Object selectByPrimaryKey(Object data){
 		CompanyWithBLOBs company =  companyMapper.selectByPrimaryKey((Long)data);
 		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), company);
  	}
 	
 	/**
 	 * 查找店铺是否超出有效期
 	 *@description <一句话方法简述>
 	 *@detail <方法详细描述>
 	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 	 *@version 1.0.0
 	 *@data 2015-6-26
 	 *@param data
 	 *@return
 	 */
 	public Object findEnabled(Object data){
 	   HeadObject head = new HeadObject();
 	   Integer id = (Integer)data;
 	   int isEnabled=companyMapper.findEnabled(id);
       head.setRetCode(ErrorCode.SUCCESS);
       return new ResultObject(head, isEnabled);
 	}
 	
 	
 	/**
 	 * 
 	 *@description 判断账号是否关联集团
 	 *@detail <方法详细描述>
 	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 	 *@version 1.0.0
 	 *@data 2015-7-13
 	 *@param data
 	 *@return
 	 */
 	public Object findByAccountId(Object data) {
        HeadObject head = new HeadObject();
        int count = companyMapper.findByAccountId((Integer)data);
        if (count == 0) {
            head.setRetCode(ErrorCode.SUCCESS);
        } else {
            head.setRetCode(ErrorCode.IS_EXIST);
        }

        return new ResultObject(head);
    }
 	
  /**
   * 查询商家详情
   * @param data
   * @return
   */
   public Object findShopDetail(Object data) {
       HeadObject head = new HeadObject();
       ShopDetailInfoDTO s = companyMapper.findShopDetail((Long)data);
       head.setRetCode(ErrorCode.SUCCESS);
       return new ResultObject(head,s);
   }
}
