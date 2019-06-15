package com.miaoc.itools.controller.message;

import com.alibaba.fastjson.JSONObject;
import com.miaoc.itools.entity.base.ResBody;
import com.miaoc.itools.service.message.MessagePushService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: itools
 * @description:
 * @author: Mr.cc
 * @create: 2019-06-13 22:01
 **/
@CrossOrigin("*")
@RestController
@RequestMapping("/app")
public class MessagePushController {

    @Resource
    private MessagePushService messagePushService;

    @RequestMapping(value = "/message")
    @ResponseBody
    public ResBody message(@RequestBody JSONObject req) throws Exception {
        ResBody res = new ResBody();//返回值
        res=messagePushService.message(req);
        return res;
    }

}
