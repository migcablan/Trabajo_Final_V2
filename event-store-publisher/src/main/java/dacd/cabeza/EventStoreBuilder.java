package dacd.cabeza;

import dacd.cabeza.messaging.HotelEventSubscriber;
import dacd.cabeza.messaging.WeatherEventSubscriber;

public class EventStoreBuilder {
	public static void main(String[] args) {
		// Suscriptor para eventos de hoteles
		HotelEventSubscriber hotelSubscriber = new HotelEventSubscriber();
		hotelSubscriber.subscribe();

		// Suscriptor para eventos meteorológicos
		WeatherEventSubscriber weatherSubscriber = new WeatherEventSubscriber();
		weatherSubscriber.subscribe();

		// Mantén el programa en ejecución
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
}