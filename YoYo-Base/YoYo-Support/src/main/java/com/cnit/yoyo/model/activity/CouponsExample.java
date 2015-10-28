package com.cnit.yoyo.model.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponsExample {
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;

    public CouponsExample() {
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

        public Criteria andCpnsIdIsNull() {
            addCriterion("cpns_id is null");
            return (Criteria) this;
        }

        public Criteria andCpnsIdIsNotNull() {
            addCriterion("cpns_id is not null");
            return (Criteria) this;
        }

        public Criteria andCpnsIdEqualTo(Integer value) {
            addCriterion("cpns_id =", value, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsIdNotEqualTo(Integer value) {
            addCriterion("cpns_id <>", value, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsIdGreaterThan(Integer value) {
            addCriterion("cpns_id >", value, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpns_id >=", value, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsIdLessThan(Integer value) {
            addCriterion("cpns_id <", value, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsIdLessThanOrEqualTo(Integer value) {
            addCriterion("cpns_id <=", value, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsIdIn(List<Integer> values) {
            addCriterion("cpns_id in", values, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsIdNotIn(List<Integer> values) {
            addCriterion("cpns_id not in", values, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsIdBetween(Integer value1, Integer value2) {
            addCriterion("cpns_id between", value1, value2, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cpns_id not between", value1, value2, "cpnsId");
            return (Criteria) this;
        }

        public Criteria andCpnsNameIsNull() {
            addCriterion("cpns_name is null");
            return (Criteria) this;
        }

        public Criteria andCpnsNameIsNotNull() {
            addCriterion("cpns_name is not null");
            return (Criteria) this;
        }

        public Criteria andCpnsNameEqualTo(String value) {
            addCriterion("cpns_name =", value, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameNotEqualTo(String value) {
            addCriterion("cpns_name <>", value, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameGreaterThan(String value) {
            addCriterion("cpns_name >", value, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameGreaterThanOrEqualTo(String value) {
            addCriterion("cpns_name >=", value, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameLessThan(String value) {
            addCriterion("cpns_name <", value, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameLessThanOrEqualTo(String value) {
            addCriterion("cpns_name <=", value, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameLike(String value) {
            addCriterion("cpns_name like", value, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameNotLike(String value) {
            addCriterion("cpns_name not like", value, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameIn(List<String> values) {
            addCriterion("cpns_name in", values, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameNotIn(List<String> values) {
            addCriterion("cpns_name not in", values, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameBetween(String value1, String value2) {
            addCriterion("cpns_name between", value1, value2, "cpnsName");
            return (Criteria) this;
        }

        public Criteria andCpnsNameNotBetween(String value1, String value2) {
            addCriterion("cpns_name not between", value1, value2, "cpnsName");
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

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Long value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Long value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Long value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Long value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Long> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Long> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Long value1, Long value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Long value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Long value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Long value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Long value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Long value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Long value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Long> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Long> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Long value1, Long value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Long value1, Long value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andPmtIdIsNull() {
            addCriterion("pmt_id is null");
            return (Criteria) this;
        }

        public Criteria andPmtIdIsNotNull() {
            addCriterion("pmt_id is not null");
            return (Criteria) this;
        }

        public Criteria andPmtIdEqualTo(Integer value) {
            addCriterion("pmt_id =", value, "pmtId");
            return (Criteria) this;
        }

        public Criteria andPmtIdNotEqualTo(Integer value) {
            addCriterion("pmt_id <>", value, "pmtId");
            return (Criteria) this;
        }

        public Criteria andPmtIdGreaterThan(Integer value) {
            addCriterion("pmt_id >", value, "pmtId");
            return (Criteria) this;
        }

        public Criteria andPmtIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pmt_id >=", value, "pmtId");
            return (Criteria) this;
        }

        public Criteria andPmtIdLessThan(Integer value) {
            addCriterion("pmt_id <", value, "pmtId");
            return (Criteria) this;
        }

        public Criteria andPmtIdLessThanOrEqualTo(Integer value) {
            addCriterion("pmt_id <=", value, "pmtId");
            return (Criteria) this;
        }

        public Criteria andPmtIdIn(List<Integer> values) {
            addCriterion("pmt_id in", values, "pmtId");
            return (Criteria) this;
        }

        public Criteria andPmtIdNotIn(List<Integer> values) {
            addCriterion("pmt_id not in", values, "pmtId");
            return (Criteria) this;
        }

        public Criteria andPmtIdBetween(Integer value1, Integer value2) {
            addCriterion("pmt_id between", value1, value2, "pmtId");
            return (Criteria) this;
        }

        public Criteria andPmtIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pmt_id not between", value1, value2, "pmtId");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixIsNull() {
            addCriterion("cpns_prefix is null");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixIsNotNull() {
            addCriterion("cpns_prefix is not null");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixEqualTo(String value) {
            addCriterion("cpns_prefix =", value, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixNotEqualTo(String value) {
            addCriterion("cpns_prefix <>", value, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixGreaterThan(String value) {
            addCriterion("cpns_prefix >", value, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixGreaterThanOrEqualTo(String value) {
            addCriterion("cpns_prefix >=", value, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixLessThan(String value) {
            addCriterion("cpns_prefix <", value, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixLessThanOrEqualTo(String value) {
            addCriterion("cpns_prefix <=", value, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixLike(String value) {
            addCriterion("cpns_prefix like", value, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixNotLike(String value) {
            addCriterion("cpns_prefix not like", value, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixIn(List<String> values) {
            addCriterion("cpns_prefix in", values, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixNotIn(List<String> values) {
            addCriterion("cpns_prefix not in", values, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixBetween(String value1, String value2) {
            addCriterion("cpns_prefix between", value1, value2, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsPrefixNotBetween(String value1, String value2) {
            addCriterion("cpns_prefix not between", value1, value2, "cpnsPrefix");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityIsNull() {
            addCriterion("cpns_gen_quantity is null");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityIsNotNull() {
            addCriterion("cpns_gen_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityEqualTo(Integer value) {
            addCriterion("cpns_gen_quantity =", value, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityNotEqualTo(Integer value) {
            addCriterion("cpns_gen_quantity <>", value, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityGreaterThan(Integer value) {
            addCriterion("cpns_gen_quantity >", value, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpns_gen_quantity >=", value, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityLessThan(Integer value) {
            addCriterion("cpns_gen_quantity <", value, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("cpns_gen_quantity <=", value, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityIn(List<Integer> values) {
            addCriterion("cpns_gen_quantity in", values, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityNotIn(List<Integer> values) {
            addCriterion("cpns_gen_quantity not in", values, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityBetween(Integer value1, Integer value2) {
            addCriterion("cpns_gen_quantity between", value1, value2, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsGenQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("cpns_gen_quantity not between", value1, value2, "cpnsGenQuantity");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyIsNull() {
            addCriterion("cpns_key is null");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyIsNotNull() {
            addCriterion("cpns_key is not null");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyEqualTo(String value) {
            addCriterion("cpns_key =", value, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyNotEqualTo(String value) {
            addCriterion("cpns_key <>", value, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyGreaterThan(String value) {
            addCriterion("cpns_key >", value, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyGreaterThanOrEqualTo(String value) {
            addCriterion("cpns_key >=", value, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyLessThan(String value) {
            addCriterion("cpns_key <", value, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyLessThanOrEqualTo(String value) {
            addCriterion("cpns_key <=", value, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyLike(String value) {
            addCriterion("cpns_key like", value, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyNotLike(String value) {
            addCriterion("cpns_key not like", value, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyIn(List<String> values) {
            addCriterion("cpns_key in", values, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyNotIn(List<String> values) {
            addCriterion("cpns_key not in", values, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyBetween(String value1, String value2) {
            addCriterion("cpns_key between", value1, value2, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsKeyNotBetween(String value1, String value2) {
            addCriterion("cpns_key not between", value1, value2, "cpnsKey");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusIsNull() {
            addCriterion("cpns_status is null");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusIsNotNull() {
            addCriterion("cpns_status is not null");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusEqualTo(String value) {
            addCriterion("cpns_status =", value, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusNotEqualTo(String value) {
            addCriterion("cpns_status <>", value, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusGreaterThan(String value) {
            addCriterion("cpns_status >", value, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("cpns_status >=", value, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusLessThan(String value) {
            addCriterion("cpns_status <", value, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusLessThanOrEqualTo(String value) {
            addCriterion("cpns_status <=", value, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusLike(String value) {
            addCriterion("cpns_status like", value, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusNotLike(String value) {
            addCriterion("cpns_status not like", value, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusIn(List<String> values) {
            addCriterion("cpns_status in", values, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusNotIn(List<String> values) {
            addCriterion("cpns_status not in", values, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusBetween(String value1, String value2) {
            addCriterion("cpns_status between", value1, value2, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsStatusNotBetween(String value1, String value2) {
            addCriterion("cpns_status not between", value1, value2, "cpnsStatus");
            return (Criteria) this;
        }

        public Criteria andCpnsPointIsNull() {
            addCriterion("cpns_point is null");
            return (Criteria) this;
        }

        public Criteria andCpnsPointIsNotNull() {
            addCriterion("cpns_point is not null");
            return (Criteria) this;
        }

        public Criteria andCpnsPointEqualTo(Integer value) {
            addCriterion("cpns_point =", value, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andCpnsPointNotEqualTo(Integer value) {
            addCriterion("cpns_point <>", value, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andCpnsPointGreaterThan(Integer value) {
            addCriterion("cpns_point >", value, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andCpnsPointGreaterThanOrEqualTo(Integer value) {
            addCriterion("cpns_point >=", value, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andCpnsPointLessThan(Integer value) {
            addCriterion("cpns_point <", value, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andCpnsPointLessThanOrEqualTo(Integer value) {
            addCriterion("cpns_point <=", value, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andCpnsPointIn(List<Integer> values) {
            addCriterion("cpns_point in", values, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andCpnsPointNotIn(List<Integer> values) {
            addCriterion("cpns_point not in", values, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andCpnsPointBetween(Integer value1, Integer value2) {
            addCriterion("cpns_point between", value1, value2, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andCpnsPointNotBetween(Integer value1, Integer value2) {
            addCriterion("cpns_point not between", value1, value2, "cpnsPoint");
            return (Criteria) this;
        }

        public Criteria andRuleIdIsNull() {
            addCriterion("rule_id is null");
            return (Criteria) this;
        }

        public Criteria andRuleIdIsNotNull() {
            addCriterion("rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andRuleIdEqualTo(Integer value) {
            addCriterion("rule_id =", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotEqualTo(Integer value) {
            addCriterion("rule_id <>", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdGreaterThan(Integer value) {
            addCriterion("rule_id >", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_id >=", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdLessThan(Integer value) {
            addCriterion("rule_id <", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdLessThanOrEqualTo(Integer value) {
            addCriterion("rule_id <=", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdIn(List<Integer> values) {
            addCriterion("rule_id in", values, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotIn(List<Integer> values) {
            addCriterion("rule_id not in", values, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdBetween(Integer value1, Integer value2) {
            addCriterion("rule_id between", value1, value2, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_id not between", value1, value2, "ruleId");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeIsNull() {
            addCriterion("cpns_type is null");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeIsNotNull() {
            addCriterion("cpns_type is not null");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeEqualTo(String value) {
            addCriterion("cpns_type =", value, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeNotEqualTo(String value) {
            addCriterion("cpns_type <>", value, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeGreaterThan(String value) {
            addCriterion("cpns_type >", value, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cpns_type >=", value, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeLessThan(String value) {
            addCriterion("cpns_type <", value, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeLessThanOrEqualTo(String value) {
            addCriterion("cpns_type <=", value, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeLike(String value) {
            addCriterion("cpns_type like", value, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeNotLike(String value) {
            addCriterion("cpns_type not like", value, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeIn(List<String> values) {
            addCriterion("cpns_type in", values, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeNotIn(List<String> values) {
            addCriterion("cpns_type not in", values, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeBetween(String value1, String value2) {
            addCriterion("cpns_type between", value1, value2, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andCpnsTypeNotBetween(String value1, String value2) {
            addCriterion("cpns_type not between", value1, value2, "cpnsType");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityIsNull() {
            addCriterion("online_gen_quantity is null");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityIsNotNull() {
            addCriterion("online_gen_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityEqualTo(Integer value) {
            addCriterion("online_gen_quantity =", value, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityNotEqualTo(Integer value) {
            addCriterion("online_gen_quantity <>", value, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityGreaterThan(Integer value) {
            addCriterion("online_gen_quantity >", value, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("online_gen_quantity >=", value, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityLessThan(Integer value) {
            addCriterion("online_gen_quantity <", value, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("online_gen_quantity <=", value, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityIn(List<Integer> values) {
            addCriterion("online_gen_quantity in", values, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityNotIn(List<Integer> values) {
            addCriterion("online_gen_quantity not in", values, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityBetween(Integer value1, Integer value2) {
            addCriterion("online_gen_quantity between", value1, value2, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineGenQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("online_gen_quantity not between", value1, value2, "onlineGenQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitIsNull() {
            addCriterion("online_limit is null");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitIsNotNull() {
            addCriterion("online_limit is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitEqualTo(Integer value) {
            addCriterion("online_limit =", value, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitNotEqualTo(Integer value) {
            addCriterion("online_limit <>", value, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitGreaterThan(Integer value) {
            addCriterion("online_limit >", value, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("online_limit >=", value, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitLessThan(Integer value) {
            addCriterion("online_limit <", value, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitLessThanOrEqualTo(Integer value) {
            addCriterion("online_limit <=", value, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitIn(List<Integer> values) {
            addCriterion("online_limit in", values, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitNotIn(List<Integer> values) {
            addCriterion("online_limit not in", values, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitBetween(Integer value1, Integer value2) {
            addCriterion("online_limit between", value1, value2, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("online_limit not between", value1, value2, "onlineLimit");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityIsNull() {
            addCriterion("online_quantity is null");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityIsNotNull() {
            addCriterion("online_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityEqualTo(Integer value) {
            addCriterion("online_quantity =", value, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityNotEqualTo(Integer value) {
            addCriterion("online_quantity <>", value, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityGreaterThan(Integer value) {
            addCriterion("online_quantity >", value, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("online_quantity >=", value, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityLessThan(Integer value) {
            addCriterion("online_quantity <", value, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("online_quantity <=", value, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityIn(List<Integer> values) {
            addCriterion("online_quantity in", values, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityNotIn(List<Integer> values) {
            addCriterion("online_quantity not in", values, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityBetween(Integer value1, Integer value2) {
            addCriterion("online_quantity between", value1, value2, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andOnlineQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("online_quantity not between", value1, value2, "onlineQuantity");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
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
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table t_coupons
     * @mbggenerated  Tue Apr 21 13:20:16 CST 2015
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
     * This class corresponds to the database table t_coupons
     *
     * @mbggenerated do_not_delete_during_merge Mon Apr 20 10:38:25 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}