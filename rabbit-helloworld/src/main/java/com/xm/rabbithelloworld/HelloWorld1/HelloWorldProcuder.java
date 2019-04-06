package com.xm.rabbithelloworld.HelloWorld1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HelloWorldProcuder {

    public static void main(String[] args) throws Exception {
        //1 创建一个ConnectionFactory, 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //服务器的IP地址
        connectionFactory.setHost("127.0.0.1");
        //端口
        connectionFactory.setPort(5672);
        //指定HOST
        connectionFactory.setVirtualHost("/");
        //登录名
        connectionFactory.setUsername("guest");
        //密码
        connectionFactory.setPassword("guest");
        //2 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        //3 通过connection创建一个Channel
        Channel channel = connection.createChannel();
        //4 通过Channel发送数据
        for(int i=0; i < 5; i++){
            String msg ="Hello RabbitMQ!";
            /**
             * 1 exchange
             * 2 routingKey
             * 3 传递AMQP.BasicProperties属性信息
             * 4 消息
             */
            channel.basicPublish("", "test001", null, msg.getBytes());
        }

        //5 记得要关闭相关的连接
        channel.close();
        connection.close();
    }

}
