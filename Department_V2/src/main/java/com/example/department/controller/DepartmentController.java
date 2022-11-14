package com.example.department.controller;

import com.example.department.dto.DepartmentDto;
import com.example.department.entity.Department;
import com.example.department.repository.DepartmentRepository;
import com.example.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public String getAllDepartments(Model model){
        List<Department> departments = departmentService.getAll();
        model.addAttribute("departments",departments);
        return "department";
    }

    @GetMapping("/add")
    public String addDepartment(){
        return "department-add";
    }

    @PostMapping ("/save")
    public String addDepartment(@ModelAttribute DepartmentDto departmentDto ){
        System.out.println(departmentDto);
        departmentService.saveDepartment(departmentDto);
        return "redirect:/department/list";
    }

}
