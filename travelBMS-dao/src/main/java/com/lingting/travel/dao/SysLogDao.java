package com.lingting.travel.dao;

import com.lingting.travel.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysLogDao {

    /** 将日志信息保存到数据库 */
    @Insert("insert into sysLog(visitTime, username, ip, url, executionTime, method) " +
            "values(#{visitTime}, #{username},  #{ip}, #{url}, #{executionTime}, #{method})")
    public Integer save(SysLog sysLog);

    /** 查询所有的日志信息 */
    @Select("select * from sysLog")
    public List<SysLog> findAll();
}
