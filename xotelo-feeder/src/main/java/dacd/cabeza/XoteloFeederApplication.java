package dacd.cabeza;

import dacd.cabeza.api.XoteloApiClient;
import dacd.cabeza.db.DatabaseManager;
import dacd.cabeza.db.RateRepository;
import dacd.cabeza.model.HotelRate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class XoteloFeederApplication {
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			DatabaseManager.initialize();

			System.out.println("=== Configuración de parámetros ===");
			System.out.print("Habitaciones: ");
			int rooms = scanner.nextInt();

			System.out.print("Adultos: ");
			int adults = scanner.nextInt();

			System.out.print("Niños: ");
			int children = scanner.nextInt();

			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

			scheduler.scheduleAtFixedRate(() ->
							updateRates(rooms, adults, children),
					0, 1, TimeUnit.HOURS
			);

			scheduler.scheduleAtFixedRate(() ->
							displayRates(),
					0, 30, TimeUnit.SECONDS
			);

		} catch (Exception e) {
			System.err.println("Error inicial: " + e.getMessage());
		}
	}

	private static void updateRates(int rooms, int adults, int children) {
		try {
			LocalDate checkInDate = LocalDate.now();
			LocalDate checkOutDate = checkInDate.plusDays(5);
			String checkIn = checkInDate.format(DATE_FORMATTER);
			String checkOut = checkOutDate.format(DATE_FORMATTER);

			XoteloApiClient apiClient = new XoteloApiClient();
			List<HotelRate> rates = apiClient.getHotelRates(
					"g230095-d530762",
					checkIn,
					checkOut,
					rooms,
					adults,
					children
			);

			new RateRepository().saveRates(rates);
			System.out.println("[Actualización] Datos guardados: " + rates.size() + " registros.");
		} catch (Exception e) {
			System.err.println("Error en actualización: " + e.getMessage());
		}
	}

	private static void displayRates() {
		try {
			List<HotelRate> rates = new RateRepository().getAllRates();
			System.out.println("\n=== Base de Datos ===");
			rates.forEach(rate -> System.out.printf(
					"""
					Proveedor: %-12s | Tarifa: %-8.2f %s
					Fechas: %s a %s | Habitaciones: %d
					Adultos: %d | Niños: %d
					------------------------------------------
					""",
					rate.getProviderName(),
					rate.getRate(),
					rate.getCurrency(),
					rate.getCheckIn(),
					rate.getCheckOut(),
					rate.getRoomsRequested(),
					rate.getAdults(),
					rate.getChildren()
			));
		} catch (Exception e) {
			System.err.println("Error al mostrar datos: " + e.getMessage());
		}
	}
}