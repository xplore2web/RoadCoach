/**
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
public enum OperationStatusType implements Serializable{
    Success,
    RequestError,
    DataError;
}
