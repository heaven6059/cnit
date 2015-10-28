package com.cnit.yoyo.spider.autohome.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.constants.TableConstant;
import com.cnit.yoyo.spider.autohome.dao.CarBaoyangAttrDao;
import com.cnit.yoyo.spider.autohome.dao.CarColorDao;
import com.cnit.yoyo.spider.common.base.dao.BaseDao;
import com.cnit.yoyo.spider.common.dto.KVObj;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangAttr;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangInfo;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarColor;
import com.cnit.yoyo.spider.common.utils.DateUtil;
import com.mysql.jdbc.log.Log;


/**
 * @author yangyi
 *
 */
@Repository
public class CarBaoyangAttrDaoImpl extends BaseDao<CarBaoyangAttr> implements CarBaoyangAttrDao {

	@Override
	public void add(CarBaoyangAttr entity) {
		String sql = "insert into " + TableConstant.AUTOHOME_BAOYANG_ATTR
				+ "(ID,BAOYANG_ID,ITEM,GEARBOX,MILEAGE,UPKEEP_COST,UPKEEP_MONTH,UPKEEP_STATUS,CREATE_TIME,UPDATE_TIME)values(:id,:baoyangId,:item,:gearBox,:mileage,:upKeepCost,:upKeepMonth,:upKeepStatus,:createTime,:updateTime)";
		super.insert(sql, entity);

	}

	@Override
	public void addOfBatch(List<CarBaoyangAttr> eList) {
		StringBuilder sql_build = new StringBuilder("insert into " + TableConstant.AUTOHOME_BAOYANG_ATTR
				+ "(ID,BAOYANG_ID,ITEM,GEARBOX,MILEAGE,UPKEEP_COST,UPKEEP_MONTH,UPKEEP_STATUS,CREATE_TIME,UPDATE_TIME)values ");
		for (CarBaoyangAttr temp : eList) {
			String createTimeStr = DateUtil.dateToDateString(temp.getCreateTime(), DateUtil.TIMEF_FORMAT);
			String updateTimeStr = DateUtil.dateToDateString(temp.getUpdateTime(), DateUtil.TIMEF_FORMAT);
			sql_build.append("('" + temp.getId() + "','" + temp.getBaoyangId() + "','" + temp.getItem() + "','" + temp.getGearBox() + "','" +temp.getMileage()+ "','" 
					+ temp.getUpKeepCost() + "','" + temp.getUpKeepMonth() + "','" + temp.getUpKeepStatus()+  "',str_to_date('" + createTimeStr
					+ "','%Y-%m-%d %H:%i:%s'),str_to_date('" + updateTimeStr + "','%Y-%m-%d %H:%i:%s')),");
		}
		String sql = sql_build.toString();
		sql = sql.substring(0, sql.length() - 1);
		super.getJdbcTemplate().update(sql);
	}

	@Override
	public void upd(CarBaoyangAttr entity) {
	}

	@Override
	public void updOfBatch(List<CarBaoyangAttr> eList) {
		List<String> sqls = new ArrayList<String>();
		for (CarBaoyangAttr temp : eList) {
			String createTimeStr = DateUtil.dateToDateString(temp.getCreateTime(), DateUtil.TIMEF_FORMAT);
			String updateTimeStr = DateUtil.dateToDateString(temp.getUpdateTime(), DateUtil.TIMEF_FORMAT);
			sqls.add("update " + TableConstant.AUTOHOME_BAOYANG_ATTR + " SET BAOYANG_ID='" + temp.getBaoyangId() + "' , ITEM = '"
					+ temp.getItem() + "', GEARBOX='" + temp.getGearBox() + "', MILEAGE='" + temp.getMileage() + "', UPKEEP_COST = '"
					+ temp.getUpKeepCost() + "', UPKEEP_MONTH = '" + temp.getUpKeepMonth() + "', UPKEEP_STATUS = '" + temp.getUpKeepStatus() 
					+ "', UPDATE_TIME=str_to_date('" + updateTimeStr
					+ "','%Y-%m-%d %H:%i:%s') WHERE ID = '" + temp.getId() + "'");
		}
		super.getJdbcTemplate().batchUpdate(sqls.toArray(new String[] {}));
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
		String beginSql = " SELECT ID ,COUNT(1) as cc FROM "
				+ TableConstant.AUTOHOME_BAOYANG_ATTR + " t WHERE t.BAOYANG_ID =  -1 AND ITEM = -1 AND MILEAGE = -1";
		StringBuilder sql_build = new StringBuilder(beginSql);
		for (String temp : ids) {
//			String[] idArr = StringUtils.split(temp, "\\-");
//			System.out.println("第一个记录"+i+"BAOYANG_ID>>>"+idArr[0]+"ITEM>>>"+idArr[1]+"MILEAGE>>>"+idArr[2]);
			sql_build.append(" UNION ALL SELECT  ID,COUNT(1) as cc FROM "
					+ TableConstant.AUTOHOME_BAOYANG_ATTR + " t WHERE t.ID =  '" + temp + "'");

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