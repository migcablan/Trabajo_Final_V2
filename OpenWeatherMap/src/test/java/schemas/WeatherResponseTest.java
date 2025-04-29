package schemas;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WeatherResponseTest {

	@Test
	void testWeatherResponseConstruction() {
		WeatherResponse.WeatherData data = new WeatherResponse.WeatherData(10.0, 15.0, 1010, 60);
		WeatherResponse response = new WeatherResponse("test_source", 1, List.of(data));

		// Usa los getters en lugar de acceder directamente a los campos
		assertEquals("test_source", response.getSource());
		assertEquals(1, response.getCount());
		assertEquals(1, response.getWeatherItems().size());
		WeatherResponse.WeatherData item = response.getWeatherItems().get(0);
		assertEquals(10.0, item.getTempMin());
		assertEquals(15.0, item.getTempMax());
		assertEquals(1010, item.getPressure());
		assertEquals(60, item.getHumidity());
	}
}
