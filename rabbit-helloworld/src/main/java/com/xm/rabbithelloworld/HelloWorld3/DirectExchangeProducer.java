package com.xm.rabbithelloworld.HelloWorld3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class DirectExchangeProducer {
    public static void main(String[] args) throws Exception {
        //1 创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //2 创建Connection
        Connection connection = connectionFactory.newConnection();
        //3 创建Channel
        Channel channel = connection.createChannel();
        //4 声明
        String exchangeName = "test_direct_exchange";
        //可以修改routingKey的值使其和消费端的bingingKey不一致来测试 直连的方式
        String routingKey = "test.direct";
        //5 发送
        String msg = "Test Direct Exchange Message";
        channel.basicPublish(exchangeName, routingKey , null , msg.getBytes());
    }

}
