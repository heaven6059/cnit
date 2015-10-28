package com.cnit.yoyo.spider.common.dto.autohome.vo;

import java.util.List;

public class OptionAttr {
	private Long					seriesid;
	private List<Configtypeitem>	configtypeitems;
	
	public class Configtypeitem {
		private String				name;
		private List<Configitem>	configitems;
		
		public class Configitem {
			private String			name;
			private List<Valueitem>	valueitems;
			
			public String getName() {
				return name;
			}
			
			public void setName(String name) {
				this.name = name;
			}
			
			public List<Valueitem> getValueitems() {
				return valueitems;
			}
			
			public void setValueitems(List<Valueitem> valueitems) {
				this.valueitems = valueitems;
			}
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public List<Configitem> getConfigitems() {
			return configitems;
		}
		
		public void setConfigitems(List<Configitem> configitems) {
			this.configitems = configitems;
		}
		
	}
	
	public Long getSeriesid() {
		return seriesid;
	}
	
	public void setSeriesid(Long seriesid) {
		this.seriesid = seriesid;
	}
	
	public List<Configtypeitem> getConfigtypeitems() {
		return configtypeitems;
	}
	
	public void setConfigtypeitems(List<Configtypeitem> configtypeitems) {
		this.configtypeitems = configtypeitems;
	}
	
	@Override
	public String toString() {
		return "OptionAttrVO [seriesid=" + seriesid + ", configtypeitems="
				+ configtypeitems + "]";
	}
	
	
}