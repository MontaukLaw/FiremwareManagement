package com.wulala.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wulala.entity.FirmwareVersion;
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
	private Grid listAllFV() {
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

	@RequestMapping(value = "/listAllFVAPP")
	@ResponseBody
	private Grid listAllFVAPP() {
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
