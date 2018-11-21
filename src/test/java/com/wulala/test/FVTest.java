package com.wulala.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wulala.service.FirmwareVersionService;
import com.wulala.util.Logger;
import com.wulala.util.PageData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/ApplicationContext.xml" })
public class FVTest {

	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource(name = "firmwareVersionService")
	private FirmwareVersionService firmwareVersionService;

	// @Test
	public void listAllTest() {
		PageData pd = new PageData();
		Map userInfo = new HashMap();
		pd.putAll(userInfo);
		try {
			logger.info(firmwareVersionService.listAllFV(pd));
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	// @Test
	public void addFVTest() {
		PageData pd = new PageData();
		Map userInfo = new HashMap();
		pd.putAll(userInfo);
		try {
			logger.info(firmwareVersionService.addFV(pd));
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	//@Test
	public void removeFVTest() {
		PageData pd = new PageData();
		Map userInfo = new HashMap();
		userInfo.put("ID", "54f2ec28-eb44-11e8-8f9c-00163e040582");
		pd.putAll(userInfo);
		try {
			logger.info(firmwareVersionService.removeFV(pd));
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}
	
	@Test
	public void updateFVTest() {
		PageData pd = new PageData();
		Map userInfo = new HashMap();
		userInfo.put("ID", "3cf8dba2-ecd4-11e8-8f9c-00163e040582");
		
		userInfo.put("HARDWARE_MODEL", "001");
		userInfo.put("SN", "001");
		userInfo.put("CLIENT_NAME", "001");
		userInfo.put("DESCRIPTION", "001");
		userInfo.put("VERSION", "001");
		
		pd.putAll(userInfo);
		try {
			logger.info(firmwareVersionService.updateFV(pd));
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

}
