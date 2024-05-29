package com.ras.baymax.controllers;

import com.ras.baymax.entities.Symptom;
import com.ras.baymax.services.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/symptoms")
@PreAuthorize("hasRole('ROLE_DOCTOR')")
public class SymptomsController {

    @Autowired
    private SymptomService symptomService;

    @GetMapping
    public List<LinkedHashMap> getSymptoms() {
        String url = "https://api-baymed.onrender.com/api/symptons";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, List.class);
    }

    @PostMapping
    public List<LinkedHashMap> postSymptoms(@RequestBody List<Symptom> symptoms) {
        String url = "https://api-baymed.onrender.com/api/prediction";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, symptomService.getSymptoms(symptoms), List.class);
    }
}
