package com.wulala.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

import com.wulala.util.MD5;
import com.wulala.util.UuidUtil;

public class MD5Test {

	@Test
	public void md5test() {
		System.out.println(MD5.md5("P@ssw0rd"));

	}

	@Test
	public void genTimeString() {
		SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyyMMdd\\HHmmss");
		String a1 = dateformat1.format(new Date());
		String fileName = a1 + "\\" + UuidUtil.get32UUID();
		System.out.println(fileName);
	}

	@Test
	public void readProperties() throws IOException {
		Properties properties = new Properties();
		// 使用InPutStream流读取properties文件
		BufferedReader bufferedReader = new BufferedReader(new FileReader("/prod.properties"));
		properties.load(bufferedReader);
		// 获取key对应的value值
		properties.getProperty("proc");

	}
	
	@Test
	public void readP() throws Exception{
		String path = MD5Test.class.getClassLoader().getResource("prod.properties").getPath();
		FileInputStream in = new FileInputStream(path);
		Properties prop = new Properties();
		prop.load(in);
		System.out.println(prop.getProperty("proc"));
		
	}

}
