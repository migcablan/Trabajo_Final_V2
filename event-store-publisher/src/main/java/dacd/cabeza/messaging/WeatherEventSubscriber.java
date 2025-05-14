package dacd.cabeza.messaging;

import org.apache.activemq.ActiveMQConnectionFactory;
import dacd.cabeza.storage.EventFileManager;
import jakarta.jms.*;

public class WeatherEventSubscriber {
	private static final String BROKER_URL = "tcp://localhost:61616";
	private static final String TOPIC_NAME = "Weather";
	private static final String CLIENT_ID = "EventStoreBuilder-Weather";
	private static final String SUBSCRIPTION_NAME = "WeatherSubscription";
	private final EventFileManager fileManager = new EventFileManager();

	public void subscribe() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
			Connection connection = factory.createConnection();
			connection.setClientID(CLIENT_ID); // Para suscripción duradera
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(TOPIC_NAME);

			// Suscripción duradera
			MessageConsumer consumer = session.createDurableSubscriber(topic, SUBSCRIPTION_NAME);

			consumer.setMessageListener(message -> {
				if (message instanceof TextMessage) {
					try {
						String jsonEvent = ((TextMessage) message).getText();
						// Validar formato JSON (opcional)
						new com.google.gson.JsonParser().parse(jsonEvent);
						fileManager.saveEvent("Weather", "OpenWeatherMap", jsonEvent);
						System.out.println("[Weather] Evento almacenado: " + jsonEvent);
					} catch (JMSException | com.google.gson.JsonSyntaxException e) {
						System.err.println("Evento no válido: " + e.getMessage());
					}
				}
			});

		} catch (JMSException e) {
			System.err.println("Error en WeatherEventSubscriber: " + e.getMessage());
		}
	}
}
