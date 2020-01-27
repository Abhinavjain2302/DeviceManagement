package com.example.devicemanagement.devicemanagement.Service;


        import com.example.devicemanagement.devicemanagement.Dao.UserRepository;
        import com.example.devicemanagement.devicemanagement.Model.Department;
        import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
        import com.example.devicemanagement.devicemanagement.Model.User;
        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.jdbc.core.RowMapper;
        import org.springframework.stereotype.Service;

        import java.sql.ResultSet;
        import java.sql.Timestamp;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Map;
        import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserRepository {
//    private enum dept_name{Sales,IT};
    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<ResponseModel> saveUser(User user) {

        if(checkDepartmentExist(user.getDepartment().getId())) {
            return ResponseEntity.ok(new ResponseModel("Department with deptid= "+user.getDepartment().getId()+" does not exist"));
        }else{
            String query2="INSERT INTO USERS (name,username,password,createdon,deptid)VALUES(?,?,?,?,?)";
             jdbcTemplate.update(query2,
                    user.getName(),
                    user.getUsername(),
                    user.getPassword(),
                    new Timestamp(System.currentTimeMillis()),
                    user.getDepartment().getId());
             return ResponseEntity.ok(new ResponseModel("User is created with details "+user.toString()));
        }
    }

    @Override
    public List<User> deleteUser(String username) {
        String query1="DELETE FROM USERS WHERE username=?";
        jdbcTemplate.update(query1,username);
        return getUsers();
    }

    @Override
    public List<User> getUsers() {
        String query1="SELECT * FROM USERS";
        String query2="SELECT * FROM DEPARTMENT";
        List<Map<String ,Object>> list=jdbcTemplate.queryForList(query2);

      List<User> user1= jdbcTemplate.query(query1,(RowMapper<User>) (ResultSet resultSet, int rowNum)->{
          User user=new User();
          user.setUsername(resultSet.getString("username"));
          user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setTimestamp(resultSet.getTimestamp("createdon"));
            int dept_id=resultSet.getInt("deptid");
            user.setDepartment(new Department(dept_id,list.stream().filter(stringObjectMap -> stringObjectMap.containsValue(dept_id)).map(stringObjectMap -> stringObjectMap.get("name")).collect(Collectors.toList()).get(0).toString()));
            return user;
        });
        return user1;
    }

    @Override
    public ResponseEntity<ResponseModel> updateUser(String username, User user) {
        if(checkDepartmentExist(user.getDepartment().getId())){
            String query="UPDATE users SET name=?,deptId=?,password=? WHERE username=?";
            jdbcTemplate.update(query,user.getName(),user.getDepartment().getId(),user.getPassword(),username);
            return ResponseEntity.ok(new ResponseModel("User Updated Successfully"));

        }else{
            return ResponseEntity.ok(new ResponseModel("Department id does not exist"));
        }

    }


    @Override
    public List<User> usersPerDept(int dept_id) {
        String query1="SELECT * FROM USERS WHERE deptid=?";
        String query2="SELECT * FROM DEPARTMENT WHERE id=?";


        List<Department> department=jdbcTemplate.query(query2,new Object[]{dept_id},(ResultSet rs,int row)->{
            Department department1=new Department();
            department1.setId(rs.getInt("id"));
            department1.setName(rs.getString("name"));
            return department1;
        });

        List<User> list1=new ArrayList<>();
        jdbcTemplate.query(query1,new Object[]{dept_id} ,(ResultSet resultSet, int rowNum )-> {
            User user=new User();
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setTimestamp(resultSet.getTimestamp("createdon"));
            user.setDepartment(department.get(0));
           return list1.add(user);
        });

        return list1;
    }

    private Boolean checkDepartmentExist(int dept_id){
        String query="SELECT * FROM DEPARTMENT WHERE id=?";
        List<Map<String,Object>> list=jdbcTemplate.queryForList(query,dept_id);
        if(list.isEmpty()){
            return false;
        }
        return true;
    }


}
