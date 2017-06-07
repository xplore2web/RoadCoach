/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.utility;

/**
 *
 * @author Ajit
 */
public class ValidationUtility {

    public static boolean isEmail(String email) {
        boolean isEmail;
        String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        isEmail = email.matches(EMAIL_REGEX);
        return isEmail;

    }

    public static boolean isNumeric(String str) {
        boolean isNumeric = str.matches("^(?:(?:\\-{1})?\\d+(?:\\.{1}\\d+)?)$");
        return isNumeric;
    }
    
     public static boolean isEmpty(String str) {
        boolean isEmpty = (str.isEmpty() && (str.length()==0));
        return isEmpty;
    }
      public static boolean isWordLimit(String str) {
        boolean isWordLimit = (str.length()< 30 && str.length()> 2);
        return !isWordLimit;
    }
}
