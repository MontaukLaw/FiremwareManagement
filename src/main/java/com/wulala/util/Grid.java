package com.wulala.util;

import java.util.ArrayList;
import java.util.List;

public class Grid {

	private int total = 0;
	private List<?> rows = new ArrayList<Object>();

	public Grid() {

	}

	public Grid(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
