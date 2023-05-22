package com.example.demo.service;

import com.example.demo.entity.Bus;
import com.example.demo.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusService {
    @Autowired
    BusRepository busRepository;
    @Async
    public List<Bus> getAllList(){
        return busRepository.findAll();
    }
    @Async
    public void delete(String id){
        busRepository.delete(Long.valueOf(id));
    }
    @Async
    public void saveNew(){
        Bus employee = new Bus();
        busRepository.save(employee);
    }
    @Async
    public Bus getById(String id){
        return busRepository.getByID(Long.valueOf(id));
    }
    @Async
    public Bus update(Bus employee){
        return busRepository.update(employee);
    }
    @Async
    public void switchStatus(String id){
        Bus employee = busRepository.getByID(Long.parseLong(id));
        employee.setDoor(!employee.getDoor());
        busRepository.update(employee);
    }
}

