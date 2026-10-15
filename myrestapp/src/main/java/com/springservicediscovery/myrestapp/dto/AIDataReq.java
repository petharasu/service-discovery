package com.springservicediscovery.myrestapp.dto;

import org.springframework.stereotype.Component;

@Component
public class AIDataReq {
	private Integer inputDataCount;

	public Integer getInputDataCount() {
		return inputDataCount;
	}

	public void setInputDataCount(Integer inputDataCount) {
		this.inputDataCount = inputDataCount;
	}

}
