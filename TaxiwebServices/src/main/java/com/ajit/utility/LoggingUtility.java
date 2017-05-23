package com.ajit.utility;

import org.springframework.stereotype.Service;

@Service
public class LoggingUtility {

	public void log(String string) {

	}

	public void log(String string, Exception e) {
		e.printStackTrace();
	}

	public void error(Throwable throwable) {
		// TODO Auto-generated method stub

	}

}
