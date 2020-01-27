package com.example.devicemanagement.devicemanagement.Dao;

import com.example.devicemanagement.devicemanagement.Model.Device;
import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
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
    List<Map<String, Object>> getDeviceHistory(int device_id);
    List<Map<String, Object>> devicesPerDept(int dept_id);



}
