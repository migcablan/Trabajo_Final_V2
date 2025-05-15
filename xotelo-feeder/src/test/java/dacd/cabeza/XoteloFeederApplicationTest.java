package dacd.cabeza;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XoteloFeederApplicationTest {

	@Test
	void testMainExecution() {
		assertDoesNotThrow(() -> XoteloFeederApplication.main(new String[]{}));
	}
}
