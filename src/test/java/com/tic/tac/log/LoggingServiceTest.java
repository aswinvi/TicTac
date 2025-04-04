package com.tic.tac.log;

import static org.mockito.Mockito.mockStatic;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class LoggingServiceTest {

	   @Mock
	   Logger logger;
	   
	   
	    @BeforeEach
	    void setUp() {
	    	MockitoAnnotations.openMocks(this);
	    }


	    @Test
	    void testLoggerOutput() {
	    	
	            try (MockedStatic<Logger> mockedLogger = mockStatic(Logger.class)) {
	                mockedLogger.when(() -> Logger.getLogger("GameLogger")).thenReturn(logger);

	                String testMessage = "Game started!";
	                LoggingService.getLogger().info(testMessage);

	                Mockito.verify(logger).info(testMessage);
	            }
	    }}
