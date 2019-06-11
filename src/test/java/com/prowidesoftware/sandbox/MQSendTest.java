package com.prowidesoftware.sandbox;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class MQSendTest {

    @Test
    public void send() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("pw2mq");
        MessageProducer messageProducer = session.createProducer(destination);
        TextMessage message = session.createTextMessage();
        message.setText("message");

        messageProducer.send(message);
        System.out.println("sent content: " + message.getText() );
    }

    @Test
    public void receive() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("pw2mq");
        MessageConsumer messageConsumer = session.createConsumer(destination);
        Message message = messageConsumer.receive();
        TextMessage text = (TextMessage) message;
        System.out.println("received content: " + text.getText());
    }

}
