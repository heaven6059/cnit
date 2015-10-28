package com.cnit.yoyo.model.member;

import java.io.Serializable;

public class CommentPraise implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment_praise.id
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment_praise.comment_id
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    private Integer commentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment_praise.member_id
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    private Integer memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_comment_praise.add_time
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    private Integer addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_comment_praise
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_comment_praise.id
     *
     * @return the value of t_comment_praise.id
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_comment_praise.id
     *
     * @param id the value for t_comment_praise.id
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_comment_praise.comment_id
     *
     * @return the value of t_comment_praise.comment_id
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_comment_praise.comment_id
     *
     * @param commentId the value for t_comment_praise.comment_id
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_comment_praise.member_id
     *
     * @return the value of t_comment_praise.member_id
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    public Integer getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_comment_praise.member_id
     *
     * @param memberId the value for t_comment_praise.member_id
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_comment_praise.add_time
     *
     * @return the value of t_comment_praise.add_time
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    public Integer getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_comment_praise.add_time
     *
     * @param addTime the value for t_comment_praise.add_time
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_comment_praise
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", commentId=").append(commentId);
        sb.append(", memberId=").append(memberId);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_comment_praise
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
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
        CommentPraise other = (CommentPraise) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_comment_praise
     *
     * @mbggenerated Tue May 19 09:26:10 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        return result;
    }
}