package com.cnit.yoyo.spider.autohome.dao.impl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.constants.TableConstant;
import com.cnit.yoyo.spider.autohome.dao.CarColorDao;
import com.cnit.yoyo.spider.common.base.dao.BaseDao;
import com.cnit.yoyo.spider.common.dto.KVObj;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarColor;


/**
 * @author yangyi
 *
 */
@Repository
public class CarColorDaoImpl extends BaseDao<CarColor> implements CarColorDao {

	@Override
	public void add(CarColor entity) {
		String sql = "insert into " + TableConstant.AUTOHOME_CAR_COLOR
				+ "(CAR_TYPE_ID,COLOR_ID,IS_INNER)values(:carTypeId,:colorId,:isInner)";
		super.insert(sql, entity);

	}

	@Override
	public void addOfBatch(List<CarColor> eList) {
		StringBuilder sql_build = new StringBuilder("insert into " + TableConstant.AUTOHOME_CAR_COLOR
				+ "(CAR_TYPE_ID,COLOR_ID,IS_INNER)values ");
		for (CarColor temp : eList) {
			sql_build.append("('" + temp.getCarTypeId() + "','" + temp.getColorId() + "'," + temp.getIsInner() + "),");
		}
		String sql = sql_build.toString();
		sql = sql.substring(0, sql.length() - 1);
		super.getJdbcTemplate().update(sql);
	}

	@Override
	public void upd(CarColor entity) {
	}

	@Override
	public void updOfBatch(List<CarColor> eList) {
	}

	private RowMapper<KVObj> rowMapper = new RowMapper<KVObj>() {
		@Override
		public KVObj mapRow(ResultSet rs, int rowNum) {
			KVObj kvObj = null;
			try {
				String id = rs.getString("id");
				Integer value = rs.getInt("cc");
				kvObj = new KVObj(id, value);
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
		String beginSql = " SELECT CONCAT(CAR_TYPE_ID , '-' , COLOR_ID) as ID ,COUNT(1) as cc FROM "
				+ TableConstant.AUTOHOME_CAR_COLOR + " t WHERE t.CAR_TYPE_ID =  -1 AND COLOR_ID = -1";
		StringBuilder sql_build = new StringBuilder(beginSql);
		for (String temp : ids) {
			String[] idArr = StringUtils.split(temp, "-");
			sql_build.append(" UNION ALL SELECT  CONCAT(CAR_TYPE_ID , '-' , COLOR_ID) as ID,COUNT(1) as cc FROM "
					+ TableConstant.AUTOHOME_CAR_COLOR + " t WHERE t.CAR_TYPE_ID =  '" + idArr[0]
					+ "' AND t.COLOR_ID = '" + idArr[1] + "'");

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