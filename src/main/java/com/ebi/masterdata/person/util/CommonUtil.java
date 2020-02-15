package com.ebi.masterdata.person.util;

public class CommonUtil {

	public static boolean isStringNotNullAndNotEmpty(String value) {
		if(value != null && !value.isEmpty()) {
			return true;
		}
		return false;
	}
}
