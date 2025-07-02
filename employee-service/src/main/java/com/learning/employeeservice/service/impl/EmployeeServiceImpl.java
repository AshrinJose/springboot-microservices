package com.learning.employeeservice.service.impl;

import com.learning.employeeservice.dto.APIResponseDTO;
import com.learning.employeeservice.dto.DepartmentDTO;
import com.learning.employeeservice.dto.EmployeeDTO;
import com.learning.employeeservice.entity.Employee;
import com.learning.employeeservice.exception.ResourceNotFoundException;
import com.learning.employeeservice.repository.EmployeeRepository;
import com.learning.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        /*Employee employee = new Employee(
                employeeDTO.getId(),
                employeeDTO.getFirstName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail()
        );
        Employee saveEmployee = employeeRepository.save(employee);

        return new EmployeeDTO(
                saveEmployee.getId(),
                saveEmployee.getFirstName(),
                saveEmployee.getLastName(),
                saveEmployee.getEmail()
        );*/
        /*
        Use ModelMapper and MapStruct Mapping Libraries in EmployeeService to convert the Employee JPA entity to EmployeeDto and Vice Versa.
         */
        //Employee employee = modelMapper.map(employeeDTO, Employee.class);
        Employee saveEmployee = employeeRepository.save(
                                        modelMapper.map(employeeDTO, Employee.class)
                                );
        return modelMapper.map(saveEmployee, EmployeeDTO.class);
    }


    @Override
    public APIResponseDTO getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID:" + employeeId));

        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        //Employee belongs to department and employee has a unique department code
        //return Employee along with its department in response
        // using RestTemplate
        /*ResponseEntity<DepartmentDTO> responseEntity
                        = restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
                                                DepartmentDTO.class);
        DepartmentDTO departmentDTO = responseEntity.getBody();*/

        DepartmentDTO departmentDTO = webClient.get()
                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDTO.class)
                .block();

        return new APIResponseDTO(
                employeeDTO,
                departmentDTO
        );
    }
}
