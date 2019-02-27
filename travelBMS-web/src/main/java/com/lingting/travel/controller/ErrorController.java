package com.lingting.travel.controller;

import com.lingting.travel.domain.ResponseMsg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/403")
    public @ResponseBody ResponseMsg forbidden() {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setStatus(403);
        responseMsg.setDesc("权限不足");
        return responseMsg;
    }
}
