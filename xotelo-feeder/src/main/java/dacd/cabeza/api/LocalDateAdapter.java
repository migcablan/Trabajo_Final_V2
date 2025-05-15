package dacd.cabeza.api;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public void write(JsonWriter out, LocalDate value) throws IOException {
		if (value == null) {
			out.nullValue();
		} else {
			out.value(value.format(FORMATTER));
		}
	}

	@Override
	public LocalDate read(JsonReader in) throws IOException {
		String str = in.nextString();
		return LocalDate.parse(str, FORMATTER);
	}
}
