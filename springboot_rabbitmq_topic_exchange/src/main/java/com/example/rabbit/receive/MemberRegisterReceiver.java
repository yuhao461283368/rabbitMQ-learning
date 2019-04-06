package com.example.rabbit.receive;

import com.example.rabbit.entity.Member;
import com.example.rabbit.mapper.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: MemberRegisterReceiver
 * @ProjectName springboot_rabbitmq_topic_exchange
 * @Description: 用户注册消息接收者
 * @Author WeiShiHuai
 * @Date 2018/9/21 15:47
 */

@Component
@RabbitListener(queues = "rabbit_mq_member_register_queue_name")
public class MemberRegisterReceiver {

    @Autowired
    private MemberRepository memberRepository;

    private static Logger logger = LoggerFactory.getLogger(MemberRegisterReceiver.class);

    @RabbitHandler
    @Transactional
    public void handler(Member member) throws Exception {
        logger.info("会员用户名: {}, 注册成功, 准备创建会员信息...", member.getUsername());

        //保存会员消息
        memberRepository.save(member);

    }



}
