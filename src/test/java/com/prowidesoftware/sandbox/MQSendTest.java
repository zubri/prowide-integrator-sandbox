package com.prowidesoftware.sandbox;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class MQSendTest {

    @Test
    public void send() throws JMSException {
        // default connection and session configuration for ActiveMQ (nothing special for SWIFT)
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("demo");
        MessageProducer messageProducer = session.createProducer(destination);
        TextMessage message = session.createTextMessage();

        // you should put the message content in raw format or with the XML v2 wrapper here
        message.setText("sample text");

        messageProducer.send(message);
        System.out.println("sent content: " + message.getText() );
    }

    @Test
    public void receive() throws JMSException {
        // default connection and session configuration for ActiveMQ (nothing special for SWIFT)
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("demo");
        MessageConsumer messageConsumer = session.createConsumer(destination);
        Message message = messageConsumer.receive();
        TextMessage text = (TextMessage) message;

        // this text content would be then parsed into a message object
        System.out.println("received content: " + text.getText());

    }

}
