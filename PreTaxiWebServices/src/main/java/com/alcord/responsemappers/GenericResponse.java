/*
 * Copyright 2016 Alcord Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Alcord
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.alcord.responsemappers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ajit
 */
public class GenericResponse<T>  extends BaseResponse implements Serializable {
    
     /**
	 * 
	 */
	private static final long serialVersionUID = -2991914035432550483L;

	public GenericResponse() {
        super();
        details = new ArrayList<>();
    }

    private List<T> details;

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }

    public void addDetail(T detail) {
        details.add(detail);
    }
}