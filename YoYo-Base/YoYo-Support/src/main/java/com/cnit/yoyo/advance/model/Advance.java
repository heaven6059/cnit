package com.cnit.yoyo.advance.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**   
 * @Description: t_members 相关字段信息
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午5:25:13 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
public class Advance implements Serializable {
	private static final long serialVersionUID = -5379369967779610756L;
	private Integer memberId; //会员Id
	 private BigDecimal  advance ;//总金额
	 private List<AdvanceHistory> advanceHistorys;//积分历史记录
	 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" memberId=").append(memberId);
        sb.append(", advance=").append(advance);
        sb.append(", pointHistorys=").append(advanceHistorys);
        return sb.toString();
    }

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}


	public BigDecimal getAdvance() {
		return advance;
	}

	public void setAdvance(BigDecimal advance) {
		this.advance = advance;
	}

	public List<AdvanceHistory> getAdvanceHistorys() {
		return advanceHistorys;
	}

	public void setAdvanceHistorys(List<AdvanceHistory> advanceHistorys) {
		this.advanceHistorys = advanceHistorys;
	}

	

}

