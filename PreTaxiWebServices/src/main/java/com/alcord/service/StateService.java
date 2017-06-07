package com.alcord.service;

import java.util.List;

import com.alcord.exception.ProcessFailed;
import com.alcord.model.State;

public interface StateService {
	
	/**
     * 
     * @return List of City
     * @throws ProcessFailed 
     */
	public List<State> getAllStates() throws ProcessFailed;

}
