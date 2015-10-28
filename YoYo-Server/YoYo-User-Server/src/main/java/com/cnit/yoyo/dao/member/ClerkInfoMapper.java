package com.cnit.yoyo.dao.member;

import java.util.List;

import com.cnit.yoyo.domain.member.ClerkInfoDo;
import com.cnit.yoyo.model.goods.dto.ClerksDTO;

/**
 * 
 *@description <一句话功能简述>
 *@detail <功能详细描述>
 *@author <a href="liguanghua@chinacnit.com">李光华</a>
 *@version 1.0.0
 */
public interface ClerkInfoMapper{
    
    List<ClerkInfoDo> findClerksInfo(ClerkInfoDo clerkInfo );
    List<ClerkInfoDo> findAllClerks(ClerksDTO dto);
    void batchDeleteClerks(Integer[] ids);
}
