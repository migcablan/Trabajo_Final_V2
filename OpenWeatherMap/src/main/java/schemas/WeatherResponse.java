package schemas;

import java.util.List;

public class WeatherResponse {
	private String source;
	private int count;
	private List<WeatherData> weatherItems;

	// Constructor
	public WeatherResponse(String source, int count, List<WeatherData> weatherItems) {
		this.source = source;
		this.count = count;
		this.weatherItems = weatherItems;
	}

	// Getters (necesarios para acceder a los campos en el test)
	public String getSource() { return source; }
	public int getCount() { return count; }
	public List<WeatherData> getWeatherItems() { return weatherItems; }

	// Clase interna estática para WeatherData
	public static class WeatherData {
		private double tempMin;
		private double tempMax;
		private int pressure;
		private int humidity;

		public WeatherData(double tempMin, double tempMax, int pressure, int humidity) {
			this.tempMin = tempMin;
			this.tempMax = tempMax;
			this.pressure = pressure;
			this.humidity = humidity;
		}

		// Getters
		public double getTempMin() { return tempMin; }
		public double getTempMax() { return tempMax; }
		public int getPressure() { return pressure; }
		public int getHumidity() { return humidity; }
	}
}
