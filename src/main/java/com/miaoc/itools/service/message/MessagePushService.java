package com.miaoc.itools.service.message;

import com.alibaba.fastjson.JSONObject;
import com.miaoc.itools.entity.base.ResBody;
import com.miaoc.itools.utils.MessagePushUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: itools
 * @description:
 * @author: Mr.cc
 * @create: 2019-06-13 22:01
 **/

@Service
public class MessagePushService {

    @Resource
    private MessagePushUtils messagePushUtils;

    public ResBody message(@RequestBody JSONObject req) throws Exception {
        ResBody res = new ResBody();//返回值
//"{\"name\":\"名字\"}"
        String params="";
        if(!req.get("relaname").equals("")){
            params="{\"name\":\""+req.get("name")+"\",\"someone\":\""+req.get("rela")+"\",\"relaname\":\""+req.get("relaname")+"\"}";
        }else{
            params="{\"name\":\""+req.get("name")+"\",\"someone\":\""+req.get("rela")+"\"}";
        }
        req.put("params",params);
        Object obj= messagePushUtils.messagesend(req);
        res.setStatus(1);
        res.setMsg("成功");
        res.setResData(obj);
        return res;
    }

}
