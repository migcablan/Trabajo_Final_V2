package dacd.cabeza.api;

import com.google.gson.Gson;
import dacd.cabeza.model.HotelRate;
import dacd.cabeza.model.XoteloApiResponse;
import dacd.cabeza.messaging.HotelEventPublisher;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class XoteloApiClient {
	private static final String BASE_URL = "https://data.xotelo.com/api/rates";
	private final HttpClient httpClient = HttpClient.newHttpClient();
	private final Gson gson = new Gson();

	public List<HotelRate> getHotelRates(
			String hotelKey,
			String checkIn,
			String checkOut,
			int rooms,
			int adults,
			int children
	) throws Exception {
		String url = String.format(
				"%s?hotel_key=%s&chk_in=%s&chk_out=%s&rooms=%d&adults=%d&children=%d",
				BASE_URL, hotelKey, checkIn, checkOut, rooms, adults, children
		);

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.GET()
				.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		XoteloApiResponse apiResponse = gson.fromJson(response.body(), XoteloApiResponse.class);

		if (apiResponse.getError() != null) {
			throw new RuntimeException("Error en la API: " + apiResponse.getError().getMessage());
		}

		List<HotelRate> rates = apiResponse.getResult().getRates();

		// Publicar cada evento en ActiveMQ
		HotelEventPublisher publisher = new HotelEventPublisher();
		rates.forEach(rate -> {
			String jsonEvent = gson.toJson(rate);
			publisher.publish(jsonEvent);
		});

		return rates;
	}
}
