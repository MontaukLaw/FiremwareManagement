package com.wulala.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wulala.entity.FirmwareVersion;
import com.wulala.entity.JsonMsg;
import com.wulala.service.FirmwareVersionService;
import com.wulala.util.Grid;
import com.wulala.util.PageData;
import com.wulala.util.Tools;

@Controller
@RequestMapping(value = "/fv")
public class FirmwareVersionController extends BaseController {

	@Resource(name = "firmwareVersionService")
	private FirmwareVersionService firmwareVersionService;

	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Grid listAllFV() {
		logger.info("start to handling listAllFV.do");
		Grid grid = new Grid();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<FirmwareVersion> fvList = firmwareVersionService.listAllFV(pd);
			grid.setRows(fvList);
			grid.setTotal(fvList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grid;
	}

	@RequestMapping(value = "addFV")
	@ResponseBody
	public JsonMsg addFV() {
		JsonMsg json = new JsonMsg();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			int result = firmwareVersionService.addFV(pd);
			if (result > 0) {
				json.setMsg("插入" + result + "条数据");
			}else{
				json.setMsg("数据插入失败, 请联系系统管理员");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getStackTrace().toString());
		}
		return json;

	}

	@RequestMapping(value = "/listAllFVAPP")
	@ResponseBody
	public Grid listAllFVAPP() {
		logger.info("start to handling listAllFV.do");
		Grid grid = new Grid();
		PageData pd = new PageData();
		pd = this.getPageData();
		String key = (String) pd.get("KEY");
		if (Tools.checkKey(key)) {
			try {
				List<FirmwareVersion> fvList = firmwareVersionService.listAllFV(pd);
				grid.setRows(fvList);
				grid.setTotal(fvList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return grid;
	}

}
