package com.cnit.yoyo.model.member;

import java.io.Serializable;

public class MemberCommentWithBLOBs extends MemberComment implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_member_comments.comment
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
	 */
	private String comment;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_member_comments.addon
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
	 */
	private String addon;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_member_comments
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_member_comments.comment
	 * @return  the value of t_member_comments.comment
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_member_comments.comment
	 * @param comment  the value for t_member_comments.comment
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_member_comments.addon
	 * @return  the value of t_member_comments.addon
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
	 */
	public String getAddon() {
		return addon;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_member_comments.addon
	 * @param addon  the value for t_member_comments.addon
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
	 */
	public void setAddon(String addon) {
		this.addon = addon;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_member_comments
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", comment=").append(comment);
		sb.append(", addon=").append(addon);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_member_comments
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
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
		MemberCommentWithBLOBs other = (MemberCommentWithBLOBs) that;
		return (this.getCommentId() == null ? other.getCommentId() == null
				: this.getCommentId().equals(other.getCommentId()))
				&& (this.getMemberId() == null ? other.getMemberId() == null
						: this.getMemberId().equals(other.getMemberId()))
				&& (this.getForCommentId() == null ? other.getForCommentId() == null
						: this.getForCommentId()
								.equals(other.getForCommentId()))
				&& (this.getTypeId() == null ? other.getTypeId() == null : this
						.getTypeId().equals(other.getTypeId()))
				&& (this.getProductId() == null ? other.getProductId() == null
						: this.getProductId().equals(other.getProductId()))
				&& (this.getOrderId() == null ? other.getOrderId() == null
						: this.getOrderId().equals(other.getOrderId()))
				&& (this.getObjectType() == null ? other.getObjectType() == null
						: this.getObjectType().equals(other.getObjectType()))
				&& (this.getAuthorId() == null ? other.getAuthorId() == null
						: this.getAuthorId().equals(other.getAuthorId()))
				&& (this.getAuthor() == null ? other.getAuthor() == null : this
						.getAuthor().equals(other.getAuthor()))
				&& (this.getContact() == null ? other.getContact() == null
						: this.getContact().equals(other.getContact()))
				&& (this.getMemReadStatus() == null ? other.getMemReadStatus() == null
						: this.getMemReadStatus().equals(
								other.getMemReadStatus()))
				&& (this.getAdmReadStatus() == null ? other.getAdmReadStatus() == null
						: this.getAdmReadStatus().equals(
								other.getAdmReadStatus()))
				&& (this.getTime() == null ? other.getTime() == null : this
						.getTime().equals(other.getTime()))
				&& (this.getLastreply() == null ? other.getLastreply() == null
						: this.getLastreply().equals(other.getLastreply()))
				&& (this.getReplyName() == null ? other.getReplyName() == null
						: this.getReplyName().equals(other.getReplyName()))
				&& (this.getInbox() == null ? other.getInbox() == null : this
						.getInbox().equals(other.getInbox()))
				&& (this.getTrack() == null ? other.getTrack() == null : this
						.getTrack().equals(other.getTrack()))
				&& (this.getHasSent() == null ? other.getHasSent() == null
						: this.getHasSent().equals(other.getHasSent()))
				&& (this.getToId() == null ? other.getToId() == null : this
						.getToId().equals(other.getToId()))
				&& (this.getToUname() == null ? other.getToUname() == null
						: this.getToUname().equals(other.getToUname()))
				&& (this.getTitle() == null ? other.getTitle() == null : this
						.getTitle().equals(other.getTitle()))
				&& (this.getIp() == null ? other.getIp() == null : this.getIp()
						.equals(other.getIp()))
				&& (this.getDisplay() == null ? other.getDisplay() == null
						: this.getDisplay().equals(other.getDisplay()))
				&& (this.getGaskType() == null ? other.getGaskType() == null
						: this.getGaskType().equals(other.getGaskType()))
				&& (this.getpIndex() == null ? other.getpIndex() == null : this
						.getpIndex().equals(other.getpIndex()))
				&& (this.getDisabled() == null ? other.getDisabled() == null
						: this.getDisabled().equals(other.getDisabled()))
				&& (this.getCommentsType() == null ? other.getCommentsType() == null
						: this.getCommentsType()
								.equals(other.getCommentsType()))
				&& (this.getStoreId() == null ? other.getStoreId() == null
						: this.getStoreId().equals(other.getStoreId()))
				&& (this.getComment() == null ? other.getComment() == null
						: this.getComment().equals(other.getComment()))
				&& (this.getAddon() == null ? other.getAddon() == null : this
						.getAddon().equals(other.getAddon()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_member_comments
	 * @mbggenerated  Thu Mar 05 10:09:08 CST 2015
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getCommentId() == null) ? 0 : getCommentId().hashCode());
		result = prime * result
				+ ((getMemberId() == null) ? 0 : getMemberId().hashCode());
		result = prime
				* result
				+ ((getForCommentId() == null) ? 0 : getForCommentId()
						.hashCode());
		result = prime * result
				+ ((getTypeId() == null) ? 0 : getTypeId().hashCode());
		result = prime * result
				+ ((getProductId() == null) ? 0 : getProductId().hashCode());
		result = prime * result
				+ ((getOrderId() == null) ? 0 : getOrderId().hashCode());
		result = prime * result
				+ ((getObjectType() == null) ? 0 : getObjectType().hashCode());
		result = prime * result
				+ ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
		result = prime * result
				+ ((getAuthor() == null) ? 0 : getAuthor().hashCode());
		result = prime * result
				+ ((getContact() == null) ? 0 : getContact().hashCode());
		result = prime
				* result
				+ ((getMemReadStatus() == null) ? 0 : getMemReadStatus()
						.hashCode());
		result = prime
				* result
				+ ((getAdmReadStatus() == null) ? 0 : getAdmReadStatus()
						.hashCode());
		result = prime * result
				+ ((getTime() == null) ? 0 : getTime().hashCode());
		result = prime * result
				+ ((getLastreply() == null) ? 0 : getLastreply().hashCode());
		result = prime * result
				+ ((getReplyName() == null) ? 0 : getReplyName().hashCode());
		result = prime * result
				+ ((getInbox() == null) ? 0 : getInbox().hashCode());
		result = prime * result
				+ ((getTrack() == null) ? 0 : getTrack().hashCode());
		result = prime * result
				+ ((getHasSent() == null) ? 0 : getHasSent().hashCode());
		result = prime * result
				+ ((getToId() == null) ? 0 : getToId().hashCode());
		result = prime * result
				+ ((getToUname() == null) ? 0 : getToUname().hashCode());
		result = prime * result
				+ ((getTitle() == null) ? 0 : getTitle().hashCode());
		result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
		result = prime * result
				+ ((getDisplay() == null) ? 0 : getDisplay().hashCode());
		result = prime * result
				+ ((getGaskType() == null) ? 0 : getGaskType().hashCode());
		result = prime * result
				+ ((getpIndex() == null) ? 0 : getpIndex().hashCode());
		result = prime * result
				+ ((getDisabled() == null) ? 0 : getDisabled().hashCode());
		result = prime
				* result
				+ ((getCommentsType() == null) ? 0 : getCommentsType()
						.hashCode());
		result = prime * result
				+ ((getStoreId() == null) ? 0 : getStoreId().hashCode());
		result = prime * result
				+ ((getComment() == null) ? 0 : getComment().hashCode());
		result = prime * result
				+ ((getAddon() == null) ? 0 : getAddon().hashCode());
		return result;
	}
}