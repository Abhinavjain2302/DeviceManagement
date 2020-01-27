package com.example.devicemanagement.devicemanagement.Controller;


import com.example.devicemanagement.devicemanagement.Model.Department;
import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
import com.example.devicemanagement.devicemanagement.Service.DepartmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
@Api("Department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/get")
    public List<Map<String, Object>> getDepartment(){
      return  departmentService.getDepartment();
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseModel> saveDepartment(@RequestBody Department department){
    return departmentService.saveDepartment(department);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseModel> updateDepartment(@PathVariable int id, @RequestBody Department department){
      return  departmentService.updateDepartment(id,department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseModel> deleteDepartment(@PathVariable int id){
      return departmentService.deleteDepartment(id);
    }


}
