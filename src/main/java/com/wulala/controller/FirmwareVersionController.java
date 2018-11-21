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

	@RequestMapping(value = "updateFVByID")
	@ResponseBody
	public JsonMsg updateFV() {
		JsonMsg json = new JsonMsg();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			int result = firmwareVersionService.updateFV(pd);
			if (result > 0) {
				json.setMsg("修改了" + result + "条数据");
			} else {
				json.setMsg("数据修改失败, 请联系系统管理员");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getStackTrace().toString());
		}
		return json;

	}

	@RequestMapping(value = "removeFVByID")
	@ResponseBody
	public JsonMsg removeFV() {
		JsonMsg json = new JsonMsg();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			int result = firmwareVersionService.removeFV(pd);
			if (result > 0) {
				json.setMsg("删除了" + result + "条数据");
			} else {
				json.setMsg("数据删除失败, 请联系系统管理员");
			}
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getStackTrace().toString());
		}
		return json;

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
			} else {
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
	public JsonMsg listAllFVAPP() throws Exception {
		logger.info("start to handling listAllFVAPP.do");
		JsonMsg json = new JsonMsg();
		PageData pd = new PageData();
		pd = this.getPageData();
		String key = (String) pd.get("KEY");
		if (Tools.checkKey(key)) {
			try {
				List<FirmwareVersion> fvList = firmwareVersionService.listAllFV(pd);
				json.setObj(fvList);
				json.setMsg("OK");
			} catch (Exception e) {
				json.setSuccess(false);
				json.setMsg("ERROR");
			}
		} else {
			json.setSuccess(false);
			json.setMsg("Key Error");
		}
		return json;
	}

	@RequestMapping(value = "/getFVByIDAPP")
	@ResponseBody
	public JsonMsg getFVByIDAPP() throws Exception {
		logger.info("start to handling getFVByIDAPP.do");
		JsonMsg json = new JsonMsg();
		PageData pd = new PageData();
		pd = this.getPageData();
		String key = (String) pd.get("KEY");
		String id = (String) pd.get("ID");
		if (id != null) {
			if (Tools.checkKey(key)) {
				try {
					FirmwareVersion fv = firmwareVersionService.getFVByID(pd);
					if(fv!=null){
						json.setMsg("OK");
					}else{
						json.setMsg("No record");
					}
					json.setObj(fv);
				} catch (Exception e) {
					json.setSuccess(false);
					json.setMsg("ERROR");
					logger.error(e.getStackTrace());
					// json.setObj(e.getStackTrace());
				}
			} else {
				json.setSuccess(false);
				json.setMsg("Key Error");
			}
		} else {
			json.setSuccess(false);
			json.setMsg("Must input ID");
		}
		return json;
	}

}
