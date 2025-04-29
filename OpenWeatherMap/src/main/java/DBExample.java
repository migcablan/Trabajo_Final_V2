import model.WeatherData;
import java.sql.*;

public class DBExample {
	public static void main(String[] args) {
		String url = "jdbc:sqlite:weather.db";
		try (Connection conn = DriverManager.getConnection(url);
			 Statement stmt = conn.createStatement()) {

			// Crear tabla si no existe
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS weather_data (" +
					"id INTEGER PRIMARY KEY AUTOINCREMENT," +
					"temp_min REAL NOT NULL," +
					"temp_max REAL NOT NULL," +
					"pressure INTEGER NOT NULL," +
					"humidity INTEGER NOT NULL)");

			// Insertar datos de ejemplo (modifica según tus necesidades)
			WeatherData data = new WeatherData(288.59, 288.59, 1022, 67);
			String sql = "INSERT INTO weather_data (temp_min, temp_max, pressure, humidity) VALUES (?, ?, ?, ?)";

			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setDouble(1, data.getTempMin());
				pstmt.setDouble(2, data.getTempMax());
				pstmt.setInt(3, data.getPressure());
				pstmt.setInt(4, data.getHumidity());
				pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
