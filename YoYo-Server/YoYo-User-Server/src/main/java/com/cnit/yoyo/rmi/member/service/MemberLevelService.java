/**
 * 文 件 名   :  MemberLevelService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-4 下午5:19:07
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.member.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.member.MemberLevelMapper;
import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.dao.member.MemberServiceMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.domain.member.MemberQueryDo;
import com.cnit.yoyo.model.member.MemberLevel;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description 会员等级管理
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Service("memberLevelService")
public class MemberLevelService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    @Autowired
    private MemberServiceMapper memberServiceMapper;
    
    
    
    
    
    public Object findAll(Object data){
        HeadObject head = new HeadObject();
        List<MemberLevel> list = memberLevelMapper.selectByExample(null);
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, list);
    }
    

    /**
     * @description 会员等级信息查询
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-4
     * @param data
     * @return
     */
    public Object getMemberLevelList(Object data) throws Exception {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        ResultPage<MemberLevel> page = new ResultPage(memberLevelMapper.findAll());
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, JSONObject.fromObject(page));
    }

    /**
     * @description 新增或修改会员等级信息
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-4
     * @param data
     * @return
     */
    public Object saveOrUpateMemberLevel(Object data) throws Exception {
        String retCode = ErrorCode.SUCCESS;
        JSONObject content = JSONObject.fromObject(data);
        String opType = content.getString("opType");
        MemberLevel memberLevel = (MemberLevel) JSONObject.toBean(content.getJSONObject("data"), MemberLevel.class);
        // 新增
        if (GlobalStatic.DATA_OP_INSERT.equalsIgnoreCase(opType)) {
            // 新增会员等级信息为默认是需检查是否已存在默认值 存在则不允许新增
            if (GlobalStatic.COMMON_SW_ON.equalsIgnoreCase(memberLevel.getIsDefault())) {
                MemberLevel defaultMemberLevel = memberLevelMapper.findByDefault(memberLevel.getType());
                if (defaultMemberLevel == null) {
                    memberLevelMapper.insertSelective(memberLevel);
                } else {
                    retCode = ErrorCode.PDE0001;
                }
            } else {
                memberLevel.setMemberLvId(null);
                memberLevel.setStatus(GlobalStatic.ACCOUNT_STATUS_ON);
                memberLevelMapper.insertSelective(memberLevel);
            }

        }
        // 修改
        else if (GlobalStatic.DATA_OP_MODIFY.equalsIgnoreCase(opType)) {
            // 当修改会员等级信息为默认时需检查是否存在默认值 如存在默认值id与修改值id不一致则不允许修改
            if (GlobalStatic.COMMON_SW_ON.equalsIgnoreCase(memberLevel.getIsDefault())) {
                MemberLevel defaultMemberLevel = memberLevelMapper.findByDefault(memberLevel.getType());
                if (defaultMemberLevel == null || defaultMemberLevel.getMemberLvId() == memberLevel.getMemberLvId()) {
                    memberLevelMapper.updateByPrimaryKeySelective(memberLevel);
                } else {
                    retCode = ErrorCode.PDE0001;
                }
            } else {
                memberLevelMapper.updateByPrimaryKeySelective(memberLevel);
            }
        } else {
            retCode = ErrorCode.PDE0001;
        }
        return new HeadObject(retCode);
    }

    /**
     * @description 删除会员等级信息
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-4
     * @param data
     * @return
     */
    public Object deleteMemberLevel(Object data) throws Exception {
        String retCode = ErrorCode.SUCCESS;
        MemberQueryDo qryDo = new MemberQueryDo();
        qryDo.setMemberLvId(Integer.parseInt((String)data));
        qryDo.setAccountStatus(GlobalStatic.ACCOUNT_STATUS_ON);
        //查询该会员等级项下是否存在有效的会员账户 若不存在则可以删除 反之不可删除
        List<MemberListDo> memberList = memberServiceMapper.findMembersInfoByParam(qryDo);
        if (memberList == null || memberList.size() < 1) {
            //memberLevel.setStatus(GlobalStatic.ACCOUNT_STATUS_OFF);//不做物理删除
            memberLevelMapper.deleteByPrimaryKey(Integer.parseInt((String)data));
        }else{
            retCode = ErrorCode.PDE0001;
        }
        return new HeadObject(retCode);
    }
}
