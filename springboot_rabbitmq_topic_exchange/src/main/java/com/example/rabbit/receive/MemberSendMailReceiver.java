package com.example.rabbit.receive;

import com.example.rabbit.constants.Constants;
import com.example.rabbit.entity.Member;
import com.example.rabbit.mail.EMailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * @Title: MemberRegisterReceiver
 * @ProjectName springboot_rabbitmq_topic_exchange
 * @Description: 发送邮件消息接收者
 * @Author WeiShiHuai
 * @Date 2018/9/21 15:47
 */
@Component
@RabbitListener(queues = "rabbit_mq_member_send_mail_queue_name")
public class MemberSendMailReceiver {


    private static Logger logger = LoggerFactory.getLogger(MemberSendMailReceiver.class);

    @Transactional
    @RabbitHandler
    public void sendMail(Member member) throws Exception {
        logger.info("会员用户名:{}，注册成功,准备发送邮件...", member.getUsername());

        //执行发送邮件操作
        new EMailSender()
                .setTitle("会员注册成功通知邮件")
                .setContent("恭喜你，你已注册成为我们的会员")
                .setContentType(Constants.SEND_MAIL_TEXT_TYPE)
                .setSendMailTargets(new ArrayList<String>() {{
                    add("2897318264@qq.com");
                }}).send();
    }


}
