package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import com.sun.corba.se.impl.encoding.CDRInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies (
            @RequestParam(name = "year", required = false) Integer year,
            @RequestParam(name = "age", required = false) Integer age,
            @RequestParam(name = "distillery", required = false) Distillery distillery,
            @RequestParam(name = "region", required = false) String region
    ) {
        if (year != null) {
            List<Whisky> foundWhiskies = whiskyRepository.findByYear(year);
            return new ResponseEntity<>(foundWhiskies, HttpStatus.OK);
        }

        if (age != null && distillery != null) {
            List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryAndAge(distillery, age);
            return new ResponseEntity<>(foundWhiskies, HttpStatus.OK);
        }

        if (region != null) {
            List<Whisky> foundWhiskies = whiskyRepository.findByDistilleryRegion(region);
            return new ResponseEntity<>(foundWhiskies, HttpStatus.OK);
        }

        List<Whisky> foundWhiskies = whiskyRepository.findAll();
        return new ResponseEntity<>(foundWhiskies, HttpStatus.OK);

    }

}
