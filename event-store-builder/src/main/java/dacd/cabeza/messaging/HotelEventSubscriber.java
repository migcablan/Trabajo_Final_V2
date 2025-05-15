package dacd.cabeza.messaging;

import org.apache.activemq.ActiveMQConnectionFactory;
import dacd.cabeza.storage.EventFileManager;
import jakarta.jms.*;

public class HotelEventSubscriber {
	private static final String BROKER_URL = "tcp://localhost:61616";
	private static final String TOPIC_NAME = "HotelPrice";
	private static final String CLIENT_ID = "HotelStoreSubscriber";
	private static final String SUBSCRIPTION_NAME = "HotelSubscription";
	private final EventFileManager fileManager = new EventFileManager();

	public void subscribe() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
			Connection connection = factory.createConnection();
			connection.setClientID(CLIENT_ID);
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(TOPIC_NAME);
			MessageConsumer consumer = session.createDurableSubscriber(topic, SUBSCRIPTION_NAME);

			consumer.setMessageListener(message -> {
				if (message instanceof TextMessage) {
					try {
						String jsonEvent = ((TextMessage) message).getText();
						fileManager.saveEvent("HotelPrice", "Xotelo", jsonEvent);
					} catch (JMSException e) {
						System.err.println("Error procesando evento: " + e.getMessage());
					}
				}
			});

		} catch (JMSException e) {
			System.err.println("Error en HotelEventSubscriber: " + e.getMessage());
		}
	}
}
