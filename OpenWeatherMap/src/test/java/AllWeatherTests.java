import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"model", "schemas"})
public class AllWeatherTests {
	// Ejecuta todos los tests en los paquetes model y schemas
}
