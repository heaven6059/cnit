package com.cnit.yoyo.spider.autohome.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.constants.TableConstant;
import com.cnit.yoyo.spider.autohome.dao.CarInfoDao;
import com.cnit.yoyo.spider.common.base.dao.BaseDao;
import com.cnit.yoyo.spider.common.dto.KVObj;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.CarInfo;
import com.cnit.yoyo.spider.common.utils.DateUtil;


/**
 * @author yangyi
 *
 */
@Repository
public class CarInfoDaoImpl extends BaseDao<CarInfo> implements CarInfoDao {

	@Override
	public void add(CarInfo entity) {
		String sql = "insert into "
				+ TableConstant.AUTOHOME_CAR_INFO
				+ "(ID,NAME,PID,IMG_PATH,AUTOHOME_URL,CREATE_TIME,UPDATE_TIME)values(:id,:name,:pid,:imgPath,:autoHomeUrl,:createTime,:updateTime)";
		super.insert(sql, entity);

	}

	@Override
	public void addOfBatch(List<CarInfo> eList) {
		StringBuilder sql_build = new StringBuilder("insert into " + TableConstant.AUTOHOME_CAR_INFO
				+ "(ID,NAME,PID,IMG_PATH,AUTOHOME_URL,LEVEL,YEAR,MADE_ID,CREATE_TIME,UPDATE_TIME)values ");
		for (CarInfo temp : eList) {
			String createTimeStr = DateUtil.dateToDateString(temp.getCreateTime(), DateUtil.TIMEF_FORMAT);
			String updateTimeStr = DateUtil.dateToDateString(temp.getUpdateTime(), DateUtil.TIMEF_FORMAT);

			sql_build.append("('" + temp.getId() + "','" + temp.getName() + "','" + temp.getPid() + "','"
					+ temp.getImgPath() + "','" + temp.getAutohomeUrl() + "',"  + (temp.getLevel() == null ? temp.getLevel():("'"+ temp.getLevel() +"'")) +  "," 
					+ (temp.getYear()==null ?  temp.getYear() : ("'"+ temp.getYear() +"'"))+"," 
					+ (temp.getMadeId()==null ? temp.getMadeId() : ("'"+ temp.getMadeId() +"'"))+ ",str_to_date('" + createTimeStr
					+ "','%Y-%m-%d %H:%i:%s'),str_to_date('" + updateTimeStr + "','%Y-%m-%d %H:%i:%s')),");
		}
		String sql = sql_build.toString();
		sql = sql.substring(0, sql.length() - 1);
		super.getJdbcTemplate().update(sql);
	}

	@Override
	public void upd(CarInfo entity) {
	}

	@Override
	public void updOfBatch(List<CarInfo> eList) {
		List<String> sqls = new ArrayList<String>();
		for (CarInfo temp : eList) {
			String createTimeStr = DateUtil.dateToDateString(temp.getCreateTime(), DateUtil.TIMEF_FORMAT);
			String updateTimeStr = DateUtil.dateToDateString(temp.getUpdateTime(), DateUtil.TIMEF_FORMAT);

			sqls.add("update " + TableConstant.AUTOHOME_CAR_INFO + " SET NAME='" + temp.getName() + "' , PID = '"
					+ temp.getPid() + "', IMG_PATH='" + temp.getImgPath() + "', AUTOHOME_URL='" + temp.getAutohomeUrl()
					+ "', LEVEL=" + (temp.getLevel() == null ? temp.getLevel():("'"+ temp.getLevel() +"'"))
					+ ", YEAR=" + (temp.getYear()==null ?  temp.getYear() : ("'"+ temp.getYear() +"'"))
					+ ", MADE_ID=" + (temp.getMadeId()==null ? temp.getMadeId() : ("'"+ temp.getMadeId() +"'"))
					+ ", SCOPE_ID=" + temp.getScopeId()
					+ ", UPDATE_TIME=str_to_date('" + updateTimeStr
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
		String beginSql = " SELECT ID,COUNT(1) as cc FROM " + TableConstant.AUTOHOME_CAR_INFO + " t WHERE t.ID =  -1";
		StringBuilder sql_build = new StringBuilder(beginSql);
		for (String temp : ids) {
			sql_build.append(" UNION ALL SELECT  ID,COUNT(1) as cc FROM " + TableConstant.AUTOHOME_CAR_INFO
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

	@Override
	public CarInfo queryByKey(CarInfo entity) {
		String beginSql = " SELECT * FROM " + TableConstant.AUTOHOME_CAR_INFO + " t WHERE t.ID =  ?";
		StringBuilder sql_build = new StringBuilder(beginSql);
		return this.getBean(sql_build.toString(), CarInfo.class, new Object[]{entity.getId()});
	}

	@Override
	public List<CarInfo> queryCarSerieInfos() {
		@SuppressWarnings("unchecked")
		List carInfos = super.getJdbcTemplate().query("SELECT * FROM USERS WHERE USERNAME LIKE '%CS'", new RowMapper()
		{
		     
		    @Override
		    public Object mapRow(ResultSet rs, int rowNum) throws SQLException
		    {
		    	CarInfo carInfo = new CarInfo();
		    	carInfo.setId(rs.getString("id"));
		    	carInfo.setName(rs.getString("name"));
		    	carInfo.setPid(rs.getString("pid"));
		    	carInfo.setImgPath(rs.getString("imgPath"));
		    	carInfo.setAutohomeUrl(rs.getString("autohomeUrl"));
		    	carInfo.setCreateTime(rs.getDate("createTime"));
		    	carInfo.setUpdateTime(rs.getDate("updateTime"));
		    	carInfo.setScopeId(rs.getInt("scopeId"));
		    	carInfo.setMadeId(rs.getString("madeId"));
		    	carInfo.setYear(rs.getString("year"));
		    	carInfo.setLevel(rs.getString("level"));
		        return carInfo;
		    }
		});
		return carInfos;
	}

	@Override
	public void batchUpdateCarLevel() {
		this.executeProc("batchUpdateCarLevel()");
	}

	
}