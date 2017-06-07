package com.alcord.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alcord.utility.LoggingUtility;

@Service
@Transactional
public class BaseServiceImpl {

	@Autowired
	LoggingUtility loggingUtility;

}
