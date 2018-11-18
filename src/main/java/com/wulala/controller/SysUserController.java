package com.wulala.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wulala.entity.JsonMsg;
import com.wulala.entity.SysUser;
import com.wulala.service.SysUserService;
import com.wulala.util.MD5;
import com.wulala.util.PageData;

@Controller
@RequestMapping(value = "/sys")
public class SysUserController extends BaseController {

	@Resource(name = "sysUserService")
	private SysUserService sysUserService;

	@RequestMapping(value = "/findSysUserByLoginNamePassword")
	@ResponseBody
	public JsonMsg findSysUserByLoginNamePassword() {

		logger.info("start to handling listArticleByVehicleID.do");
		JsonMsg json = new JsonMsg();
		PageData pd = new PageData();
		pd = this.getPageData();
		//先把password拿出来, 然后MD5一下, 因为后台保存的都是MD5过的.
		String password = MD5.md5(pd.getString("PASSWORD"));
		//除掉原来的, 再赋值
		pd.remove("PASSWORD");
		pd.put("PASSWORD", password);
		try {
			SysUser sysUser = sysUserService.findSysUserByLoginNamePassword(pd);
			if (sysUser != null) {
				json.setObj(sysUser);
			} else {
				json.setSuccess(false);
				json.setMsg("用户名密码不对");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg("接口访问错误");
			logger.error(e.toString(), e);
		}

		logger.info("findSysUserByLoginNamePassword.do end");
		return json;
	}

}
