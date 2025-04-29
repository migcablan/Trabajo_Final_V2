package dacd.cabeza.api;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class XoteloApiClientTest {

	@Test
	void testGetHotelRates() throws Exception {
		XoteloApiClient apiClient = new XoteloApiClient();
		List<HotelRate> rates = apiClient.getHotelRates(
				"g230095-d530762",
				"2025-04-01",
				"2025-04-05",
				2,
				2,
				0
		);

		assertNotNull(rates);
		assertFalse(rates.isEmpty());
		assertEquals("Booking.com", rates.get(0).getProviderName());
	}
}
