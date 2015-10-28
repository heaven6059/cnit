package com.cnit.yoyo.rmi.shop.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.cnit.yoyo.dao.member.ClerkInfoMapper;
import com.cnit.yoyo.dao.member.ClerkMapper;
import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.dao.member.PamAccountMapper;
import com.cnit.yoyo.dao.member.RolesMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.domain.member.ClerkInfoDo;
import com.cnit.yoyo.model.goods.dto.ClerksDTO;
import com.cnit.yoyo.model.member.Clerks;
import com.cnit.yoyo.model.member.MemberWithBLOBs;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.rmi.member.service.LoginAuthoriedService;
import com.cnit.yoyo.rmi.system.service.impl.PasswordHelper;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("clerkManagService")
public class ClerkManagService {

    @Autowired
    private ClerkMapper clerksMapper;

    @Autowired
    private PamAccountMapper pamAccountMapper;
    @Autowired
    private PasswordHelper passwordHelper;

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private ClerkInfoMapper clerksInfoMapper;
    @Resource
	private IMainService mainService;
    
    LoginAuthoriedService loginAuthoriedService;

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
		map.put("password", data.get("password1"));//密码 String类型
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
    
    public Object selectClerkInfo(Object data) {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        ClerkInfoDo clerkInfo = new ClerkInfoDo();
        clerkInfo.setCompanyId(content.getLong("companyId"));
        clerkInfo.setStoreId(content.getLong("storeId"));
        @SuppressWarnings("unchecked")
        ResultPage<ClerkInfoDo> page = new ResultPage(clerksInfoMapper.findClerksInfo(clerkInfo));
        head.setRetCode(ErrorCode.SUCCESS);

        return new ResultObject(head, JSONObject.fromObject(page));
    }
    
    //逻辑删除
    public Object deleteClerk(Object data) {
        HeadObject head = new HeadObject();
        try{
	        JSONObject content = JSONObject.fromObject(data);
	        clerksMapper.deleteByPrimaryKey(content.getLong("attachId"));
	        head.setRetCode(ErrorCode.SUCCESS);
        }catch(Exception e){
        	e.printStackTrace();
        	 head.setRetCode(ErrorCode.FAILURE);
        }
        return new ResultObject(head);
    }

    //更新店员的分店与角色
    public Object updateClerk(Object data) {
        HeadObject head = new HeadObject();
        try{
	        Clerks clerk = (Clerks) JSONObject.toBean(JSONObject.fromObject(data), Clerks.class);
	        clerksMapper.updateById(clerk);
	        head.setRetCode(ErrorCode.SUCCESS);
        }catch (Exception e) {
        	 e.printStackTrace();
        	 head.setRetCode(ErrorCode.FAILURE);
		}
        return new ResultObject(head);
    }

    //添加店员
    public Object insertClerk(Object data) {
    	JSONObject content=JSONObject.fromObject(data);
    	ResponseObj resp=sendUserInfoToTaoPing(content);
    	if(!resp.isSuccess()){
    		if(StringUtils.isBlank(resp.getMsg())){
    			return new HeadObject(ErrorCode.USERCENTER_REG_FAILURE);
    		}
    		return new HeadObject(ErrorCode.USERCENTER_REG_OTHER_FAILURE,GlobalStatic.RET_TYPE_OTHER,resp.getMsg());
    	}
        HeadObject head = new HeadObject();
        ClerksDTO clerkDto = (ClerksDTO) JSONObject.toBean(JSONObject.fromObject(data), ClerksDTO.class);
        Clerks clerk = new Clerks();
        PamAccount pa = new PamAccount();
        MemberWithBLOBs member = new MemberWithBLOBs();
        pa.setLoginName(clerkDto.getLoginName());
        pa.setLoginPassword(clerkDto.getPassword1());
        pa.setAccountStatus(GlobalStatic.ACCOUNT_STATUS_ON);
        pa.setAccountType(GlobalStatic.ACCOUNT_TYPE_SELLER_HAND);
        pa.setRegTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
        member.setEmail(clerkDto.getEmail());
        member.setMemberLvId(1);
        member.setUserCenterId(Integer.valueOf(resp.getResult() != null ? resp.getResult().toString():""));
		member.setRegUserCenter(ThirdAccountConstants.ACCOUNT_REGCENTER_0);
        clerk.setRoleId(clerkDto.getRoleId());
       
        if (null!=clerkDto.getMemberId()|| clerkDto.getMemberId()==0 ) {
            // 保存新注册的账户信息
        	//加密密码
        	passwordHelper.encryptPassword(pa);
            pamAccountMapper.insert(pa);
            // 保存新注册的会员信息
            member.setAccountId(pa.getAccountId());
            memberMapper.insert(member);
            //保存店员信息
            clerk.setMemberId(member.getMemberId().longValue());
            clerk.setStoreId(clerkDto.getStoreId());
            clerk.setCommanyId(clerkDto.getCompanyId());
            clerksMapper.insert(clerk);
            head.setRetCode(ErrorCode.SUCCESS);
                
        } else {
            clerk.setMemberId(clerkDto.getMemberId());
            clerksMapper.insert(clerk);
            head.setRetCode(ErrorCode.SUCCESS);
            
        }
        return new ResultObject(head);
    }
    /**
     * 
     *@description 查找是否存在拥有指定角色的店员 
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-9
     *@param data
     *@return
     */
    public Object findClerksByRoleId(Object data){
        return clerksMapper.findClerksByRoleId((Integer)data);
    }
    
    /**
     * 
     *@description 平台店铺管理中店员列表
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-9
     *@param data
     *@return
     */
    public Object findAllClerks(Object data){
        HeadObject head = new HeadObject();
        ResultPage<ClerkInfoDo> page = null;
        JSONObject content = JSONObject.fromObject(data);
        ClerksDTO dto = (ClerksDTO) JSONObject.toBean(content.getJSONObject("clerk"), ClerksDTO.class);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        page = new ResultPage<ClerkInfoDo>(clerksInfoMapper.findAllClerks(dto));
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head,JSONObject.fromObject(page));
    }
    
    
    //批量逻辑删除
    public Object batchDeleteClerks(Object data) {
        HeadObject head = new HeadObject();
        clerksInfoMapper.batchDeleteClerks((Integer[])data);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head);
    }
}
