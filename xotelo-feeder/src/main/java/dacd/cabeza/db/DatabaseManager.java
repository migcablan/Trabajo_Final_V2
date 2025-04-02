package dacd.cabeza.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	public static final String DB_URL = "jdbc:sqlite:xotelo_data.db";

	// Método para obtener una conexión
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL); // <-- Añadir este método
	}

	public static void initialize() throws SQLException {
		try (Connection conn = getConnection(); // Usar getConnection() aquí también
			 Statement stmt = conn.createStatement()) {

			String sql = """
                CREATE TABLE IF NOT EXISTS hotel_rates (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    provider_code TEXT NOT NULL,
                    provider_name TEXT NOT NULL,
                    rate REAL NOT NULL,
                    tax REAL NOT NULL,
                    currency TEXT NOT NULL,
                    check_in DATE NOT NULL,
                    check_out DATE NOT NULL,
                    rooms_requested INTEGER NOT NULL,
                    adults INTEGER NOT NULL,
                    children INTEGER NOT NULL,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                )""";

			stmt.execute(sql);
		}
	}
}
