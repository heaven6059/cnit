package com.cnit.yoyo.model.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreViolationCatExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public StoreViolationCatExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value,
				String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1,
				Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property
						+ " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andCatIdIsNull() {
			addCriterion("cat_id is null");
			return (Criteria) this;
		}

		public Criteria andCatIdIsNotNull() {
			addCriterion("cat_id is not null");
			return (Criteria) this;
		}

		public Criteria andCatIdEqualTo(Integer value) {
			addCriterion("cat_id =", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdNotEqualTo(Integer value) {
			addCriterion("cat_id <>", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdGreaterThan(Integer value) {
			addCriterion("cat_id >", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("cat_id >=", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdLessThan(Integer value) {
			addCriterion("cat_id <", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdLessThanOrEqualTo(Integer value) {
			addCriterion("cat_id <=", value, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdIn(List<Integer> values) {
			addCriterion("cat_id in", values, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdNotIn(List<Integer> values) {
			addCriterion("cat_id not in", values, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdBetween(Integer value1, Integer value2) {
			addCriterion("cat_id between", value1, value2, "catId");
			return (Criteria) this;
		}

		public Criteria andCatIdNotBetween(Integer value1, Integer value2) {
			addCriterion("cat_id not between", value1, value2, "catId");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNull() {
			addCriterion("parent_id is null");
			return (Criteria) this;
		}

		public Criteria andParentIdIsNotNull() {
			addCriterion("parent_id is not null");
			return (Criteria) this;
		}

		public Criteria andParentIdEqualTo(Integer value) {
			addCriterion("parent_id =", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotEqualTo(Integer value) {
			addCriterion("parent_id <>", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThan(Integer value) {
			addCriterion("parent_id >", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("parent_id >=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThan(Integer value) {
			addCriterion("parent_id <", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdLessThanOrEqualTo(Integer value) {
			addCriterion("parent_id <=", value, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdIn(List<Integer> values) {
			addCriterion("parent_id in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotIn(List<Integer> values) {
			addCriterion("parent_id not in", values, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdBetween(Integer value1, Integer value2) {
			addCriterion("parent_id between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
			addCriterion("parent_id not between", value1, value2, "parentId");
			return (Criteria) this;
		}

		public Criteria andCatPathIsNull() {
			addCriterion("cat_path is null");
			return (Criteria) this;
		}

		public Criteria andCatPathIsNotNull() {
			addCriterion("cat_path is not null");
			return (Criteria) this;
		}

		public Criteria andCatPathEqualTo(String value) {
			addCriterion("cat_path =", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathNotEqualTo(String value) {
			addCriterion("cat_path <>", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathGreaterThan(String value) {
			addCriterion("cat_path >", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathGreaterThanOrEqualTo(String value) {
			addCriterion("cat_path >=", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathLessThan(String value) {
			addCriterion("cat_path <", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathLessThanOrEqualTo(String value) {
			addCriterion("cat_path <=", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathLike(String value) {
			addCriterion("cat_path like", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathNotLike(String value) {
			addCriterion("cat_path not like", value, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathIn(List<String> values) {
			addCriterion("cat_path in", values, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathNotIn(List<String> values) {
			addCriterion("cat_path not in", values, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathBetween(String value1, String value2) {
			addCriterion("cat_path between", value1, value2, "catPath");
			return (Criteria) this;
		}

		public Criteria andCatPathNotBetween(String value1, String value2) {
			addCriterion("cat_path not between", value1, value2, "catPath");
			return (Criteria) this;
		}

		public Criteria andIsLeafIsNull() {
			addCriterion("is_leaf is null");
			return (Criteria) this;
		}

		public Criteria andIsLeafIsNotNull() {
			addCriterion("is_leaf is not null");
			return (Criteria) this;
		}

		public Criteria andIsLeafEqualTo(Integer value) {
			addCriterion("is_leaf =", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafNotEqualTo(Integer value) {
			addCriterion("is_leaf <>", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafGreaterThan(Integer value) {
			addCriterion("is_leaf >", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_leaf >=", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafLessThan(Integer value) {
			addCriterion("is_leaf <", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafLessThanOrEqualTo(Integer value) {
			addCriterion("is_leaf <=", value, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafIn(List<Integer> values) {
			addCriterion("is_leaf in", values, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafNotIn(List<Integer> values) {
			addCriterion("is_leaf not in", values, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafBetween(Integer value1, Integer value2) {
			addCriterion("is_leaf between", value1, value2, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andIsLeafNotBetween(Integer value1, Integer value2) {
			addCriterion("is_leaf not between", value1, value2, "isLeaf");
			return (Criteria) this;
		}

		public Criteria andCatNameIsNull() {
			addCriterion("cat_name is null");
			return (Criteria) this;
		}

		public Criteria andCatNameIsNotNull() {
			addCriterion("cat_name is not null");
			return (Criteria) this;
		}

		public Criteria andCatNameEqualTo(String value) {
			addCriterion("cat_name =", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameNotEqualTo(String value) {
			addCriterion("cat_name <>", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameGreaterThan(String value) {
			addCriterion("cat_name >", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameGreaterThanOrEqualTo(String value) {
			addCriterion("cat_name >=", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameLessThan(String value) {
			addCriterion("cat_name <", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameLessThanOrEqualTo(String value) {
			addCriterion("cat_name <=", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameLike(String value) {
			addCriterion("cat_name like", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameNotLike(String value) {
			addCriterion("cat_name not like", value, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameIn(List<String> values) {
			addCriterion("cat_name in", values, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameNotIn(List<String> values) {
			addCriterion("cat_name not in", values, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameBetween(String value1, String value2) {
			addCriterion("cat_name between", value1, value2, "catName");
			return (Criteria) this;
		}

		public Criteria andCatNameNotBetween(String value1, String value2) {
			addCriterion("cat_name not between", value1, value2, "catName");
			return (Criteria) this;
		}

		public Criteria andScoreIsNull() {
			addCriterion("score is null");
			return (Criteria) this;
		}

		public Criteria andScoreIsNotNull() {
			addCriterion("score is not null");
			return (Criteria) this;
		}

		public Criteria andScoreEqualTo(Integer value) {
			addCriterion("score =", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotEqualTo(Integer value) {
			addCriterion("score <>", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThan(Integer value) {
			addCriterion("score >", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
			addCriterion("score >=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThan(Integer value) {
			addCriterion("score <", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreLessThanOrEqualTo(Integer value) {
			addCriterion("score <=", value, "score");
			return (Criteria) this;
		}

		public Criteria andScoreIn(List<Integer> values) {
			addCriterion("score in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotIn(List<Integer> values) {
			addCriterion("score not in", values, "score");
			return (Criteria) this;
		}

		public Criteria andScoreBetween(Integer value1, Integer value2) {
			addCriterion("score between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andScoreNotBetween(Integer value1, Integer value2) {
			addCriterion("score not between", value1, value2, "score");
			return (Criteria) this;
		}

		public Criteria andAddTimeIsNull() {
			addCriterion("add_time is null");
			return (Criteria) this;
		}

		public Criteria andAddTimeIsNotNull() {
			addCriterion("add_time is not null");
			return (Criteria) this;
		}

		public Criteria andAddTimeEqualTo(Date value) {
			addCriterion("add_time =", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotEqualTo(Date value) {
			addCriterion("add_time <>", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeGreaterThan(Date value) {
			addCriterion("add_time >", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("add_time >=", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeLessThan(Date value) {
			addCriterion("add_time <", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeLessThanOrEqualTo(Date value) {
			addCriterion("add_time <=", value, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeIn(List<Date> values) {
			addCriterion("add_time in", values, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotIn(List<Date> values) {
			addCriterion("add_time not in", values, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeBetween(Date value1, Date value2) {
			addCriterion("add_time between", value1, value2, "addTime");
			return (Criteria) this;
		}

		public Criteria andAddTimeNotBetween(Date value1, Date value2) {
			addCriterion("add_time not between", value1, value2, "addTime");
			return (Criteria) this;
		}

		public Criteria andLastModifyIsNull() {
			addCriterion("last_modify is null");
			return (Criteria) this;
		}

		public Criteria andLastModifyIsNotNull() {
			addCriterion("last_modify is not null");
			return (Criteria) this;
		}

		public Criteria andLastModifyEqualTo(Date value) {
			addCriterion("last_modify =", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyNotEqualTo(Date value) {
			addCriterion("last_modify <>", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyGreaterThan(Date value) {
			addCriterion("last_modify >", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyGreaterThanOrEqualTo(Date value) {
			addCriterion("last_modify >=", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyLessThan(Date value) {
			addCriterion("last_modify <", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyLessThanOrEqualTo(Date value) {
			addCriterion("last_modify <=", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyIn(List<Date> values) {
			addCriterion("last_modify in", values, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyNotIn(List<Date> values) {
			addCriterion("last_modify not in", values, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyBetween(Date value1, Date value2) {
			addCriterion("last_modify between", value1, value2, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyNotBetween(Date value1, Date value2) {
			addCriterion("last_modify not between", value1, value2,
					"lastModify");
			return (Criteria) this;
		}

		public Criteria andDisabledIsNull() {
			addCriterion("disabled is null");
			return (Criteria) this;
		}

		public Criteria andDisabledIsNotNull() {
			addCriterion("disabled is not null");
			return (Criteria) this;
		}

		public Criteria andDisabledEqualTo(Integer value) {
			addCriterion("disabled =", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotEqualTo(Integer value) {
			addCriterion("disabled <>", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledGreaterThan(Integer value) {
			addCriterion("disabled >", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledGreaterThanOrEqualTo(Integer value) {
			addCriterion("disabled >=", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledLessThan(Integer value) {
			addCriterion("disabled <", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledLessThanOrEqualTo(Integer value) {
			addCriterion("disabled <=", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledIn(List<Integer> values) {
			addCriterion("disabled in", values, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotIn(List<Integer> values) {
			addCriterion("disabled not in", values, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledBetween(Integer value1, Integer value2) {
			addCriterion("disabled between", value1, value2, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotBetween(Integer value1, Integer value2) {
			addCriterion("disabled not between", value1, value2, "disabled");
			return (Criteria) this;
		}

		public Criteria andPOrderIsNull() {
			addCriterion("p_order is null");
			return (Criteria) this;
		}

		public Criteria andPOrderIsNotNull() {
			addCriterion("p_order is not null");
			return (Criteria) this;
		}

		public Criteria andPOrderEqualTo(Integer value) {
			addCriterion("p_order =", value, "pOrder");
			return (Criteria) this;
		}

		public Criteria andPOrderNotEqualTo(Integer value) {
			addCriterion("p_order <>", value, "pOrder");
			return (Criteria) this;
		}

		public Criteria andPOrderGreaterThan(Integer value) {
			addCriterion("p_order >", value, "pOrder");
			return (Criteria) this;
		}

		public Criteria andPOrderGreaterThanOrEqualTo(Integer value) {
			addCriterion("p_order >=", value, "pOrder");
			return (Criteria) this;
		}

		public Criteria andPOrderLessThan(Integer value) {
			addCriterion("p_order <", value, "pOrder");
			return (Criteria) this;
		}

		public Criteria andPOrderLessThanOrEqualTo(Integer value) {
			addCriterion("p_order <=", value, "pOrder");
			return (Criteria) this;
		}

		public Criteria andPOrderIn(List<Integer> values) {
			addCriterion("p_order in", values, "pOrder");
			return (Criteria) this;
		}

		public Criteria andPOrderNotIn(List<Integer> values) {
			addCriterion("p_order not in", values, "pOrder");
			return (Criteria) this;
		}

		public Criteria andPOrderBetween(Integer value1, Integer value2) {
			addCriterion("p_order between", value1, value2, "pOrder");
			return (Criteria) this;
		}

		public Criteria andPOrderNotBetween(Integer value1, Integer value2) {
			addCriterion("p_order not between", value1, value2, "pOrder");
			return (Criteria) this;
		}

		public Criteria andChildCountIsNull() {
			addCriterion("child_count is null");
			return (Criteria) this;
		}

		public Criteria andChildCountIsNotNull() {
			addCriterion("child_count is not null");
			return (Criteria) this;
		}

		public Criteria andChildCountEqualTo(Integer value) {
			addCriterion("child_count =", value, "childCount");
			return (Criteria) this;
		}

		public Criteria andChildCountNotEqualTo(Integer value) {
			addCriterion("child_count <>", value, "childCount");
			return (Criteria) this;
		}

		public Criteria andChildCountGreaterThan(Integer value) {
			addCriterion("child_count >", value, "childCount");
			return (Criteria) this;
		}

		public Criteria andChildCountGreaterThanOrEqualTo(Integer value) {
			addCriterion("child_count >=", value, "childCount");
			return (Criteria) this;
		}

		public Criteria andChildCountLessThan(Integer value) {
			addCriterion("child_count <", value, "childCount");
			return (Criteria) this;
		}

		public Criteria andChildCountLessThanOrEqualTo(Integer value) {
			addCriterion("child_count <=", value, "childCount");
			return (Criteria) this;
		}

		public Criteria andChildCountIn(List<Integer> values) {
			addCriterion("child_count in", values, "childCount");
			return (Criteria) this;
		}

		public Criteria andChildCountNotIn(List<Integer> values) {
			addCriterion("child_count not in", values, "childCount");
			return (Criteria) this;
		}

		public Criteria andChildCountBetween(Integer value1, Integer value2) {
			addCriterion("child_count between", value1, value2, "childCount");
			return (Criteria) this;
		}

		public Criteria andChildCountNotBetween(Integer value1, Integer value2) {
			addCriterion("child_count not between", value1, value2,
					"childCount");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_business_violationcat
	 * @mbggenerated  Wed Jun 03 16:26:50 CST 2015
	 */
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

	/**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_business_violationcat
     *
     * @mbggenerated do_not_delete_during_merge Tue Jun 02 13:44:39 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}