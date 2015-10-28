package com.cnit.yoyo.model.goods;

import java.util.ArrayList;
import java.util.List;

public class AttributeExample {

    protected String orderByClause;
    
    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AttributeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andAttrIdIsNull() {
            addCriterion("ATTR_ID is null");
            return (Criteria) this;
        }

        public Criteria andAttrIdIsNotNull() {
            addCriterion("ATTR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAttrIdEqualTo(Integer value) {
            addCriterion("ATTR_ID =", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotEqualTo(Integer value) {
            addCriterion("ATTR_ID <>", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdGreaterThan(Integer value) {
            addCriterion("ATTR_ID >", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ATTR_ID >=", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdLessThan(Integer value) {
            addCriterion("ATTR_ID <", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdLessThanOrEqualTo(Integer value) {
            addCriterion("ATTR_ID <=", value, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdIn(List<Integer> values) {
            addCriterion("ATTR_ID in", values, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotIn(List<Integer> values) {
            addCriterion("ATTR_ID not in", values, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdBetween(Integer value1, Integer value2) {
            addCriterion("ATTR_ID between", value1, value2, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ATTR_ID not between", value1, value2, "attrId");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNull() {
            addCriterion("ATTR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAttrNameIsNotNull() {
            addCriterion("ATTR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAttrNameEqualTo(String value) {
            addCriterion("ATTR_NAME =", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotEqualTo(String value) {
            addCriterion("ATTR_NAME <>", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThan(String value) {
            addCriterion("ATTR_NAME >", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_NAME >=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThan(String value) {
            addCriterion("ATTR_NAME <", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLessThanOrEqualTo(String value) {
            addCriterion("ATTR_NAME <=", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameLike(String value) {
            addCriterion("ATTR_NAME like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotLike(String value) {
            addCriterion("ATTR_NAME not like", value, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameIn(List<String> values) {
            addCriterion("ATTR_NAME in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotIn(List<String> values) {
            addCriterion("ATTR_NAME not in", values, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameBetween(String value1, String value2) {
            addCriterion("ATTR_NAME between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrNameNotBetween(String value1, String value2) {
            addCriterion("ATTR_NAME not between", value1, value2, "attrName");
            return (Criteria) this;
        }

        public Criteria andAttrAliasIsNull() {
            addCriterion("ATTR_ALIAS is null");
            return (Criteria) this;
        }

        public Criteria andAttrAliasIsNotNull() {
            addCriterion("ATTR_ALIAS is not null");
            return (Criteria) this;
        }

        public Criteria andAttrAliasEqualTo(String value) {
            addCriterion("ATTR_ALIAS =", value, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasNotEqualTo(String value) {
            addCriterion("ATTR_ALIAS <>", value, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasGreaterThan(String value) {
            addCriterion("ATTR_ALIAS >", value, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_ALIAS >=", value, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasLessThan(String value) {
            addCriterion("ATTR_ALIAS <", value, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasLessThanOrEqualTo(String value) {
            addCriterion("ATTR_ALIAS <=", value, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasLike(String value) {
            addCriterion("ATTR_ALIAS like", value, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasNotLike(String value) {
            addCriterion("ATTR_ALIAS not like", value, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasIn(List<String> values) {
            addCriterion("ATTR_ALIAS in", values, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasNotIn(List<String> values) {
            addCriterion("ATTR_ALIAS not in", values, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasBetween(String value1, String value2) {
            addCriterion("ATTR_ALIAS between", value1, value2, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrAliasNotBetween(String value1, String value2) {
            addCriterion("ATTR_ALIAS not between", value1, value2, "attrAlias");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeIsNull() {
            addCriterion("ATTR_VALUE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeIsNotNull() {
            addCriterion("ATTR_VALUE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeEqualTo(String value) {
            addCriterion("ATTR_VALUE_TYPE =", value, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeNotEqualTo(String value) {
            addCriterion("ATTR_VALUE_TYPE <>", value, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeGreaterThan(String value) {
            addCriterion("ATTR_VALUE_TYPE >", value, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE_TYPE >=", value, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeLessThan(String value) {
            addCriterion("ATTR_VALUE_TYPE <", value, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeLessThanOrEqualTo(String value) {
            addCriterion("ATTR_VALUE_TYPE <=", value, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeLike(String value) {
            addCriterion("ATTR_VALUE_TYPE like", value, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeNotLike(String value) {
            addCriterion("ATTR_VALUE_TYPE not like", value, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeIn(List<String> values) {
            addCriterion("ATTR_VALUE_TYPE in", values, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeNotIn(List<String> values) {
            addCriterion("ATTR_VALUE_TYPE not in", values, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE_TYPE between", value1, value2, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrValueTypeNotBetween(String value1, String value2) {
            addCriterion("ATTR_VALUE_TYPE not between", value1, value2, "attrValueType");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleIsNull() {
            addCriterion("ATTR_ISMULTIPLE is null");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleIsNotNull() {
            addCriterion("ATTR_ISMULTIPLE is not null");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleEqualTo(String value) {
            addCriterion("ATTR_ISMULTIPLE =", value, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleNotEqualTo(String value) {
            addCriterion("ATTR_ISMULTIPLE <>", value, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleGreaterThan(String value) {
            addCriterion("ATTR_ISMULTIPLE >", value, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_ISMULTIPLE >=", value, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleLessThan(String value) {
            addCriterion("ATTR_ISMULTIPLE <", value, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleLessThanOrEqualTo(String value) {
            addCriterion("ATTR_ISMULTIPLE <=", value, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleLike(String value) {
            addCriterion("ATTR_ISMULTIPLE like", value, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleNotLike(String value) {
            addCriterion("ATTR_ISMULTIPLE not like", value, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleIn(List<String> values) {
            addCriterion("ATTR_ISMULTIPLE in", values, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleNotIn(List<String> values) {
            addCriterion("ATTR_ISMULTIPLE not in", values, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleBetween(String value1, String value2) {
            addCriterion("ATTR_ISMULTIPLE between", value1, value2, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrIsmultipleNotBetween(String value1, String value2) {
            addCriterion("ATTR_ISMULTIPLE not between", value1, value2, "attrIsmultiple");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeIsNull() {
            addCriterion("ATTR_INPUT_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeIsNotNull() {
            addCriterion("ATTR_INPUT_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeEqualTo(String value) {
            addCriterion("ATTR_INPUT_TYPE =", value, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeNotEqualTo(String value) {
            addCriterion("ATTR_INPUT_TYPE <>", value, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeGreaterThan(String value) {
            addCriterion("ATTR_INPUT_TYPE >", value, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_INPUT_TYPE >=", value, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeLessThan(String value) {
            addCriterion("ATTR_INPUT_TYPE <", value, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeLessThanOrEqualTo(String value) {
            addCriterion("ATTR_INPUT_TYPE <=", value, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeLike(String value) {
            addCriterion("ATTR_INPUT_TYPE like", value, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeNotLike(String value) {
            addCriterion("ATTR_INPUT_TYPE not like", value, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeIn(List<String> values) {
            addCriterion("ATTR_INPUT_TYPE in", values, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeNotIn(List<String> values) {
            addCriterion("ATTR_INPUT_TYPE not in", values, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeBetween(String value1, String value2) {
            addCriterion("ATTR_INPUT_TYPE between", value1, value2, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrInputTypeNotBetween(String value1, String value2) {
            addCriterion("ATTR_INPUT_TYPE not between", value1, value2, "attrInputType");
            return (Criteria) this;
        }

        public Criteria andAttrShowIsNull() {
            addCriterion("ATTR_SHOW is null");
            return (Criteria) this;
        }

        public Criteria andAttrShowIsNotNull() {
            addCriterion("ATTR_SHOW is not null");
            return (Criteria) this;
        }

        public Criteria andAttrShowEqualTo(String value) {
            addCriterion("ATTR_SHOW =", value, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowNotEqualTo(String value) {
            addCriterion("ATTR_SHOW <>", value, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowGreaterThan(String value) {
            addCriterion("ATTR_SHOW >", value, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowGreaterThanOrEqualTo(String value) {
            addCriterion("ATTR_SHOW >=", value, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowLessThan(String value) {
            addCriterion("ATTR_SHOW <", value, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowLessThanOrEqualTo(String value) {
            addCriterion("ATTR_SHOW <=", value, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowLike(String value) {
            addCriterion("ATTR_SHOW like", value, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowNotLike(String value) {
            addCriterion("ATTR_SHOW not like", value, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowIn(List<String> values) {
            addCriterion("ATTR_SHOW in", values, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowNotIn(List<String> values) {
            addCriterion("ATTR_SHOW not in", values, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowBetween(String value1, String value2) {
            addCriterion("ATTR_SHOW between", value1, value2, "attrShow");
            return (Criteria) this;
        }

        public Criteria andAttrShowNotBetween(String value1, String value2) {
            addCriterion("ATTR_SHOW not between", value1, value2, "attrShow");
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

        public Criteria andCatIdIsNull() {
            addCriterion("CAT_ID is null");
            return (Criteria) this;
        }

        public Criteria andCatIdIsNotNull() {
            addCriterion("CAT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCatIdEqualTo(Integer value) {
            addCriterion("CAT_ID =", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotEqualTo(Integer value) {
            addCriterion("CAT_ID <>", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThan(Integer value) {
            addCriterion("CAT_ID >", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CAT_ID >=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThan(Integer value) {
            addCriterion("CAT_ID <", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("CAT_ID <=", value, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdIn(List<Integer> values) {
            addCriterion("CAT_ID in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotIn(List<Integer> values) {
            addCriterion("CAT_ID not in", values, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdBetween(Integer value1, Integer value2) {
            addCriterion("CAT_ID between", value1, value2, "catId");
            return (Criteria) this;
        }

        public Criteria andCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CAT_ID not between", value1, value2, "catId");
            return (Criteria) this;
        }
    }

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

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}