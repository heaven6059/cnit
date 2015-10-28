package com.cnit.yoyo.model.car;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccessoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccessoryExample() {
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

        public Criteria andAccNameIsNull() {
            addCriterion("ACC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andAccNameIsNotNull() {
            addCriterion("ACC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andAccNameEqualTo(String value) {
            addCriterion("ACC_NAME =", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotEqualTo(String value) {
            addCriterion("ACC_NAME <>", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThan(String value) {
            addCriterion("ACC_NAME >", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_NAME >=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThan(String value) {
            addCriterion("ACC_NAME <", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLessThanOrEqualTo(String value) {
            addCriterion("ACC_NAME <=", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameLike(String value) {
            addCriterion("ACC_NAME like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotLike(String value) {
            addCriterion("ACC_NAME not like", value, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameIn(List<String> values) {
            addCriterion("ACC_NAME in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotIn(List<String> values) {
            addCriterion("ACC_NAME not in", values, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameBetween(String value1, String value2) {
            addCriterion("ACC_NAME between", value1, value2, "accName");
            return (Criteria) this;
        }

        public Criteria andAccNameNotBetween(String value1, String value2) {
            addCriterion("ACC_NAME not between", value1, value2, "accName");
            return (Criteria) this;
        }

        public Criteria andAccSpecIsNull() {
            addCriterion("ACC_SPEC is null");
            return (Criteria) this;
        }

        public Criteria andAccSpecIsNotNull() {
            addCriterion("ACC_SPEC is not null");
            return (Criteria) this;
        }

        public Criteria andAccSpecEqualTo(String value) {
            addCriterion("ACC_SPEC =", value, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecNotEqualTo(String value) {
            addCriterion("ACC_SPEC <>", value, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecGreaterThan(String value) {
            addCriterion("ACC_SPEC >", value, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_SPEC >=", value, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecLessThan(String value) {
            addCriterion("ACC_SPEC <", value, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecLessThanOrEqualTo(String value) {
            addCriterion("ACC_SPEC <=", value, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecLike(String value) {
            addCriterion("ACC_SPEC like", value, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecNotLike(String value) {
            addCriterion("ACC_SPEC not like", value, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecIn(List<String> values) {
            addCriterion("ACC_SPEC in", values, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecNotIn(List<String> values) {
            addCriterion("ACC_SPEC not in", values, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecBetween(String value1, String value2) {
            addCriterion("ACC_SPEC between", value1, value2, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccSpecNotBetween(String value1, String value2) {
            addCriterion("ACC_SPEC not between", value1, value2, "accSpec");
            return (Criteria) this;
        }

        public Criteria andAccUnitIsNull() {
            addCriterion("ACC_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andAccUnitIsNotNull() {
            addCriterion("ACC_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andAccUnitEqualTo(String value) {
            addCriterion("ACC_UNIT =", value, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitNotEqualTo(String value) {
            addCriterion("ACC_UNIT <>", value, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitGreaterThan(String value) {
            addCriterion("ACC_UNIT >", value, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_UNIT >=", value, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitLessThan(String value) {
            addCriterion("ACC_UNIT <", value, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitLessThanOrEqualTo(String value) {
            addCriterion("ACC_UNIT <=", value, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitLike(String value) {
            addCriterion("ACC_UNIT like", value, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitNotLike(String value) {
            addCriterion("ACC_UNIT not like", value, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitIn(List<String> values) {
            addCriterion("ACC_UNIT in", values, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitNotIn(List<String> values) {
            addCriterion("ACC_UNIT not in", values, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitBetween(String value1, String value2) {
            addCriterion("ACC_UNIT between", value1, value2, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccUnitNotBetween(String value1, String value2) {
            addCriterion("ACC_UNIT not between", value1, value2, "accUnit");
            return (Criteria) this;
        }

        public Criteria andAccForshortIsNull() {
            addCriterion("ACC_FORSHORT is null");
            return (Criteria) this;
        }

        public Criteria andAccForshortIsNotNull() {
            addCriterion("ACC_FORSHORT is not null");
            return (Criteria) this;
        }

        public Criteria andAccForshortEqualTo(String value) {
            addCriterion("ACC_FORSHORT =", value, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortNotEqualTo(String value) {
            addCriterion("ACC_FORSHORT <>", value, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortGreaterThan(String value) {
            addCriterion("ACC_FORSHORT >", value, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_FORSHORT >=", value, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortLessThan(String value) {
            addCriterion("ACC_FORSHORT <", value, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortLessThanOrEqualTo(String value) {
            addCriterion("ACC_FORSHORT <=", value, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortLike(String value) {
            addCriterion("ACC_FORSHORT like", value, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortNotLike(String value) {
            addCriterion("ACC_FORSHORT not like", value, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortIn(List<String> values) {
            addCriterion("ACC_FORSHORT in", values, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortNotIn(List<String> values) {
            addCriterion("ACC_FORSHORT not in", values, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortBetween(String value1, String value2) {
            addCriterion("ACC_FORSHORT between", value1, value2, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccForshortNotBetween(String value1, String value2) {
            addCriterion("ACC_FORSHORT not between", value1, value2, "accForshort");
            return (Criteria) this;
        }

        public Criteria andAccCodeIsNull() {
            addCriterion("ACC_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAccCodeIsNotNull() {
            addCriterion("ACC_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAccCodeEqualTo(String value) {
            addCriterion("ACC_CODE =", value, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeNotEqualTo(String value) {
            addCriterion("ACC_CODE <>", value, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeGreaterThan(String value) {
            addCriterion("ACC_CODE >", value, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_CODE >=", value, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeLessThan(String value) {
            addCriterion("ACC_CODE <", value, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeLessThanOrEqualTo(String value) {
            addCriterion("ACC_CODE <=", value, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeLike(String value) {
            addCriterion("ACC_CODE like", value, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeNotLike(String value) {
            addCriterion("ACC_CODE not like", value, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeIn(List<String> values) {
            addCriterion("ACC_CODE in", values, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeNotIn(List<String> values) {
            addCriterion("ACC_CODE not in", values, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeBetween(String value1, String value2) {
            addCriterion("ACC_CODE between", value1, value2, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccCodeNotBetween(String value1, String value2) {
            addCriterion("ACC_CODE not between", value1, value2, "accCode");
            return (Criteria) this;
        }

        public Criteria andAccOemIsNull() {
            addCriterion("ACC_OEM is null");
            return (Criteria) this;
        }

        public Criteria andAccOemIsNotNull() {
            addCriterion("ACC_OEM is not null");
            return (Criteria) this;
        }

        public Criteria andAccOemEqualTo(String value) {
            addCriterion("ACC_OEM =", value, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemNotEqualTo(String value) {
            addCriterion("ACC_OEM <>", value, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemGreaterThan(String value) {
            addCriterion("ACC_OEM >", value, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_OEM >=", value, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemLessThan(String value) {
            addCriterion("ACC_OEM <", value, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemLessThanOrEqualTo(String value) {
            addCriterion("ACC_OEM <=", value, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemLike(String value) {
            addCriterion("ACC_OEM like", value, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemNotLike(String value) {
            addCriterion("ACC_OEM not like", value, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemIn(List<String> values) {
            addCriterion("ACC_OEM in", values, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemNotIn(List<String> values) {
            addCriterion("ACC_OEM not in", values, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemBetween(String value1, String value2) {
            addCriterion("ACC_OEM between", value1, value2, "accOem");
            return (Criteria) this;
        }

        public Criteria andAccOemNotBetween(String value1, String value2) {
            addCriterion("ACC_OEM not between", value1, value2, "accOem");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("BRAND_ID is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("BRAND_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Integer value) {
            addCriterion("BRAND_ID =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Integer value) {
            addCriterion("BRAND_ID <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Integer value) {
            addCriterion("BRAND_ID >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("BRAND_ID >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Integer value) {
            addCriterion("BRAND_ID <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("BRAND_ID <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Integer> values) {
            addCriterion("BRAND_ID in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Integer> values) {
            addCriterion("BRAND_ID not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("BRAND_ID between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("BRAND_ID not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNull() {
            addCriterion("BRAND_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBrandNameIsNotNull() {
            addCriterion("BRAND_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBrandNameEqualTo(String value) {
            addCriterion("BRAND_NAME =", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotEqualTo(String value) {
            addCriterion("BRAND_NAME <>", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThan(String value) {
            addCriterion("BRAND_NAME >", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameGreaterThanOrEqualTo(String value) {
            addCriterion("BRAND_NAME >=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThan(String value) {
            addCriterion("BRAND_NAME <", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLessThanOrEqualTo(String value) {
            addCriterion("BRAND_NAME <=", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameLike(String value) {
            addCriterion("BRAND_NAME like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotLike(String value) {
            addCriterion("BRAND_NAME not like", value, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameIn(List<String> values) {
            addCriterion("BRAND_NAME in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotIn(List<String> values) {
            addCriterion("BRAND_NAME not in", values, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameBetween(String value1, String value2) {
            addCriterion("BRAND_NAME between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andBrandNameNotBetween(String value1, String value2) {
            addCriterion("BRAND_NAME not between", value1, value2, "brandName");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsIsNull() {
            addCriterion("ACC_MAIN_PLANTS is null");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsIsNotNull() {
            addCriterion("ACC_MAIN_PLANTS is not null");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsEqualTo(String value) {
            addCriterion("ACC_MAIN_PLANTS =", value, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsNotEqualTo(String value) {
            addCriterion("ACC_MAIN_PLANTS <>", value, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsGreaterThan(String value) {
            addCriterion("ACC_MAIN_PLANTS >", value, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_MAIN_PLANTS >=", value, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsLessThan(String value) {
            addCriterion("ACC_MAIN_PLANTS <", value, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsLessThanOrEqualTo(String value) {
            addCriterion("ACC_MAIN_PLANTS <=", value, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsLike(String value) {
            addCriterion("ACC_MAIN_PLANTS like", value, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsNotLike(String value) {
            addCriterion("ACC_MAIN_PLANTS not like", value, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsIn(List<String> values) {
            addCriterion("ACC_MAIN_PLANTS in", values, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsNotIn(List<String> values) {
            addCriterion("ACC_MAIN_PLANTS not in", values, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsBetween(String value1, String value2) {
            addCriterion("ACC_MAIN_PLANTS between", value1, value2, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccMainPlantsNotBetween(String value1, String value2) {
            addCriterion("ACC_MAIN_PLANTS not between", value1, value2, "accMainPlants");
            return (Criteria) this;
        }

        public Criteria andAccScPriceIsNull() {
            addCriterion("ACC_SC_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andAccScPriceIsNotNull() {
            addCriterion("ACC_SC_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andAccScPriceEqualTo(BigDecimal value) {
            addCriterion("ACC_SC_PRICE =", value, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccScPriceNotEqualTo(BigDecimal value) {
            addCriterion("ACC_SC_PRICE <>", value, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccScPriceGreaterThan(BigDecimal value) {
            addCriterion("ACC_SC_PRICE >", value, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccScPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC_SC_PRICE >=", value, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccScPriceLessThan(BigDecimal value) {
            addCriterion("ACC_SC_PRICE <", value, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccScPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC_SC_PRICE <=", value, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccScPriceIn(List<BigDecimal> values) {
            addCriterion("ACC_SC_PRICE in", values, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccScPriceNotIn(List<BigDecimal> values) {
            addCriterion("ACC_SC_PRICE not in", values, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccScPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC_SC_PRICE between", value1, value2, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccScPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC_SC_PRICE not between", value1, value2, "accScPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceIsNull() {
            addCriterion("ACC_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andAccPriceIsNotNull() {
            addCriterion("ACC_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andAccPriceEqualTo(BigDecimal value) {
            addCriterion("ACC_PRICE =", value, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceNotEqualTo(BigDecimal value) {
            addCriterion("ACC_PRICE <>", value, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceGreaterThan(BigDecimal value) {
            addCriterion("ACC_PRICE >", value, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC_PRICE >=", value, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceLessThan(BigDecimal value) {
            addCriterion("ACC_PRICE <", value, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ACC_PRICE <=", value, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceIn(List<BigDecimal> values) {
            addCriterion("ACC_PRICE in", values, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceNotIn(List<BigDecimal> values) {
            addCriterion("ACC_PRICE not in", values, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC_PRICE between", value1, value2, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ACC_PRICE not between", value1, value2, "accPrice");
            return (Criteria) this;
        }

        public Criteria andAccPackIsNull() {
            addCriterion("ACC_PACK is null");
            return (Criteria) this;
        }

        public Criteria andAccPackIsNotNull() {
            addCriterion("ACC_PACK is not null");
            return (Criteria) this;
        }

        public Criteria andAccPackEqualTo(String value) {
            addCriterion("ACC_PACK =", value, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackNotEqualTo(String value) {
            addCriterion("ACC_PACK <>", value, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackGreaterThan(String value) {
            addCriterion("ACC_PACK >", value, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_PACK >=", value, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackLessThan(String value) {
            addCriterion("ACC_PACK <", value, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackLessThanOrEqualTo(String value) {
            addCriterion("ACC_PACK <=", value, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackLike(String value) {
            addCriterion("ACC_PACK like", value, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackNotLike(String value) {
            addCriterion("ACC_PACK not like", value, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackIn(List<String> values) {
            addCriterion("ACC_PACK in", values, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackNotIn(List<String> values) {
            addCriterion("ACC_PACK not in", values, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackBetween(String value1, String value2) {
            addCriterion("ACC_PACK between", value1, value2, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccPackNotBetween(String value1, String value2) {
            addCriterion("ACC_PACK not between", value1, value2, "accPack");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsIsNull() {
            addCriterion("ACC_LOGISTICS is null");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsIsNotNull() {
            addCriterion("ACC_LOGISTICS is not null");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsEqualTo(String value) {
            addCriterion("ACC_LOGISTICS =", value, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsNotEqualTo(String value) {
            addCriterion("ACC_LOGISTICS <>", value, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsGreaterThan(String value) {
            addCriterion("ACC_LOGISTICS >", value, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsGreaterThanOrEqualTo(String value) {
            addCriterion("ACC_LOGISTICS >=", value, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsLessThan(String value) {
            addCriterion("ACC_LOGISTICS <", value, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsLessThanOrEqualTo(String value) {
            addCriterion("ACC_LOGISTICS <=", value, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsLike(String value) {
            addCriterion("ACC_LOGISTICS like", value, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsNotLike(String value) {
            addCriterion("ACC_LOGISTICS not like", value, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsIn(List<String> values) {
            addCriterion("ACC_LOGISTICS in", values, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsNotIn(List<String> values) {
            addCriterion("ACC_LOGISTICS not in", values, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsBetween(String value1, String value2) {
            addCriterion("ACC_LOGISTICS between", value1, value2, "accLogistics");
            return (Criteria) this;
        }

        public Criteria andAccLogisticsNotBetween(String value1, String value2) {
            addCriterion("ACC_LOGISTICS not between", value1, value2, "accLogistics");
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

        public Criteria andIsRelatedCarIsNull() {
            addCriterion("IS_RELATED_CAR is null");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarIsNotNull() {
            addCriterion("IS_RELATED_CAR is not null");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarEqualTo(String value) {
            addCriterion("IS_RELATED_CAR =", value, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarNotEqualTo(String value) {
            addCriterion("IS_RELATED_CAR <>", value, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarGreaterThan(String value) {
            addCriterion("IS_RELATED_CAR >", value, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarGreaterThanOrEqualTo(String value) {
            addCriterion("IS_RELATED_CAR >=", value, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarLessThan(String value) {
            addCriterion("IS_RELATED_CAR <", value, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarLessThanOrEqualTo(String value) {
            addCriterion("IS_RELATED_CAR <=", value, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarLike(String value) {
            addCriterion("IS_RELATED_CAR like", value, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarNotLike(String value) {
            addCriterion("IS_RELATED_CAR not like", value, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarIn(List<String> values) {
            addCriterion("IS_RELATED_CAR in", values, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarNotIn(List<String> values) {
            addCriterion("IS_RELATED_CAR not in", values, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarBetween(String value1, String value2) {
            addCriterion("IS_RELATED_CAR between", value1, value2, "isRelatedCar");
            return (Criteria) this;
        }

        public Criteria andIsRelatedCarNotBetween(String value1, String value2) {
            addCriterion("IS_RELATED_CAR not between", value1, value2, "isRelatedCar");
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