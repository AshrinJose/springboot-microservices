package com.learning.employeeservice.controller;

import com.learning.employeeservice.dto.APIResponseDTO;
import com.learning.employeeservice.dto.EmployeeDTO;
import com.learning.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO employee = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<APIResponseDTO> getDepartment(@PathVariable("id") Long employeeId){
        APIResponseDTO apiResponseDTO = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }
}
