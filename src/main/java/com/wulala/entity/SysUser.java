package com.wulala.entity;

import java.util.Date;

public class SysUser {
	private String ID, LOGIN_NAME, PASSWORD;
	private Date CREATE_TIME;
	private int STATUS;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getLOGIN_NAME() {
		return LOGIN_NAME;
	}

	public void setLOGIN_NAME(String lOGIN_NAME) {
		LOGIN_NAME = lOGIN_NAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public int getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(int sTATUS) {
		STATUS = sTATUS;
	}

	@Override
	public String toString() {
		return "SysUser [ID=" + ID + ", LOGIN_NAME=" + LOGIN_NAME + ", PASSWORD=" + PASSWORD + ", CREATE_TIME="
				+ CREATE_TIME + ", STATUS=" + STATUS + "]";
	}

}
