package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class RecoveryRoomTypeFormatter implements Formatter<RecoveryRoomType> {

	RecoveryRoomService service;

	@Override
	public String print(RecoveryRoomType object, Locale locale) {
		return object.getName();
	}

	@Override
	public RecoveryRoomType parse(String text, Locale locale) throws ParseException {
		RecoveryRoomType result = service.getRecoveryRoomType(text);
		if (result == null) {
			throw new ParseException("Recovery room type not found: " + text, 0);
		}
		return result;
	}

}
