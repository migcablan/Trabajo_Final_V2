package dacd.cabeza;

import dacd.cabeza.api.XoteloApiClient;
import dacd.cabeza.db.DatabaseManager;
import dacd.cabeza.db.RateRepository;
import dacd.cabeza.model.HotelRate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class XoteloFeederApplication {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static void main(String[] args) {
		try {
			DatabaseManager.initialize();

			// Parámetros fijos
			int rooms = 2;
			int adults = 2;
			int children = 1;

			LocalDate checkInDate = LocalDate.now();
			LocalDate checkOutDate = checkInDate.plusDays(5);
			String checkIn = checkInDate.format(DATE_FORMATTER);
			String checkOut = checkOutDate.format(DATE_FORMATTER);

			XoteloApiClient apiClient = new XoteloApiClient();
			List<HotelRate> rates = apiClient.getHotelRates(
					"g230095-d237098",
					checkIn,
					checkOut,
					rooms,
					adults,
					children
			);

			RateRepository rateRepository = new RateRepository();
			rateRepository.saveRates(rates);

			System.out.println("[Actualización] Datos guardados: " + rates.size() + " registros.");

			displayRates(rateRepository);
		} catch (Exception e) {
			System.err.println("Error en la aplicación: " + e.getMessage());
		}
	}

	private static void displayRates(RateRepository rateRepository) {
		try {
			List<HotelRate> rates = rateRepository.getAllRates();
			System.out.println("\n=== Base de Datos ===");

			if (rates.isEmpty()) {
				System.out.println("No hay registros disponibles.");
				return;
			}

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
			System.err.println("Error al mostrar los datos: " + e.getMessage());
		}
	}
}
