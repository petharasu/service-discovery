package com.springservicediscovery.myrestapp.dto;

public class OpenAiRequest {
	private String model;
	private String prompt;

	public OpenAiRequest(String model, String prompt) {
		super();
		this.model = model;
		this.prompt = prompt;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	

}
