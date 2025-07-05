package com.learning.employeeservice.service;

import com.learning.employeeservice.dto.DepartmentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*@FeignClient(url="http://localhost:8080", value="DEPARTMENT-SERVICE")*/
@FeignClient(name="DEPARTMENT-SERVICE")
public interface APIClient {
    @GetMapping("api/departments/{departmentCode}")
    DepartmentDTO getDepartment(@PathVariable("departmentCode") String departmentCode);
}
