package com.springservicediscovery.myrestapp.dto;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AIDataRes {
	private List<StudentReq> sampleData;

	public List<StudentReq> getSampleData() {
		return sampleData;
	}

	public void setSampleData(List<StudentReq> sampleData) {
		this.sampleData = sampleData;
	}

}
