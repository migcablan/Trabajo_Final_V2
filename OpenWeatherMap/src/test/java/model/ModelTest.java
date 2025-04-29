package model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

	@Test
	void testGetWeatherReturnsNonEmptyList() {
		Model model = new Model();
		List<WeatherData> result = model.getWeather("Madrid");
		assertNotNull(result);
		assertFalse(result.isEmpty());
		WeatherData data = result.get(0);
		assertTrue(data.getTempMin() <= data.getTempMax());
		assertTrue(data.getPressure() >= 1000 && data.getPressure() <= 1050);
		assertTrue(data.getHumidity() >= 50 && data.getHumidity() <= 100);
	}
}
