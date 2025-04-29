import schemas.WeatherResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class WeatherService {
	private static final String API_KEY = "e93160750c6a261fc4d48a27bbd14d17";
	private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

	public static WeatherResponse getWeatherData(String city) throws Exception {
		String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return new Gson().fromJson(response.body(), WeatherResponse.class);
	}
}
