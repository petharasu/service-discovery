package com.springservicediscovery.myrestapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springservicediscovery.myrestapp.dto.OpenAiProperties;
import com.springservicediscovery.myrestapp.dto.OpenAiRequest;
import com.springservicediscovery.myrestapp.dto.StudentReq;

@Service
public class AiSampleDataService {
		private final RestClient restClient;
	    private final OpenAiProperties properties;
	    private final ObjectMapper objectMapper;

	    public AiSampleDataService(
	            RestClient restClient,
	            OpenAiProperties properties,
	            ObjectMapper objectMapper) {

	        this.restClient = restClient;
	        this.properties = properties;
	        this.objectMapper = objectMapper;
	    }

	    public List<StudentReq> generateUsers(Integer count) {

	        if (count <= 0 || count > 100) {
	            throw new IllegalArgumentException(
	                    "Count must be between 1 and 100"
	            );
	        }

	        String prompt = """
	                Generate exactly %d sample user records.

	                Return ONLY valid JSON.

	                Each record must contain exactly these fields:

	                id   : number
	                name : string
	                city : string

	                Requirements:
	                - Generate exactly %d records.
	                - Every id must be unique.
	                - Names should be realistic.
	                - Cities should be realistic.
	                - Do not add additional fields.
	                - Do not return markdown.
	                - Do not wrap JSON inside ```json.
	                """.formatted(count, count);

	        OpenAiRequest request =
	                new OpenAiRequest(
	                        properties.getModel(),
	                        prompt
	                );

	        try {

	            JsonNode response = restClient
	                    .post()
	                    .uri("/v1/responses")
//	                    .uri("/v1/chat/completions")
	                    .body(request)
	                    .retrieve()
	                    .body(JsonNode.class);

	            String json = extractOutputText(response);

	            List<StudentReq> users =
	                    objectMapper.readValue(
	                            json,
	                            new TypeReference<List<StudentReq>>() {}
	                    );

	            // Important validation
	            if (users.size() != count) {
	                throw new RuntimeException(
	                        "AI returned " + users.size()
	                                + " records instead of "
	                                + count
	                );
	            }

	            return users;

	        } catch (Exception e) {

	            throw new RuntimeException(
	                    "Failed to generate sample data using AI",
	                    e
	            );
	        }
	    }

	    private String extractOutputText(JsonNode response) {

	        JsonNode outputText =
	                response.get("output_text");

	        if (outputText == null ||
	                outputText.isNull()) {

	            throw new RuntimeException(
	                    "AI response did not contain output_text"
	            );
	        }

	        return outputText.asText();
	    }
	}

