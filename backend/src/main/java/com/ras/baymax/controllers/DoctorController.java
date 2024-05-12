package com.ras.baymax.controllers;

import com.ras.baymax.entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.ras.baymax.services.DoctorService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/doctors")
@PreAuthorize("hasRole('ROLE_DOCTOR')")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> save(@RequestBody Doctor doctor) {
        Doctor saved = doctorService.save(doctor);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).body(saved);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Doctor> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(doctorService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> findAll() {
        return ResponseEntity.ok().body(doctorService.findAll());
    }
}
