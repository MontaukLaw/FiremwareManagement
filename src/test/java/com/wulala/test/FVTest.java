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

	@Test
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

}
