package com.springservicediscovery.myrestapp.badservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springservicediscovery.myrestapp.dto.EmpReq;

@Repository
public interface EmployeeRepository extends JpaRepository<EmpReq, Integer>{
	
}
