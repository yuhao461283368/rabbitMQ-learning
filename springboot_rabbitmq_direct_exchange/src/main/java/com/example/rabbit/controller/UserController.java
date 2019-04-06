package com.example.rabbit.controller;

import com.example.rabbit.entity.UserEntity;
import com.example.rabbit.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/sendMessage")
    public void sendMessage(){
        UserEntity userEntity=new UserEntity();
        userEntity.setName("笑话");
        userEntity.setId((long) 1);
        userEntity.setAge(20);
        userService.saveUser(userEntity);
    }


    @RequestMapping("/sendMessage/oneToMany")
    public void sendMessageOneToMany() {
        //循环十次，消费者发送十条消息到RabbitMQ
        for (int i = 1; i <= 10; i++) {
            UserEntity userEntity = new UserEntity();
            userEntity.setAge(20);
            userEntity.setName("zhangsan".concat(String.valueOf(i)));
            userService.saveUser(userEntity);
        }
    }

}
