package com.yzx.shiro.controller;

import com.yzx.shiro.config.msg.MsgProducer;
import com.yzx.shiro.config.msg.MsgReceiver;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 消息队列处理器
 *
 * @author：yzx
 * @date:2020/01/17
 */
@Controller
@RequestMapping("/com/yzx/msg")
public class MsgController {

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private MsgReceiver msgReceiver;

    @RequestMapping("/sendMsg")
    public String sendMsg() {
        for (int i = 0; i <= 5; i++) {
            msgProducer.sendMsg("消息信息：" + i);
        }
        return "";
    }
}
