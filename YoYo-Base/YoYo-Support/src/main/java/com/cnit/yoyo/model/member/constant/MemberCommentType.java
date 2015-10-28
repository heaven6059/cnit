package com.cnit.yoyo.model.member.constant;


/**
 * 
 * @ClassName: MemberCommentType 
 * @Description: 评论类型:0 => '解释',1 => '评论',2 => '回复',3 => '追加',
 * @author xiaox
 * @date 2015-3-5 上午9:17:29
 */

public enum MemberCommentType {
	EXPLAIN("0","解释"), 
	COMMENT("1", "评论"),
	REPLY("2", "回复"),
	APPEND("3", "追加")
	;

	private String value;
	private String displayName;
	
	/**
	 * 枚举转换
	 */
	public static MemberCommentType parseOf( String value ){
		for( MemberCommentType item:values() )
			if( item.getValue().equals(value) )
				return item;
		
		throw new IllegalArgumentException("会员评论类型枚举值["+value+"]不匹配!");
	}

	public String getValue() {
		return this.value;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	MemberCommentType(String value, String displayName) {
		this.value = value;
		this.displayName = displayName;
	}
}
