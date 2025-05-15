package dacd.cabeza.messaging;

import org.apache.activemq.ActiveMQConnectionFactory;
import jakarta.jms.*;

public class HotelEventPublisher {
	private static final String BROKER_URL = "tcp://localhost:61616";
	private static final String TOPIC_NAME = "HotelPrice";

	public void publish(String jsonEvent) {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
			Connection connection = factory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(TOPIC_NAME);
			MessageProducer producer = session.createProducer(topic);

			TextMessage message = session.createTextMessage(jsonEvent);
			producer.send(message);

			System.out.println("[xotelo-feeder] Evento publicado en HotelPrice: " + jsonEvent);

			session.close();
			connection.close();
		} catch (JMSException e) {
			System.err.println("Error en HotelEventPublisher: " + e.getMessage());
		}
	}
}
