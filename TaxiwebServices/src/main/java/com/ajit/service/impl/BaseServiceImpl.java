package com.ajit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajit.utility.LoggingUtility;

@Service
@Transactional
public class BaseServiceImpl {

	@Autowired
	LoggingUtility loggingUtility;

}
