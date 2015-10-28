package com.cnit.yoyo.model.member.constant;


/**
 * 
 * @ClassName: MemberObjectType 
 * @Description: 消息类型： 咨询,评论,留言,短消息ask ,discuss, buy, message, msg, order
 * @author xiaox
 * @date 2015-3-5 上午9:17:29
 */

public enum MemberObjectType {
	ASK("ask","咨询"), 
	DISCUSS("discuss", "评论"),
	BUY("buy", "留言"),
	MESSAGE("message", "短消息"),
	ORDER("order","订单")
	;

	private String value;
	private String displayName;
	
	/**
	 * 枚举转换
	 */
	public static MemberObjectType parseOf( String value ){
		for( MemberObjectType item:values() )
			if( item.getValue().equals(value) )
				return item;
		
		throw new IllegalArgumentException("会员评论状态枚举值["+value+"]不匹配!");
	}

	public String getValue() {
		return this.value;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	MemberObjectType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
}
