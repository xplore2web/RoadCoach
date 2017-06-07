/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.utility;

import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author Ajit
 */
public class GeneratePassword {

    public static String generateRandomPassword() {
       
        return RandomStringUtils.randomAlphanumeric(8);
    }
    
     public static String generateRandomOTPPassword() {
      
        return RandomStringUtils.randomNumeric(4);
    }

}
