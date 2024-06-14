package com.ras.baymax.controllers;

import com.ras.baymax.entities.Appointment;
import com.ras.baymax.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/prognostic")
@CrossOrigin()
@PreAuthorize("hasRole('ROLE_DOCTOR')")
public class SymptomsController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public List<LinkedHashMap> getSymptoms() {
        String url = "https://api-baymed.onrender.com/api/symptoms";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, List.class);
    }

    @PostMapping
    public List<LinkedHashMap> getPrognostic(@RequestBody Appointment appointment) {
        String url = "https://api-baymed.onrender.com/api/prediction";
        appointmentService.extractSymptoms(appointment).forEach(System.out::println);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, appointmentService.extractSymptoms(appointment), List.class);
    }
}
