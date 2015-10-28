package com.cnit.yoyo.thirdaccount.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.taoping.jsonrpc.core.dto.ReqParamsObj;
import cn.taoping.jsonrpc.core.dto.ResponseObj;
import cn.taoping.mainserver.service.IMainService;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.constant.ThirdAccountConstants;
import com.cnit.yoyo.dao.member.ClerkMapper;
import com.cnit.yoyo.dao.member.MemberLevelMapper;
import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.dao.member.MemberServiceMapper;
import com.cnit.yoyo.dao.member.PamAccountMapper;
import com.cnit.yoyo.dao.member.PamLogSiteMapper;
import com.cnit.yoyo.dao.shop.CompanyMapper;
import com.cnit.yoyo.dao.shop.StoreMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.domain.member.MemberQueryDo;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.member.Clerks;
import com.cnit.yoyo.model.member.MemberLevel;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.MemberWithBLOBs;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.member.PamLogSite;
import com.cnit.yoyo.model.shop.ShopGrade;
import com.cnit.yoyo.model.thirdaccount.ThirdAccount;
import com.cnit.yoyo.rmi.member.service.LoginAuthoriedService;
import com.cnit.yoyo.rmi.member.service.OauthService;
import com.cnit.yoyo.rmi.system.service.impl.UserServiceImpl;
import com.cnit.yoyo.thirdaccount.dao.ThirdAccountMapper;
import com.cnit.yoyo.util.StringUtil;

/**
 *@Description:  第三方账号服务层
 *@Author:yangyi
 *@Date:2015年6月2日  
 *@Version:1.0.0
 */
@Service("thirdAccountService")
public class ThirdAccountService {
	
	public static final Logger logger = LogManager.getLogger(ThirdAccountService.class);

    @Autowired
    private OauthService oauthService;
    @Autowired
    PamAccountMapper accountMapper;
    @Autowired
    ThirdAccountMapper thirdAccountMapper;
    @Autowired
    LoginAuthoriedService loginAuthoriedService;
    @Autowired
    private MemberServiceMapper memberServiceMapper;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PamLogSiteMapper pamLogSiteMapper;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private ClerkMapper clerkMapper;
    @Autowired
    private PamAccountMapper pamAccountMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Resource
	private IMainService mainService;
   /**
    * @Description查询第三方账号信息，存在则返回第三方信息，不存在则新建账号，并且和第三方绑定
    * @param data
    * @return
 * @throws Exception 
    */
    public Object findAndUpdateThirdAccountByParam(Object data) throws Exception{
    	JSONObject dto = JSONObject.fromObject(data);
    	ThirdAccount thirdAccountdto = (ThirdAccount) JSONObject.toBean(dto,ThirdAccount.class);
    	ThirdAccount thirdAccount = thirdAccountMapper.selectByThirdAccountCode(thirdAccountdto);
    	if(thirdAccount == null){
    		//创建优优账号,会员信息，账户信息
    		this.saveMember(data);
    	}
    	//返回相应的账户信息
    	return doLogin(data);
    }
    
    /**
     * @Description:通知用户中心注册用户
     * @param thirdAccount
     * @return
     */
    public ResponseObj sendUserInfoToTaoPing(JSONObject data){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAlias", data.get("loginName"));//用户名(别名) String类型
//		map.put("realName", String);//真实姓名 String类型
		map.put("password", data.get("loginPassword"));//密码 String类型
		ReqParamsObj reqparam = new ReqParamsObj();
		reqparam.setParamMap(map);
		ResponseObj result = this.mainService.regUserForAlias(reqparam);
		if (result == null) {
			result=new ResponseObj();
			result.setMsg("通知用户中心失败。");
			result.setSuccess(false);
		}else if (result != null && result.isSuccess()) {
			result.setMsg("通知用户中心成功。");
			result.setSuccess(true);
		}
		return result;
    }
    
    public Object doLogin(Object data) throws Exception {
        JSONObject content = JSONObject.fromObject(data);
        // 根据注册账户名称类型查询邮箱 手机 用户名是否被注册
        String loginName = (String) content.get("loginName");
        MemberQueryDo queryDo = new MemberQueryDo();
        queryDo.setLoginName(loginName);
        List<MemberListDo> memberList = memberServiceMapper.findMembersInfoByParam(queryDo);
        if (memberList == null || memberList.isEmpty()) {
            return new ResultObject(new HeadObject("PDE002"), null);
        } else {
        	MemberListDo memberDo = memberList.get(0);
        	
            PamAccount pamAccount = pamAccountMapper.selectByPrimaryKey(memberDo.getAccountId());
            int loginCount = pamAccount.getLoginCount() == null ? 1 : pamAccount.getLoginCount() + 1;
            pamAccount.setLoginIp(content.getString("loginIp"));
            pamAccount.setLoginCount(loginCount);
            pamAccountMapper.updateByPrimaryKeySelective(pamAccount);
            
            String accountType = pamAccount.getAccountType();
            //如果是商家登录店铺Id分店Id
            if(accountType.startsWith("11")){
            	List<Store> stores = storeMapper.selectByAccountId(memberDo.getAccountId());
            	if(stores != null && stores.size() >0){
            		memberDo.setCompanyId(stores.get(0).getCompanyId());
            		memberDo.setStoreId(stores.get(0).getStoreId());
            		memberDo.setCompanyName(stores.get(0).getCompanyName());
            		ShopGrade grade=companyMapper.findGradeById(memberDo.getCompanyId());   //查找店铺等级
            		memberDo.setGradeType(grade.getIssueType());
            	}
            }
            if(accountType.startsWith("12")){//店员账户
            	Clerks clerks = clerkMapper.getStoreMemByMemid(Long.valueOf(memberDo.getMemberId()));
            	memberDo.setCompanyId(clerks.getCommanyId());
        		memberDo.setStoreId(clerks.getStoreId());
        		memberDo.setCompanyName(clerks.getCompanyName());
        		ShopGrade grade=companyMapper.findGradeById(memberDo.getCompanyId());   //查找店铺等级
                memberDo.setGradeType(grade.getIssueType());
            }
            return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(memberDo));
        }
    }
    /**
     * @description 会员注册
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-9
     * @param data
     * @return
     * @throws Exception
     */
    public void saveMember(Object data) throws Exception {
        JSONObject content = JSONObject.fromObject(data);
        // 根据注册账户名称类型查询邮箱 手机 用户名是否被注册
        MemberQueryDo queryDo = new MemberQueryDo();
        queryDo.setLoginName((String) content.get("loginName"));
        queryDo.setEmail((String) content.get("email"));
        queryDo.setMobile((String) content.get("mobile"));
        List<MemberListDo> memberList = memberServiceMapper.findMembersInfoByParam(queryDo);
        if (memberList == null || memberList.isEmpty()) {
            // pamAccount数据要求
        	PamAccount pa = new PamAccount();
          //加密密码
            pa.setAccountType((String) content.get("yyAccountType"));
            pa.setLoginName((String) content.get("loginName"));
            pa.setLoginPassword((String) content.get("loginPassword"));
            pa.setAccountStatus(GlobalStatic.ACCOUNT_STATUS_ON);
            pa.setRegIp((String) content.get("regIp"));
            pa.setRegType((String) content.get("regType"));
            pa.setRegTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            pa.setSource((String) content.get("source"));
            // MemberWithBLOBs数据要求
            MemberWithBLOBs member = new MemberWithBLOBs();
            member.setMobile((String) content.get("mobile"));
            member.setEmail((String) content.get("email"));
            member.setChannel((String) content.get("source"));
            if (!StringUtil.isEmpty(member.getMobile())) {
                member.setState(GlobalStatic.MEMBER_STATUS_MOBILE);
            }
            if (!StringUtil.isEmpty(member.getEmail())) {
                member.setState(GlobalStatic.MEMBER_STATUS_EMAIL);
            }
            // 默认等级
            MemberLevel memberLevel = memberLevelMapper.findByDefault(GlobalStatic.MEMBER_LEVEL_TYPE_BUYER);
            member.setMemberLvId(memberLevel.getMemberLvId());
            // PamLogSite数据要求
            PamLogSite pls = new PamLogSite();
            pls.setEventId(UUID.randomUUID().toString());
            pls.setEventType("新增帐号");
            pls.setEventTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            pls.setEventData(content.toString());
            // 保存新注册的账户信息
            userServiceImpl.createUser(pa);
            // 保存新注册的会员信息
            member.setAccountId(pa.getAccountId());
            ResponseObj resp=sendUserInfoToTaoPing(content);
        	if(resp.isSuccess()){
        		member.setUserCenterId(Integer.valueOf(resp.getResult() != null ? resp.getResult().toString():""));
        		member.setRegUserCenter(ThirdAccountConstants.ACCOUNT_REGCENTER_0);
        	}
            memberMapper.insert(member);
            // 保存日志流水
            pls.setAccountId(pa.getAccountId());
            pamLogSiteMapper.insert(pls);
            //保存第三方账户和YOYO账户的关联
            ThirdAccount thirdAccount=new ThirdAccount();
            thirdAccount.setOpenId((String)content.get("openId"));
            thirdAccount.setAccountType((String)content.get("accountType"));
            thirdAccount.setAccountName(content.get("accountName") == null ? "" :content.get("accountName").toString());
            thirdAccount.setPamAccountId(pa.getAccountId());
            thirdAccount.setAccountStatus(ThirdAccountConstants.ACCOUNT_STATUS_1);//启用
            thirdAccount.setRegTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            thirdAccount.setSource(ThirdAccountConstants.ACCOUNT_SOURCE_10);//web前端
            thirdAccount.setUpdateTime(new Date());
            thirdAccount.setCreateTime(new Date());
            //创建第三方账号和优优账号的关系
    		thirdAccountMapper.insert(thirdAccount);
        } 
    }
    
    
    /**
     * 根据YOYO账号ID查询已绑定的第三方账号
     * @param data
     * @return
     */
    public Object findByAccountId(Object data){
        logger.info("###########ThirdAccountService.findByAccountId----->start");
        HeadObject head = new HeadObject();
        Integer pamAccountId = (Integer)data;
        List<ThirdAccount> list = new ArrayList<ThirdAccount>();
        try{
        	list = thirdAccountMapper.selectByPamAccountId(pamAccountId);
        }catch (Exception e){
        	logger.error(e.toString());
        	e.printStackTrace();
        	head.setRetCode(ErrorCode.FAILURE);
        }
    	head.setRetCode(ErrorCode.SUCCESS);
        logger.info("###########ThirdAccountService.findByAccountId----->start");
    	return new ResultObject(head,list);
    }
    
    /**
     * 取消绑定
     * @param data
     * @return
     */
    public Object cancleBinding(Object data){
        logger.info("###########ThirdAccountService.cancleBinding----->start");
        HeadObject head = new HeadObject();
        Integer accountId = (Integer)data;
        try{
        	thirdAccountMapper.deleteByPrimaryKey(accountId);
        }catch (Exception e){
        	logger.error(e.toString());
        	e.printStackTrace();
        	head.setRetCode(ErrorCode.FAILURE);
        }
    	head.setRetCode(ErrorCode.SUCCESS);
        logger.info("###########ThirdAccountService.cancleBinding----->start");
    	return head;
    }
    
    /**
     * 第三方绑定yoyo账号
     * @param data
     * @return
     */
    public Object bindingAccount(Object data){
        logger.info("###########ThirdAccountService.bindingAccount----->start");
        HeadObject head = new HeadObject();
        ThirdAccount account = (ThirdAccount)data;
        try{
        	thirdAccountMapper.insertSelective(account);
        }catch (Exception e){
        	logger.error(e.toString());
        	e.printStackTrace();
        	head.setRetCode(ErrorCode.FAILURE);
        }
    	head.setRetCode(ErrorCode.SUCCESS);
        logger.info("###########ThirdAccountService.bindingAccount----->end");
    	return new ResultObject(head, account.getAccountName());
    }
    
    public Object findIfExisted(Object data){
    	HeadObject head = new HeadObject();
        ThirdAccount account = (ThirdAccount)data;
    	int count = thirdAccountMapper.selectExist(account);
		if(count > 0){
			head.setRetCode(ErrorCode.IS_EXIST);
			head.setRetMsg("已绑定一个该第三方类型的账号!");
		}
		return new ResultObject(head);
    }
}
