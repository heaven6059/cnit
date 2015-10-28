package com.cnit.yoyo.model.car;

import java.util.ArrayList;
import java.util.List;

public class AccessoryCarIndexExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccessoryCarIndexExample() {
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

        public Criteria andRelateIdIsNull() {
            addCriterion("RELATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andRelateIdIsNotNull() {
            addCriterion("RELATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRelateIdEqualTo(Integer value) {
            addCriterion("RELATE_ID =", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdNotEqualTo(Integer value) {
            addCriterion("RELATE_ID <>", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdGreaterThan(Integer value) {
            addCriterion("RELATE_ID >", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("RELATE_ID >=", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdLessThan(Integer value) {
            addCriterion("RELATE_ID <", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdLessThanOrEqualTo(Integer value) {
            addCriterion("RELATE_ID <=", value, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdIn(List<Integer> values) {
            addCriterion("RELATE_ID in", values, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdNotIn(List<Integer> values) {
            addCriterion("RELATE_ID not in", values, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdBetween(Integer value1, Integer value2) {
            addCriterion("RELATE_ID between", value1, value2, "relateId");
            return (Criteria) this;
        }

        public Criteria andRelateIdNotBetween(Integer value1, Integer value2) {
            addCriterion("RELATE_ID not between", value1, value2, "relateId");
            return (Criteria) this;
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

        public Criteria andCarIdIsNull() {
            addCriterion("CAR_ID is null");
            return (Criteria) this;
        }

        public Criteria andCarIdIsNotNull() {
            addCriterion("CAR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCarIdEqualTo(Integer value) {
            addCriterion("CAR_ID =", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotEqualTo(Integer value) {
            addCriterion("CAR_ID <>", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThan(Integer value) {
            addCriterion("CAR_ID >", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("CAR_ID >=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThan(Integer value) {
            addCriterion("CAR_ID <", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdLessThanOrEqualTo(Integer value) {
            addCriterion("CAR_ID <=", value, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdIn(List<Integer> values) {
            addCriterion("CAR_ID in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotIn(List<Integer> values) {
            addCriterion("CAR_ID not in", values, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdBetween(Integer value1, Integer value2) {
            addCriterion("CAR_ID between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andCarIdNotBetween(Integer value1, Integer value2) {
            addCriterion("CAR_ID not between", value1, value2, "carId");
            return (Criteria) this;
        }

        public Criteria andCarNameIsNull() {
            addCriterion("CAR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCarNameIsNotNull() {
            addCriterion("CAR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCarNameEqualTo(String value) {
            addCriterion("CAR_NAME =", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotEqualTo(String value) {
            addCriterion("CAR_NAME <>", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameGreaterThan(String value) {
            addCriterion("CAR_NAME >", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameGreaterThanOrEqualTo(String value) {
            addCriterion("CAR_NAME >=", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameLessThan(String value) {
            addCriterion("CAR_NAME <", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameLessThanOrEqualTo(String value) {
            addCriterion("CAR_NAME <=", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameLike(String value) {
            addCriterion("CAR_NAME like", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotLike(String value) {
            addCriterion("CAR_NAME not like", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameIn(List<String> values) {
            addCriterion("CAR_NAME in", values, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotIn(List<String> values) {
            addCriterion("CAR_NAME not in", values, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameBetween(String value1, String value2) {
            addCriterion("CAR_NAME between", value1, value2, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotBetween(String value1, String value2) {
            addCriterion("CAR_NAME not between", value1, value2, "carName");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIsNull() {
            addCriterion("FACTORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIsNotNull() {
            addCriterion("FACTORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryIdEqualTo(Integer value) {
            addCriterion("FACTORY_ID =", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotEqualTo(Integer value) {
            addCriterion("FACTORY_ID <>", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThan(Integer value) {
            addCriterion("FACTORY_ID >", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("FACTORY_ID >=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThan(Integer value) {
            addCriterion("FACTORY_ID <", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("FACTORY_ID <=", value, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdIn(List<Integer> values) {
            addCriterion("FACTORY_ID in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotIn(List<Integer> values) {
            addCriterion("FACTORY_ID not in", values, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdBetween(Integer value1, Integer value2) {
            addCriterion("FACTORY_ID between", value1, value2, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("FACTORY_ID not between", value1, value2, "factoryId");
            return (Criteria) this;
        }

        public Criteria andFactoryNameIsNull() {
            addCriterion("FACTORY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFactoryNameIsNotNull() {
            addCriterion("FACTORY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryNameEqualTo(String value) {
            addCriterion("FACTORY_NAME =", value, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameNotEqualTo(String value) {
            addCriterion("FACTORY_NAME <>", value, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameGreaterThan(String value) {
            addCriterion("FACTORY_NAME >", value, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameGreaterThanOrEqualTo(String value) {
            addCriterion("FACTORY_NAME >=", value, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameLessThan(String value) {
            addCriterion("FACTORY_NAME <", value, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameLessThanOrEqualTo(String value) {
            addCriterion("FACTORY_NAME <=", value, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameLike(String value) {
            addCriterion("FACTORY_NAME like", value, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameNotLike(String value) {
            addCriterion("FACTORY_NAME not like", value, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameIn(List<String> values) {
            addCriterion("FACTORY_NAME in", values, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameNotIn(List<String> values) {
            addCriterion("FACTORY_NAME not in", values, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameBetween(String value1, String value2) {
            addCriterion("FACTORY_NAME between", value1, value2, "factoryName");
            return (Criteria) this;
        }

        public Criteria andFactoryNameNotBetween(String value1, String value2) {
            addCriterion("FACTORY_NAME not between", value1, value2, "factoryName");
            return (Criteria) this;
        }

        public Criteria andDeptIdIsNull() {
            addCriterion("DEPT_ID is null");
            return (Criteria) this;
        }

        public Criteria andDeptIdIsNotNull() {
            addCriterion("DEPT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDeptIdEqualTo(Integer value) {
            addCriterion("DEPT_ID =", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotEqualTo(Integer value) {
            addCriterion("DEPT_ID <>", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThan(Integer value) {
            addCriterion("DEPT_ID >", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("DEPT_ID >=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThan(Integer value) {
            addCriterion("DEPT_ID <", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdLessThanOrEqualTo(Integer value) {
            addCriterion("DEPT_ID <=", value, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdIn(List<Integer> values) {
            addCriterion("DEPT_ID in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotIn(List<Integer> values) {
            addCriterion("DEPT_ID not in", values, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdBetween(Integer value1, Integer value2) {
            addCriterion("DEPT_ID between", value1, value2, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptIdNotBetween(Integer value1, Integer value2) {
            addCriterion("DEPT_ID not between", value1, value2, "deptId");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNull() {
            addCriterion("DEPT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeptNameIsNotNull() {
            addCriterion("DEPT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeptNameEqualTo(String value) {
            addCriterion("DEPT_NAME =", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotEqualTo(String value) {
            addCriterion("DEPT_NAME <>", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThan(String value) {
            addCriterion("DEPT_NAME >", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME >=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThan(String value) {
            addCriterion("DEPT_NAME <", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLessThanOrEqualTo(String value) {
            addCriterion("DEPT_NAME <=", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameLike(String value) {
            addCriterion("DEPT_NAME like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotLike(String value) {
            addCriterion("DEPT_NAME not like", value, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameIn(List<String> values) {
            addCriterion("DEPT_NAME in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotIn(List<String> values) {
            addCriterion("DEPT_NAME not in", values, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameBetween(String value1, String value2) {
            addCriterion("DEPT_NAME between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andDeptNameNotBetween(String value1, String value2) {
            addCriterion("DEPT_NAME not between", value1, value2, "deptName");
            return (Criteria) this;
        }

        public Criteria andYearIdIsNull() {
            addCriterion("YEAR_ID is null");
            return (Criteria) this;
        }

        public Criteria andYearIdIsNotNull() {
            addCriterion("YEAR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andYearIdEqualTo(Integer value) {
            addCriterion("YEAR_ID =", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdNotEqualTo(Integer value) {
            addCriterion("YEAR_ID <>", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdGreaterThan(Integer value) {
            addCriterion("YEAR_ID >", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("YEAR_ID >=", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdLessThan(Integer value) {
            addCriterion("YEAR_ID <", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdLessThanOrEqualTo(Integer value) {
            addCriterion("YEAR_ID <=", value, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdIn(List<Integer> values) {
            addCriterion("YEAR_ID in", values, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdNotIn(List<Integer> values) {
            addCriterion("YEAR_ID not in", values, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdBetween(Integer value1, Integer value2) {
            addCriterion("YEAR_ID between", value1, value2, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearIdNotBetween(Integer value1, Integer value2) {
            addCriterion("YEAR_ID not between", value1, value2, "yearId");
            return (Criteria) this;
        }

        public Criteria andYearValueIsNull() {
            addCriterion("YEAR_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andYearValueIsNotNull() {
            addCriterion("YEAR_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andYearValueEqualTo(String value) {
            addCriterion("YEAR_VALUE =", value, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueNotEqualTo(String value) {
            addCriterion("YEAR_VALUE <>", value, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueGreaterThan(String value) {
            addCriterion("YEAR_VALUE >", value, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueGreaterThanOrEqualTo(String value) {
            addCriterion("YEAR_VALUE >=", value, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueLessThan(String value) {
            addCriterion("YEAR_VALUE <", value, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueLessThanOrEqualTo(String value) {
            addCriterion("YEAR_VALUE <=", value, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueLike(String value) {
            addCriterion("YEAR_VALUE like", value, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueNotLike(String value) {
            addCriterion("YEAR_VALUE not like", value, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueIn(List<String> values) {
            addCriterion("YEAR_VALUE in", values, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueNotIn(List<String> values) {
            addCriterion("YEAR_VALUE not in", values, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueBetween(String value1, String value2) {
            addCriterion("YEAR_VALUE between", value1, value2, "yearValue");
            return (Criteria) this;
        }

        public Criteria andYearValueNotBetween(String value1, String value2) {
            addCriterion("YEAR_VALUE not between", value1, value2, "yearValue");
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