package com.alcord.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.dao.StateDao;
import com.alcord.exception.ProcessFailed;
import com.alcord.model.State;
import com.alcord.service.StateService;

@Service
@Transactional
public class StateServiceImpl implements StateService{

	@Autowired
	private StateDao stateDao;
	
	public List<State> getAllStates() throws ProcessFailed {
		return stateDao.getAllStates();
	}
}
