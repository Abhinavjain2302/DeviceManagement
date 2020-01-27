package com.example.devicemanagement.devicemanagement.Service;

import com.example.devicemanagement.devicemanagement.Dao.DeviceRepository;
import com.example.devicemanagement.devicemanagement.Model.Device;
import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeviceService implements DeviceRepository {
    @Autowired
    public JdbcTemplate jdbcTemplate;


    @Override
    public ResponseEntity<ResponseModel> saveDevice(Device device) {
        String query="INSERT INTO DEVICE(id,listed_on,type,platform) VALUES(?,?,?,?)";
         jdbcTemplate.update(query,
                device.getId(),
                new Timestamp(System.currentTimeMillis()),
                device.getType(),
                device.getPlatform());
         return ResponseEntity.ok(new ResponseModel("Device Added Successfully"));
    }

    @Override
    public List<Map<String, Object>> updateDevice(int id, Device device) {
        String query="UPDATE DEVICE SET (type,platform)=(?,?) WHERE id=?";
        jdbcTemplate.update(query,device.getType(),device.getPlatform(),id);
       return getDevices();
    }

    @Override
    public List<Map<String, Object>> getDevices() {
        String query="SELECT * FROM DEVICE";
        return jdbcTemplate.queryForList(query);
    }

    @Override
    public List<Map<String, Object>> deleteDevice(int id) {
        String query="DELETE FROM DEVICE WHERE id=?";
        jdbcTemplate.update(query,id);
        return getDevices();
    }

    @Override
    public ResponseEntity assign(String username, Device device) {

        //To handle device does not exist

        if(isDeviceExist(device.getId())){
            String query2="SELECT * FROM USERS_DEVICES WHERE device_id=? AND assigned=?";
            List<Map<String, Object>> list= jdbcTemplate.queryForList(query2,device.getId(),true);
            System.out.println(list);
            if(list.isEmpty()){
                try {
                    String query = "INSERT INTO USERS_DEVICES (username,device_id,assigned) VALUES(?,?,?)";
                    jdbcTemplate.update(query, username, device.getId(),true);
                }
                catch (Exception e){
                    String query = "UPDATE USERS_DEVICES SET assigned=?";
                    jdbcTemplate.update(query,true);
                }

            }
            else {
                return new ResponseEntity<>("Device is Already Assigned",HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>("Device is Assigned Successfully",HttpStatus.ACCEPTED);

        }else{
            return new ResponseEntity<>("Device does not exist",HttpStatus.NOT_FOUND);
        }




    }

    @Override
    public List<Map<String, Object>> unAssign(String username, Device device) {
        String query = "UPDATE USERS_DEVICES SET assigned=? WHERE device_id=? AND username=?";
        jdbcTemplate.update(query,false,device.getId(),username);

        String query1="SELECT * FROM USERS_DEVICES";
        return jdbcTemplate.queryForList(query1);
    }

    @Override
    public List<Device> devicesPerUser(String username) {
        String query="SELECT device_id FROM USERS_DEVICES WHERE username=? AND assigned=?";

        String query2="SELECT * FROM DEVICE WHERE id=?";

      List<Device> device1=  jdbcTemplate.query(query,new Object[]{username,true} ,(ResultSet rs,int row)->{

         return  (jdbcTemplate.query(query2,new Object[]{rs.getInt("device_id")}, (ResultSet rs1, int num)->{
              Device device=new Device();
              device.setId(rs1.getInt("id"));
              device.setPlatform(rs1.getString("platform"));
              device.setType(rs1.getString("type"));
              device.setListed_on(rs1.getTimestamp("listed_on"));
              return device;
          })).get(0);
        });
        return device1;

     //   List<Map<String, Object>> list= jdbcTemplate.queryForList(query,username,true);


/**     List<Object> list2= list.stream().map(stringObjectMap -> stringObjectMap.get("device_id")).collect(Collectors.toList());
        System.out.println(list2);*/

/**        List<Map<String, Object>> list3=new ArrayList<>();
        for (Map<String, Object> row :list) {
//            System.out.println(row.get("device_id"));
          list3.add((jdbcTemplate.queryForList(query2,row.get("device_id"))).get(0));
        }
        return list3;*/

    }

    @Override
    public List<Map<String, Object>> getDeviceHistory(int device_id) {

        String query="SELECT username FROM USERS_DEVICES WHERE device_id=?";
        List<Map<String, Object>> list= jdbcTemplate.queryForList(query,device_id);
       // System.out.println(list.get(0).keySet());

        String query2="SELECT * FROM USERS WHERE username=?";
        List<Map<String, Object>> list3=new ArrayList<>();
        for (Map<String, Object> row :list) {

            list3.add((jdbcTemplate.queryForList(query2,row.get("username"))).get(0));
        }
        return list3;
    }

    @Override
    public List<Map<String, Object>> devicesPerDept(int dept_id) {
        String query1="SELECT username from USERS where deptid=?";
        List<Map<String, Object>> list= jdbcTemplate.queryForList(query1,dept_id);
        List<Map<String, Object>> list2=new ArrayList<>();

        String query2="SELECT device_id FROM USERS_DEVICES WHERE username=?";
        for(Map<String,Object> row:list){
            list2.add(jdbcTemplate.queryForList(query2,row.get("username")).get(0));
        }

        List<Map<String,Object>> list4=new ArrayList<>();

        List<Map<String, Object>> list3=jdbcTemplate.queryForList("SELECT * FROM DEVICE");
        for(Map<String,Object> row:list2){

             list4.add(list3.stream().filter(stringObjectMap -> stringObjectMap.containsValue(

//                list2.stream().filter((Map<String,Object> m)-> {return m.get("device_id");}))
//                    list2.stream().map((Map<String,Object> m)-> m.get("device_id")
//                    ).collect(Collectors.toList())

                     row.get("device_id")
            )).collect(Collectors.toList()).get(0));
        }
        return list4;
    }


    private boolean isDeviceExist(int device_id){
        String query="Select type from device where id=?";
       List<Map<String,Object>> list= jdbcTemplate.queryForList(query,device_id);
       if(list.isEmpty()){
        return false;
       }
       return true;
    }


}
