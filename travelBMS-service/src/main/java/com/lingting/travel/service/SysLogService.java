package com.lingting.travel.service;

import com.lingting.travel.domain.SysLog;

import java.util.List;

public interface SysLogService {

    /** 将日志信息保存到数据库 */
    public Integer save(SysLog sysLog);

    /** 查询所有的日志信息，分页查询 */
    public List<SysLog> findAll(Integer pageNum, Integer pageSize);
}
