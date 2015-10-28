package com.cnit.yoyo.spider.common.dto.autohome.vo;

import java.util.List;

public class ColorAttr {
	private List<Specitem> specitems;
	private Long total;

	public class Specitem {
		private Long specid;
		private List<Coloritem> coloritems;

		public class Coloritem {
			private Long id;
			private String name;
			private String value;
			private Integer picnum;
			private Integer clubpicnum;

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

			public String getValue() {
				return value;
			}

			public void setValue(String value) {
				this.value = value;
			}

			public Integer getPicnum() {
				return picnum;
			}

			public void setPicnum(Integer picnum) {
				this.picnum = picnum;
			}

			public Integer getClubpicnum() {
				return clubpicnum;
			}

			public void setClubpicnum(Integer clubpicnum) {
				this.clubpicnum = clubpicnum;
			}
		}

		public Long getSpecid() {
			return specid;
		}

		public void setSpecid(Long specid) {
			this.specid = specid;
		}

		public List<Coloritem> getColoritems() {
			return coloritems;
		}

		public void setColoritems(List<Coloritem> coloritems) {
			this.coloritems = coloritems;
		}

	}

	public List<Specitem> getSpecitems() {
		return specitems;
	}

	public void setSpecitems(List<Specitem> specitems) {
		this.specitems = specitems;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}