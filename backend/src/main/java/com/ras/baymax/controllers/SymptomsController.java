package com.ras.baymax.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/symptoms")
@PreAuthorize("hasRole('ROLE_DOCTOR')")
public class SymptomsController {

    @GetMapping
    public List<LinkedHashMap> getSymptoms() {
        String url = "https://api-baymed.onrender.com/api/symptons";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, List.class);
    }

    @PostMapping
    public List<LinkedHashMap> postSymptoms(@RequestBody List<String> symptoms) {
        String url = "https://api-baymed.onrender.com/api/prediction";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, symptoms, List.class);
    }
}
