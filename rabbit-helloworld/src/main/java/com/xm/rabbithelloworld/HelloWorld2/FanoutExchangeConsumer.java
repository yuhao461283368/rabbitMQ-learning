package com.xm.rabbithelloworld.HelloWorld2;

/**
 * 消费者
 */

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 *这个方法的返回值是 Exchange.DeclaeOK 用来标识成功声明了一个交换器，也就是说：在客户端声明了一个交换器之后，需要等待服务器的返回(服务器会返回 Exchange.Declare-Ok 这个 AMQP 命令)
 * exchangeDeclare(
 * 		 String exchange,
 * 		 String type,
 * 		 boolean durable,
 * 		 boolean autoDelete,
 * 		 boolean internal,
 * 		 Map arguments) throws IOException ;
 *
 * exchange 交换器的名称
 * type  交换器的类型，常见的有fanout、direct、topic、headers这四种
 * durable 设置是否持久 durab 设置为 true 表示持久化， 反之是非持久,设置为true则将Exchange存盘，即使服务器重启数据也不会丢失
 * autoDelete 设置是否自动删除，当最后一个绑定到Exchange上的队列删除后，自动删除该Exchange，简单来说也就是如果该Exchange没有和任何队列Queue绑定则删除
 * internal 设置是否是RabbitMQ内部使用，默认false。如果设置为 true ，则表示是内置的交换器，客户端程序无法直接发送消息到这个交换器中，只能通过交换器路由到交换器这种方式。
 * argument 扩展参数，用于扩展AMQP协议自制定化使用
 */
public class FanoutExchangeConsumer {
    public static void main(String[] args) throws Exception {
        //1 创建一个ConnectionFactory, 并进行配置
        ConnectionFactory connectionFactory = new ConnectionFactory() ;
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //2 通过连接工厂创建连接
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //4 定义
        String exchangeName = "test_fanout_exchange";
        //5 指定类型为fanout
        String exchangeType = "fanout";
        String queueName = "test_fanout_queue";
        //不设置路由键，没有路由情况下也能收到证明并不处理任何的路由键
        String routingKey = "";
        channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);
        //5 创建消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("消费端:" + msg);
            }
        };
        //参数：队列名称、是否自动ACK、Consumer
        channel.basicConsume(queueName, true, consumer);
    }
}
