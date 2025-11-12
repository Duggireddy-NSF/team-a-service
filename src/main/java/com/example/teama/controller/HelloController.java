package com.example.teama.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Arrays;

@RestController
public class HelloController {
    
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
    
    @Value("${app.version:1.0.0}")
    private String appVersion;
    
    @Value("${spring.application.name:team-a-service}")
    private String appName;
    
    @GetMapping("/")
    public Map<String, Object> home() {
        logger.info("Home endpoint accessed");
        Map<String, Object> response = new HashMap<>();
        response.put("service", appName);
        response.put("version", appVersion);
        response.put("message", "Welcome to Team A Service!");
        response.put("timestamp", LocalDateTime.now());
        response.put("status", "healthy");
        return response;
    }
    
    @GetMapping("/hello")
    public Map<String, Object> hello() {
        logger.info("Hello endpoint accessed");
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello from Team A Service!");
        response.put("service", appName);
        response.put("timestamp", LocalDateTime.now());
        return response;
    }
    
    @GetMapping("/hello/{name}")
    public Map<String, Object> helloName(@PathVariable String name) {
        logger.info("Hello endpoint accessed for name: {}", name);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hello " + name + " from Team A Service!");
        response.put("service", appName);
        response.put("name", name);
        response.put("timestamp", LocalDateTime.now());
        return response;
    }
    
    @GetMapping("/api/info")
    public Map<String, Object> info() {
        logger.info("Info endpoint accessed");
        Map<String, Object> response = new HashMap<>();
        response.put("service", appName);
        response.put("version", appVersion);
        response.put("description", "Team A microservice for GitOps POC");
        response.put("team", "Team A");
        response.put("environment", System.getProperty("spring.profiles.active", "default"));
        response.put("endpoints", Arrays.asList("/", "/hello", "/hello/{name}", "/api/info", "/api/health"));
        response.put("timestamp", LocalDateTime.now());
        return response;
    }
    
    @GetMapping("/api/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", appName);
        response.put("timestamp", LocalDateTime.now());
        response.put("checks", Map.of(
            "database", "UP",
            "memory", "OK",
            "disk", "OK"
        ));
        return response;
    }
}
