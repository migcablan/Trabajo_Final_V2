package model;

public class WeatherData {
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
