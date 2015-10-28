package com.cnit.yoyo.spider.autohome.dao.impl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.constants.TableConstant;
import com.cnit.yoyo.spider.autohome.dao.CarAttrDao;
import com.cnit.yoyo.spider.common.base.dao.BaseDao;
import com.cnit.yoyo.spider.common.dto.KVObj;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarAttr;

/**
 * @author yangyi
 *
 */
@Repository
public class CarAttrDaoImpl extends BaseDao<CarAttr> implements CarAttrDao {

	@Override
	public void add(CarAttr entity) {
		String sql = "insert into "
				+ TableConstant.AUTOHOME_CAR_ATTR
				+ "(ID,NAME,VAL,CAR_TYPE_ID,PARENT_ORDER_INDEX,CHILD_ORDER_INDEX)values(:id,:name,:val,:carTypeId,:parentOrderIndex,:childOrderIndex)";
		super.insert(sql, entity);

	}

	@Override
	public void addOfBatch(List<CarAttr> eList) {
		StringBuilder sql_build = new StringBuilder("insert into " + TableConstant.AUTOHOME_CAR_ATTR
				+ "(ID,NAME,VAL,CAR_TYPE_ID,VAL_TYPE,PARENT_ORDER_INDEX,CHILD_ORDER_INDEX)values ");
		for (CarAttr temp : eList) {
			sql_build.append("('" + temp.getId() + "','" + temp.getName() + "','" + temp.getVal() + "','"
					+ temp.getCarTypeId() + "','" + temp.getType()+ "'," + temp.getParentOrderIndex() + "," + temp.getChildOrderIndex() + "),");
		}
		String sql = sql_build.toString();
		sql = sql.substring(0, sql.length() - 1);
		super.getJdbcTemplate().update(sql);
	}

	@Override
	public void upd(CarAttr entity) {
	}

	@Override
	public void updOfBatch(List<CarAttr> eList) {
		int eLen = eList.size();
		String[] sqls = new String[eLen];
		for (int i = 0; i < eLen; i++) {
			CarAttr temp = eList.get(i);
			sqls[i] = "update " + TableConstant.AUTOHOME_CAR_ATTR + " SET NAME='" + temp.getName() + "' , VAL = '"
					+ temp.getVal() + "', CAR_TYPE_ID='" + temp.getCarTypeId() + "', VAL_TYPE='" + temp.getType() + "', PARENT_ORDER_INDEX="
					+ temp.getParentOrderIndex() + ", CHILD_ORDER_INDEX=" + temp.getChildOrderIndex() + " WHERE ID = '"
					+ temp.getId() + "'";
		}
		super.getJdbcTemplate().batchUpdate(sqls);
	}

	private RowMapper<KVObj> rowMapper = new RowMapper<KVObj>() {
		@Override
		public KVObj mapRow(ResultSet rs, int rowNum) {
			KVObj kvObj = null;
			try {
				String key = rs.getString("id");
				Integer value = rs.getInt("cc");
				kvObj = new KVObj(key, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return kvObj;
		}
	};

	@Override
	public Map<String, Integer> exists(Iterable<String> ids) {
		int i = 0;
		Map<String, Integer> kvMap = new HashMap<String, Integer>();
		String beginSql = " SELECT t.ID,COUNT(1) as cc FROM " + TableConstant.AUTOHOME_CAR_ATTR + " t WHERE t.ID =  -1";

		StringBuilder sql_build = new StringBuilder(beginSql);
		for (String temp : ids) {
			sql_build.append(" UNION ALL SELECT  ID,COUNT(1) as cc FROM " + TableConstant.AUTOHOME_CAR_ATTR
					+ " t WHERE t.ID =  '" + temp + "'");

			if ((i++) % Constant.SQL_COUNT_LIMIT == 0) {
				Map<String, Integer> tempMap = KVObj.toMap(super.getJdbcTemplate().query(sql_build.toString(),
						rowMapper));
				kvMap.putAll(tempMap);
				sql_build = new StringBuilder(beginSql);
			}
		}
		Map<String, Integer> tempMap = KVObj.toMap(super.getJdbcTemplate().query(sql_build.toString(), rowMapper));
		kvMap.putAll(tempMap);
		return kvMap;

	}

}
