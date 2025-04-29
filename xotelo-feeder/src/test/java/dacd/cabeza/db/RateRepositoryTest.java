package dacd.cabeza.db;

import dacd.cabeza.model.HotelRate;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RateRepositoryTest {

	@Test
	void testSaveAndGetRates() throws SQLException {
		RateRepository repository = new RateRepository();

		HotelRate rate = new HotelRate();
		rate.setProviderCode("TEST_CODE");
		rate.setProviderName("TEST_PROVIDER");
		rate.setRate(100.0);
		rate.setTax(10.0);
		rate.setCurrency("USD");
		rate.setCheckIn("2025-04-01");
		rate.setCheckOut("2025-04-05");
		rate.setRoomsRequested(2);
		rate.setAdults(2);
		rate.setChildren(0);

		repository.saveRates(List.of(rate));

		List<HotelRate> rates = repository.getAllRates();
		assertFalse(rates.isEmpty());
		assertEquals("TEST_PROVIDER", rates.get(0).getProviderName());
	}
}
