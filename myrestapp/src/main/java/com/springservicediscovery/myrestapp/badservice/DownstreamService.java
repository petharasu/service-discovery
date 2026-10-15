package com.springservicediscovery.myrestapp.badservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class DownstreamService {

	private final Logger log = LoggerFactory.getLogger("TheDownstreamService");

	@Retryable(includes = RuntimeException.class, maxRetries = 10)
	public void callApiInternal() {
		log.info("Calling the failing downstream service");
//		String apiResponse = RestClient.create("http://pie.dev/invalidendpoint").get().retrieve().body(String.class);
//		log.info("Response from Endpoint: " + apiResponse);
		throw new RuntimeException("Peth will not listen to me Exception");
	}
}
