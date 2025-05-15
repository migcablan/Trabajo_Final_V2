import model.WeatherData;
import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class DBExampleTest {

	@Test
	void testInsertAndRetrieveWeatherData() throws Exception {
		// Inserta un dato de ejemplo
		WeatherData data = new WeatherData(20.0, 25.0, 1015, 70);

		// Ejecuta el main de DBExample para crear la tabla e insertar
		DBExample.main(new String[]{});

		// Verifica que la tabla existe y contiene algún dato
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:weather.db");
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM weather_data")) {
			assertTrue(rs.next());
			// No hay columna city, así que solo comprobamos los valores numéricos
			assertEquals(288.59, rs.getDouble("temp_min"), 0.01); // Valor por defecto del ejemplo
			assertEquals(288.59, rs.getDouble("temp_max"), 0.01);
			assertEquals(1022, rs.getInt("pressure"));
			assertEquals(67, rs.getInt("humidity"));
		}
	}
}
