package com.cnit.yoyo.model.shop;

import java.io.Serializable;

public class ShopIndexPushWithBLOBs extends ShopIndexPush implements Serializable {
    private String leftPush;

    private String rightPush;

    private static final long serialVersionUID = 1L;

    public String getLeftPush() {
        return leftPush;
    }

    public void setLeftPush(String leftPush) {
        this.leftPush = leftPush;
    }

    public String getRightPush() {
        return rightPush;
    }

    public void setRightPush(String rightPush) {
        this.rightPush = rightPush;
    }
}