package com.springservicediscovery.myrestapp.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.springservicediscovery.myrestapp.badservice.RedisService;
import com.springservicediscovery.myrestapp.dto.EmpReq;



@RestController
@RequestMapping("/Employee")
public class RedisController {
	
	private final RedisService redisServiceobj;
	
	public RedisController(RedisService redisService) {
		this.redisServiceobj=redisService;
	}
	
	@PostMapping("/")
	public EmpReq storeEmployee(@RequestBody EmpReq requestData) {
		return redisServiceobj.addData(requestData);
	}
	
	@GetMapping("/{empid}")
	public EmpReq GetEmployee(@PathVariable Integer empid) {
		IO.println("path var "+empid);
		return redisServiceobj.getData(empid);
	}
	
	@DeleteMapping("/")
	public String deleteEmp(@RequestParam Integer empid) {
		
		return redisServiceobj.deleteEmployee(empid);
		
	}

}
