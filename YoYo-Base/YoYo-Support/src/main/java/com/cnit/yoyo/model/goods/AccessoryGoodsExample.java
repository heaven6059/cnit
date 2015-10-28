package com.cnit.yoyo.model.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccessoryGoodsExample {
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria;
    public AccessoryGoodsExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameIsNull() {
            addCriterion("acc_group_name is null");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameIsNotNull() {
            addCriterion("acc_group_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameEqualTo(String value) {
            addCriterion("acc_group_name =", value, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameNotEqualTo(String value) {
            addCriterion("acc_group_name <>", value, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameGreaterThan(String value) {
            addCriterion("acc_group_name >", value, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("acc_group_name >=", value, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameLessThan(String value) {
            addCriterion("acc_group_name <", value, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameLessThanOrEqualTo(String value) {
            addCriterion("acc_group_name <=", value, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameLike(String value) {
            addCriterion("acc_group_name like", value, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameNotLike(String value) {
            addCriterion("acc_group_name not like", value, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameIn(List<String> values) {
            addCriterion("acc_group_name in", values, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameNotIn(List<String> values) {
            addCriterion("acc_group_name not in", values, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameBetween(String value1, String value2) {
            addCriterion("acc_group_name between", value1, value2, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andAccGroupNameNotBetween(String value1, String value2) {
            addCriterion("acc_group_name not between", value1, value2, "accGroupName");
            return (Criteria) this;
        }

        public Criteria andMinBuyIsNull() {
            addCriterion("min_buy is null");
            return (Criteria) this;
        }

        public Criteria andMinBuyIsNotNull() {
            addCriterion("min_buy is not null");
            return (Criteria) this;
        }

        public Criteria andMinBuyEqualTo(Integer value) {
            addCriterion("min_buy =", value, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMinBuyNotEqualTo(Integer value) {
            addCriterion("min_buy <>", value, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMinBuyGreaterThan(Integer value) {
            addCriterion("min_buy >", value, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMinBuyGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_buy >=", value, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMinBuyLessThan(Integer value) {
            addCriterion("min_buy <", value, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMinBuyLessThanOrEqualTo(Integer value) {
            addCriterion("min_buy <=", value, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMinBuyIn(List<Integer> values) {
            addCriterion("min_buy in", values, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMinBuyNotIn(List<Integer> values) {
            addCriterion("min_buy not in", values, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMinBuyBetween(Integer value1, Integer value2) {
            addCriterion("min_buy between", value1, value2, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMinBuyNotBetween(Integer value1, Integer value2) {
            addCriterion("min_buy not between", value1, value2, "minBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyIsNull() {
            addCriterion("max_buy is null");
            return (Criteria) this;
        }

        public Criteria andMaxBuyIsNotNull() {
            addCriterion("max_buy is not null");
            return (Criteria) this;
        }

        public Criteria andMaxBuyEqualTo(Integer value) {
            addCriterion("max_buy =", value, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyNotEqualTo(Integer value) {
            addCriterion("max_buy <>", value, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyGreaterThan(Integer value) {
            addCriterion("max_buy >", value, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_buy >=", value, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyLessThan(Integer value) {
            addCriterion("max_buy <", value, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyLessThanOrEqualTo(Integer value) {
            addCriterion("max_buy <=", value, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyIn(List<Integer> values) {
            addCriterion("max_buy in", values, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyNotIn(List<Integer> values) {
            addCriterion("max_buy not in", values, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyBetween(Integer value1, Integer value2) {
            addCriterion("max_buy between", value1, value2, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andMaxBuyNotBetween(Integer value1, Integer value2) {
            addCriterion("max_buy not between", value1, value2, "maxBuy");
            return (Criteria) this;
        }

        public Criteria andDiscTypeIsNull() {
            addCriterion("disc_type is null");
            return (Criteria) this;
        }

        public Criteria andDiscTypeIsNotNull() {
            addCriterion("disc_type is not null");
            return (Criteria) this;
        }

        public Criteria andDiscTypeEqualTo(String value) {
            addCriterion("disc_type =", value, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeNotEqualTo(String value) {
            addCriterion("disc_type <>", value, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeGreaterThan(String value) {
            addCriterion("disc_type >", value, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeGreaterThanOrEqualTo(String value) {
            addCriterion("disc_type >=", value, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeLessThan(String value) {
            addCriterion("disc_type <", value, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeLessThanOrEqualTo(String value) {
            addCriterion("disc_type <=", value, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeLike(String value) {
            addCriterion("disc_type like", value, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeNotLike(String value) {
            addCriterion("disc_type not like", value, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeIn(List<String> values) {
            addCriterion("disc_type in", values, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeNotIn(List<String> values) {
            addCriterion("disc_type not in", values, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeBetween(String value1, String value2) {
            addCriterion("disc_type between", value1, value2, "discType");
            return (Criteria) this;
        }

        public Criteria andDiscTypeNotBetween(String value1, String value2) {
            addCriterion("disc_type not between", value1, value2, "discType");
            return (Criteria) this;
        }

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(BigDecimal value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(BigDecimal value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(BigDecimal value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(BigDecimal value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(BigDecimal value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<BigDecimal> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<BigDecimal> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("credit not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsIsNull() {
            addCriterion("accessory_goods is null");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsIsNotNull() {
            addCriterion("accessory_goods is not null");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsEqualTo(String value) {
            addCriterion("accessory_goods =", value, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsNotEqualTo(String value) {
            addCriterion("accessory_goods <>", value, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsGreaterThan(String value) {
            addCriterion("accessory_goods >", value, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsGreaterThanOrEqualTo(String value) {
            addCriterion("accessory_goods >=", value, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsLessThan(String value) {
            addCriterion("accessory_goods <", value, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsLessThanOrEqualTo(String value) {
            addCriterion("accessory_goods <=", value, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsLike(String value) {
            addCriterion("accessory_goods like", value, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsNotLike(String value) {
            addCriterion("accessory_goods not like", value, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsIn(List<String> values) {
            addCriterion("accessory_goods in", values, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsNotIn(List<String> values) {
            addCriterion("accessory_goods not in", values, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsBetween(String value1, String value2) {
            addCriterion("accessory_goods between", value1, value2, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andAccessoryGoodsNotBetween(String value1, String value2) {
            addCriterion("accessory_goods not between", value1, value2, "accessoryGoods");
            return (Criteria) this;
        }

        public Criteria andActypeIsNull() {
            addCriterion("actype is null");
            return (Criteria) this;
        }

        public Criteria andActypeIsNotNull() {
            addCriterion("actype is not null");
            return (Criteria) this;
        }

        public Criteria andActypeEqualTo(String value) {
            addCriterion("actype =", value, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeNotEqualTo(String value) {
            addCriterion("actype <>", value, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeGreaterThan(String value) {
            addCriterion("actype >", value, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeGreaterThanOrEqualTo(String value) {
            addCriterion("actype >=", value, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeLessThan(String value) {
            addCriterion("actype <", value, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeLessThanOrEqualTo(String value) {
            addCriterion("actype <=", value, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeLike(String value) {
            addCriterion("actype like", value, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeNotLike(String value) {
            addCriterion("actype not like", value, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeIn(List<String> values) {
            addCriterion("actype in", values, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeNotIn(List<String> values) {
            addCriterion("actype not in", values, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeBetween(String value1, String value2) {
            addCriterion("actype between", value1, value2, "actype");
            return (Criteria) this;
        }

        public Criteria andActypeNotBetween(String value1, String value2) {
            addCriterion("actype not between", value1, value2, "actype");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsIsNull() {
            addCriterion("search_keywords is null");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsIsNotNull() {
            addCriterion("search_keywords is not null");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsEqualTo(String value) {
            addCriterion("search_keywords =", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotEqualTo(String value) {
            addCriterion("search_keywords <>", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsGreaterThan(String value) {
            addCriterion("search_keywords >", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("search_keywords >=", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLessThan(String value) {
            addCriterion("search_keywords <", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLessThanOrEqualTo(String value) {
            addCriterion("search_keywords <=", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsLike(String value) {
            addCriterion("search_keywords like", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotLike(String value) {
            addCriterion("search_keywords not like", value, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsIn(List<String> values) {
            addCriterion("search_keywords in", values, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotIn(List<String> values) {
            addCriterion("search_keywords not in", values, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsBetween(String value1, String value2) {
            addCriterion("search_keywords between", value1, value2, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordsNotBetween(String value1, String value2) {
            addCriterion("search_keywords not between", value1, value2, "searchKeywords");
            return (Criteria) this;
        }

        public Criteria andCatIdsIsNull() {
            addCriterion("cat_ids is null");
            return (Criteria) this;
        }

        public Criteria andCatIdsIsNotNull() {
            addCriterion("cat_ids is not null");
            return (Criteria) this;
        }

        public Criteria andCatIdsEqualTo(String value) {
            addCriterion("cat_ids =", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotEqualTo(String value) {
            addCriterion("cat_ids <>", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsGreaterThan(String value) {
            addCriterion("cat_ids >", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsGreaterThanOrEqualTo(String value) {
            addCriterion("cat_ids >=", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsLessThan(String value) {
            addCriterion("cat_ids <", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsLessThanOrEqualTo(String value) {
            addCriterion("cat_ids <=", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsLike(String value) {
            addCriterion("cat_ids like", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotLike(String value) {
            addCriterion("cat_ids not like", value, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsIn(List<String> values) {
            addCriterion("cat_ids in", values, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotIn(List<String> values) {
            addCriterion("cat_ids not in", values, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsBetween(String value1, String value2) {
            addCriterion("cat_ids between", value1, value2, "catIds");
            return (Criteria) this;
        }

        public Criteria andCatIdsNotBetween(String value1, String value2) {
            addCriterion("cat_ids not between", value1, value2, "catIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsIsNull() {
            addCriterion("brand_ids is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdsIsNotNull() {
            addCriterion("brand_ids is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdsEqualTo(String value) {
            addCriterion("brand_ids =", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotEqualTo(String value) {
            addCriterion("brand_ids <>", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsGreaterThan(String value) {
            addCriterion("brand_ids >", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsGreaterThanOrEqualTo(String value) {
            addCriterion("brand_ids >=", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsLessThan(String value) {
            addCriterion("brand_ids <", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsLessThanOrEqualTo(String value) {
            addCriterion("brand_ids <=", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsLike(String value) {
            addCriterion("brand_ids like", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotLike(String value) {
            addCriterion("brand_ids not like", value, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsIn(List<String> values) {
            addCriterion("brand_ids in", values, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotIn(List<String> values) {
            addCriterion("brand_ids not in", values, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsBetween(String value1, String value2) {
            addCriterion("brand_ids between", value1, value2, "brandIds");
            return (Criteria) this;
        }

        public Criteria andBrandIdsNotBetween(String value1, String value2) {
            addCriterion("brand_ids not between", value1, value2, "brandIds");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andPriceformIsNull() {
            addCriterion("priceform is null");
            return (Criteria) this;
        }

        public Criteria andPriceformIsNotNull() {
            addCriterion("priceform is not null");
            return (Criteria) this;
        }

        public Criteria andPriceformEqualTo(BigDecimal value) {
            addCriterion("priceform =", value, "priceform");
            return (Criteria) this;
        }

        public Criteria andPriceformNotEqualTo(BigDecimal value) {
            addCriterion("priceform <>", value, "priceform");
            return (Criteria) this;
        }

        public Criteria andPriceformGreaterThan(BigDecimal value) {
            addCriterion("priceform >", value, "priceform");
            return (Criteria) this;
        }

        public Criteria andPriceformGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("priceform >=", value, "priceform");
            return (Criteria) this;
        }

        public Criteria andPriceformLessThan(BigDecimal value) {
            addCriterion("priceform <", value, "priceform");
            return (Criteria) this;
        }

        public Criteria andPriceformLessThanOrEqualTo(BigDecimal value) {
            addCriterion("priceform <=", value, "priceform");
            return (Criteria) this;
        }

        public Criteria andPriceformIn(List<BigDecimal> values) {
            addCriterion("priceform in", values, "priceform");
            return (Criteria) this;
        }

        public Criteria andPriceformNotIn(List<BigDecimal> values) {
            addCriterion("priceform not in", values, "priceform");
            return (Criteria) this;
        }

        public Criteria andPriceformBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("priceform between", value1, value2, "priceform");
            return (Criteria) this;
        }

        public Criteria andPriceformNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("priceform not between", value1, value2, "priceform");
            return (Criteria) this;
        }

        public Criteria andPricetoIsNull() {
            addCriterion("priceto is null");
            return (Criteria) this;
        }

        public Criteria andPricetoIsNotNull() {
            addCriterion("priceto is not null");
            return (Criteria) this;
        }

        public Criteria andPricetoEqualTo(BigDecimal value) {
            addCriterion("priceto =", value, "priceto");
            return (Criteria) this;
        }

        public Criteria andPricetoNotEqualTo(BigDecimal value) {
            addCriterion("priceto <>", value, "priceto");
            return (Criteria) this;
        }

        public Criteria andPricetoGreaterThan(BigDecimal value) {
            addCriterion("priceto >", value, "priceto");
            return (Criteria) this;
        }

        public Criteria andPricetoGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("priceto >=", value, "priceto");
            return (Criteria) this;
        }

        public Criteria andPricetoLessThan(BigDecimal value) {
            addCriterion("priceto <", value, "priceto");
            return (Criteria) this;
        }

        public Criteria andPricetoLessThanOrEqualTo(BigDecimal value) {
            addCriterion("priceto <=", value, "priceto");
            return (Criteria) this;
        }

        public Criteria andPricetoIn(List<BigDecimal> values) {
            addCriterion("priceto in", values, "priceto");
            return (Criteria) this;
        }

        public Criteria andPricetoNotIn(List<BigDecimal> values) {
            addCriterion("priceto not in", values, "priceto");
            return (Criteria) this;
        }

        public Criteria andPricetoBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("priceto between", value1, value2, "priceto");
            return (Criteria) this;
        }

        public Criteria andPricetoNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("priceto not between", value1, value2, "priceto");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Long> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Long> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator. This class corresponds to the database table t_accessory_goods
     * @mbggenerated  Tue Apr 14 15:55:30 CST 2015
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
     * This class corresponds to the database table t_accessory_goods
     *
     * @mbggenerated do_not_delete_during_merge Tue Apr 14 14:13:10 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}