package com.nks.quotejava2.controllers;

import com.nks.quotejava2.models.Status;
import com.nks.quotejava2.services.StatusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @Autowired
    StatusService statusService;

    @ApiOperation(
            value = "show application status",
            notes = "application status - uptime, name, version"
    )
    @GetMapping(value = "/api/v1/status", produces = "application/json")
    public Status status() {
        return statusService.appInfo();
    }
}
