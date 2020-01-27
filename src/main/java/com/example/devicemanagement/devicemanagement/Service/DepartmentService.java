package com.example.devicemanagement.devicemanagement.Service;


import com.example.devicemanagement.devicemanagement.Dao.DepartmentRepository;
import com.example.devicemanagement.devicemanagement.Model.Department;
import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DepartmentService implements DepartmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getDepartment() {
        String query="SELECT * FROM DEPARTMENT";
     return  jdbcTemplate.queryForList(query);

    }

    @Override
    public ResponseEntity<ResponseModel> saveDepartment(Department department) {
        String query="INSERT INTO department (name) VALUES (?)";
        jdbcTemplate.update(query,department.getName());
        return ResponseEntity.ok(new ResponseModel("Department information saved in Database Successfully"));
    }

    @Override
    public ResponseEntity<ResponseModel> updateDepartment(int id, Department department) {
        String query="UPDATE department SET name=? WHERE id=?";
        jdbcTemplate.update(query,department.getName(),id);
        return  ResponseEntity.ok(new ResponseModel("Department Information is updated Successfully"));
    }

    @Override
    public ResponseEntity<ResponseModel> deleteDepartment(int id) {
        String query="DELETE FROM department where id=?";
        jdbcTemplate.update(query,id);
        return ResponseEntity.ok(new ResponseModel("Department with id"+id+"id deleted Successfully"));

    }


}
