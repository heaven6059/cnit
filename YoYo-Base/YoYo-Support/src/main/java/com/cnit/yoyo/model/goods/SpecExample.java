package com.cnit.yoyo.model.goods;

import java.util.ArrayList;
import java.util.List;

public class SpecExample {
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    protected String orderByClause;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    protected boolean distinct;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public SpecExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
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
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_spec
     * @mbggenerated  Wed Mar 25 10:32:24 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table t_spec
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

        public Criteria andSpecNameIsNull() {
            addCriterion("SPEC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSpecNameIsNotNull() {
            addCriterion("SPEC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSpecNameEqualTo(String value) {
            addCriterion("SPEC_NAME =", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotEqualTo(String value) {
            addCriterion("SPEC_NAME <>", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameGreaterThan(String value) {
            addCriterion("SPEC_NAME >", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_NAME >=", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameLessThan(String value) {
            addCriterion("SPEC_NAME <", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameLessThanOrEqualTo(String value) {
            addCriterion("SPEC_NAME <=", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameLike(String value) {
            addCriterion("SPEC_NAME like", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotLike(String value) {
            addCriterion("SPEC_NAME not like", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameIn(List<String> values) {
            addCriterion("SPEC_NAME in", values, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotIn(List<String> values) {
            addCriterion("SPEC_NAME not in", values, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameBetween(String value1, String value2) {
            addCriterion("SPEC_NAME between", value1, value2, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotBetween(String value1, String value2) {
            addCriterion("SPEC_NAME not between", value1, value2, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecMemoIsNull() {
            addCriterion("SPEC_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andSpecMemoIsNotNull() {
            addCriterion("SPEC_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andSpecMemoEqualTo(String value) {
            addCriterion("SPEC_MEMO =", value, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoNotEqualTo(String value) {
            addCriterion("SPEC_MEMO <>", value, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoGreaterThan(String value) {
            addCriterion("SPEC_MEMO >", value, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_MEMO >=", value, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoLessThan(String value) {
            addCriterion("SPEC_MEMO <", value, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoLessThanOrEqualTo(String value) {
            addCriterion("SPEC_MEMO <=", value, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoLike(String value) {
            addCriterion("SPEC_MEMO like", value, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoNotLike(String value) {
            addCriterion("SPEC_MEMO not like", value, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoIn(List<String> values) {
            addCriterion("SPEC_MEMO in", values, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoNotIn(List<String> values) {
            addCriterion("SPEC_MEMO not in", values, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoBetween(String value1, String value2) {
            addCriterion("SPEC_MEMO between", value1, value2, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecMemoNotBetween(String value1, String value2) {
            addCriterion("SPEC_MEMO not between", value1, value2, "specMemo");
            return (Criteria) this;
        }

        public Criteria andSpecAliasIsNull() {
            addCriterion("SPEC_ALIAS is null");
            return (Criteria) this;
        }

        public Criteria andSpecAliasIsNotNull() {
            addCriterion("SPEC_ALIAS is not null");
            return (Criteria) this;
        }

        public Criteria andSpecAliasEqualTo(String value) {
            addCriterion("SPEC_ALIAS =", value, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasNotEqualTo(String value) {
            addCriterion("SPEC_ALIAS <>", value, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasGreaterThan(String value) {
            addCriterion("SPEC_ALIAS >", value, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_ALIAS >=", value, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasLessThan(String value) {
            addCriterion("SPEC_ALIAS <", value, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasLessThanOrEqualTo(String value) {
            addCriterion("SPEC_ALIAS <=", value, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasLike(String value) {
            addCriterion("SPEC_ALIAS like", value, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasNotLike(String value) {
            addCriterion("SPEC_ALIAS not like", value, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasIn(List<String> values) {
            addCriterion("SPEC_ALIAS in", values, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasNotIn(List<String> values) {
            addCriterion("SPEC_ALIAS not in", values, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasBetween(String value1, String value2) {
            addCriterion("SPEC_ALIAS between", value1, value2, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecAliasNotBetween(String value1, String value2) {
            addCriterion("SPEC_ALIAS not between", value1, value2, "specAlias");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeIsNull() {
            addCriterion("SPEC_SHOW_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeIsNotNull() {
            addCriterion("SPEC_SHOW_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeEqualTo(String value) {
            addCriterion("SPEC_SHOW_TYPE =", value, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeNotEqualTo(String value) {
            addCriterion("SPEC_SHOW_TYPE <>", value, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeGreaterThan(String value) {
            addCriterion("SPEC_SHOW_TYPE >", value, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_SHOW_TYPE >=", value, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeLessThan(String value) {
            addCriterion("SPEC_SHOW_TYPE <", value, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeLessThanOrEqualTo(String value) {
            addCriterion("SPEC_SHOW_TYPE <=", value, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeLike(String value) {
            addCriterion("SPEC_SHOW_TYPE like", value, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeNotLike(String value) {
            addCriterion("SPEC_SHOW_TYPE not like", value, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeIn(List<String> values) {
            addCriterion("SPEC_SHOW_TYPE in", values, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeNotIn(List<String> values) {
            addCriterion("SPEC_SHOW_TYPE not in", values, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeBetween(String value1, String value2) {
            addCriterion("SPEC_SHOW_TYPE between", value1, value2, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecShowTypeNotBetween(String value1, String value2) {
            addCriterion("SPEC_SHOW_TYPE not between", value1, value2, "specShowType");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeIsNull() {
            addCriterion("SPEC_SELECT_MODE is null");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeIsNotNull() {
            addCriterion("SPEC_SELECT_MODE is not null");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeEqualTo(String value) {
            addCriterion("SPEC_SELECT_MODE =", value, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeNotEqualTo(String value) {
            addCriterion("SPEC_SELECT_MODE <>", value, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeGreaterThan(String value) {
            addCriterion("SPEC_SELECT_MODE >", value, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeGreaterThanOrEqualTo(String value) {
            addCriterion("SPEC_SELECT_MODE >=", value, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeLessThan(String value) {
            addCriterion("SPEC_SELECT_MODE <", value, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeLessThanOrEqualTo(String value) {
            addCriterion("SPEC_SELECT_MODE <=", value, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeLike(String value) {
            addCriterion("SPEC_SELECT_MODE like", value, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeNotLike(String value) {
            addCriterion("SPEC_SELECT_MODE not like", value, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeIn(List<String> values) {
            addCriterion("SPEC_SELECT_MODE in", values, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeNotIn(List<String> values) {
            addCriterion("SPEC_SELECT_MODE not in", values, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeBetween(String value1, String value2) {
            addCriterion("SPEC_SELECT_MODE between", value1, value2, "specSelectMode");
            return (Criteria) this;
        }

        public Criteria andSpecSelectModeNotBetween(String value1, String value2) {
            addCriterion("SPEC_SELECT_MODE not between", value1, value2, "specSelectMode");
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
     * This class was generated by MyBatis Generator. This class corresponds to the database table t_spec
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
     * This class corresponds to the database table t_spec
     *
     * @mbggenerated do_not_delete_during_merge Sat Mar 21 17:29:03 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}