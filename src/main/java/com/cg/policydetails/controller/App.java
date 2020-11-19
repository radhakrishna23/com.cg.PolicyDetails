package com.cg.policydetails.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class App {
	
	static Logger myLogger=LogManager.getLogger(App.class);
    
    public static void doWork() {
    
        myLogger.info("App doWork() called");
    }
}
