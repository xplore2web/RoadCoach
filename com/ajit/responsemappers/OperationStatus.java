package com.ajit.responsemappers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajit
 */
public class OperationStatus implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2796986863551880303L;
	private OperationStatusType statusCode;
    private List<String> messages;

    public OperationStatus() {
        statusCode = OperationStatusType.Success;
        messages = new ArrayList<>();
    }

    public OperationStatusType getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(OperationStatusType statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
