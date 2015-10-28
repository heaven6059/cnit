/**
 * 文 件 名   :  ComboBoxSerivce.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-3 下午5:51:55
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.dao.member.MemberLevelMapper;

/**
 *@description <一句话功能简述>
 *@detail <功能详细描述>
 *@author <a href="liming@cnit.com">李明</a>
 *@version 1.0.0
 */
@Service("comboBoxSerivce")
public class ComboBoxSerivce {
    @Autowired
    private MemberLevelMapper memberLMapper;
}
