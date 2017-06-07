package com.alcord.modelmappers;

import java.io.Serializable;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.alcord.utility.ValidationUtility;

public class ChangePasswordDetail implements Serializable, Validator {

	private static final long serialVersionUID = 1L;
	private String accountId;
    private String currentPassword;
    private String newPassword;
    
    public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    @Override
	public boolean supports(Class<?> type) {
		return ChangePasswordDetail.class.isAssignableFrom(type);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (ValidationUtility.isEmpty(this.accountId)) {
			errors.reject(accountId, "Account id password missing");
		}
		if (ValidationUtility.isEmpty(this.currentPassword)) {
			errors.reject(currentPassword, "Current password missing");
		}
		if (ValidationUtility.isEmpty(this.newPassword)) {
			errors.reject(newPassword, "New password missing");
		}
		if ((this.newPassword).equals(this.currentPassword)) {
			errors.reject("invalidPassword", "Current and new password not same");
		}
	}
}
