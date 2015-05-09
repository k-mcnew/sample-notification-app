package org.example.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;

/**
 * Joda DateTime to JSON serializer
 */
public class DateTimeSerializer extends JsonSerializer<DateTime> {

	/**
	 * Serializes a Joda DateTime to JSON
	 * @param date the DateTime object to serialize
	 * @param jsonGenerator the generator for JSON
	 * @param serializerProvider the serializer provider
	 * @throws IOException if errors while serializing the Joda DateTime to JSON
	 */
	@Override
	public void serialize(DateTime date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		String formattedDate = date.toString(Formats.DATETIME_FORMAT);
		jsonGenerator.writeString(formattedDate);
	}
}
