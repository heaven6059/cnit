package com.cnit.yoyo.spider.autohome.dao.impl;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cnit.yoyo.spider.autohome.constants.Constant;
import com.cnit.yoyo.spider.autohome.constants.TableConstant;
import com.cnit.yoyo.spider.autohome.dao.ColorInfoDao;
import com.cnit.yoyo.spider.common.base.dao.BaseDao;
import com.cnit.yoyo.spider.common.dto.KVObj;
import com.cnit.yoyo.spider.common.dto.autohome.pojo.ColorInfo;


/**
 * @author yangyi
 *
 */
@Repository
public class ColorInfoDaoImpl extends BaseDao<ColorInfo> implements ColorInfoDao {

	@Override
	public void add(ColorInfo entity) {
		String sql = "insert into "
				+ TableConstant.AUTOHOME_COLOR_INFO
				+ "(ID,NAME,VAL,PIC_NUM,CLUB_PIC_NUM)values(:id,:name,:val,:picnum,:clubPicnum)";
		super.insert(sql, entity);

	}

	@Override
	public void addOfBatch(List<ColorInfo> eList) {
		StringBuilder sql_build = new StringBuilder("insert into " + TableConstant.AUTOHOME_COLOR_INFO
				+ "(ID,NAME,VAL,PIC_NUM,CLUB_PIC_NUM)values ");
		for (ColorInfo temp : eList) {
			sql_build.append("('" + temp.getId() + "','" + temp.getName() + "','" + temp.getVal() + "','"
					+ temp.getPicnum() + "'," + temp.getClubPicnum()+ "),");
		}
		String sql = sql_build.toString();
		sql = sql.substring(0, sql.length() - 1);
		super.getJdbcTemplate().update(sql);
	}

	@Override
	public void upd(ColorInfo entity) {
	}

	@Override
	public void updOfBatch(List<ColorInfo> eList) {
		int eLen = eList.size();
		String[] sqls = new String[eLen];
		for (int i = 0; i < eLen; i++) {
			ColorInfo temp = eList.get(i);
			sqls[i] = "update " + TableConstant.AUTOHOME_COLOR_INFO + " SET NAME='" + temp.getName() + "' , VAL = '"
					+ temp.getVal() + "', PIC_NUM='" + temp.getPicnum() + "', CLUB_PIC_NUM="
					+ temp.getClubPicnum() + " WHERE ID = '"
					+ temp.getId() + "'";
		}
		super.getJdbcTemplate().batchUpdate(sqls);
	}

	private RowMapper<KVObj> rowMapper = new RowMapper<KVObj>() {
		@Override
		public KVObj mapRow(ResultSet rs, int rowNum) {
			KVObj kvObj = null;
			try {
				Long key = rs.getLong("id");
				Integer value = rs.getInt("cc");
				kvObj = new KVObj(key, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return kvObj;
		}
	};

	@Override
	public Map<Long, Integer> exists(Iterable<Long> ids) {
		int i = 0;
		Map<Long, Integer> kvMap = new HashMap<Long, Integer>();
		String beginSql = " SELECT ID,COUNT(1) as cc FROM " + TableConstant.AUTOHOME_COLOR_INFO + " t WHERE t.ID =  -1";

		StringBuilder sql_build = new StringBuilder(beginSql);
		for (Long temp : ids) {
			sql_build.append(" UNION ALL SELECT  ID,COUNT(1) as cc FROM " + TableConstant.AUTOHOME_COLOR_INFO
					+ " t WHERE t.ID =  '" + temp + "'");

			if ((i++) % Constant.SQL_COUNT_LIMIT == 0) {
				Map<Long, Integer> tempMap = KVObj.toMap(super.getJdbcTemplate().query(sql_build.toString(),
						rowMapper));
				kvMap.putAll(tempMap);
				sql_build = new StringBuilder(beginSql);
			}
		}
		Map<Long, Integer> tempMap = KVObj.toMap(super.getJdbcTemplate().query(sql_build.toString(), rowMapper));
		kvMap.putAll(tempMap);
		return kvMap;

	}

}
