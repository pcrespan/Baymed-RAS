package com.ras.baymax.controllers;

import com.ras.baymax.entities.Companion;
import com.ras.baymax.entities.Patient;
import com.ras.baymax.services.CompanionService;
import com.ras.baymax.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patients")
@CrossOrigin()
@PreAuthorize("hasRole('ROLE_NURSE')")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private CompanionService companionService;

    @PostMapping
    public ResponseEntity<String> registerPatient(@RequestBody Patient patient) {
        Companion savedCompanion = companionService.save(patient.getCompanion());
        patient.setCompanion(savedCompanion);
        patientService.save(patient);
        return ResponseEntity.ok().body("Patient registered");
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients() {
        return ResponseEntity.ok().body(patientService.findAll());
    }
}
