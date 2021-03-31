package com.nks.quotejava2.controllers;

import com.nks.quotejava2.CommandLineAppStartupRunner;
import com.nks.quotejava2.models.mysql.Info;
import com.nks.quotejava2.services.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InfoController {
    @Autowired
    InfoService infoService;

    Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @GetMapping(value = "/api/v1/info", produces = "application/json")
    public ResponseEntity<List<Info>> allInfo(@RequestParam int page, @RequestParam int size) {
        List<Info> infoList = new ArrayList<>();
        try {
            infoList = infoService.findAllInfo(page, size);
        } catch (Exception e) {
            logger.error("error fetching all info records=%s".formatted(e.getMessage()));
        }
        return new ResponseEntity(infoList, HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/info/{id}", produces = "application/json")
    public ResponseEntity<Info> allInfo(@PathVariable Long id) {
        Info info = new Info();
        try {
            info = infoService.findInfoById(id);
        } catch (Exception e) {
            logger.error("error fetching all info records=%s".formatted(e.getMessage()));
        }
        return new ResponseEntity(info, HttpStatus.OK);
    }

}
