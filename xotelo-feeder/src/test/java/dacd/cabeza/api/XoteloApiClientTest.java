package dacd.cabeza.api;

import dacd.cabeza.model.HotelRate;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class XoteloApiClientTest {

	@Test
	void testGetHotelRates() throws Exception {
		XoteloApiClient apiClient = new XoteloApiClient();
		List<HotelRate> rates = apiClient.getHotelRates(
				"g230095-d530762",  // Reemplaza con tu clave real
				"2025-04-01",
				"2025-04-05",
				2,
				2,
				0
		);

		assertNotNull(rates);
		assertFalse(rates.isEmpty());

		// Corrección: Acepta "Booking.com" o "Hotels.com"
		assertTrue(
				List.of("Hotels.com", "Booking.com").contains(rates.get(0).getProviderName()),
				"El proveedor debe ser Hotels.com o Booking.com"
		);
	}
}
