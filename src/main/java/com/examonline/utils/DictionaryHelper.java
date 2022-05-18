package com.examonline.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class DictionaryHelper {

	@Autowired
	private MessageSource messageSource;
	

	public String getMessage(String message, String lang) {
		String result = "";
		if (message == null) {
			return null;
		}

		switch (DataUtils.safeToString(lang)) {
		case "vi":
			result = messageSource.getMessage(message, null, new Locale("vi", "VN"));
			break;
		case "en":
			result = messageSource.getMessage(message, null, Locale.US);
			break;
		default:
			result = messageSource.getMessage(message, null, Locale.ENGLISH);
			break;
		}
		return result;
	}
	
	
	public String getMessage(String message, String[] params, String lang) {
		String result = "";
		if (message == null) {
			return null;
		}
		
		switch (DataUtils.safeToString(lang)) {
		case "vi":
			result = messageSource.getMessage(message, params, new Locale("vi", "VN"));
			break;
		case "en":
			result = messageSource.getMessage(message, params, Locale.US);
			break;
		default:
			result = messageSource.getMessage(message, params, Locale.ENGLISH);
			break;
		}
		return result;
	}
	
}
