import model.Model;
import model.WeatherData;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Model model = new Model();
		List<WeatherData> dataList = model.getWeather("Madrid"); // Ejemplo con "Madrid"

		// Guardar en la base de datos
		DBExample.main(args);

		// Mostrar resultados
		dataList.forEach(data -> System.out.printf(
				"Temp Mín: %.2f | Temp Máx: %.2f | Presión: %d | Humedad: %d%%\n",
				data.getTempMin(), data.getTempMax(), data.getPressure(), data.getHumidity()
		));
	}
}
