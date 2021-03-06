package com.cnit.yoyo.model.goods;

import java.util.ArrayList;
import java.util.List;

public class SpecValuesExample {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    protected String orderByClause;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    protected boolean distinct;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public SpecValuesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
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

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSpecValueIdIsNull() {
            addCriterion("SPEC_VALUE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdIsNotNull() {
            addCriterion("SPEC_VALUE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdEqualTo(Integer value) {
            addCriterion("SPEC_VALUE_ID =", value, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdNotEqualTo(Integer value) {
            addCriterion("SPEC_VALUE_ID <>", value, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdGreaterThan(Integer value) {
            addCriterion("SPEC_VALUE_ID >", value, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPEC_VALUE_ID >=", value, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdLessThan(Integer value) {
            addCriterion("SPEC_VALUE_ID <", value, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdLessThanOrEqualTo(Integer value) {
            addCriterion("SPEC_VALUE_ID <=", value, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdIn(List<Integer> values) {
            addCriterion("SPEC_VALUE_ID in", values, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdNotIn(List<Integer> values) {
            addCriterion("SPEC_VALUE_ID not in", values, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdBetween(Integer value1, Integer value2) {
            addCriterion("SPEC_VALUE_ID between", value1, value2, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecValueIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SPEC_VALUE_ID not between", value1, value2, "specValueId");
            return (Criteria) this;
        }

        public Criteria andSpecIdIsNull() {
            addCriterion("SPEC_ID is null");
            return (Criteria) this;
        }

        public Criteria andSpecIdIsNotNull() {
            addCriterion("SPEC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSpecIdEqualTo(Integer value) {
            addCriterion("SPEC_ID =", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdNotEqualTo(Integer value) {
            addCriterion("SPEC_ID <>", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdGreaterThan(Integer value) {
            addCriterion("SPEC_ID >", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SPEC_ID >=", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdLessThan(Integer value) {
            addCriterion("SPEC_ID <", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdLessThanOrEqualTo(Integer value) {
            addCriterion("SPEC_ID <=", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdIn(List<Integer> values) {
            addCriterion("SPEC_ID in", values, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdNotIn(List<Integer> values) {
            addCriterion("SPEC_ID not in", values, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdBetween(Integer value1, Integer value2) {
            addCriterion("SPEC_ID between", value1, value2, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SPEC_ID not between", value1, value2, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameIsNull() {
            addCriterion("SPEC_VALUE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameIsNotNull() {
            addCriterion("SPEC_VALUE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameEqualTo(String value) {
            addCriterion("SPEC_VALUE_NAME =", value, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameNotEqualTo(String value) {
            addCriterion("SPEC_VALUE_NAME <>", value, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameGreaterThan(String value) {
            addCriterion("SPEC_VALUE_NAME >", value, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_VALUE_NAME >=", value, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameLessThan(String value) {
            addCriterion("SPEC_VALUE_NAME <", value, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameLessThanOrEqualTo(String value) {
            addCriterion("SPEC_VALUE_NAME <=", value, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameLike(String value) {
            addCriterion("SPEC_VALUE_NAME like", value, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameNotLike(String value) {
            addCriterion("SPEC_VALUE_NAME not like", value, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameIn(List<String> values) {
            addCriterion("SPEC_VALUE_NAME in", values, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameNotIn(List<String> values) {
            addCriterion("SPEC_VALUE_NAME not in", values, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameBetween(String value1, String value2) {
            addCriterion("SPEC_VALUE_NAME between", value1, value2, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueNameNotBetween(String value1, String value2) {
            addCriterion("SPEC_VALUE_NAME not between", value1, value2, "specValueName");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasIsNull() {
            addCriterion("SPEC_VALUE_ALIAS is null");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasIsNotNull() {
            addCriterion("SPEC_VALUE_ALIAS is not null");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasEqualTo(String value) {
            addCriterion("SPEC_VALUE_ALIAS =", value, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasNotEqualTo(String value) {
            addCriterion("SPEC_VALUE_ALIAS <>", value, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasGreaterThan(String value) {
            addCriterion("SPEC_VALUE_ALIAS >", value, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_VALUE_ALIAS >=", value, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasLessThan(String value) {
            addCriterion("SPEC_VALUE_ALIAS <", value, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasLessThanOrEqualTo(String value) {
            addCriterion("SPEC_VALUE_ALIAS <=", value, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasLike(String value) {
            addCriterion("SPEC_VALUE_ALIAS like", value, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasNotLike(String value) {
            addCriterion("SPEC_VALUE_ALIAS not like", value, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasIn(List<String> values) {
            addCriterion("SPEC_VALUE_ALIAS in", values, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasNotIn(List<String> values) {
            addCriterion("SPEC_VALUE_ALIAS not in", values, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasBetween(String value1, String value2) {
            addCriterion("SPEC_VALUE_ALIAS between", value1, value2, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueAliasNotBetween(String value1, String value2) {
            addCriterion("SPEC_VALUE_ALIAS not between", value1, value2, "specValueAlias");
            return (Criteria) this;
        }

        public Criteria andSpecValueIsNull() {
            addCriterion("SPEC_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andSpecValueIsNotNull() {
            addCriterion("SPEC_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andSpecValueEqualTo(String value) {
            addCriterion("SPEC_VALUE =", value, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueNotEqualTo(String value) {
            addCriterion("SPEC_VALUE <>", value, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueGreaterThan(String value) {
            addCriterion("SPEC_VALUE >", value, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_VALUE >=", value, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueLessThan(String value) {
            addCriterion("SPEC_VALUE <", value, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueLessThanOrEqualTo(String value) {
            addCriterion("SPEC_VALUE <=", value, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueLike(String value) {
            addCriterion("SPEC_VALUE like", value, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueNotLike(String value) {
            addCriterion("SPEC_VALUE not like", value, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueIn(List<String> values) {
            addCriterion("SPEC_VALUE in", values, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueNotIn(List<String> values) {
            addCriterion("SPEC_VALUE not in", values, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueBetween(String value1, String value2) {
            addCriterion("SPEC_VALUE between", value1, value2, "specValue");
            return (Criteria) this;
        }

        public Criteria andSpecValueNotBetween(String value1, String value2) {
            addCriterion("SPEC_VALUE not between", value1, value2, "specValue");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNull() {
            addCriterion("ORDER_NUM is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("ORDER_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(Integer value) {
            addCriterion("ORDER_NUM =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(Integer value) {
            addCriterion("ORDER_NUM <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(Integer value) {
            addCriterion("ORDER_NUM >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("ORDER_NUM >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(Integer value) {
            addCriterion("ORDER_NUM <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("ORDER_NUM <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<Integer> values) {
            addCriterion("ORDER_NUM in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<Integer> values) {
            addCriterion("ORDER_NUM not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_NUM between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("ORDER_NUM not between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andDisabledIsNull() {
            addCriterion("DISABLED is null");
            return (Criteria) this;
        }

        public Criteria andDisabledIsNotNull() {
            addCriterion("DISABLED is not null");
            return (Criteria) this;
        }

        public Criteria andDisabledEqualTo(String value) {
            addCriterion("DISABLED =", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotEqualTo(String value) {
            addCriterion("DISABLED <>", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledGreaterThan(String value) {
            addCriterion("DISABLED >", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledGreaterThanOrEqualTo(String value) {
            addCriterion("DISABLED >=", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLessThan(String value) {
            addCriterion("DISABLED <", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLessThanOrEqualTo(String value) {
            addCriterion("DISABLED <=", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledLike(String value) {
            addCriterion("DISABLED like", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotLike(String value) {
            addCriterion("DISABLED not like", value, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledIn(List<String> values) {
            addCriterion("DISABLED in", values, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotIn(List<String> values) {
            addCriterion("DISABLED not in", values, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledBetween(String value1, String value2) {
            addCriterion("DISABLED between", value1, value2, "disabled");
            return (Criteria) this;
        }

        public Criteria andDisabledNotBetween(String value1, String value2) {
            addCriterion("DISABLED not between", value1, value2, "disabled");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table t_spec_values
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
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

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
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
     * This class corresponds to the database table t_spec_values
     *
     * @mbggenerated do_not_delete_during_merge Sat Mar 21 17:29:03 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}