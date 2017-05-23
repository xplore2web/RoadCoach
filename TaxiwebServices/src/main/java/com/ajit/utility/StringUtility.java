/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.ajit.utility;

/**
 *
 * @author Ajit
 */
public class StringUtility {

	public static String safeBoolean(boolean valid) {
        return valid ? "true" : "false";
    }

    public static boolean isEmpty(final String value) {
        return (value == null || (value.trim().length() == 0) || value.equalsIgnoreCase("null"));
    }
    
    
}
