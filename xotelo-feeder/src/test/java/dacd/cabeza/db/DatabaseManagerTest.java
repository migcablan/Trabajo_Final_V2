package dacd.cabeza.db;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {

	@Test
	void testInitialize() throws SQLException {
		DatabaseManager.initialize();
		Connection connection = DatabaseManager.getConnection();
		assertNotNull(connection);
		assertFalse(connection.isClosed());
	}
}
