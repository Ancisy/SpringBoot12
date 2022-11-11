package com.example.department.service;

import com.example.department.dto.DepartmentDto;
import com.example.department.entity.Department;
import com.example.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements  DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @Override
    public void saveDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setAddress(departmentDto.getAddress());
        departmentRepository.save(department);
    }


}
