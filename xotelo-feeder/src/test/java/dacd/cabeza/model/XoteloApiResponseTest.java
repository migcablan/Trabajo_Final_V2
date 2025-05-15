package dacd.cabeza.model;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XoteloApiResponseTest {

	@Test
	void testDeserializeApiResponse() {
		String jsonResponse = """
            {
                "error": null,
                "result": {
                    "chk_in": "2025-04-01",
                    "chk_out": "2025-04-05",
                    "rates": [
                        { "code": "HotelsCom2", "name": "Hotels.com", "rate": 150.0 }
                    ]
                }
            }
            """;

		Gson gson = new Gson();
		XoteloApiResponse response = gson.fromJson(jsonResponse, XoteloApiResponse.class);

		assertNotNull(response);
		assertNull(response.getError());
		assertEquals("2025-04-01", response.getResult().getCheckIn());
	}
}
