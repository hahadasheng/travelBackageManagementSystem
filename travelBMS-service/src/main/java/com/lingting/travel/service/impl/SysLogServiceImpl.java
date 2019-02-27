package com.lingting.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.lingting.travel.dao.SysLogDao;
import com.lingting.travel.domain.SysLog;
import com.lingting.travel.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    /** 将日志信息保存到数据库 */
    @Override
    public Integer save(SysLog sysLog) {
        return sysLogDao.save(sysLog);
    }

    /** 分页查询所有的日志信息 */
    @Override
    public List<SysLog> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return sysLogDao.findAll();
    }
}
