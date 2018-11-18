package com.wulala.test;

import org.junit.Test;

import com.wulala.util.MD5;

public class MD5Test {
	
	@Test
	public void md5test(){
		
		System.out.println(MD5.md5("P@ssw0rd"));
		
	}

}
