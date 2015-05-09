package org.example.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;

import java.io.IOException;


/**
 * Converts JSON back to a Joda DateTime.
 */
public class DateTimeDeserializer extends JsonDeserializer<DateTime> {

	/**
	 * Converts JSON back to a Joda DateTime
	 * @param parser the JSON parser
	 * @param context the deserializer
	 * @return a Joda DateTime object.
	 * @throws IOException if errors occur while deserialization
	 */
	@Override
	public DateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
		String formattedDate = parser.getText();
		return Formats.DATETIME_FORMAT.parseDateTime(formattedDate);
	}
}
