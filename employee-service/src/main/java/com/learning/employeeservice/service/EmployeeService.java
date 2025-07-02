package com.learning.employeeservice.service;

import com.learning.employeeservice.dto.APIResponseDTO;
import com.learning.employeeservice.dto.EmployeeDTO;

public interface EmployeeService {
    EmployeeDTO saveEmployee(EmployeeDTO employeeDTO);

    APIResponseDTO getEmployeeById(Long employeeId);
}
