package com.example.department.service;

import com.example.department.dto.DepartmentDto;
import com.example.department.entity.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    List<Department> getAll();

    void saveDepartment(DepartmentDto departmentDto);
}
