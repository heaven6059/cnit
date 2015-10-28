package com.cnit.yoyo.model.activity;

import java.io.Serializable;

public class SalesRuleGoodsWithBLOBs extends SalesRuleGoods implements Serializable {

    private static final long serialVersionUID = -1254354651357560515L;
    private String description;
    private String conditions;
    private String actionSolution;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getActionSolution() {
        return actionSolution;
    }

    public void setActionSolution(String actionSolution) {
        this.actionSolution = actionSolution;
    }

}