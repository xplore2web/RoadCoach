package com.ajit.responsemappers;

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
