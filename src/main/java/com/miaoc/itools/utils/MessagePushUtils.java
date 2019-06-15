package com.miaoc.itools.utils;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.miaoc.itools.entity.utils.MessagePush;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagePushUtils {

    @Autowired
    private MessagePush messagePush;

    public Object messagesend(JSONObject req) {
        JSONObject obj=new JSONObject();
        DefaultProfile profile = DefaultProfile.getProfile("default", messagePush.getAccesskeyid(), messagePush.getAccesssecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
//        request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("SignName", messagePush.getSignname());

        request.putQueryParameter("PhoneNumbers", req.get("number").toString());
//        request.putQueryParameter("TemplateCode", messagePush.getTemplatecode());
        request.putQueryParameter("TemplateCode", req.get("mould").toString());
        request.putQueryParameter("TemplateParam", req.get("params").toString());
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            obj=JSONObject.parseObject(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return obj;
    }
}