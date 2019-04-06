package com.example.rabbit.service.Impl;

import com.example.rabbit.entity.UserEntity;
import com.example.rabbit.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: UserService
 * @ProjectName springboot_rabbit_mq
 * @Description: 用户业务层接口
 * @Author WeiShiHuai
 * @Date 2018/9/20 14:25
 */
@Service
public class UserService {
        @Autowired
        private SendMessageService sendMessageService;

    @Transactional
    public Long saveUser(UserEntity userEntity) {
        //保存用户操作
        //这里写保存数据库操作...

        //发送消息到RabbitMQ
        sendMessageService.sendMessage(userEntity.getName());
        return userEntity.getId();
    }


}
