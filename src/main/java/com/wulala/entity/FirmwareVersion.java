package com.wulala.entity;

import java.util.Date;

public class FirmwareVersion {
	private String ID, HARDWARE_MODEL, SN, FILE_NAME, FILE_MD5;
	private String CLIENT_NAME, DESCRIPTION;
	private String VERSION;
	private Date CREATE_TIME;
	private Long FILE_SIZE;

	public Date getCREATE_TIME() {
		return CREATE_TIME;
	}

	public void setCREATE_TIME(Date cREATE_TIME) {
		CREATE_TIME = cREATE_TIME;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getHARDWARE_MODEL() {
		return HARDWARE_MODEL;
	}

	public void setHARDWARE_MODEL(String hARDWARE_MODEL) {
		HARDWARE_MODEL = hARDWARE_MODEL;
	}

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}

	public String getFILE_NAME() {
		return FILE_NAME;
	}

	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}

	public String getFILE_MD5() {
		return FILE_MD5;
	}

	public void setFILE_MD5(String fILE_MD5) {
		FILE_MD5 = fILE_MD5;
	}

	public String getCLIENT_NAME() {
		return CLIENT_NAME;
	}

	public void setCLIENT_NAME(String cLIENT_NAME) {
		CLIENT_NAME = cLIENT_NAME;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public String getVERSION() {
		return VERSION;
	}

	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}

	public Long getFILE_SIZE() {
		return FILE_SIZE;
	}

	public void setFILE_SIZE(Long fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}

	@Override
	public String toString() {
		return "FirmwareVersion [ID=" + ID + ", HARDWARE_MODEL=" + HARDWARE_MODEL + ", SN=" + SN + ", FILE_NAME="
				+ FILE_NAME + ", FILE_MD5=" + FILE_MD5 + ", CLIENT_NAME=" + CLIENT_NAME + ", DESCRIPTION=" + DESCRIPTION
				+ ", VERSION=" + VERSION + ", CREATE_TIME=" + CREATE_TIME + ", FILE_SIZE=" + FILE_SIZE + "]";
	}

}
