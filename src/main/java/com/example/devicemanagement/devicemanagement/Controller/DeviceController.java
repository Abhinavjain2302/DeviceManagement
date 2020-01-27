package com.example.devicemanagement.devicemanagement.Controller;

import com.example.devicemanagement.devicemanagement.Model.Device;
import com.example.devicemanagement.devicemanagement.Model.ResponseModel;
import com.example.devicemanagement.devicemanagement.Service.DeviceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/device")
@Api("Device")
public class DeviceController {
    @Autowired
    public DeviceService deviceService;

    @PostMapping("/save")
    public ResponseEntity<ResponseModel> saveDevice(@RequestBody Device device){
       return deviceService.saveDevice(device);
    }

    @PutMapping("/update/{id}")
    public List<Map<String, Object>> updateDevice(@PathVariable int id, @RequestBody Device device){
    return deviceService.updateDevice(id,device);
    }

    @GetMapping("/get")
    public List<Map<String,Object>> getDevices(){
      return deviceService.getDevices();
    }

    @DeleteMapping("/delete/{id}")
    public List<Map<String,Object>> deleteDevice(@PathVariable int id){
    return deviceService.deleteDevice(id);
    }


    //device id from UI mapping
    @PostMapping("/assign/{username}")
    public ResponseEntity assignDevice(@PathVariable String username, @RequestBody Device device){
        return deviceService.assign(username,device);
    }

    @PostMapping("/unassign/{username}")
    public List<Map<String, Object>> unAssignDevice(@PathVariable String username, @RequestBody Device device){
        return deviceService.unAssign(username,device);
    }

    @GetMapping("/devicesPerUser/{username}")
    public List<Device> devicesPerUser(@PathVariable String username){
   return deviceService.devicesPerUser(username);
    }

    @GetMapping("/deviceHistory/{device_id}")
    public List<Map<String, Object>> getDeviceHistory(@PathVariable int device_id){
       return deviceService.getDeviceHistory(device_id);
    }

    @GetMapping("/devicesPerDept/{dept_id}")
    public List<Map<String, Object>> devicesPerDept(@PathVariable int dept_id){
       return deviceService.devicesPerDept(dept_id);
    }




}
