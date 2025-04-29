package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {

	@Test
	void testWeatherDataProperties() {
		WeatherData data = new WeatherData(10.0, 15.0, 1010, 60);
		assertEquals(10.0, data.getTempMin());
		assertEquals(15.0, data.getTempMax());
		assertEquals(1010, data.getPressure());
		assertEquals(60, data.getHumidity());
	}
}
