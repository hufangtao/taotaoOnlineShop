package com.taotao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;

public class TestActiveMq {

    @Test
    public void testQueueProducer() throws Exception{

        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.10.10:61616");
        Connection connection = connectionFactory.createConnection();

        connection.start();
        //是否开启事务，一般不使用
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test-queue");

        MessageProducer producer = session.createProducer(queue);

//        TextMessage textMessage=new ActiveMQTextMessage();
//        textMessage.setText("hello activemq");

        TextMessage textMessage = session.createTextMessage("hello activemq");

        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }

    @Test
    public void testQueueConsumer() throws Exception{
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.10.10:61616");
        Connection connection = connectionFactory.createConnection();

        connection.start();
        //是否开启事务，一般不使用
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue("test-queue");

        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if (message instanceof TextMessage){
                    TextMessage textMessage= (TextMessage) message;
                    try {
                        String text = textMessage.getText();
                        System.out.println(text);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

//        while (true){
//            Thread.sleep(100);
//        }
        System.in.read();
        consumer.close();
        session.close();
        connection.close();
    }


    @Test
    public void testTopicProducer() throws Exception{
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.10.10:61616");
        Connection connection = connectionFactory.createConnection();

        connection.start();
        //是否开启事务，一般不使用
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic("test-topic");
        MessageProducer producer = session.createProducer(topic);
        TextMessage textMessage = session.createTextMessage("hello activemq topic");
        producer.send(textMessage);

        producer.close();
        session.close();
        connection.close();
    }

}
