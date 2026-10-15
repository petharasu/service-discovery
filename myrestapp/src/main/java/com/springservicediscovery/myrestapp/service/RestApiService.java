package com.springservicediscovery.myrestapp.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.springservicediscovery.myrestapp.dto.StudentReq;
import com.springservicediscovery.myrestapp.exception.UserInputException;

@Service
public class RestApiService {

	private final Map<Integer, StudentReq> studentList = new LinkedHashMap<Integer, StudentReq>();

	public String save(StudentReq data) {

		// Save data
		/// if success return success message
		/// else throw Bad User Input exception
		// return status

		if (!studentList.containsKey(data.id())) {

			studentList.put(data.id(), data);
			return "Added successfully";
		} else {
			throw new UserInputException("Requested data already present");
		}

	}

	public StudentReq get(Integer id) {

		/// Production issue fix
		/// If user provided ID < 0, throew invalid userinput("ID cannot be negative")
		// Fetch data by ID
		// if Data present, return the same
		// if absent, throw invalid user input exception

		if (studentList.containsKey(id)) {
			StudentReq currentData = studentList.get(id);
			return currentData;
		} else {
			throw new UserInputException("data not there");
		}
	}

	public String updatethedata(StudentReq data) {
		if (studentList.containsKey(data.id())) {
			studentList.put(data.id(), data);
			return "Updated successfully";
		} else {
			return "requested student data not present";
		}

	}

	public String parcialUpdate(StudentReq parcialUpdateData) {
		if (studentList.containsKey(parcialUpdateData.id())) {
			studentList.put(parcialUpdateData.id(), parcialUpdateData);
			return "Updated the resquested data";
		} else {
			return "requested student data not present";
		}
	}

	public String deleteRecord(Integer id) {
		if (studentList.containsKey(id)) {
			studentList.remove(id);
			return "deleted successfully";
		} else {
			return "data not present to delete";
		}

	}

	public List<StudentReq> getStudents(int page, int size) {
		studentList.put(1, new StudentReq("John", 1, "Chennai"));
		studentList.put(2, new StudentReq("David", 2, "Mumbai"));
		studentList.put(3, new StudentReq("Peter", 3, "Delhi"));
		studentList.put(4, new StudentReq("Sam", 4, "Pune"));
		studentList.put(5, new StudentReq("Alex", 5, "Hyderabad"));
		studentList.put(6, new StudentReq("Tom", 6, "Bangalore"));
		studentList.put(7, new StudentReq("Mike", 7, "Kolkata"));
		studentList.put(8, new StudentReq("Chris", 8, "Coimbatore"));
		studentList.put(9, new StudentReq("Robert", 9, "Madurai"));
		studentList.put(10, new StudentReq("Steve", 10, "Trichy"));

		return studentList
				.values()
				.stream()
				.skip((long) page * size)
				.limit(size).toList();
	}
}
