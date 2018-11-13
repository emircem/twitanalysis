package mrcm.twitanalysis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@GetMapping("/test")
	private String test() {
		logger.error("Error test log");
		
		return "test";
	}

}
