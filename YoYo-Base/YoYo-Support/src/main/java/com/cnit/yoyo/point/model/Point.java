package com.cnit.yoyo.point.model;

import java.io.Serializable;
import java.util.List;

/**   
 * @Description: 当前阶段积分
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午5:25:13 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
public class Point implements Serializable {

	/***/
	private static final long serialVersionUID = 2314714824309522359L;
	private Integer memberId; //会员Id
	 private Integer  totalPoint ;//总积分
	 private Integer pointFreeze;//冻结积分
	 private List<MemberPoint> pointHistorys;//积分历史记录
	 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" memberId=").append(memberId);
        sb.append(", totalPoint=").append(totalPoint);
        sb.append(", pointFreeze=").append(pointFreeze);
        sb.append(", pointHistorys=").append(pointHistorys);
        return sb.toString();
    }

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getTotalPoint() {
		return totalPoint;
	}

	public void setTotalPoint(Integer totalPoint) {
		this.totalPoint = totalPoint;
	}

	public Integer getPointFreeze() {
		return pointFreeze;
	}

	public void setPointFreeze(Integer pointFreeze) {
		this.pointFreeze = pointFreeze;
	}

	public List<MemberPoint> getPointHistorys() {
		return pointHistorys;
	}

	public void setPointHistorys(List<MemberPoint> pointHistorys) {
		this.pointHistorys = pointHistorys;
	} 
    
    

}

