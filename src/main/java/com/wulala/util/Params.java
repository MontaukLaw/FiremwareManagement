package com.wulala.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Params {
	public static final String WIN_BASE_FOLDER = "c:\\upload\\";
	public static final String LINUX_BASE_FOLDER = "//tomcat//tomcat8//webapps//ROOT";

	public static String getBaseFolder() {
		Params p = new Params();
		String status = null;
		String baseFolder = "";
		try {
			status = p.getProd();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (status.equals("debug")) {
			baseFolder = WIN_BASE_FOLDER;
		} else if (status.equals("production")) {
			baseFolder = LINUX_BASE_FOLDER;
		}

		return baseFolder;
	}

	public String getProd() throws IOException {
		String path = Params.class.getClassLoader().getResource("prod.properties").getPath();
		// System.out.println(path);
		FileInputStream in = null;
		try {
			in = new FileInputStream(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop.getProperty("proc");

	}

	public static void main(String[] args) throws IOException {
		Params p = new Params();
		System.out.println(p.getProd());

	}

}
