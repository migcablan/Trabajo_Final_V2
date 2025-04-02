package dacd.cabeza.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HotelRateTest {

	@Test
	void testHotelRateProperties() {
		HotelRate rate = new HotelRate();
		rate.setProviderName("Hotels.com");
		rate.setRate(150.0);

		assertEquals("Hotels.com", rate.getProviderName());
		assertEquals(150.0, rate.getRate());
	}
}
