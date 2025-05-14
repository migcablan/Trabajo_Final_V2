package dacd.cabeza.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventFileManager {
	private static final String EVENT_STORE_ROOT = "eventstore";
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

	public void saveEvent(String topic, String source, String eventData) {
		try {
			String date = LocalDate.now().format(DATE_FORMATTER);
			Path directoryPath = Paths.get(EVENT_STORE_ROOT, topic, source);
			Files.createDirectories(directoryPath);

			Path filePath = directoryPath.resolve(date + ".events");
			try (FileWriter writer = new FileWriter(filePath.toFile(), true)) {
				writer.write(eventData + System.lineSeparator());
			}
		} catch (IOException e) {
			System.err.println("Error guardando evento: " + e.getMessage());
		}
	}
}