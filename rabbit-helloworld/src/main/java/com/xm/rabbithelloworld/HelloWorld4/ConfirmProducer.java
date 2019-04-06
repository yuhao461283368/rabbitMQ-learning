package com.xm.rabbithelloworld.HelloWorld4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class ConfirmProducer {
    public static void main(String[] args) throws IOException, Exception {
        //建立ConnectionFactory
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        //创建Connection
        Connection connection=connectionFactory.newConnection();
        //创建channel
        Channel channel = connection.createChannel();
        //指定我们的消息投递模式：消息确认模式
        channel.confirmSelect();
        //交换机 以及路由key
        String exchangName="test_confirm_exchange";
        String routingKey="confirm.send";
        //发送一条消息
        String msg = "Test Confirm Message";
        channel.basicPublish(exchangName, routingKey, null, msg.getBytes());
        //添加确认监听
        channel.addConfirmListener(new ConfirmListener(){
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("收到NACK应答");
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.err.println("收到ACK应答");
            }
        });

    }
}
