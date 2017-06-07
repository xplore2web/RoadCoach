package com.alcord.utility;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class LoggingUtility {
	
	private Logger logger = Logger.getLogger(LoggingUtility.class.getName());
	public void log(String string) {
		logger.info(string);
	}

	public void log(String message, Exception e) {
		logger.log(Level.SEVERE, message, e);
	}

	public void error(Throwable throwable) {
		// TODO Auto-generated method stub
		logger.log(Level.SEVERE, "Data Error" , throwable);
	}

}
