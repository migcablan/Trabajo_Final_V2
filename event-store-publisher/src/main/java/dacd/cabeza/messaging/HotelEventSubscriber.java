package dacd.cabeza.messaging;

import dacd.cabeza.storage.EventFileManager;
import org.apache.activemq.ActiveMQConnectionFactory;
import jakarta.jms.*;
import java.time.Instant;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HotelEventSubscriber {
	public void start() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection connection = factory.createConnection();
			connection.setClientID("hotel-subscriber");
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("HotelPrice");
			MessageConsumer consumer = session.createDurableSubscriber(topic, "hotel-sub");

			consumer.setMessageListener(message -> {
				if (message instanceof TextMessage) {
					try {
						String json = ((TextMessage) message).getText();
						JsonObject event = JsonParser.parseString(json).getAsJsonObject();

						// Extraer campos del evento
						String ss = event.get("ss").getAsString();
						Instant ts = Instant.parse(event.get("ts").getAsString());

						// Guardar en disco
						EventFileManager.saveEvent("HotelPrice", ss, ts, json);
						System.out.println("[HotelPrice] Evento guardado: " + json);
					} catch (Exception e) {
						System.err.println("Error procesando mensaje HotelPrice: " + e.getMessage());
					}
				}
			});
		} catch (JMSException e) {
			System.err.println("Error en HotelEventSubscriber: " + e.getMessage());
		}
	}
}
