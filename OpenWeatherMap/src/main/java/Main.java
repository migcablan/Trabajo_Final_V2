import model.Model;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

		Runnable task = () -> {
			try {
				// 1. Obtener datos reales de la API y publicar en ActiveMQ
				WeatherService.getWeatherData("Las%20Palmas");

				// 2. (Opcional) Mostrar datos simulados por consola
				Model model = new Model();
				model.getWeather("Las Palmas").forEach(data ->
						System.out.printf("Temp Mín: %.2f | Temp Máx: %.2f%n", data.getTempMin(), data.getTempMax())
				);
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		};

		// Programa la tarea cada hora (3600 segundos)
		scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.HOURS);

		// Mantén el programa en ejecución
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
