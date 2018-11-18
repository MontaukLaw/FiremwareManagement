package com.wulala.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wulala.entity.SysUser;
import com.wulala.service.SysUserService;
import com.wulala.util.Logger;
import com.wulala.util.PageData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/ApplicationContext.xml" })
public class SysUserTest {

	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource(name = "sysUserService")
	private SysUserService sysUserService;

	@Test
	public void listAllTest() {
		PageData pd = new PageData();
		Map userInfo = new HashMap();
		userInfo.put("LOGIN_NAME", "admin");
		// userInfo.put("PASSWORD", "161ebd7d45089b3446ee4e0d86dbcf92");
		pd.putAll(userInfo);
		try {
			SysUser sysUser = sysUserService.findSysUserByLoginNamePassword(pd);
			if (sysUser != null) {
				logger.info("输出是: " + sysUser);
			} else {
				logger.info("用户" + userInfo.get("LOGIN_NAME") + "用户名密码不对");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

}
