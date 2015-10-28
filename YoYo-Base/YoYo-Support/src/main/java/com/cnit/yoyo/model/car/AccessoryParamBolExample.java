package com.cnit.yoyo.model.car;

import java.util.ArrayList;
import java.util.List;

public class AccessoryParamBolExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccessoryParamBolExample() {
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

        public Criteria andAccIdIsNull() {
            addCriterion("ACC_ID is null");
            return (Criteria) this;
        }

        public Criteria andAccIdIsNotNull() {
            addCriterion("ACC_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAccIdEqualTo(Integer value) {
            addCriterion("ACC_ID =", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotEqualTo(Integer value) {
            addCriterion("ACC_ID <>", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThan(Integer value) {
            addCriterion("ACC_ID >", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ACC_ID >=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThan(Integer value) {
            addCriterion("ACC_ID <", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdLessThanOrEqualTo(Integer value) {
            addCriterion("ACC_ID <=", value, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdIn(List<Integer> values) {
            addCriterion("ACC_ID in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotIn(List<Integer> values) {
            addCriterion("ACC_ID not in", values, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdBetween(Integer value1, Integer value2) {
            addCriterion("ACC_ID between", value1, value2, "accId");
            return (Criteria) this;
        }

        public Criteria andAccIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ACC_ID not between", value1, value2, "accId");
            return (Criteria) this;
        }

        public Criteria andParamIdIsNull() {
            addCriterion("PARAM_ID is null");
            return (Criteria) this;
        }

        public Criteria andParamIdIsNotNull() {
            addCriterion("PARAM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParamIdEqualTo(Integer value) {
            addCriterion("PARAM_ID =", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotEqualTo(Integer value) {
            addCriterion("PARAM_ID <>", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThan(Integer value) {
            addCriterion("PARAM_ID >", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARAM_ID >=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThan(Integer value) {
            addCriterion("PARAM_ID <", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdLessThanOrEqualTo(Integer value) {
            addCriterion("PARAM_ID <=", value, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdIn(List<Integer> values) {
            addCriterion("PARAM_ID in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotIn(List<Integer> values) {
            addCriterion("PARAM_ID not in", values, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdBetween(Integer value1, Integer value2) {
            addCriterion("PARAM_ID between", value1, value2, "paramId");
            return (Criteria) this;
        }

        public Criteria andParamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PARAM_ID not between", value1, value2, "paramId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdIsNull() {
            addCriterion("CATALOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andCatalogIdIsNotNull() {
            addCriterion("CATALOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCatalogIdEqualTo(Integer value) {
            addCriterion("CATALOG_ID =", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdNotEqualTo(Integer value) {
            addCriterion("CATALOG_ID <>", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdGreaterThan(Integer value) {
            addCriterion("CATALOG_ID >", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CATALOG_ID >=", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdLessThan(Integer value) {
            addCriterion("CATALOG_ID <", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdLessThanOrEqualTo(Integer value) {
            addCriterion("CATALOG_ID <=", value, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdIn(List<Integer> values) {
            addCriterion("CATALOG_ID in", values, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdNotIn(List<Integer> values) {
            addCriterion("CATALOG_ID not in", values, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdBetween(Integer value1, Integer value2) {
            addCriterion("CATALOG_ID between", value1, value2, "catalogId");
            return (Criteria) this;
        }

        public Criteria andCatalogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CATALOG_ID not between", value1, value2, "catalogId");
            return (Criteria) this;
        }

        public Criteria andValueIsNull() {
            addCriterion("VALUE is null");
            return (Criteria) this;
        }

        public Criteria andValueIsNotNull() {
            addCriterion("VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andValueEqualTo(Boolean value) {
            addCriterion("VALUE =", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotEqualTo(Boolean value) {
            addCriterion("VALUE <>", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThan(Boolean value) {
            addCriterion("VALUE >", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueGreaterThanOrEqualTo(Boolean value) {
            addCriterion("VALUE >=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThan(Boolean value) {
            addCriterion("VALUE <", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueLessThanOrEqualTo(Boolean value) {
            addCriterion("VALUE <=", value, "value");
            return (Criteria) this;
        }

        public Criteria andValueIn(List<Boolean> values) {
            addCriterion("VALUE in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotIn(List<Boolean> values) {
            addCriterion("VALUE not in", values, "value");
            return (Criteria) this;
        }

        public Criteria andValueBetween(Boolean value1, Boolean value2) {
            addCriterion("VALUE between", value1, value2, "value");
            return (Criteria) this;
        }

        public Criteria andValueNotBetween(Boolean value1, Boolean value2) {
            addCriterion("VALUE not between", value1, value2, "value");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
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
}