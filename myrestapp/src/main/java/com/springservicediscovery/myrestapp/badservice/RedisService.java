package com.springservicediscovery.myrestapp.badservice;

import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.springservicediscovery.myrestapp.dto.EmpReq;

@Service
public class RedisService {
//    @Autowired
	private final EmployeeRepository empObject;

	public RedisService(EmployeeRepository employeeRepositoryobj) {
		this.empObject = employeeRepositoryobj;
	}

	@CachePut(value = "Employee", key = "#requestData.empid")
	public EmpReq addData(EmpReq requestData) {
		var result=empObject.save(requestData);
//		IO.println("added successfully");
//		return "added successfully";
		return result;
	}

	@Cacheable(value  = "Employee", key = "#empid")
	public EmpReq getData(Integer empid) {
		IO.println("Fetching from database");
		return empObject.findById(empid)
				.orElseThrow(
						()-> new RuntimeException("Requested emp id not present"));
	}
	
	@CacheEvict(value = "Employee", key = "#id")
	public String deleteEmployee(Integer id) {
		var Employeeid = empObject.findById(id);
		IO.println("Emp idddd"+Employeeid);
		if (Employeeid.isEmpty()) {
			return "data not available";
		} else {
			empObject.deleteById(id);
			return "successfully deleted";
		}

	}

}
