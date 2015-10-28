package com.cnit.yoyo.model.trade;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.reship.model.AftersalesReturnProduct;

public class AftersalesReturnProductWithBLOBs extends AftersalesReturnProduct
		implements Serializable {

	private List<AftersalesReturnLogWithBLOBs> logs;

	public List<AftersalesReturnLogWithBLOBs> getLogs() {
		return logs;
	}

	public void setLogs(List<AftersalesReturnLogWithBLOBs> logs) {
		this.logs = logs;
	}
	private static final long serialVersionUID = 1L;
}