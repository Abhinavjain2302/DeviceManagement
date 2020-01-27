package com.example.devicemanagement.devicemanagement.Dao;

import com.example.devicemanagement.devicemanagement.Model.Department;
import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DepartmentRepository {
    List<Map<String, Object>> getDepartment();

    ResponseEntity<ResponseModel> saveDepartment(Department department);

    ResponseEntity<ResponseModel> updateDepartment(int id, Department department);

    ResponseEntity<ResponseModel> deleteDepartment(int id);


}
