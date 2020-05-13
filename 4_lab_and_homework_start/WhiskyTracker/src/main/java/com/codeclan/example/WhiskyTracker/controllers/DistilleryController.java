package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value = "/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleries (
            @RequestParam(name = "region", required = false) String region,
            @RequestParam(name = "age", required = false) Integer age
    ) {
        if (region != null) {
            List<Distillery> foundDistilleries = distilleryRepository.findByRegion(region);
            return new ResponseEntity<>(foundDistilleries, HttpStatus.OK);
        }

        if (age != null) {
            List<Distillery> foundDistilleries = distilleryRepository.findByWhiskiesAge(age);
            return new ResponseEntity<>(foundDistilleries, HttpStatus.OK);
        }

        List<Distillery> foundDistilleries = distilleryRepository.findAll();
        return new ResponseEntity<>(foundDistilleries, HttpStatus.OK);

    }

}
