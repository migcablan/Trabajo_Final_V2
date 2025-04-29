import org.junit.jupiter.api.Test;
import schemas.WeatherResponse;
import com.google.gson.Gson;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

	@Test
	void testWeatherResponseMapping() {
		String json = """
            {
                "source": "api.openweathermap.org",
                "count": 1,
                "weatherItems": [
                    { "tempMin": 10.0, "tempMax": 15.0, "pressure": 1010, "humidity": 60 }
                ]
            }
        """;

		Gson gson = new Gson();
		WeatherResponse response = gson.fromJson(json, WeatherResponse.class);

		assertNotNull(response);
		assertEquals(1, response.getCount());
		assertEquals("api.openweathermap.org", response.getSource());
		assertEquals(10.0, response.getWeatherItems().get(0).getTempMin());
	}
}
