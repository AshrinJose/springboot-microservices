package com.learning.departmentservice.service.impl;

import com.learning.departmentservice.dto.DepartmentDTO;
import com.learning.departmentservice.entity.Department;
import com.learning.departmentservice.exception.ResourceNotFoundException;
import com.learning.departmentservice.repository.DepartmentRepository;
import com.learning.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO) {

        // convert Department dto to department jpa entity
        /*Department department = new Department(
                departmentDTO.getId(),
                departmentDTO.getDepartmentName(),
                departmentDTO.getDepartmentDescription(),
                departmentDTO.getDepartmentCode()
        );
        Department saveDepartment = departmentRepository.save(department);
        return new DepartmentDTO(
                saveDepartment.getId(),
                saveDepartment.getDepartmentName(),
                saveDepartment.getDepartmentDescription(),
                saveDepartment.getDepartmentCode()
        );*/
        /*
            Use ModelMapper and MapStruct Mapping Libraries in DepartmentService to convert the Department JPA entity to DepartmentDto and Vice Versa.
         */
        //Department department = modelMapper.map(departmentDTO, Department.class);
        Department saveDepartment = departmentRepository.save(
                                        modelMapper.map(departmentDTO, Department.class)
                                    );
        return modelMapper.map(saveDepartment, DepartmentDTO.class);
    }

    /*
        Retrieve department by code
     */
    @Override
    public DepartmentDTO getDepartmentByCode(String code) {
        System.out.println("code = " + code);
        Department department = departmentRepository.findByDepartmentCode(code)
                .orElseThrow(()->new ResourceNotFoundException("Department not found with code:" + code));

        return new DepartmentDTO(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
    }
}
