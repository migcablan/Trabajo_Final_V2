package schemas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.Gson;

public class WeatherResponseTest {

	@Test
	void testWeatherResponseDeserialization() {
		String json = """
            {
              "main": {
                "temp_min": 12.5,
                "temp_max": 18.3,
                "pressure": 1012,
                "humidity": 65
              }
            }
        """;

		Gson gson = new Gson();
		WeatherResponse response = gson.fromJson(json, WeatherResponse.class);

		assertNotNull(response);
		assertNotNull(response.getMain());
		assertEquals(12.5, response.getMain().getTempMin());
		assertEquals(18.3, response.getMain().getTempMax());
		assertEquals(1012, response.getMain().getPressure());
		assertEquals(65, response.getMain().getHumidity());
	}
}
