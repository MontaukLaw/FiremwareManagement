package com.wulala.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wulala.entity.FileInfo;
import com.wulala.entity.JsonMsg;
import com.wulala.util.Md5CaculateUtil;
import com.wulala.util.PageData;
import com.wulala.util.Params;

@Controller
@RequestMapping(value = "/file")
public class UploadController extends BaseController {

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg upload(MultipartFile file) {
		logger.info("start to handling uploading");

		PageData pd = new PageData();
		pd = this.getPageData();

		JsonMsg json = new JsonMsg();

		// logger.info("xxx" + pd.get("HARDWARE_MODEL"));
		try {
			SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyyMMdd//HHmmss");

			// 获取日期,组成目录名
			String subFolderName = dateformat1.format(new Date()) + "//";

			// 获取根目录名, 当然这个解决方案很烂, 但是没有google到正确的方法, 回头再改
			// 找到问题了,可以读properties文件了
			String baseFolder = Params.getBaseFolder();

			// 组成长文件名
			String fullFilePath = baseFolder + subFolderName + file.getOriginalFilename();

			// 写入
			FileUtils.writeByteArrayToFile(new File(fullFilePath), file.getBytes());

			// 将结果准备输出
			FileInfo fileInfo = new FileInfo();

			// 计算MD5
			String md5 = Md5CaculateUtil.getMD5(new File(fullFilePath));

			// 记录文件目录信息
			fileInfo.setFilePath(subFolderName + file.getOriginalFilename());

			// 记录文件大小
			fileInfo.setFileSize((long) file.getBytes().length);

			// 记录MD5编码
			fileInfo.setFileMD5(md5);

			// 保存到输出Json
			json.setObj(fileInfo);
			json.setMsg("OK");
			return json;

		} catch (IOException e) {
			json.setSuccess(false);
			json.setMsg(e.getStackTrace().toString());
			return json;
		}
	}
}
