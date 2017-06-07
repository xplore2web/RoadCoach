package com.alcord.modelmappers;

import java.io.Serializable;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.alcord.utility.ValidationUtility;

public class ForgotPassword implements Serializable, Validator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6208354238967872219L;
	private String mobileNumber;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public boolean supports(Class<?> type) {
		return AccountActivationKey.class.isAssignableFrom(type);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		if (ValidationUtility.isEmpty(this.mobileNumber)) {
            errors.reject(mobileNumber,"Mobile number  missing");
        }
		
	}

}
