package com.cnit.yoyo.spider.common.base.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.cnit.yoyo.spider.common.base.sql.LoadSqlUtil;
import com.cnit.yoyo.spider.common.base.vo.Page;


public  abstract class BaseDao<T> extends NamedParameterJdbcDaoSupport {
	@Resource(name = "dataSource")
	protected void setSuperDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	
 
	
	protected StringBuilder getSql(String sqlId){
		String sql=LoadSqlUtil.loadSql(getClass(),sqlId);
		if(null!=sql){
			return new StringBuilder( sql );
		}
		return null;
	}
	
	protected StringBuilder getSql(){
		StackTraceElement stack[] = (new Throwable()).getStackTrace();  
		StackTraceElement s =stack[1];
		String id= s.getMethodName();
		return getSql(id);
		
	}
	
	
	
	/**
	 * 适用于更新数据库,insert,update, delete
	 */
	protected int update(String sql, Object... paramValue) {
		return this.getJdbcTemplate().update(sql, paramValue);
	}

	protected int update(String namedSql, Object javaBean) {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(
				javaBean);
		return this.getNamedParameterJdbcTemplate().update(namedSql,
				paramSource);
	}

	protected long insert(String namedSql, Object javaBean) {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(
				javaBean);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getNamedParameterJdbcTemplate().update(namedSql, paramSource,
				keyHolder);
		return keyHolder.getKey().longValue();
	}

	protected long insert(String namedSql, Map<String, Object> param) {
		SqlParameterSource paramSource = new MapSqlParameterSource(param);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.getNamedParameterJdbcTemplate().update(namedSql, paramSource,
				keyHolder);
		return keyHolder.getKey().longValue();
	}


	protected int update(String namedSql, List list) {
		return update(namedSql, list.toArray());
	}

	protected int update(String sql, Map map) {
		return this.getNamedParameterJdbcTemplate().update(sql, map);
	}

	protected long queryForLong(String sql, Map<String, Object> paramMap) {
		return this.getNamedParameterJdbcTemplate().queryForLong(sql, paramMap);
	}

	protected int queryForInt(String sql, Map<String, Object> paramMap) {
		return this.getNamedParameterJdbcTemplate().queryForInt(sql, paramMap);
	}

	protected T getBean(String sql, RowMapper<T> rowMapper,
			Object... paramValue) {
		try {
			return this.getJdbcTemplate().queryForObject(sql, rowMapper,
					paramValue);
		} catch (Exception ex) {
			return null;
		}
	}

	protected List<T> getList(String sql, RowMapper<T> rowMapper,
			Object... paramValue) {
		return this.getJdbcTemplate().query(sql, rowMapper, paramValue);
	}

	protected List<T> getList(String sql, RowMapper<T> rowMapper) {
		return this.getJdbcTemplate().query(sql, rowMapper);
	}

	protected List<T> query(String sql, Class<T> cla, Object[] args) {
		List<T> list = super.getJdbcTemplate().query(sql.toString(), args,
				new BeanPropertyRowMapper<T>(cla));
		return list;
	}

	protected List<T> query(String sql, Class<T> cla, List<T> args) {
		return this.query(sql, cla, args.toArray());
	}

	protected List<T> query(String sql, Class<T> cla, Object[] args, Page page) {
		if (page != null) {
			String countSql = "select count(1) from ( " + sql + " ) t";
			int counts = this.getJdbcTemplate().queryForInt(countSql, args);
			page.setCounts(counts, page.getPageSize());
			int beginPos = (page.getCurPage() - 1) * page.getPageSize();
			sql += " LIMIT " + beginPos + "," + page.getPageSize();
		}
		return this.query(sql, cla, args);
	}

	protected List<T> query(String sql, Class<T> cla, Map<String, Object> args,
			Page page) {
		if (page != null) {
			String countSql = "select count(1) from ( " + sql + " ) t";
			int counts = this.getNamedParameterJdbcTemplate().queryForInt(
					countSql, args);
			page.setCounts(counts, page.getPageSize());
			int beginPos = (page.getCurPage() - 1) * page.getPageSize();
			sql += " LIMIT " + beginPos + "," + page.getPageSize();
		}
		return this.query(sql, cla, args);
	}

	protected List<T> query(String sql, Class<T> cla, Map<String, Object> args) {
		return getNamedParameterJdbcTemplate().query(sql, args,
				BeanPropertyRowMapper.newInstance(cla));
	}

	protected List<T> query(String sql, Class<T> cla, List<T> args, Page page) {
		return this.query(sql, cla, args.toArray(), page);
	}

	protected T getBean(String sql, Class cla, Object[] args) {
		List<T> list = super.getJdbcTemplate().query(sql.toString(), args,
				new BeanPropertyRowMapper<T>(cla));
		T t = null;
		if (CollectionUtils.isNotEmpty(list)) {
			t = list.get(0);
		}
		return t;
	}

	protected T getObject(String sql, Class cla, Object... paramValue) {
		try {
			return this.getJdbcTemplate().queryForObject(sql,
					new BeanPropertyRowMapper<T>(cla), paramValue);
		} catch (Exception ex) {
			return null;
		}
	}

	protected int getCount(String countSQL, List paramValue) {
		int result;
		try {
			// logger.info("excuting sql : " + countSQL);
			result = this.getJdbcTemplate().queryForInt(countSQL,
					paramValue.toArray());
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return 0;
		}
		return result;
	}

	protected int getCount(String countSQL, Object... paramValue) {
		int result;
		try {
			// logger.info("excuting sql : " + countSQL);
			result = this.getJdbcTemplate().queryForInt(countSQL, paramValue);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return 0;
		}
		return result;
	}
	
	protected void executeProc(String procSQL){
		try{
			this.getJdbcTemplate().execute("call "+procSQL);
		} catch (Exception ex) {
			logger.error(ex);
		}
		
	}

}
