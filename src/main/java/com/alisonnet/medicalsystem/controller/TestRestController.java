package com.alisonnet.medicalsystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class TestRestController {

//    @Autowired
//    HibTestRepo testRepo;
//
//    @GetMapping("/new")
//    public void checkIRISDB(){
//        log.info("Checking IRIS DB");
//
//        testRepo.updateInsert(new Book(1,"Mertyy"));
//    }
//
//    @GetMapping("/test")
//    public String test(){
//
//        return testRepo.entityManager.toString();
//    }
}
