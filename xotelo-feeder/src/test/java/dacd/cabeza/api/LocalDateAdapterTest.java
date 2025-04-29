package dacd.cabeza.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LocalDateAdapterTest {

	@Test
	void testSerializeAndDeserialize() {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
				.create();

		LocalDate date = LocalDate.of(2025, 4, 1);
		String json = gson.toJson(date);
		assertEquals("\"2025-04-01\"", json);

		LocalDate deserializedDate = gson.fromJson(json, LocalDate.class);
		assertEquals(date, deserializedDate);
	}
}
