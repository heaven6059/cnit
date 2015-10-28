package com.cnit.yoyo.model.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ArticleIndexExample {
    /**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	protected boolean distinct;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	protected List<Criteria> oredCriteria;

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public ArticleIndexExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
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

		public Criteria andArticleIdIsNull() {
			addCriterion("article_id is null");
			return (Criteria) this;
		}

		public Criteria andArticleIdIsNotNull() {
			addCriterion("article_id is not null");
			return (Criteria) this;
		}

		public Criteria andArticleIdEqualTo(Integer value) {
			addCriterion("article_id =", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdNotEqualTo(Integer value) {
			addCriterion("article_id <>", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdGreaterThan(Integer value) {
			addCriterion("article_id >", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("article_id >=", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdLessThan(Integer value) {
			addCriterion("article_id <", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdLessThanOrEqualTo(Integer value) {
			addCriterion("article_id <=", value, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdIn(List<Integer> values) {
			addCriterion("article_id in", values, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdNotIn(List<Integer> values) {
			addCriterion("article_id not in", values, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdBetween(Integer value1, Integer value2) {
			addCriterion("article_id between", value1, value2, "articleId");
			return (Criteria) this;
		}

		public Criteria andArticleIdNotBetween(Integer value1, Integer value2) {
			addCriterion("article_id not between", value1, value2, "articleId");
			return (Criteria) this;
		}

		public Criteria andTitleIsNull() {
			addCriterion("title is null");
			return (Criteria) this;
		}

		public Criteria andTitleIsNotNull() {
			addCriterion("title is not null");
			return (Criteria) this;
		}

		public Criteria andTitleEqualTo(String value) {
			addCriterion("title =", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotEqualTo(String value) {
			addCriterion("title <>", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThan(String value) {
			addCriterion("title >", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleGreaterThanOrEqualTo(String value) {
			addCriterion("title >=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThan(String value) {
			addCriterion("title <", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLessThanOrEqualTo(String value) {
			addCriterion("title <=", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleLike(String value) {
			addCriterion("title like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotLike(String value) {
			addCriterion("title not like", value, "title");
			return (Criteria) this;
		}

		public Criteria andTitleIn(List<String> values) {
			addCriterion("title in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotIn(List<String> values) {
			addCriterion("title not in", values, "title");
			return (Criteria) this;
		}

		public Criteria andTitleBetween(String value1, String value2) {
			addCriterion("title between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andTitleNotBetween(String value1, String value2) {
			addCriterion("title not between", value1, value2, "title");
			return (Criteria) this;
		}

		public Criteria andPlatformIsNull() {
			addCriterion("platform is null");
			return (Criteria) this;
		}

		public Criteria andPlatformIsNotNull() {
			addCriterion("platform is not null");
			return (Criteria) this;
		}

		public Criteria andPlatformEqualTo(String value) {
			addCriterion("platform =", value, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformNotEqualTo(String value) {
			addCriterion("platform <>", value, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformGreaterThan(String value) {
			addCriterion("platform >", value, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformGreaterThanOrEqualTo(String value) {
			addCriterion("platform >=", value, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformLessThan(String value) {
			addCriterion("platform <", value, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformLessThanOrEqualTo(String value) {
			addCriterion("platform <=", value, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformLike(String value) {
			addCriterion("platform like", value, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformNotLike(String value) {
			addCriterion("platform not like", value, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformIn(List<String> values) {
			addCriterion("platform in", values, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformNotIn(List<String> values) {
			addCriterion("platform not in", values, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformBetween(String value1, String value2) {
			addCriterion("platform between", value1, value2, "platform");
			return (Criteria) this;
		}

		public Criteria andPlatformNotBetween(String value1, String value2) {
			addCriterion("platform not between", value1, value2, "platform");
			return (Criteria) this;
		}

		public Criteria andTypeIsNull() {
			addCriterion("type is null");
			return (Criteria) this;
		}

		public Criteria andTypeIsNotNull() {
			addCriterion("type is not null");
			return (Criteria) this;
		}

		public Criteria andTypeEqualTo(Integer value) {
			addCriterion("type =", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotEqualTo(Integer value) {
			addCriterion("type <>", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThan(Integer value) {
			addCriterion("type >", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
			addCriterion("type >=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThan(Integer value) {
			addCriterion("type <", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeLessThanOrEqualTo(Integer value) {
			addCriterion("type <=", value, "type");
			return (Criteria) this;
		}

		public Criteria andTypeIn(List<Integer> values) {
			addCriterion("type in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotIn(List<Integer> values) {
			addCriterion("type not in", values, "type");
			return (Criteria) this;
		}

		public Criteria andTypeBetween(Integer value1, Integer value2) {
			addCriterion("type between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andTypeNotBetween(Integer value1, Integer value2) {
			addCriterion("type not between", value1, value2, "type");
			return (Criteria) this;
		}

		public Criteria andNodeIdIsNull() {
			addCriterion("node_id is null");
			return (Criteria) this;
		}

		public Criteria andNodeIdIsNotNull() {
			addCriterion("node_id is not null");
			return (Criteria) this;
		}

		public Criteria andNodeIdEqualTo(Integer value) {
			addCriterion("node_id =", value, "nodeId");
			return (Criteria) this;
		}

		public Criteria andNodeIdNotEqualTo(Integer value) {
			addCriterion("node_id <>", value, "nodeId");
			return (Criteria) this;
		}

		public Criteria andNodeIdGreaterThan(Integer value) {
			addCriterion("node_id >", value, "nodeId");
			return (Criteria) this;
		}

		public Criteria andNodeIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("node_id >=", value, "nodeId");
			return (Criteria) this;
		}

		public Criteria andNodeIdLessThan(Integer value) {
			addCriterion("node_id <", value, "nodeId");
			return (Criteria) this;
		}

		public Criteria andNodeIdLessThanOrEqualTo(Integer value) {
			addCriterion("node_id <=", value, "nodeId");
			return (Criteria) this;
		}

		public Criteria andNodeIdIn(List<Integer> values) {
			addCriterion("node_id in", values, "nodeId");
			return (Criteria) this;
		}

		public Criteria andNodeIdNotIn(List<Integer> values) {
			addCriterion("node_id not in", values, "nodeId");
			return (Criteria) this;
		}

		public Criteria andNodeIdBetween(Integer value1, Integer value2) {
			addCriterion("node_id between", value1, value2, "nodeId");
			return (Criteria) this;
		}

		public Criteria andNodeIdNotBetween(Integer value1, Integer value2) {
			addCriterion("node_id not between", value1, value2, "nodeId");
			return (Criteria) this;
		}

		public Criteria andAuthorIsNull() {
			addCriterion("author is null");
			return (Criteria) this;
		}

		public Criteria andAuthorIsNotNull() {
			addCriterion("author is not null");
			return (Criteria) this;
		}

		public Criteria andAuthorEqualTo(String value) {
			addCriterion("author =", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotEqualTo(String value) {
			addCriterion("author <>", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorGreaterThan(String value) {
			addCriterion("author >", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorGreaterThanOrEqualTo(String value) {
			addCriterion("author >=", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorLessThan(String value) {
			addCriterion("author <", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorLessThanOrEqualTo(String value) {
			addCriterion("author <=", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorLike(String value) {
			addCriterion("author like", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotLike(String value) {
			addCriterion("author not like", value, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorIn(List<String> values) {
			addCriterion("author in", values, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotIn(List<String> values) {
			addCriterion("author not in", values, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorBetween(String value1, String value2) {
			addCriterion("author between", value1, value2, "author");
			return (Criteria) this;
		}

		public Criteria andAuthorNotBetween(String value1, String value2) {
			addCriterion("author not between", value1, value2, "author");
			return (Criteria) this;
		}

		public Criteria andPubtimeIsNull() {
			addCriterion("pubtime is null");
			return (Criteria) this;
		}

		public Criteria andPubtimeIsNotNull() {
			addCriterion("pubtime is not null");
			return (Criteria) this;
		}

		public Criteria andPubtimeEqualTo(Date value) {
			addCriterion("pubtime =", value, "pubtime");
			return (Criteria) this;
		}

		public Criteria andPubtimeNotEqualTo(Date value) {
			addCriterion("pubtime <>", value, "pubtime");
			return (Criteria) this;
		}

		public Criteria andPubtimeGreaterThan(Date value) {
			addCriterion("pubtime >", value, "pubtime");
			return (Criteria) this;
		}

		public Criteria andPubtimeGreaterThanOrEqualTo(Date value) {
			addCriterion("pubtime >=", value, "pubtime");
			return (Criteria) this;
		}

		public Criteria andPubtimeLessThan(Date value) {
			addCriterion("pubtime <", value, "pubtime");
			return (Criteria) this;
		}

		public Criteria andPubtimeLessThanOrEqualTo(Date value) {
			addCriterion("pubtime <=", value, "pubtime");
			return (Criteria) this;
		}

		public Criteria andPubtimeIn(List<Date> values) {
			addCriterion("pubtime in", values, "pubtime");
			return (Criteria) this;
		}

		public Criteria andPubtimeNotIn(List<Date> values) {
			addCriterion("pubtime not in", values, "pubtime");
			return (Criteria) this;
		}

		public Criteria andPubtimeBetween(Date value1, Date value2) {
			addCriterion("pubtime between", value1, value2, "pubtime");
			return (Criteria) this;
		}

		public Criteria andPubtimeNotBetween(Date value1, Date value2) {
			addCriterion("pubtime not between", value1, value2, "pubtime");
			return (Criteria) this;
		}

		public Criteria andUptimeIsNull() {
			addCriterion("uptime is null");
			return (Criteria) this;
		}

		public Criteria andUptimeIsNotNull() {
			addCriterion("uptime is not null");
			return (Criteria) this;
		}

		public Criteria andUptimeEqualTo(Date value) {
			addCriterion("uptime =", value, "uptime");
			return (Criteria) this;
		}

		public Criteria andUptimeNotEqualTo(Date value) {
			addCriterion("uptime <>", value, "uptime");
			return (Criteria) this;
		}

		public Criteria andUptimeGreaterThan(Date value) {
			addCriterion("uptime >", value, "uptime");
			return (Criteria) this;
		}

		public Criteria andUptimeGreaterThanOrEqualTo(Date value) {
			addCriterion("uptime >=", value, "uptime");
			return (Criteria) this;
		}

		public Criteria andUptimeLessThan(Date value) {
			addCriterion("uptime <", value, "uptime");
			return (Criteria) this;
		}

		public Criteria andUptimeLessThanOrEqualTo(Date value) {
			addCriterion("uptime <=", value, "uptime");
			return (Criteria) this;
		}

		public Criteria andUptimeIn(List<Date> values) {
			addCriterion("uptime in", values, "uptime");
			return (Criteria) this;
		}

		public Criteria andUptimeNotIn(List<Date> values) {
			addCriterion("uptime not in", values, "uptime");
			return (Criteria) this;
		}

		public Criteria andUptimeBetween(Date value1, Date value2) {
			addCriterion("uptime between", value1, value2, "uptime");
			return (Criteria) this;
		}

		public Criteria andUptimeNotBetween(Date value1, Date value2) {
			addCriterion("uptime not between", value1, value2, "uptime");
			return (Criteria) this;
		}

		public Criteria andLevelIsNull() {
			addCriterion("level is null");
			return (Criteria) this;
		}

		public Criteria andLevelIsNotNull() {
			addCriterion("level is not null");
			return (Criteria) this;
		}

		public Criteria andLevelEqualTo(Integer value) {
			addCriterion("level =", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotEqualTo(Integer value) {
			addCriterion("level <>", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelGreaterThan(Integer value) {
			addCriterion("level >", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
			addCriterion("level >=", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelLessThan(Integer value) {
			addCriterion("level <", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelLessThanOrEqualTo(Integer value) {
			addCriterion("level <=", value, "level");
			return (Criteria) this;
		}

		public Criteria andLevelIn(List<Integer> values) {
			addCriterion("level in", values, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotIn(List<Integer> values) {
			addCriterion("level not in", values, "level");
			return (Criteria) this;
		}

		public Criteria andLevelBetween(Integer value1, Integer value2) {
			addCriterion("level between", value1, value2, "level");
			return (Criteria) this;
		}

		public Criteria andLevelNotBetween(Integer value1, Integer value2) {
			addCriterion("level not between", value1, value2, "level");
			return (Criteria) this;
		}

		public Criteria andIfpubIsNull() {
			addCriterion("ifpub is null");
			return (Criteria) this;
		}

		public Criteria andIfpubIsNotNull() {
			addCriterion("ifpub is not null");
			return (Criteria) this;
		}

		public Criteria andIfpubEqualTo(Integer value) {
			addCriterion("ifpub =", value, "ifpub");
			return (Criteria) this;
		}

		public Criteria andIfpubNotEqualTo(Integer value) {
			addCriterion("ifpub <>", value, "ifpub");
			return (Criteria) this;
		}

		public Criteria andIfpubGreaterThan(Integer value) {
			addCriterion("ifpub >", value, "ifpub");
			return (Criteria) this;
		}

		public Criteria andIfpubGreaterThanOrEqualTo(Integer value) {
			addCriterion("ifpub >=", value, "ifpub");
			return (Criteria) this;
		}

		public Criteria andIfpubLessThan(Integer value) {
			addCriterion("ifpub <", value, "ifpub");
			return (Criteria) this;
		}

		public Criteria andIfpubLessThanOrEqualTo(Integer value) {
			addCriterion("ifpub <=", value, "ifpub");
			return (Criteria) this;
		}

		public Criteria andIfpubIn(List<Integer> values) {
			addCriterion("ifpub in", values, "ifpub");
			return (Criteria) this;
		}

		public Criteria andIfpubNotIn(List<Integer> values) {
			addCriterion("ifpub not in", values, "ifpub");
			return (Criteria) this;
		}

		public Criteria andIfpubBetween(Integer value1, Integer value2) {
			addCriterion("ifpub between", value1, value2, "ifpub");
			return (Criteria) this;
		}

		public Criteria andIfpubNotBetween(Integer value1, Integer value2) {
			addCriterion("ifpub not between", value1, value2, "ifpub");
			return (Criteria) this;
		}

		public Criteria andPvIsNull() {
			addCriterion("pv is null");
			return (Criteria) this;
		}

		public Criteria andPvIsNotNull() {
			addCriterion("pv is not null");
			return (Criteria) this;
		}

		public Criteria andPvEqualTo(Integer value) {
			addCriterion("pv =", value, "pv");
			return (Criteria) this;
		}

		public Criteria andPvNotEqualTo(Integer value) {
			addCriterion("pv <>", value, "pv");
			return (Criteria) this;
		}

		public Criteria andPvGreaterThan(Integer value) {
			addCriterion("pv >", value, "pv");
			return (Criteria) this;
		}

		public Criteria andPvGreaterThanOrEqualTo(Integer value) {
			addCriterion("pv >=", value, "pv");
			return (Criteria) this;
		}

		public Criteria andPvLessThan(Integer value) {
			addCriterion("pv <", value, "pv");
			return (Criteria) this;
		}

		public Criteria andPvLessThanOrEqualTo(Integer value) {
			addCriterion("pv <=", value, "pv");
			return (Criteria) this;
		}

		public Criteria andPvIn(List<Integer> values) {
			addCriterion("pv in", values, "pv");
			return (Criteria) this;
		}

		public Criteria andPvNotIn(List<Integer> values) {
			addCriterion("pv not in", values, "pv");
			return (Criteria) this;
		}

		public Criteria andPvBetween(Integer value1, Integer value2) {
			addCriterion("pv between", value1, value2, "pv");
			return (Criteria) this;
		}

		public Criteria andPvNotBetween(Integer value1, Integer value2) {
			addCriterion("pv not between", value1, value2, "pv");
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
	}

	/**
	 * This class was generated by MyBatis Generator. This class corresponds to the database table t_content_article_indexs
	 * @mbggenerated  Mon Jul 06 17:33:18 CST 2015
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
     * This class corresponds to the database table t_content_article_indexs
     *
     * @mbggenerated do_not_delete_during_merge Mon Jul 06 17:13:28 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}