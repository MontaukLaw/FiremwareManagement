package com.wulala.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wulala.dao.DaoSupport;
import com.wulala.entity.SysUser;
import com.wulala.util.PageData;

@Service("sysUserService")
public class SysUserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	public SysUser findSysUserByLoginNamePassword(PageData pd) throws Exception {
		return (SysUser) dao.findForObject("SysUserMapper.findSysUserByLoginNamePassword", pd);
	}

}
