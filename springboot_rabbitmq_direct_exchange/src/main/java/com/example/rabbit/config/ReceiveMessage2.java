package com.example.rabbit.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接受消息
 */
@Component
@RabbitListener(queues = "user.save.queue.name")
public class ReceiveMessage2 {
    private static Logger logger = LoggerFactory.getLogger(ReceiveMessage2.class);

    @RabbitHandler
    public void receiveMessage(String userName) {
        logger.info("【消息接收者2】消息接收成功，用户名为：" + userName);
        //可以添加自定义业务逻辑处理
    }


}
