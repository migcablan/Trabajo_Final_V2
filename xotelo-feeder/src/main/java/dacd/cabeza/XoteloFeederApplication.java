package dacd.cabeza;

import dacd.cabeza.api.XoteloApiClient;
import dacd.cabeza.model.HotelRate;
import dacd.cabeza.messaging.HotelEventPublisher;

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
				// Configura los parámetros de la petición (ajusta según tu caso)
				String hotelKey = "g230095-d530762";
				String checkIn = LocalDate.now().toString();
				String checkOut = LocalDate.now().plusDays(1).toString();
				int rooms = 1;
				int adults = 2;
				int children = 0;

				// Llama al cliente de la API de Xotelo
				XoteloApiClient client = new XoteloApiClient();
				List<HotelRate> rates = client.getHotelRates(hotelKey, checkIn, checkOut, rooms, adults, children);

				// Publica cada tarifa como un evento en ActiveMQ
				HotelEventPublisher publisher = new HotelEventPublisher();
				for (HotelRate rate : rates) {
					publisher.publish(new com.google.gson.Gson().toJson(rate));
				}

				System.out.println("Petición realizada y eventos publicados: " + java.time.LocalDateTime.now());
			} catch (Exception e) {
				System.err.println("Error en la petición o publicación: " + e.getMessage());
				e.printStackTrace();
			}
		};

		// Ejecuta la tarea inmediatamente y luego cada 1 hora
		scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);

		// Mantén el programa vivo
		try {
			// Esto hace que la aplicación siga corriendo
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
