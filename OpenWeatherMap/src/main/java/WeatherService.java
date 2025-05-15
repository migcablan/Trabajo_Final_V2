import com.google.gson.Gson;
import model.WeatherData;
import schemas.WeatherResponse;
import messaging.WeatherEventPublisher;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;

public class WeatherService {
	private static final String API_KEY = "e93160750c6a261fc4d48a27bbd14d17";
	private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

	public static WeatherResponse getWeatherData(String city) throws Exception {
		String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		WeatherResponse weatherResponse = new Gson().fromJson(response.body(), WeatherResponse.class);

		// --- INTEGRACIÓN CON ACTIVEMQ ---
		// Crear objeto WeatherData con los datos de la API
		WeatherData data = new WeatherData(
				weatherResponse.getMain().getTempMin(),
				weatherResponse.getMain().getTempMax(),
				weatherResponse.getMain().getPressure(),
				weatherResponse.getMain().getHumidity()
		);

		// Construir el evento JSON
		com.google.gson.JsonObject event = new com.google.gson.JsonObject();
		event.addProperty("ts", Instant.now().toString()); // Timestamp UTC
		event.addProperty("ss", "OpenWeatherMap"); // Identificador de la fuente
		event.addProperty("tempMin", data.getTempMin());
		event.addProperty("tempMax", data.getTempMax());
		event.addProperty("pressure", data.getPressure());
		event.addProperty("humidity", data.getHumidity());

		// Publicar el evento en ActiveMQ
		new WeatherEventPublisher().publish(event.toString());

		return weatherResponse;
	}
}
