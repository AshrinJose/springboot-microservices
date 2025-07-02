package com.learning.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDTO {
    private EmployeeDTO employee;
    private DepartmentDTO department;
}
