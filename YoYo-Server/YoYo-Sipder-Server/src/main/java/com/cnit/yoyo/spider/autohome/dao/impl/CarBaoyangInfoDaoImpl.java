package com.cnit.yoyo.spider.autohome.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.constants.TableConstant;
import com.cnit.yoyo.spider.autohome.dao.CarBaoyangInfoDao;
import com.cnit.yoyo.spider.autohome.dao.CarInfoDao;
import com.cnit.yoyo.spider.common.base.dao.BaseDao;
import com.cnit.yoyo.spider.common.dto.KVObj;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarBaoyangInfo;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;
import com.cnit.yoyo.spider.common.utils.DateUtil;


/**
 * @author yangyi
 *
 */
@Repository
public class CarBaoyangInfoDaoImpl extends BaseDao<CarBaoyangInfo> implements CarBaoyangInfoDao {

	@Override
	public void add(CarBaoyangInfo entity) {
		String sql = "insert into "
				+ TableConstant.AUTOHOME_CAR_INFO
				+ "(ID,CAR_TYPE_ID,CAR_SERIES_ID,GEARBOX,GEARBOX_ID,FIRST_UPKEEP,FIRST_UPKEEP_MON,SECOND_UPKEEP,SECOND_UPKEEP_MON,UPKEEP_INTERVAL,UPKEEP_INTERVAL_MON,UPKEEP_STATUS,REMARK,CREATE_TIME,UPDATE_TIME)values(:id,:carTypeId,:carSeriesId,:gearBox,:gearBoxId,:firstUpKeep,:firstUpKeepMon,:secondUpKeep,:secondUpKeepMon,:interval,:intervalMon,:upKeepStatus,:remark,:createTime,:updateTime)";
		super.insert(sql, entity);

	}

	@Override
	public void addOfBatch(List<CarBaoyangInfo> eList) {
		StringBuilder sql_build = new StringBuilder("insert into " + TableConstant.AUTOHOME_BAOYANG_INFO
				+ "(ID,CAR_TYPE_ID,CAR_SERIES_ID,GEARBOX,GEARBOX_ID,FIRST_UPKEEP,FIRST_UPKEEP_MON,SECOND_UPKEEP,SECOND_UPKEEP_MON,UPKEEP_INTERVAL,UPKEEP_INTERVAL_MON,UPKEEP_STATUS,REMARK,CREATE_TIME,UPDATE_TIME)values ");
		for (CarBaoyangInfo temp : eList) {
			String createTimeStr = DateUtil.dateToDateString(temp.getCreateTime(), DateUtil.TIMEF_FORMAT);
			String updateTimeStr = DateUtil.dateToDateString(temp.getUpdateTime(), DateUtil.TIMEF_FORMAT);

			sql_build.append("('" + temp.getId() + "','" + temp.getCarTypeId() + "','" + temp.getCarSeriesId() + "','"
					+ temp.getGearBox() + "','" + temp.getGearBoxId() + "','" +temp.getFirstUpKeep() + "','" +temp.getFirstUpKeepMon()+ "','"
					+ temp.getSecondUpKeep() + "','"+ temp.getSecondUpKeepMon() + "','"
					+ temp.getInterval() + "','"+ temp.getIntervalMon() + "','" + temp.getUpKeepStatus() + "','" + temp.getRemark() + "',str_to_date('" + createTimeStr
					+ "','%Y-%m-%d %H:%i:%s'),str_to_date('" + updateTimeStr + "','%Y-%m-%d %H:%i:%s')),");
		}
		String sql = sql_build.toString();
		sql = sql.substring(0, sql.length() - 1);
		super.getJdbcTemplate().update(sql);
	}

	@Override
	public void upd(CarBaoyangInfo entity) {
	}

	@Override
	public void updOfBatch(List<CarBaoyangInfo> eList) {
		List<String> sqls = new ArrayList<String>();
		for (CarBaoyangInfo temp : eList) {
			String createTimeStr = DateUtil.dateToDateString(temp.getCreateTime(), DateUtil.TIMEF_FORMAT);
			String updateTimeStr = DateUtil.dateToDateString(temp.getUpdateTime(), DateUtil.TIMEF_FORMAT);

			sqls.add("update " + TableConstant.AUTOHOME_BAOYANG_INFO + " SET CAR_TYPE_ID='" + temp.getCarTypeId() + "' , CAR_SERIES_ID = '"
					+ temp.getCarSeriesId() + "', GEARBOX='" + temp.getGearBox() + "', GEARBOX_ID='" + temp.getGearBoxId() + "', FIRST_UPKEEP = '"
					+ temp.getFirstUpKeep() + "', FIRST_UPKEEP_MON = '"	+ temp.getFirstUpKeepMon()
					+ "', SECOND_UPKEEP = '" + temp.getSecondUpKeep() + "', SECOND_UPKEEP_MON = '" + temp.getSecondUpKeepMon()
					+ "', UPKEEP_INTERVAL = '" + temp.getInterval() + "', UPKEEP_INTERVAL_MON = '" + temp.getIntervalMon() + "', UPKEEP_STATUS = '"
					+ temp.getUpKeepStatus() + "', REMARK = '" + temp.getRemark()
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
		String beginSql = " SELECT ID,COUNT(1) as cc FROM " + TableConstant.AUTOHOME_BAOYANG_INFO + " t WHERE t.ID =  -1";
		StringBuilder sql_build = new StringBuilder(beginSql);
		for (String temp : ids) {
			sql_build.append(" UNION ALL SELECT  ID,COUNT(1) as cc FROM " + TableConstant.AUTOHOME_BAOYANG_INFO
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