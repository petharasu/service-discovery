package com.springservicediscovery.myrestapp.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springservicediscovery.myrestapp.dto.AIDataReq;
import com.springservicediscovery.myrestapp.dto.StudentReq;
import com.springservicediscovery.myrestapp.dto.StudentRes;
import com.springservicediscovery.myrestapp.service.AiSampleDataService;
import com.springservicediscovery.myrestapp.service.RestApiService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/student/v1")
public class StudentController {

	public StudentController(RestApiService obj,AiSampleDataService  aiobject) {
		this.restApiService = obj;
		this.sampleDataservice=aiobject;
	}
	
	private int startvalue = 20;

	private final RestApiService restApiService;
	private final AiSampleDataService sampleDataservice;

	@PostMapping(path = "/")
	public ResponseEntity<StudentRes> createMethod(@Valid @RequestBody StudentReq initialReq) {
		StudentRes studentResobj = new StudentRes();
		// Get/Validate Req
		// store request
		/// result = studentApiService.save();
		/// Decide on response
		// build and return resp
		String saveResult = restApiService.save(initialReq);

		studentResobj.setId(initialReq.id());
		studentResobj.setMessage(saveResult);

		ResponseEntity<StudentRes> respose = new ResponseEntity<StudentRes>(studentResobj, HttpStatus.CREATED);

		return respose;

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<StudentReq> getMethod(@PathVariable(name = "id") Integer ids) {

		// Get and validate Request
		// fetchData
		/// Service class will return data
		// Return response
		StudentReq result=restApiService.get(ids);
		ResponseEntity<StudentReq> response=new ResponseEntity<StudentReq>(result,HttpStatus.OK);
		return response;
	}
	
	@GetMapping(path="/")
    public List<StudentReq> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "2") int size) {

        return restApiService.getStudents(page, size);
    }

	@PutMapping(path = "/")
	public String updateMethod(@RequestBody StudentReq updaterequestData) {
		return restApiService.updatethedata(updaterequestData);
	}

	@PatchMapping(path = "/")
	public String parcialUpdateMethod(@RequestBody StudentReq parcialUpdateData) {
		return restApiService.parcialUpdate(parcialUpdateData);
	}

	@DeleteMapping(path = "/{studentid}")
	public String deleteMethod(@PathVariable Integer studentid) {
		return restApiService.deleteRecord(studentid);
	}
	
	@PostMapping(path="/ai")
		public List<StudentReq> generateSampleData(@RequestBody AIDataReq inputDataCount) {
			return sampleDataservice.generateUsers(inputDataCount.getInputDataCount());
		}
	

}
