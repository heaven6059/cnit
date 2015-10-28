/**
 * 文 件 名   :  MemberService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-27 下午1:30:05
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
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

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.constant.ThirdAccountConstants;
import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.dao.member.MemberServiceMapper;
import com.cnit.yoyo.dao.member.PamAccountMapper;
import com.cnit.yoyo.dao.member.PamLogPlatMapper;
import com.cnit.yoyo.dao.member.PamLogSiteMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.domain.member.MemberQueryDo;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.member.MemberWithBLOBs;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.member.PamLogPlat;
import com.cnit.yoyo.model.member.PamLogSite;
import com.cnit.yoyo.rmi.system.service.impl.UserServiceImpl;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Service("memberService")
public class MemberService {

    @Autowired
    private MemberServiceMapper memberServiceMapper;
    @Autowired
    private PamAccountMapper pamAccountMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PamLogPlatMapper pamLogPlatMapper;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private PamLogSiteMapper pamLogSiteMapper;
    @Resource
	private IMainService mainService;
    
    /**
     * @Description:通知用户中心注册用户
     * @author yangyi
     * @param data
     * @return ResponseObj
     */
    public ResponseObj sendUserInfoToTaoPing(JSONObject data){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userAlias", data.get("loginName"));//用户名(别名) String类型
//		map.put("realName", String);//真实姓名 String类型
		map.put("password", data.get("password"));//密码 String类型
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
    
    /**
     * @description 按条件查询会员信息
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-27
     * @param data
     * @return
     * @throws Exception
     */
    public Object findMembersInfoByParam(Object data) throws Exception {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        MemberQueryDo queryDo = (MemberQueryDo) JSONObject.toBean(content, MemberQueryDo.class);
        ResultPage<MemberListDo> page = new ResultPage(memberServiceMapper.findMembersInfoByParam(queryDo));
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, JSONObject.fromObject(page));
    }
    
    /**
     * 
     * @Title: findMemberByParam 
     * @Description: 会员高级查询 
     * @param @param data
     * @param @return
     * @param @throws Exception    设定文件 
     * @return Object    返回类型 
     * @throws
     */
    public Object findMemberByParam(Object data) throws Exception {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        PageHelper.startPage(QueryParamObject.getPage(content), QueryParamObject.getRows(content));
		ResultPage<MemberListDo> page = new ResultPage<MemberListDo>(memberServiceMapper.selectByQueryParams(
				QueryParamObject.getMqls(content), QueryParamObject.getOrderByCause(content)));
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page));
    }

    public Object findMemberAndPamByAccountId(Object data) throws Exception {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        int accountId = content.getInt("accountId");
        PamAccount pamAccount = pamAccountMapper.selectByPrimaryKey(accountId);
        Member member = new Member();
        member.setAccountId(accountId);
        List<Member> list = memberMapper.findByMember(member);
        if (list != null && list.size() > 0) {
            member = list.get(0);
        }
        head.setRetCode(ErrorCode.SUCCESS);
        content.clear();
        content.put("member", member);
        content.put("pamAccount", pamAccount);
        return new ResultObject(head, content);
    }

    public Object insertMember(Object data) throws Exception {
    	JSONObject content = JSONObject.fromObject(data);
    	ResponseObj resp=sendUserInfoToTaoPing(content);
    	if(!resp.isSuccess()){
    		if(StringUtils.isBlank(resp.getMsg())){
    			return new HeadObject(ErrorCode.USERCENTER_REG_FAILURE);
    		}
    		return new HeadObject(ErrorCode.USERCENTER_REG_OTHER_FAILURE,GlobalStatic.RET_TYPE_OTHER,resp.getMsg());
    	}
        HeadObject head = new HeadObject();
        PamAccount pa = new PamAccount();
        //加密密码
        pa.setAccountType(GlobalStatic.ACCOUNT_TYPE_BUYER);
        pa.setLoginName(content.getString("loginName"));
        pa.setLoginPassword(content.getString("password"));
        pa.setAccountStatus(GlobalStatic.ACCOUNT_STATUS_ON);
        pa.setRegIp(content.getString("regIp"));
        pa.setRegTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        // 保存新注册的账户信息
        userServiceImpl.createUser(pa);
        // MemberWithBLOBs数据要求
        MemberWithBLOBs member = new MemberWithBLOBs();
        member.setEmail(content.getString("email"));
        member.setMemberLvId(content.getInt("memberLvId"));
        // PamLogSite数据要求
        PamLogSite pls = new PamLogSite();
        pls.setEventId(UUID.randomUUID().toString());
        pls.setEventType("新增帐号");
        pls.setEventTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        pls.setEventData(content.toString());
        // 保存新注册的会员信息
        member.setAccountId(pa.getAccountId());
		member.setUserCenterId(Integer.valueOf(resp.getResult() != null ? resp.getResult().toString():""));
		member.setRegUserCenter(ThirdAccountConstants.ACCOUNT_REGCENTER_0);
        memberMapper.insert(member);
        // 保存日志流水
        pls.setAccountId(pa.getAccountId());
        pamLogSiteMapper.insert(pls);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, content);
    }

    /**
     * @description <一句话方法简述>
     * @detail <方法详细描述>
     * @author <a href="liguanghua@chinacnit.com">李光华</a>
     * @version 1.0.0
     * @data 2015-3-3
     * @param data
     * @return
     * @throws Exception
     */
    public Object deleteMember(Object data) throws Exception {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        String str = content.getString("accountId");
        String[] strArray = str.split(",");
        for (int i = 0; i < strArray.length; i++) {
            PamAccount pamAccount = pamAccountMapper.selectByPrimaryKey(Integer.parseInt(strArray[i]));
            pamAccount.setAccountStatus(GlobalStatic.ACCOUNT_STATUS_OFF);
            pamAccountMapper.updateByPrimaryKeySelective(pamAccount);
            head.setRetCode(ErrorCode.SUCCESS);
            content.put("pamAccount", pamAccount);
            pamlogplat(data,"deleteMember",strArray[i]);
        }
        return new ResultObject(head, content);

    }

    /**
     * @description 日志操作
     * @detail <方法详细描述>
     * @author <a href="liguanghua@chinacnit.com">李光华</a>
     * @version 1.0.0
     * @data 2015-3-4
     * @param data
     * @return
     * @throws Exception
     */
    public void pamlogplat(Object data,String methodName,String accountId) throws Exception {
        HeadObject head = new HeadObject();
        PamLogPlat pamlogplat = new PamLogPlat();
            pamlogplat.setAccountId(Integer.parseInt(accountId));
            pamlogplat.setEventData(data.toString());
            pamlogplat.setEventId(StringUtil.getUUID());
            pamlogplat.setEventTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
            pamlogplat.setEventType(methodName);
            pamLogPlatMapper.insert(pamlogplat);
      

        head.setRetCode(ErrorCode.SUCCESS);
      
    }

    public Object modifyMember(Object data) throws Exception {
        HeadObject head = new HeadObject();
        MemberWithBLOBs member = (MemberWithBLOBs) JSONObject.toBean(JSONObject.fromObject(data), MemberWithBLOBs.class);
        member.setAccountId(null);
        memberMapper.updateByPrimaryKeySelective(member);
        head.setRetCode(ErrorCode.SUCCESS);
        return head;
    }

    /**
     * 
    * @Title: selectMemberByAccountId 
    * @Description: 根据用户id查询用户对象
    * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
    * @param @param data
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Object    返回类型 
    * @throws
     */
    public Object selectMemberByAccountId(Object data) throws Exception{
        JSONObject jsonObject = JSONObject.fromObject(data);
        Integer accountId = jsonObject.getInt("accountId");
        Member member = new Member();
        member.setAccountId(accountId);
        List<Member> memberList = memberMapper.findByMember(member);
        if(memberList!=null&&memberList.size()>=1){
        	member=memberList.get(0);
        }else{
        	member=null;
        }
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(member));
        
    }
    /**
     * 
     *@description 检查是账户是否存在，并且是否已经开店
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-8
     *@param data
     *@return
     *@throws Exception
     */
    public Object checkMembersInfoByParam(Object data) throws Exception {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        MemberQueryDo queryDo = (MemberQueryDo) JSONObject.toBean(content, MemberQueryDo.class);
        ResultPage<MemberListDo> page = new ResultPage(memberServiceMapper.checkMembersInfoByParam(queryDo));
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, JSONObject.fromObject(page));
    }
    
    public Object findMembersById(Object data) throws Exception {
    	HeadObject head = new HeadObject();
    	String id = (String)data;
    	MemberListDo member = null;
    	if(StringUtils.isNotBlank(id)){
    		Integer memberId = Integer.valueOf(id);
        	member = memberServiceMapper.selectByPrimaryKey(memberId);
        	head.setRetCode(ErrorCode.SUCCESS);
    	}
    	return new ResultObject(head, member);
    }
    
}
