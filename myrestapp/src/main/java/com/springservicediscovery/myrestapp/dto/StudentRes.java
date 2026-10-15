package com.springservicediscovery.myrestapp.dto;

import org.springframework.stereotype.Component;

@Component
public class StudentRes {

	private Integer id;
	private String message;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
