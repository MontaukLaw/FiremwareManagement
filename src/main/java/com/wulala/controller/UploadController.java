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
import com.wulala.util.Params;

@Controller
@RequestMapping(value = "/file")
public class UploadController {

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public JsonMsg upload(MultipartFile file) {
		JsonMsg json = new JsonMsg();
		try {
			SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyyMMdd\\HHmmss");
			String subFolderName = dateformat1.format(new Date()) + "\\";
			String baseFolder = Params.BASE_FOLDER;
			String fullFilePath = baseFolder + subFolderName + file.getOriginalFilename();
			FileUtils.writeByteArrayToFile(new File(fullFilePath), file.getBytes());
			FileInfo fileInfo = new FileInfo();
			// fileInfo.setFileMD5(fileMD5);
			String md5 = Md5CaculateUtil.getMD5(new File(fullFilePath));
			fileInfo.setFilePath(fullFilePath);
			fileInfo.setFileSize((long) file.getBytes().length);
			fileInfo.setFileMD5(md5);
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
