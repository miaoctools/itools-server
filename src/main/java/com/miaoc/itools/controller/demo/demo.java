package com.miaoc.itools.controller.demo;

import com.alibaba.fastjson.JSONObject;
import com.miaoc.itools.entity.base.ResBody;
import com.miaoc.itools.utils.AESUtils;
//import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/app")
public class demo {

    @RequestMapping(value = "/demo")
    @ResponseBody
    public ResBody demo(@RequestBody JSONObject req) throws Exception {
        ResBody res = new ResBody();//返回值
        AESUtils aesUtils=new AESUtils();
        String model=(String) req.get("data");
        String desData=aesUtils.desEncrypt(model);
        JSONObject resultData=new JSONObject();
        if(desData==null){
            resultData.put("data","failed");

            resultData=AESUtils.encrypt(resultData.toString());//json转string后加密
            res.setStatus(0);
            res.setMsg("失败");
            res.setResData(resultData);
            return res;
        }
//        net.sf.json.JSONObject obj = net.sf.json.JSONObject.fromObject(desData);//json对象字符串转json
        resultData.put("data","success");
        res.setStatus(1);
        res.setMsg("成功");
        resultData=AESUtils.encrypt(resultData.toString());//json转string后加密
        res.setResData(resultData);
        return res;
    }

}
