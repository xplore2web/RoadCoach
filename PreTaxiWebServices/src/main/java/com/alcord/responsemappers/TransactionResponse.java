/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.responsemappers;

import java.io.Serializable;

/**
 *
 * @author ajit
 */
public class TransactionResponse extends BaseResponse implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1613083572078767809L;
	private String id;
    private String message;

    public TransactionResponse() {
    	super();
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
