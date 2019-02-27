package com.lingting.travel.controller;

import com.github.pagehelper.PageInfo;
import com.lingting.travel.domain.SysLog;
import com.lingting.travel.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll")
    public @ResponseBody PageInfo<SysLog> findAll(
            @RequestParam(name = "pageNum", required = true, defaultValue = "1")Integer pageNum,
            @RequestParam(name = "pageSize", required = true, defaultValue = "10")Integer pageSize
    ) {
        List<SysLog> sysLogs = sysLogService.findAll(pageNum, pageSize);
        return new PageInfo<SysLog>(sysLogs);
    }
}
