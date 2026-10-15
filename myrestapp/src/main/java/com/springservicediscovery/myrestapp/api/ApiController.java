package com.springservicediscovery.myrestapp.api;

import org.springframework.web.bind.annotation.RestController;

import com.springservicediscovery.myrestapp.badservice.DownstreamService;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(path = "/")
public class ApiController {
	
	@Autowired
	private DownstreamService ds;
	
	private AtomicInteger apicounter=new AtomicInteger(0);
	private static final Logger log=LoggerFactory.getLogger("MyRestController");

	@GetMapping
	public String getMethodName() {
		return new String("Hello from myRestApp");
	}
	
	@GetMapping("erukatestapi/{username}")
	public String getMethodNameforEruka(@PathVariable String username) {
		log.info("testing eruke open feign client response" +apicounter.incrementAndGet());
		return new String("Hello from myRestApp " +username);
	}
	
	@GetMapping(path = "/test-resiliance")
	public ResponseEntity<String> method2TestResilience(){
		try {
			ds.callApiInternal();
		} catch (Exception e) {
			log.error("Error Connecting to Service: " + e.getMessage());
		}
		return ResponseEntity.ok("Processed");
	}
	
}
