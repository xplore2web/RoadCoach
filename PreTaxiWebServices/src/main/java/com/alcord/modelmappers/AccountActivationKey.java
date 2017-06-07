/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alcord.modelmappers;

import java.io.Serializable;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.alcord.utility.ValidationUtility;

/**
 *
 * @author Ajit
 */
public class AccountActivationKey implements Serializable, Validator {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1159180222071727638L;
	private String activationKey;

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    @Override
    public boolean supports(Class<?> type) {
         return AccountActivationKey.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
         if (ValidationUtility.isEmpty(this.activationKey)) {
            errors.reject(activationKey,"Activation key missing");
        }
    }

}
