package com.example.devicemanagement.devicemanagement.Dao;

import com.example.devicemanagement.devicemanagement.Model.Device;
import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
import com.example.devicemanagement.devicemanagement.Model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeviceRepository {

    ResponseEntity<ResponseModel> saveDevice(Device device);
    List<Map<String, Object>> updateDevice(int id, Device device);
    List<Map<String, Object>> getDevices();
    List<Map<String ,Object>> deleteDevice(int id);
    ResponseEntity assign(String username, Device device);
    List<Map<String, Object>> unAssign(String username, Device device);
    List<Device> devicesPerUser(String username);
    List<User> getDeviceHistory(int device_id);
    List<Device> devicesPerDept(int dept_id);



}
