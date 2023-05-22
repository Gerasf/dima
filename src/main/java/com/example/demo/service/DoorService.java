package com.example.demo.service;

import com.example.demo.entity.abobus;
import com.example.demo.repository.repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

public class DoorService {
    @Autowired
    repo jsonRepo;
    @Async
    public void switchdoors(String id) {
        abobus lighting = jsonRepo.getByID(Long.parseLong(id));
        lighting.setDoor(!lighting.getDoor());
        jsonRepo.update(lighting);}
}

