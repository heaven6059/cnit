package com.cnit.yoyo.spider.common.dto.autohome.vo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class FactoryConfig{
	private List<Paramtypeitem>	factoryitems;
	
	public class Paramtypeitem {
		private Long id;
		private String			name;
		private String firstletter;
		private List<SeriesItem>	seriesitems;
		
		public class SeriesItem {
            private Long id;
			private String			name;
			private String firstletter;
			private Long seriesstate;
			private Long seriesorder;
			public Long getId() {
				return id;
			}
			public void setId(Long id) {
				this.id = id;
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getFirstletter() {
				return firstletter;
			}
			public void setFirstletter(String firstletter) {
				this.firstletter = firstletter;
			}
			public Long getSeriesstate() {
				return seriesstate;
			}
			public void setSeriesstate(Long seriesstate) {
				this.seriesstate = seriesstate;
			}
			public Long getSeriesorder() {
				return seriesorder;
			}
			public void setSeriesorder(Long seriesorder) {
				this.seriesorder = seriesorder;
			}
			
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFirstletter() {
			return firstletter;
		}

		public void setFirstletter(String firstletter) {
			this.firstletter = firstletter;
		}

		public List<SeriesItem> getSeriesitems() {
			return seriesitems;
		}

		public void setSeriesitems(List<SeriesItem> seriesitems) {
			this.seriesitems = seriesitems;
		}
		
	}

	public List<Paramtypeitem> getFactoryitems() {
		return factoryitems;
	}

	public void setFactoryitems(List<Paramtypeitem> factoryitems) {
		this.factoryitems = factoryitems;
	}

	public static void main(String[] args) {
		String config = "{'returncode':0,'message':'成功','result':{'factoryitems':[]}}";
		System.out.println(JSONObject.parseObject(config).getString("result"));
		FactoryConfig confAttrVO = JSONObject.parseObject(JSONObject.parseObject(config).getString("result").replace("\"", "'"),FactoryConfig.class);
		System.out.println(confAttrVO.toString());
	}
	
}