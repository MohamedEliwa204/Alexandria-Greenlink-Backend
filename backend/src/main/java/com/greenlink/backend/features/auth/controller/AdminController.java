package com.greenlink.backend.features.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @GetMapping("/users")
    public String getAllUsers() {
        return "Users List (Only Admin can see this)";
    }

    @GetMapping("/stats")
    public String getStatistics() {
        return "Greenlink Stats: ...";
    }

}
