package dacd.cabeza.storage;

import java.nio.file.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class EventFileManager {
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("yyyyMMdd").withZone(ZoneId.of("UTC"));

	public static void saveEvent(String topic, String source, Instant timestamp, String json) {
		try {
			String date = DATE_FORMATTER.format(timestamp);
			Path path = Paths.get("eventstore", topic, source, date + ".events");
			Files.createDirectories(path.getParent());
			Files.writeString(path, json + System.lineSeparator(),
					StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (Exception e) {
			System.err.println("Error guardando evento: " + e.getMessage());
		}
	}
}
