package com.cnit.yoyo.model.goods;

import java.io.Serializable;

public class GoodsViewHistoryKey implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_view_history.member_id
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	private Integer memberId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_goods_view_history.goods_id
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	private Long goodsId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_goods_view_history
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_view_history.member_id
	 * @return  the value of t_goods_view_history.member_id
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	public Integer getMemberId() {
		return memberId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_view_history.member_id
	 * @param memberId  the value for t_goods_view_history.member_id
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_goods_view_history.goods_id
	 * @return  the value of t_goods_view_history.goods_id
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	public Long getGoodsId() {
		return goodsId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_goods_view_history.goods_id
	 * @param goodsId  the value for t_goods_view_history.goods_id
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_view_history
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", memberId=").append(memberId);
		sb.append(", goodsId=").append(goodsId);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_view_history
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		GoodsViewHistoryKey other = (GoodsViewHistoryKey) that;
		return (this.getMemberId() == null ? other.getMemberId() == null : this
				.getMemberId().equals(other.getMemberId()))
				&& (this.getGoodsId() == null ? other.getGoodsId() == null
						: this.getGoodsId().equals(other.getGoodsId()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_goods_view_history
	 * @mbggenerated  Sat May 09 18:27:15 CST 2015
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getMemberId() == null) ? 0 : getMemberId().hashCode());
		result = prime * result
				+ ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
		return result;
	}
}