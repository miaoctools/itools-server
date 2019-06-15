package com.miaoc.itools;

import com.miaoc.itools.entity.utils.MessagePush;
import com.miaoc.itools.utils.MessagePushUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItoolsApplicationTests {

    @Autowired
    MessagePush messagePush;

    MessagePushUtils mess;

    @Test
    public void contextLoads() {
//        mess.messagesend();
        System.out.println(messagePush.getSignname());
    }

}
