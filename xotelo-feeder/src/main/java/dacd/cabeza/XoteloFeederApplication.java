package dacd.cabeza;

import dacd.cabeza.api.XoteloApiClient;
import dacd.cabeza.model.HotelRate;
import dacd.cabeza.messaging.HotelEventPublisher;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class XoteloFeederApplication {
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

		Runnable task = () -> {
			try {
				String hotelKey = "g187472-d228541";
				String checkIn = LocalDate.now().toString();
				String checkOut = LocalDate.now().plusDays(1).toString();
				int rooms = 1;
				int adults = 2;
				int children = 0;

				XoteloApiClient client = new XoteloApiClient();
				List<HotelRate> rates = client.getHotelRates(hotelKey, checkIn, checkOut, rooms, adults, children);

				HotelEventPublisher publisher = new HotelEventPublisher();
				Gson gson = new Gson();

				for (HotelRate rate : rates) {
					// Convertir HotelRate a JsonObject
					JsonObject event = gson.toJsonTree(rate).getAsJsonObject();

					// Añadir campos adicionales
					event.addProperty("ts", Instant.now().toString());
					event.addProperty("ss", "Xotelo");

					// Publicar el JSON combinado
					publisher.publish(gson.toJson(event));
				}

				System.out.println("Petición realizada y eventos publicados: " + java.time.LocalDateTime.now());
			} catch (Exception e) {
				System.err.println("Error en la petición o publicación: " + e.getMessage());
				e.printStackTrace();
			}
		};

		scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);

		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
