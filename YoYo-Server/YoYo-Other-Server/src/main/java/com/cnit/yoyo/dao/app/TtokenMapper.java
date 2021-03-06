package com.cnit.yoyo.dao.app;

import java.util.List;

import com.cnit.yoyo.model.app.Ttoken;

public interface TtokenMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_token
	 * @mbggenerated  Thu Jul 16 14:23:27 CST 2015
	 */
	int deleteByPrimaryKey(String ftokenid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_token
	 * @mbggenerated  Thu Jul 16 14:23:27 CST 2015
	 */
	int insert(Ttoken record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_token
	 * @mbggenerated  Thu Jul 16 14:23:27 CST 2015
	 */
	int insertSelective(Ttoken record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_token
	 * @mbggenerated  Thu Jul 16 14:23:27 CST 2015
	 */
	Ttoken selectByPrimaryKey(String ftokenid);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_token
	 * @mbggenerated  Thu Jul 16 14:23:27 CST 2015
	 */
	int updateByPrimaryKeySelective(Ttoken record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_token
	 * @mbggenerated  Thu Jul 16 14:23:27 CST 2015
	 */
	int updateByPrimaryKey(Ttoken record);
	
	List<Ttoken> queryTokenList(String ftokenid);
	void deleteTokenList(List<Ttoken> list);
}