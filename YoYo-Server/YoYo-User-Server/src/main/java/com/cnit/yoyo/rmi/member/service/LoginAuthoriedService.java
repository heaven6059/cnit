package com.cnit.yoyo.rmi.member.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.taoping.jsonrpc.core.dto.ReqParamsObj;
import cn.taoping.jsonrpc.core.dto.ResponseObj;
import cn.taoping.mainserver.service.IMainService;

import com.cnit.yoyo.accountsecurity.dao.AccountSecurityMapper;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.constant.ThirdAccountConstants;
import com.cnit.yoyo.dao.member.ClerkMapper;
import com.cnit.yoyo.dao.member.MemberLevelMapper;
import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.dao.member.MemberServiceMapper;
import com.cnit.yoyo.dao.member.PamAccountMapper;
import com.cnit.yoyo.dao.member.PamLogSiteMapper;
import com.cnit.yoyo.dao.member.TempKeyMapper;
import com.cnit.yoyo.dao.shop.CompanyMapper;
import com.cnit.yoyo.dao.shop.StoreMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.domain.member.MemberQueryDo;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.member.Clerks;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberLevel;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.MemberWithBLOBs;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.member.PamLogSite;
import com.cnit.yoyo.model.member.TempKey;
import com.cnit.yoyo.model.shop.ShopGrade;
import com.cnit.yoyo.rmi.system.service.impl.PasswordHelper;
import com.cnit.yoyo.rmi.system.service.impl.UserServiceImpl;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;

@Service("loginAuthoriedService")
public class LoginAuthoriedService {
    @Autowired
    private PamAccountMapper pamAccountMapper;
    @Autowired
    private PamLogSiteMapper pamLogSiteMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    @Autowired
    private TempKeyMapper tempKeyMapper;
    @Autowired
    private MemberServiceMapper memberServiceMapper;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private StoreMapper storeMapper;
    @Autowired
    private ClerkMapper clerkMapper;
    @Autowired
	private PasswordHelper passwordHelper;//注入密码服务
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
	private AccountSecurityMapper accountSecurityMapper ;
    @Resource
	private IMainService mainService;

    /**
     * @Description:注册用户信息到用户中心（测试用）
     * @param data
     * @return
     */
    public Object regUserToUserCenter(Object data){
    	JSONObject content = JSONObject.fromObject(data);
    	ResponseObj resp=sendUserInfoToTaoPing(content);
    	if(!resp.isSuccess()){
    		if(StringUtils.isBlank(resp.getMsg())){
    			return new HeadObject(ErrorCode.USERCENTER_REG_FAILURE);
    		}
    		return new HeadObject(ErrorCode.USERCENTER_REG_OTHER_FAILURE,GlobalStatic.RET_TYPE_OTHER,resp.getMsg());
    	}
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
    }
    /**
     * 去用户中心校验账号密码是否正确
     */
    public Object verifyAndSyncUserInfo(Object data){
    	JSONObject content = JSONObject.fromObject(data);
    	ResponseObj resp=verifyUserFromTaoPing(content);
    	if(resp.isSuccess()){
    		// 根据注册账户名称类型查询邮箱 手机 用户名是否被注册
            MemberQueryDo queryDo = new MemberQueryDo();
            queryDo.setLoginName((String) content.get("loginName"));
            queryDo.setEmail((String) content.get("email"));
            queryDo.setMobile((String) content.get("mobile"));
            //更正为根据登录名来判断，不允许有重复的登陆账号
            PamAccount accountInfo=pamAccountMapper.findByUsername(queryDo.getLoginName());
            //用户中心存在账户信息，yoyo平台不存在则新增到yoyo平台
            if(accountInfo == null){
            	 // pamAccount数据要求
            	PamAccount pa = new PamAccount();
              //加密密码
                pa.setAccountType(GlobalStatic.ACCOUNT_TYPE_BUYER);
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
        		member.setUserCenterId(Integer.valueOf(resp.getResult() != null ? resp.getResult().toString():""));
        		member.setRegUserCenter(ThirdAccountConstants.ACCOUNT_REGCENTER_0);
                memberMapper.insert(member);
                // 保存日志流水
                pls.setAccountId(pa.getAccountId());
                pamLogSiteMapper.insert(pls);
                JSONObject retData = new JSONObject();
                retData.put("pamAccount", pa);
                retData.put("member", member);
                return new ResultObject(new HeadObject(ErrorCode.SUCCESS), retData);
            }else{
            	//用户中心和yoyo平台都存在，判断用户中心是否修改了密码，如果不一致，则更新yoyo密码(防止用户中心修改了密码 ，yoyo平台密码不一致的情况)
            	String yoyoPassword=accountInfo.getLoginPassword();
            	String userCenterPassword=(String) content.get("loginPassword");
            	if(userCenterPassword != null){
            		accountInfo.setLoginPassword(userCenterPassword);
                    String encryptPassword = passwordHelper.getEncryptPassword(accountInfo);
                    if(!yoyoPassword.equals(encryptPassword)){
                    	Map<String,Object> para = new HashMap<String, Object>(); 
                    	para.put("accountId", accountInfo.getAccountId());
                    	para.put("encryptPassword",encryptPassword);
                    	accountSecurityMapper.updateNewPassword(para);
                    }
            	}
            	return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
            }
    	}else{
    		if(StringUtils.isBlank(resp.getMsg())){
    			return new HeadObject(ErrorCode.USERCENTER_VERIFY_FAILURE);
    		}
    		return new HeadObject(ErrorCode.USERCENTER_VERIFY_FAILURE,GlobalStatic.RET_TYPE_OTHER,resp.getMsg());
    	}
    }
    
    public ResponseObj verifyUserFromTaoPing(JSONObject data){
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", data.get("loginName"));//用户名(别名) String类型
		map.put("password", data.get("loginPassword"));//密码 String类型
		ReqParamsObj reqparam = new ReqParamsObj();
		reqparam.setParamMap(map);
		ResponseObj result = new ResponseObj();
//		if (result == null) {
//			result=new ResponseObj();
//			result.setMsg("用户中心校验用户失败。");
//			result.setSuccess(false);
//		}else if (result != null && result.isSuccess()) {
			result.setMsg("用户中心校验用户成功。");
			result.setSuccess(true);
			result.setResult(123);
//		}
		return result;
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
		ResponseObj result =  new ResponseObj();
//		if (result == null) {
//			result=new ResponseObj();
//			result.setMsg("通知用户中心失败。");
//			result.setSuccess(false);
//		}else if (result != null && result.isSuccess()) {
			result.setMsg("通知用户中心成功。");
			result.setSuccess(true);
			result.setResult(123);
//		}
		return result;
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
    public Object saveMember(Object data) throws Exception {
        JSONObject content = JSONObject.fromObject(data);
        // 根据注册账户名称类型查询邮箱 手机 用户名是否被注册
        MemberQueryDo queryDo = new MemberQueryDo();
        queryDo.setLoginName((String) content.get("loginName"));
        queryDo.setEmail((String) content.get("email"));
        queryDo.setMobile((String) content.get("mobile"));
        //更正为根据登录名来判断，不允许有重复的登陆账号
        PamAccount accountInfo=pamAccountMapper.findByUsername(queryDo.getLoginName());
        if (accountInfo == null) {
        	ResponseObj resp=sendUserInfoToTaoPing(content);
        	if(!resp.isSuccess()){
        		if(StringUtils.isBlank(resp.getMsg())){
        			return new HeadObject(ErrorCode.USERCENTER_REG_FAILURE);
        		}
        		return new HeadObject(ErrorCode.USERCENTER_REG_OTHER_FAILURE,GlobalStatic.RET_TYPE_OTHER,resp.getMsg());
        	}
            // pamAccount数据要求
        	PamAccount pa = new PamAccount();
          //加密密码
            pa.setAccountType((String) content.get("accountType"));
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
    		member.setUserCenterId(Integer.valueOf(resp.getResult() != null ? resp.getResult().toString():""));
    		member.setRegUserCenter(ThirdAccountConstants.ACCOUNT_REGCENTER_0);
            memberMapper.insert(member);
            // 保存日志流水
            pls.setAccountId(pa.getAccountId());
            pamLogSiteMapper.insert(pls);
            JSONObject retData = new JSONObject();
            retData.put("pamAccount", pa);
            retData.put("member", member);
            return new ResultObject(new HeadObject(ErrorCode.SUCCESS), retData);
        } else {
            return new HeadObject("PDE003");
        }
    }

    /**
     * @description 修改密码
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-9
     * @param data
     * @return
     * @throws Exception
     */
    public Object modifyPassword(Object data) throws Exception {
        HeadObject head = new HeadObject();
        Map<String, String> dataMap = (Map<String, String>) data;
        // 根据登录名类型查询用户帐号
        String loginNameType = dataMap.get("loginNameType");
        String loginName = dataMap.get("loginName");

        MemberQueryDo qureyDo = new MemberQueryDo();
        if (GlobalStatic.EMAIL.equalsIgnoreCase(loginNameType)) {
            qureyDo.setEmail(loginName);
        } else if (GlobalStatic.MOBILE.equalsIgnoreCase(loginNameType)) {
            qureyDo.setMobile(loginName);
        } else if (GlobalStatic.USERNAME.equalsIgnoreCase(loginNameType)) {
            qureyDo.setLoginName(loginName);
        }
        List<MemberListDo> members = memberServiceMapper.findMembersInfoByParam(qureyDo);
        if (members != null && members.size() == 1) {
            MemberListDo member = members.get(0);
            Integer accountId = member.getAccountId();
            //PamAccount pamAccount = pamAccountMapper.selectByPrimaryKey(accountId);
            PamAccount pamAccount = userServiceImpl.findOne(accountId);
            if (pamAccount != null) {
                // 修改帐号对应的密码
                String loginPassword = dataMap.get("loginPassword");
                userServiceImpl.changePassword(accountId, loginPassword);
                //pamAccount.setLoginPassword(loginPassword);
                //pamAccountMapper.updateByPrimaryKeySelective(pamAccount);
                // 写入日志
                PamLogSite pls = new PamLogSite();
                pls.setEventId(UUID.randomUUID().toString());
                pls.setAccountId(pamAccount.getAccountId());
                pls.setEventType("修改密码");
                pls.setEventTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
                pls.setEventData(data.toString());
                pamLogSiteMapper.insert(pls);
                head.setRetCode(ErrorCode.SUCCESS);
            }
        } else {
            head.setRetCode("PDE004");
        }
        return head;
    }

    /**
     * @description 生成临时密钥
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-9
     * @param data
     * @return
     * @throws Exception
     */
    public Object genTempKey(Object data) throws Exception {
        HeadObject head = new HeadObject();
        Map<String, String> dataMap = (Map<String, String>) data;
        String loginNameType = dataMap.get("loginNameType");
        String loginName = dataMap.get("loginName");
        List<Member> members = null;
        if (GlobalStatic.EMAIL.equalsIgnoreCase(loginNameType)) {
            members = memberMapper.findByEmail(loginName);
        } else if (GlobalStatic.MOBILE.equalsIgnoreCase(loginNameType)) {
            members = memberMapper.findByMobile(loginName);
        }
        if (members != null && members.size() == 1) {
            Integer accountId = members.get(0).getAccountId();
            TempKey tempKey = new TempKey();
            tempKey.setTempKey(StringUtil.getUUID());
            tempKey.setAccountId(accountId);
            tempKey.setGenTime(DateUtils.getCurrentDate(DateUtils.NORMAL_DATETIME_PATTERN));
            tempKey.setExpireTime(DateUtils.addDay(tempKey.getGenTime(), 3, DateUtils.NORMAL_DATETIME_PATTERN));
            tempKeyMapper.insert(tempKey);
            dataMap.put("tempKey", tempKey.getTempKey());
            dataMap.put("account", accountId.toString());
            head.setRetCode(ErrorCode.SUCCESS);
        } else {
            head.setRetCode(ErrorCode.NO_DATA);
        }
        return new ResultObject(head, dataMap);
    }

    /**
     * @description 获取临时密钥信息
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-9
     * @param data
     * @return
     * @throws Exception
     */
    public Object getTempKey(Object data) throws Exception {
        HeadObject head = new HeadObject();
        TempKey tempKey = tempKeyMapper.selectByPrimaryKey((String) data);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, tempKey);
    }

    /**
     * @description 获取临时密钥信息
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-9
     * @param data
     * @return
     * @throws Exception
     */
    public Object getMemberByMobile(Object data) throws Exception {
        HeadObject head = new HeadObject();
        List<Member> members = memberMapper.findByMobile((String) data);
        if (members != null && members.size() > 0) {
            head.setRetCode(ErrorCode.SUCCESS);
        } else {
            head.setRetCode(ErrorCode.NO_DATA);
        }
        return new ResultObject(head, members);
    }

    /**
     * @description 会员登录
     * @detail
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-26
     * @return
     * @throws Exception
     */
    public Object doLogin(Object data) {
        JSONObject content = JSONObject.fromObject(data);
        HeadObject head = new HeadObject();
        // 根据注册账户名称类型查询邮箱 手机 用户名是否被注册
        String loginNameType = (String) content.get("loginNameType");
        String loginName = (String) content.get("loginName");
        MemberQueryDo queryDo = new MemberQueryDo();
        //queryDo.setLoginPassword(content.getString("loginPassword"));
//        if (GlobalStatic.USERNAME.equalsIgnoreCase(loginNameType)) {
//            queryDo.setLoginName(loginName);
//        } else if (GlobalStatic.MOBILE.equalsIgnoreCase(loginNameType)) {
//            queryDo.setMobile(loginName);
//        } else if (GlobalStatic.EMAIL.equalsIgnoreCase(loginNameType)) {
//            queryDo.setEmail(loginName);
//        }
        JSONObject json = null;
        try {
        	 queryDo.setLoginName(loginName);
             List<MemberListDo> memberList = memberServiceMapper.findMembersInfoByParam(queryDo);
             if (memberList == null || memberList.isEmpty()) {
                 head.setRetCode(ErrorCode.PDE0002);
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
                 		//memberDo.setValidity(stores.get(0).getLastModify());
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
                 json = JSONObject.fromObject(memberDo);
                 head.setRetCode(ErrorCode.SUCCESS);
                 //return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(memberDo));
             }
        }catch(Exception e) {
        	e.printStackTrace();
        }
        return new ResultObject(head, json);
    }

    /**
     * @description 管理平台用户登录
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-17
     * @param data
     * @return
     * @throws Exception
     */
    public Object doDeskLogin(Object data) throws Exception {
        JSONObject content = JSONObject.fromObject(data);
        String loginName = (String) content.get("loginName");
        //String loginPassword = content.getString("loginPassword");
        PamAccount example = new PamAccount();
        example.setLoginName(loginName);
        //example.setLoginPassword(loginPassword);
        example.setAccountType("2__");
        List<PamAccount> list = pamAccountMapper.selectByPrimaryKeySelective(example);
        if (list != null && !list.isEmpty()) {
            return new ResultObject(new HeadObject("000000"), list.get(0));
        } else {
            return new ResultObject(new HeadObject("PDE002"), null);
        }
    }
}
