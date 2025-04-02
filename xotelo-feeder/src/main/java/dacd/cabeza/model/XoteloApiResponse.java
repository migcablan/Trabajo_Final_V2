package dacd.cabeza.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class XoteloApiResponse {
	@SerializedName("error")
	private ApiError error;

	@SerializedName("result")
	private Result result;

	public ApiError getError() { return error; }
	public Result getResult() { return result; }

	public static class ApiError {
		@SerializedName("status_code")
		private int statusCode;
		private String message;
		public int getStatusCode() { return statusCode; }
		public String getMessage() { return message; }
	}

	public static class Result {
		@SerializedName("chk_in")
		private String checkIn;

		@SerializedName("chk_out")
		private String checkOut;

		@SerializedName("currency")
		private String currency;

		@SerializedName("rates")
		private List<HotelRate> rates;

		public String getCheckIn() { return checkIn; }
		public String getCheckOut() { return checkOut; }
		public String getCurrency() { return currency; }
		public List<HotelRate> getRates() { return rates; }
	}
}
