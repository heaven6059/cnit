package com.cnit.yoyo.model.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GoodsCatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsCatExample() {
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

        public Criteria andCatNameIsNull() {
            addCriterion("CAT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCatNameIsNotNull() {
            addCriterion("CAT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCatNameEqualTo(String value) {
            addCriterion("CAT_NAME =", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotEqualTo(String value) {
            addCriterion("CAT_NAME <>", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameGreaterThan(String value) {
            addCriterion("CAT_NAME >", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameGreaterThanOrEqualTo(String value) {
            addCriterion("CAT_NAME >=", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameLessThan(String value) {
            addCriterion("CAT_NAME <", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameLessThanOrEqualTo(String value) {
            addCriterion("CAT_NAME <=", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameLike(String value) {
            addCriterion("CAT_NAME like", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotLike(String value) {
            addCriterion("CAT_NAME not like", value, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameIn(List<String> values) {
            addCriterion("CAT_NAME in", values, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotIn(List<String> values) {
            addCriterion("CAT_NAME not in", values, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameBetween(String value1, String value2) {
            addCriterion("CAT_NAME between", value1, value2, "catName");
            return (Criteria) this;
        }

        public Criteria andCatNameNotBetween(String value1, String value2) {
            addCriterion("CAT_NAME not between", value1, value2, "catName");
            return (Criteria) this;
        }

        public Criteria andParentCatIdIsNull() {
            addCriterion("PARENT_CAT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentCatIdIsNotNull() {
            addCriterion("PARENT_CAT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentCatIdEqualTo(Integer value) {
            addCriterion("PARENT_CAT_ID =", value, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andParentCatIdNotEqualTo(Integer value) {
            addCriterion("PARENT_CAT_ID <>", value, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andParentCatIdGreaterThan(Integer value) {
            addCriterion("PARENT_CAT_ID >", value, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andParentCatIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("PARENT_CAT_ID >=", value, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andParentCatIdLessThan(Integer value) {
            addCriterion("PARENT_CAT_ID <", value, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andParentCatIdLessThanOrEqualTo(Integer value) {
            addCriterion("PARENT_CAT_ID <=", value, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andParentCatIdIn(List<Integer> values) {
            addCriterion("PARENT_CAT_ID in", values, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andParentCatIdNotIn(List<Integer> values) {
            addCriterion("PARENT_CAT_ID not in", values, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andParentCatIdBetween(Integer value1, Integer value2) {
            addCriterion("PARENT_CAT_ID between", value1, value2, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andParentCatIdNotBetween(Integer value1, Integer value2) {
            addCriterion("PARENT_CAT_ID not between", value1, value2, "parentCatId");
            return (Criteria) this;
        }

        public Criteria andProfitPointIsNull() {
            addCriterion("PROFIT_POINT is null");
            return (Criteria) this;
        }

        public Criteria andProfitPointIsNotNull() {
            addCriterion("PROFIT_POINT is not null");
            return (Criteria) this;
        }

        public Criteria andProfitPointEqualTo(BigDecimal value) {
            addCriterion("PROFIT_POINT =", value, "profitPoint");
            return (Criteria) this;
        }

        public Criteria andProfitPointNotEqualTo(BigDecimal value) {
            addCriterion("PROFIT_POINT <>", value, "profitPoint");
            return (Criteria) this;
        }

        public Criteria andProfitPointGreaterThan(BigDecimal value) {
            addCriterion("PROFIT_POINT >", value, "profitPoint");
            return (Criteria) this;
        }

        public Criteria andProfitPointGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("PROFIT_POINT >=", value, "profitPoint");
            return (Criteria) this;
        }

        public Criteria andProfitPointLessThan(BigDecimal value) {
            addCriterion("PROFIT_POINT <", value, "profitPoint");
            return (Criteria) this;
        }

        public Criteria andProfitPointLessThanOrEqualTo(BigDecimal value) {
            addCriterion("PROFIT_POINT <=", value, "profitPoint");
            return (Criteria) this;
        }

        public Criteria andProfitPointIn(List<BigDecimal> values) {
            addCriterion("PROFIT_POINT in", values, "profitPoint");
            return (Criteria) this;
        }

        public Criteria andProfitPointNotIn(List<BigDecimal> values) {
            addCriterion("PROFIT_POINT not in", values, "profitPoint");
            return (Criteria) this;
        }

        public Criteria andProfitPointBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROFIT_POINT between", value1, value2, "profitPoint");
            return (Criteria) this;
        }

        public Criteria andProfitPointNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("PROFIT_POINT not between", value1, value2, "profitPoint");
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

        public Criteria andHitCountIsNull() {
            addCriterion("HIT_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andHitCountIsNotNull() {
            addCriterion("HIT_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andHitCountEqualTo(Integer value) {
            addCriterion("HIT_COUNT =", value, "hitCount");
            return (Criteria) this;
        }

        public Criteria andHitCountNotEqualTo(Integer value) {
            addCriterion("HIT_COUNT <>", value, "hitCount");
            return (Criteria) this;
        }

        public Criteria andHitCountGreaterThan(Integer value) {
            addCriterion("HIT_COUNT >", value, "hitCount");
            return (Criteria) this;
        }

        public Criteria andHitCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("HIT_COUNT >=", value, "hitCount");
            return (Criteria) this;
        }

        public Criteria andHitCountLessThan(Integer value) {
            addCriterion("HIT_COUNT <", value, "hitCount");
            return (Criteria) this;
        }

        public Criteria andHitCountLessThanOrEqualTo(Integer value) {
            addCriterion("HIT_COUNT <=", value, "hitCount");
            return (Criteria) this;
        }

        public Criteria andHitCountIn(List<Integer> values) {
            addCriterion("HIT_COUNT in", values, "hitCount");
            return (Criteria) this;
        }

        public Criteria andHitCountNotIn(List<Integer> values) {
            addCriterion("HIT_COUNT not in", values, "hitCount");
            return (Criteria) this;
        }

        public Criteria andHitCountBetween(Integer value1, Integer value2) {
            addCriterion("HIT_COUNT between", value1, value2, "hitCount");
            return (Criteria) this;
        }

        public Criteria andHitCountNotBetween(Integer value1, Integer value2) {
            addCriterion("HIT_COUNT not between", value1, value2, "hitCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountIsNull() {
            addCriterion("GOODS_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andGoodsCountIsNotNull() {
            addCriterion("GOODS_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsCountEqualTo(Integer value) {
            addCriterion("GOODS_COUNT =", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountNotEqualTo(Integer value) {
            addCriterion("GOODS_COUNT <>", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountGreaterThan(Integer value) {
            addCriterion("GOODS_COUNT >", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("GOODS_COUNT >=", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountLessThan(Integer value) {
            addCriterion("GOODS_COUNT <", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountLessThanOrEqualTo(Integer value) {
            addCriterion("GOODS_COUNT <=", value, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountIn(List<Integer> values) {
            addCriterion("GOODS_COUNT in", values, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountNotIn(List<Integer> values) {
            addCriterion("GOODS_COUNT not in", values, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountBetween(Integer value1, Integer value2) {
            addCriterion("GOODS_COUNT between", value1, value2, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andGoodsCountNotBetween(Integer value1, Integer value2) {
            addCriterion("GOODS_COUNT not between", value1, value2, "goodsCount");
            return (Criteria) this;
        }

        public Criteria andChildCountIsNull() {
            addCriterion("CHILD_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andChildCountIsNotNull() {
            addCriterion("CHILD_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andChildCountEqualTo(Integer value) {
            addCriterion("CHILD_COUNT =", value, "childCount");
            return (Criteria) this;
        }

        public Criteria andChildCountNotEqualTo(Integer value) {
            addCriterion("CHILD_COUNT <>", value, "childCount");
            return (Criteria) this;
        }

        public Criteria andChildCountGreaterThan(Integer value) {
            addCriterion("CHILD_COUNT >", value, "childCount");
            return (Criteria) this;
        }

        public Criteria andChildCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("CHILD_COUNT >=", value, "childCount");
            return (Criteria) this;
        }

        public Criteria andChildCountLessThan(Integer value) {
            addCriterion("CHILD_COUNT <", value, "childCount");
            return (Criteria) this;
        }

        public Criteria andChildCountLessThanOrEqualTo(Integer value) {
            addCriterion("CHILD_COUNT <=", value, "childCount");
            return (Criteria) this;
        }

        public Criteria andChildCountIn(List<Integer> values) {
            addCriterion("CHILD_COUNT in", values, "childCount");
            return (Criteria) this;
        }

        public Criteria andChildCountNotIn(List<Integer> values) {
            addCriterion("CHILD_COUNT not in", values, "childCount");
            return (Criteria) this;
        }

        public Criteria andChildCountBetween(Integer value1, Integer value2) {
            addCriterion("CHILD_COUNT between", value1, value2, "childCount");
            return (Criteria) this;
        }

        public Criteria andChildCountNotBetween(Integer value1, Integer value2) {
            addCriterion("CHILD_COUNT not between", value1, value2, "childCount");
            return (Criteria) this;
        }

        public Criteria andCatLogoIsNull() {
            addCriterion("CAT_LOGO is null");
            return (Criteria) this;
        }

        public Criteria andCatLogoIsNotNull() {
            addCriterion("CAT_LOGO is not null");
            return (Criteria) this;
        }

        public Criteria andCatLogoEqualTo(String value) {
            addCriterion("CAT_LOGO =", value, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoNotEqualTo(String value) {
            addCriterion("CAT_LOGO <>", value, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoGreaterThan(String value) {
            addCriterion("CAT_LOGO >", value, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoGreaterThanOrEqualTo(String value) {
            addCriterion("CAT_LOGO >=", value, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoLessThan(String value) {
            addCriterion("CAT_LOGO <", value, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoLessThanOrEqualTo(String value) {
            addCriterion("CAT_LOGO <=", value, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoLike(String value) {
            addCriterion("CAT_LOGO like", value, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoNotLike(String value) {
            addCriterion("CAT_LOGO not like", value, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoIn(List<String> values) {
            addCriterion("CAT_LOGO in", values, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoNotIn(List<String> values) {
            addCriterion("CAT_LOGO not in", values, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoBetween(String value1, String value2) {
            addCriterion("CAT_LOGO between", value1, value2, "catLogo");
            return (Criteria) this;
        }

        public Criteria andCatLogoNotBetween(String value1, String value2) {
            addCriterion("CAT_LOGO not between", value1, value2, "catLogo");
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

        public Criteria andHiddenIsNull() {
            addCriterion("HIDDEN is null");
            return (Criteria) this;
        }

        public Criteria andHiddenIsNotNull() {
            addCriterion("HIDDEN is not null");
            return (Criteria) this;
        }

        public Criteria andHiddenEqualTo(String value) {
            addCriterion("HIDDEN =", value, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenNotEqualTo(String value) {
            addCriterion("HIDDEN <>", value, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenGreaterThan(String value) {
            addCriterion("HIDDEN >", value, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenGreaterThanOrEqualTo(String value) {
            addCriterion("HIDDEN >=", value, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenLessThan(String value) {
            addCriterion("HIDDEN <", value, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenLessThanOrEqualTo(String value) {
            addCriterion("HIDDEN <=", value, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenLike(String value) {
            addCriterion("HIDDEN like", value, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenNotLike(String value) {
            addCriterion("HIDDEN not like", value, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenIn(List<String> values) {
            addCriterion("HIDDEN in", values, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenNotIn(List<String> values) {
            addCriterion("HIDDEN not in", values, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenBetween(String value1, String value2) {
            addCriterion("HIDDEN between", value1, value2, "hidden");
            return (Criteria) this;
        }

        public Criteria andHiddenNotBetween(String value1, String value2) {
            addCriterion("HIDDEN not between", value1, value2, "hidden");
            return (Criteria) this;
        }

        public Criteria andLastModifyIsNull() {
            addCriterion("LAST_MODIFY is null");
            return (Criteria) this;
        }

        public Criteria andLastModifyIsNotNull() {
            addCriterion("LAST_MODIFY is not null");
            return (Criteria) this;
        }

        public Criteria andLastModifyEqualTo(String value) {
            addCriterion("LAST_MODIFY =", value, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyNotEqualTo(String value) {
            addCriterion("LAST_MODIFY <>", value, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyGreaterThan(String value) {
            addCriterion("LAST_MODIFY >", value, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_MODIFY >=", value, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyLessThan(String value) {
            addCriterion("LAST_MODIFY <", value, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyLessThanOrEqualTo(String value) {
            addCriterion("LAST_MODIFY <=", value, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyLike(String value) {
            addCriterion("LAST_MODIFY like", value, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyNotLike(String value) {
            addCriterion("LAST_MODIFY not like", value, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyIn(List<String> values) {
            addCriterion("LAST_MODIFY in", values, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyNotIn(List<String> values) {
            addCriterion("LAST_MODIFY not in", values, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyBetween(String value1, String value2) {
            addCriterion("LAST_MODIFY between", value1, value2, "lastModify");
            return (Criteria) this;
        }

        public Criteria andLastModifyNotBetween(String value1, String value2) {
            addCriterion("LAST_MODIFY not between", value1, value2, "lastModify");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsIsNull() {
            addCriterion("META_KEYWORDS is null");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsIsNotNull() {
            addCriterion("META_KEYWORDS is not null");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsEqualTo(String value) {
            addCriterion("META_KEYWORDS =", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsNotEqualTo(String value) {
            addCriterion("META_KEYWORDS <>", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsGreaterThan(String value) {
            addCriterion("META_KEYWORDS >", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("META_KEYWORDS >=", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsLessThan(String value) {
            addCriterion("META_KEYWORDS <", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsLessThanOrEqualTo(String value) {
            addCriterion("META_KEYWORDS <=", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsLike(String value) {
            addCriterion("META_KEYWORDS like", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsNotLike(String value) {
            addCriterion("META_KEYWORDS not like", value, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsIn(List<String> values) {
            addCriterion("META_KEYWORDS in", values, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsNotIn(List<String> values) {
            addCriterion("META_KEYWORDS not in", values, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsBetween(String value1, String value2) {
            addCriterion("META_KEYWORDS between", value1, value2, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaKeywordsNotBetween(String value1, String value2) {
            addCriterion("META_KEYWORDS not between", value1, value2, "metaKeywords");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionIsNull() {
            addCriterion("META_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionIsNotNull() {
            addCriterion("META_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionEqualTo(String value) {
            addCriterion("META_DESCRIPTION =", value, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionNotEqualTo(String value) {
            addCriterion("META_DESCRIPTION <>", value, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionGreaterThan(String value) {
            addCriterion("META_DESCRIPTION >", value, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("META_DESCRIPTION >=", value, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionLessThan(String value) {
            addCriterion("META_DESCRIPTION <", value, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionLessThanOrEqualTo(String value) {
            addCriterion("META_DESCRIPTION <=", value, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionLike(String value) {
            addCriterion("META_DESCRIPTION like", value, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionNotLike(String value) {
            addCriterion("META_DESCRIPTION not like", value, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionIn(List<String> values) {
            addCriterion("META_DESCRIPTION in", values, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionNotIn(List<String> values) {
            addCriterion("META_DESCRIPTION not in", values, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionBetween(String value1, String value2) {
            addCriterion("META_DESCRIPTION between", value1, value2, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andMetaDescriptionNotBetween(String value1, String value2) {
            addCriterion("META_DESCRIPTION not between", value1, value2, "metaDescription");
            return (Criteria) this;
        }

        public Criteria andIdentifierIsNull() {
            addCriterion("identifier is null");
            return (Criteria) this;
        }

        public Criteria andIdentifierIsNotNull() {
            addCriterion("identifier is not null");
            return (Criteria) this;
        }

        public Criteria andIdentifierEqualTo(String value) {
            addCriterion("identifier =", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierNotEqualTo(String value) {
            addCriterion("identifier <>", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierGreaterThan(String value) {
            addCriterion("identifier >", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierGreaterThanOrEqualTo(String value) {
            addCriterion("identifier >=", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierLessThan(String value) {
            addCriterion("identifier <", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierLessThanOrEqualTo(String value) {
            addCriterion("identifier <=", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierLike(String value) {
            addCriterion("identifier like", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierNotLike(String value) {
            addCriterion("identifier not like", value, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierIn(List<String> values) {
            addCriterion("identifier in", values, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierNotIn(List<String> values) {
            addCriterion("identifier not in", values, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierBetween(String value1, String value2) {
            addCriterion("identifier between", value1, value2, "identifier");
            return (Criteria) this;
        }

        public Criteria andIdentifierNotBetween(String value1, String value2) {
            addCriterion("identifier not between", value1, value2, "identifier");
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