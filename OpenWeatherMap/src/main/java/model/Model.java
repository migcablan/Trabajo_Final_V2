package model;

import java.util.List;
import java.util.Random;

public class Model {
	public List<WeatherData> getWeather(String city) {
		// Datos aleatorios (simulando respuesta de la API)
		Random rand = new Random();
		double tempMin = 15 + rand.nextDouble() * 10;
		double tempMax = tempMin + rand.nextDouble() * 5;
		int pressure = 1000 + rand.nextInt(50);
		int humidity = 50 + rand.nextInt(50);

		return List.of(new WeatherData(tempMin, tempMax, pressure, humidity));
	}
}
