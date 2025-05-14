package messaging;

import org.apache.activemq.ActiveMQConnectionFactory;
import jakarta.jms.*;

public class WeatherEventPublisher {
	private static final String BROKER_URL = "tcp://localhost:61616";
	private static final String TOPIC_NAME = "Weather";

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

			System.out.println("[OpenWeatherMap] Evento publicado en Weather: " + jsonEvent);

			session.close();
			connection.close();
		} catch (JMSException e) {
			System.err.println("Error en WeatherEventPublisher: " + e.getMessage());
		}
	}
}
