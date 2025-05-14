package schemas;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {
	@SerializedName("main")
	private Main main;

	public static class Main {
		@SerializedName("temp_min")
		private double tempMin;

		@SerializedName("temp_max")
		private double tempMax;

		@SerializedName("pressure")
		private int pressure;

		@SerializedName("humidity")
		private int humidity;

		// Getters (necesarios para acceder a los datos)
		public double getTempMin() { return tempMin; }
		public double getTempMax() { return tempMax; }
		public int getPressure() { return pressure; }
		public int getHumidity() { return humidity; }
	}

	public Main getMain() { return main; }
}
