package com.springservicediscovery.myrestapp.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentReq(
		@NotBlank(message = "Name is required")
	    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
		String name,
		
		@NotNull(message = "Id is required")
	    @Min(value = 1, message = "Id must be greater than 0")
		Integer id,
		
		
		@NotBlank(message = "City is required")
		String city
		) {

}
