package com.tic.tac.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingService {
	
	private static Logger logger = Logger.getLogger("GameLogger");
	
	 static {
	        ConsoleHandler handler = new ConsoleHandler();
	        handler.setLevel(Level.INFO);
	        handler.setFormatter(new java.util.logging.Formatter() {
	            @Override
	            public String format(java.util.logging.LogRecord record) {
	                return record.getMessage() + System.lineSeparator(); 
	            }
	        });
	        logger.setUseParentHandlers(false); 
	        logger.addHandler(handler);
	    }

	 
	 public static Logger getLogger() {
	        return logger;
	    }
}
