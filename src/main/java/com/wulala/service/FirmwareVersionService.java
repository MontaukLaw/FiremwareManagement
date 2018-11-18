package com.wulala.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wulala.dao.DaoSupport;
import com.wulala.entity.FirmwareVersion;
import com.wulala.util.PageData;

@Service("firmwareVersionService")
public class FirmwareVersionService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	public List<FirmwareVersion> listAllFV(PageData pd) throws Exception {
		return (List<FirmwareVersion>) dao.findForList("FirmwareVersionMapper.listAll", pd);
	}

}
