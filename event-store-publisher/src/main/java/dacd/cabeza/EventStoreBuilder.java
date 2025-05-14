package dacd.cabeza;

import dacd.cabeza.messaging.HotelEventSubscriber;

public class EventStoreBuilder {
	public static void main(String[] args) {
		new HotelEventSubscriber().start();

		// Mantener el programa activo
		while (true) {
			try { Thread.sleep(1000); }
			catch (InterruptedException e) {}
		}
	}
}
