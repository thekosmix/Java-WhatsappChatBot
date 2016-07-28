package in.strollup.whatsapp.utils;

import java.util.Collection;
import java.util.Date;

public class Utils {
	public static void log(Object obj) {
		System.out.println(new Date() + ": " + obj);
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmptyOrNull(Collection elements) {
		if (elements == null || elements.isEmpty()) {
			return true;
		}
		return false;
	}
}
