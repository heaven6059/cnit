package com.cnit.yoyo.model.shop;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class StoreGradeExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public StoreGradeExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
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

		public Criteria andGradeIdIsNull() {
			addCriterion("grade_id is null");
			return (Criteria) this;
		}

		public Criteria andGradeIdIsNotNull() {
			addCriterion("grade_id is not null");
			return (Criteria) this;
		}

		public Criteria andGradeIdEqualTo(Long value) {
			addCriterion("grade_id =", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdNotEqualTo(Long value) {
			addCriterion("grade_id <>", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdGreaterThan(Long value) {
			addCriterion("grade_id >", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdGreaterThanOrEqualTo(Long value) {
			addCriterion("grade_id >=", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdLessThan(Long value) {
			addCriterion("grade_id <", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdLessThanOrEqualTo(Long value) {
			addCriterion("grade_id <=", value, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdIn(List<Long> values) {
			addCriterion("grade_id in", values, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdNotIn(List<Long> values) {
			addCriterion("grade_id not in", values, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdBetween(Long value1, Long value2) {
			addCriterion("grade_id between", value1, value2, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGradeIdNotBetween(Long value1, Long value2) {
			addCriterion("grade_id not between", value1, value2, "gradeId");
			return (Criteria) this;
		}

		public Criteria andGoodsNumIsNull() {
			addCriterion("goods_num is null");
			return (Criteria) this;
		}

		public Criteria andGoodsNumIsNotNull() {
			addCriterion("goods_num is not null");
			return (Criteria) this;
		}

		public Criteria andGoodsNumEqualTo(Long value) {
			addCriterion("goods_num =", value, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andGoodsNumNotEqualTo(Long value) {
			addCriterion("goods_num <>", value, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andGoodsNumGreaterThan(Long value) {
			addCriterion("goods_num >", value, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andGoodsNumGreaterThanOrEqualTo(Long value) {
			addCriterion("goods_num >=", value, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andGoodsNumLessThan(Long value) {
			addCriterion("goods_num <", value, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andGoodsNumLessThanOrEqualTo(Long value) {
			addCriterion("goods_num <=", value, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andGoodsNumIn(List<Long> values) {
			addCriterion("goods_num in", values, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andGoodsNumNotIn(List<Long> values) {
			addCriterion("goods_num not in", values, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andGoodsNumBetween(Long value1, Long value2) {
			addCriterion("goods_num between", value1, value2, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andGoodsNumNotBetween(Long value1, Long value2) {
			addCriterion("goods_num not between", value1, value2, "goodsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumIsNull() {
			addCriterion("coupons_num is null");
			return (Criteria) this;
		}

		public Criteria andCouponsNumIsNotNull() {
			addCriterion("coupons_num is not null");
			return (Criteria) this;
		}

		public Criteria andCouponsNumEqualTo(Long value) {
			addCriterion("coupons_num =", value, "couponsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumNotEqualTo(Long value) {
			addCriterion("coupons_num <>", value, "couponsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumGreaterThan(Long value) {
			addCriterion("coupons_num >", value, "couponsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumGreaterThanOrEqualTo(Long value) {
			addCriterion("coupons_num >=", value, "couponsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumLessThan(Long value) {
			addCriterion("coupons_num <", value, "couponsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumLessThanOrEqualTo(Long value) {
			addCriterion("coupons_num <=", value, "couponsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumIn(List<Long> values) {
			addCriterion("coupons_num in", values, "couponsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumNotIn(List<Long> values) {
			addCriterion("coupons_num not in", values, "couponsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumBetween(Long value1, Long value2) {
			addCriterion("coupons_num between", value1, value2, "couponsNum");
			return (Criteria) this;
		}

		public Criteria andCouponsNumNotBetween(Long value1, Long value2) {
			addCriterion("coupons_num not between", value1, value2,
					"couponsNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumIsNull() {
			addCriterion("theme_num is null");
			return (Criteria) this;
		}

		public Criteria andThemeNumIsNotNull() {
			addCriterion("theme_num is not null");
			return (Criteria) this;
		}

		public Criteria andThemeNumEqualTo(Long value) {
			addCriterion("theme_num =", value, "themeNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumNotEqualTo(Long value) {
			addCriterion("theme_num <>", value, "themeNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumGreaterThan(Long value) {
			addCriterion("theme_num >", value, "themeNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumGreaterThanOrEqualTo(Long value) {
			addCriterion("theme_num >=", value, "themeNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumLessThan(Long value) {
			addCriterion("theme_num <", value, "themeNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumLessThanOrEqualTo(Long value) {
			addCriterion("theme_num <=", value, "themeNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumIn(List<Long> values) {
			addCriterion("theme_num in", values, "themeNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumNotIn(List<Long> values) {
			addCriterion("theme_num not in", values, "themeNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumBetween(Long value1, Long value2) {
			addCriterion("theme_num between", value1, value2, "themeNum");
			return (Criteria) this;
		}

		public Criteria andThemeNumNotBetween(Long value1, Long value2) {
			addCriterion("theme_num not between", value1, value2, "themeNum");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyIsNull() {
			addCriterion("grade_money is null");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyIsNotNull() {
			addCriterion("grade_money is not null");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyEqualTo(BigDecimal value) {
			addCriterion("grade_money =", value, "gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyNotEqualTo(BigDecimal value) {
			addCriterion("grade_money <>", value, "gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyGreaterThan(BigDecimal value) {
			addCriterion("grade_money >", value, "gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("grade_money >=", value, "gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyLessThan(BigDecimal value) {
			addCriterion("grade_money <", value, "gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyLessThanOrEqualTo(BigDecimal value) {
			addCriterion("grade_money <=", value, "gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyIn(List<BigDecimal> values) {
			addCriterion("grade_money in", values, "gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyNotIn(List<BigDecimal> values) {
			addCriterion("grade_money not in", values, "gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("grade_money between", value1, value2, "gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeMoneyNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("grade_money not between", value1, value2,
					"gradeMoney");
			return (Criteria) this;
		}

		public Criteria andGradeNameIsNull() {
			addCriterion("grade_name is null");
			return (Criteria) this;
		}

		public Criteria andGradeNameIsNotNull() {
			addCriterion("grade_name is not null");
			return (Criteria) this;
		}

		public Criteria andGradeNameEqualTo(String value) {
			addCriterion("grade_name =", value, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameNotEqualTo(String value) {
			addCriterion("grade_name <>", value, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameGreaterThan(String value) {
			addCriterion("grade_name >", value, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameGreaterThanOrEqualTo(String value) {
			addCriterion("grade_name >=", value, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameLessThan(String value) {
			addCriterion("grade_name <", value, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameLessThanOrEqualTo(String value) {
			addCriterion("grade_name <=", value, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameLike(String value) {
			addCriterion("grade_name like", value, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameNotLike(String value) {
			addCriterion("grade_name not like", value, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameIn(List<String> values) {
			addCriterion("grade_name in", values, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameNotIn(List<String> values) {
			addCriterion("grade_name not in", values, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameBetween(String value1, String value2) {
			addCriterion("grade_name between", value1, value2, "gradeName");
			return (Criteria) this;
		}

		public Criteria andGradeNameNotBetween(String value1, String value2) {
			addCriterion("grade_name not between", value1, value2, "gradeName");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyIsNull() {
			addCriterion("issue_money is null");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyIsNotNull() {
			addCriterion("issue_money is not null");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyEqualTo(BigDecimal value) {
			addCriterion("issue_money =", value, "issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyNotEqualTo(BigDecimal value) {
			addCriterion("issue_money <>", value, "issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyGreaterThan(BigDecimal value) {
			addCriterion("issue_money >", value, "issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyGreaterThanOrEqualTo(BigDecimal value) {
			addCriterion("issue_money >=", value, "issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyLessThan(BigDecimal value) {
			addCriterion("issue_money <", value, "issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyLessThanOrEqualTo(BigDecimal value) {
			addCriterion("issue_money <=", value, "issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyIn(List<BigDecimal> values) {
			addCriterion("issue_money in", values, "issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyNotIn(List<BigDecimal> values) {
			addCriterion("issue_money not in", values, "issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("issue_money between", value1, value2, "issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueMoneyNotBetween(BigDecimal value1,
				BigDecimal value2) {
			addCriterion("issue_money not between", value1, value2,
					"issueMoney");
			return (Criteria) this;
		}

		public Criteria andIssueTypeIsNull() {
			addCriterion("issue_type is null");
			return (Criteria) this;
		}

		public Criteria andIssueTypeIsNotNull() {
			addCriterion("issue_type is not null");
			return (Criteria) this;
		}

		public Criteria andIssueTypeEqualTo(String value) {
			addCriterion("issue_type =", value, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeNotEqualTo(String value) {
			addCriterion("issue_type <>", value, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeGreaterThan(String value) {
			addCriterion("issue_type >", value, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeGreaterThanOrEqualTo(String value) {
			addCriterion("issue_type >=", value, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeLessThan(String value) {
			addCriterion("issue_type <", value, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeLessThanOrEqualTo(String value) {
			addCriterion("issue_type <=", value, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeLike(String value) {
			addCriterion("issue_type like", value, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeNotLike(String value) {
			addCriterion("issue_type not like", value, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeIn(List<String> values) {
			addCriterion("issue_type in", values, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeNotIn(List<String> values) {
			addCriterion("issue_type not in", values, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeBetween(String value1, String value2) {
			addCriterion("issue_type between", value1, value2, "issueType");
			return (Criteria) this;
		}

		public Criteria andIssueTypeNotBetween(String value1, String value2) {
			addCriterion("issue_type not between", value1, value2, "issueType");
			return (Criteria) this;
		}

		public Criteria andExperienceIsNull() {
			addCriterion("experience is null");
			return (Criteria) this;
		}

		public Criteria andExperienceIsNotNull() {
			addCriterion("experience is not null");
			return (Criteria) this;
		}

		public Criteria andExperienceEqualTo(Integer value) {
			addCriterion("experience =", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceNotEqualTo(Integer value) {
			addCriterion("experience <>", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceGreaterThan(Integer value) {
			addCriterion("experience >", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceGreaterThanOrEqualTo(Integer value) {
			addCriterion("experience >=", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceLessThan(Integer value) {
			addCriterion("experience <", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceLessThanOrEqualTo(Integer value) {
			addCriterion("experience <=", value, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceIn(List<Integer> values) {
			addCriterion("experience in", values, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceNotIn(List<Integer> values) {
			addCriterion("experience not in", values, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceBetween(Integer value1, Integer value2) {
			addCriterion("experience between", value1, value2, "experience");
			return (Criteria) this;
		}

		public Criteria andExperienceNotBetween(Integer value1, Integer value2) {
			addCriterion("experience not between", value1, value2, "experience");
			return (Criteria) this;
		}

		public Criteria andDefaultLvIsNull() {
			addCriterion("default_lv is null");
			return (Criteria) this;
		}

		public Criteria andDefaultLvIsNotNull() {
			addCriterion("default_lv is not null");
			return (Criteria) this;
		}

		public Criteria andDefaultLvEqualTo(String value) {
			addCriterion("default_lv =", value, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvNotEqualTo(String value) {
			addCriterion("default_lv <>", value, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvGreaterThan(String value) {
			addCriterion("default_lv >", value, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvGreaterThanOrEqualTo(String value) {
			addCriterion("default_lv >=", value, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvLessThan(String value) {
			addCriterion("default_lv <", value, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvLessThanOrEqualTo(String value) {
			addCriterion("default_lv <=", value, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvLike(String value) {
			addCriterion("default_lv like", value, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvNotLike(String value) {
			addCriterion("default_lv not like", value, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvIn(List<String> values) {
			addCriterion("default_lv in", values, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvNotIn(List<String> values) {
			addCriterion("default_lv not in", values, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvBetween(String value1, String value2) {
			addCriterion("default_lv between", value1, value2, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andDefaultLvNotBetween(String value1, String value2) {
			addCriterion("default_lv not between", value1, value2, "defaultLv");
			return (Criteria) this;
		}

		public Criteria andCertificationIsNull() {
			addCriterion("certification is null");
			return (Criteria) this;
		}

		public Criteria andCertificationIsNotNull() {
			addCriterion("certification is not null");
			return (Criteria) this;
		}

		public Criteria andCertificationEqualTo(String value) {
			addCriterion("certification =", value, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationNotEqualTo(String value) {
			addCriterion("certification <>", value, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationGreaterThan(String value) {
			addCriterion("certification >", value, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationGreaterThanOrEqualTo(String value) {
			addCriterion("certification >=", value, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationLessThan(String value) {
			addCriterion("certification <", value, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationLessThanOrEqualTo(String value) {
			addCriterion("certification <=", value, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationLike(String value) {
			addCriterion("certification like", value, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationNotLike(String value) {
			addCriterion("certification not like", value, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationIn(List<String> values) {
			addCriterion("certification in", values, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationNotIn(List<String> values) {
			addCriterion("certification not in", values, "certification");
			return (Criteria) this;
		}

		public Criteria andCertificationBetween(String value1, String value2) {
			addCriterion("certification between", value1, value2,
					"certification");
			return (Criteria) this;
		}

		public Criteria andCertificationNotBetween(String value1, String value2) {
			addCriterion("certification not between", value1, value2,
					"certification");
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

		public Criteria andDisabledEqualTo(String value) {
			addCriterion("disabled =", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotEqualTo(String value) {
			addCriterion("disabled <>", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledGreaterThan(String value) {
			addCriterion("disabled >", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledGreaterThanOrEqualTo(String value) {
			addCriterion("disabled >=", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledLessThan(String value) {
			addCriterion("disabled <", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledLessThanOrEqualTo(String value) {
			addCriterion("disabled <=", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledLike(String value) {
			addCriterion("disabled like", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotLike(String value) {
			addCriterion("disabled not like", value, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledIn(List<String> values) {
			addCriterion("disabled in", values, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotIn(List<String> values) {
			addCriterion("disabled not in", values, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledBetween(String value1, String value2) {
			addCriterion("disabled between", value1, value2, "disabled");
			return (Criteria) this;
		}

		public Criteria andDisabledNotBetween(String value1, String value2) {
			addCriterion("disabled not between", value1, value2, "disabled");
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

		public Criteria andLastModifyEqualTo(Integer value) {
			addCriterion("last_modify =", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyNotEqualTo(Integer value) {
			addCriterion("last_modify <>", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyGreaterThan(Integer value) {
			addCriterion("last_modify >", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyGreaterThanOrEqualTo(Integer value) {
			addCriterion("last_modify >=", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyLessThan(Integer value) {
			addCriterion("last_modify <", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyLessThanOrEqualTo(Integer value) {
			addCriterion("last_modify <=", value, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyIn(List<Integer> values) {
			addCriterion("last_modify in", values, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyNotIn(List<Integer> values) {
			addCriterion("last_modify not in", values, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyBetween(Integer value1, Integer value2) {
			addCriterion("last_modify between", value1, value2, "lastModify");
			return (Criteria) this;
		}

		public Criteria andLastModifyNotBetween(Integer value1, Integer value2) {
			addCriterion("last_modify not between", value1, value2,
					"lastModify");
			return (Criteria) this;
		}

		public Criteria andDOrderIsNull() {
			addCriterion("d_order is null");
			return (Criteria) this;
		}

		public Criteria andDOrderIsNotNull() {
			addCriterion("d_order is not null");
			return (Criteria) this;
		}

		public Criteria andDOrderEqualTo(Integer value) {
			addCriterion("d_order =", value, "dOrder");
			return (Criteria) this;
		}

		public Criteria andDOrderNotEqualTo(Integer value) {
			addCriterion("d_order <>", value, "dOrder");
			return (Criteria) this;
		}

		public Criteria andDOrderGreaterThan(Integer value) {
			addCriterion("d_order >", value, "dOrder");
			return (Criteria) this;
		}

		public Criteria andDOrderGreaterThanOrEqualTo(Integer value) {
			addCriterion("d_order >=", value, "dOrder");
			return (Criteria) this;
		}

		public Criteria andDOrderLessThan(Integer value) {
			addCriterion("d_order <", value, "dOrder");
			return (Criteria) this;
		}

		public Criteria andDOrderLessThanOrEqualTo(Integer value) {
			addCriterion("d_order <=", value, "dOrder");
			return (Criteria) this;
		}

		public Criteria andDOrderIn(List<Integer> values) {
			addCriterion("d_order in", values, "dOrder");
			return (Criteria) this;
		}

		public Criteria andDOrderNotIn(List<Integer> values) {
			addCriterion("d_order not in", values, "dOrder");
			return (Criteria) this;
		}

		public Criteria andDOrderBetween(Integer value1, Integer value2) {
			addCriterion("d_order between", value1, value2, "dOrder");
			return (Criteria) this;
		}

		public Criteria andDOrderNotBetween(Integer value1, Integer value2) {
			addCriterion("d_order not between", value1, value2, "dOrder");
			return (Criteria) this;
		}

		public Criteria andRemarkIsNull() {
			addCriterion("remark is null");
			return (Criteria) this;
		}

		public Criteria andRemarkIsNotNull() {
			addCriterion("remark is not null");
			return (Criteria) this;
		}

		public Criteria andRemarkEqualTo(String value) {
			addCriterion("remark =", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkNotEqualTo(String value) {
			addCriterion("remark <>", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkGreaterThan(String value) {
			addCriterion("remark >", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkGreaterThanOrEqualTo(String value) {
			addCriterion("remark >=", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkLessThan(String value) {
			addCriterion("remark <", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkLessThanOrEqualTo(String value) {
			addCriterion("remark <=", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkLike(String value) {
			addCriterion("remark like", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkNotLike(String value) {
			addCriterion("remark not like", value, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkIn(List<String> values) {
			addCriterion("remark in", values, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkNotIn(List<String> values) {
			addCriterion("remark not in", values, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkBetween(String value1, String value2) {
			addCriterion("remark between", value1, value2, "remark");
			return (Criteria) this;
		}

		public Criteria andRemarkNotBetween(String value1, String value2) {
			addCriterion("remark not between", value1, value2, "remark");
			return (Criteria) this;
		}
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
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
     * This class corresponds to the database table t_business_storegrade
     *
     * @mbggenerated do_not_delete_during_merge Tue Apr 28 14:55:48 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}