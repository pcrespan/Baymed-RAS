package com.ras.baymax.controllers;

import com.ras.baymax.entities.Nurse;
import com.ras.baymax.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/nurses")
@CrossOrigin()
@PreAuthorize("hasRole('ROLE_NURSE')")
public class NurseController {
    @Autowired
    private NurseService nurseService;

    @GetMapping
    public ResponseEntity<List<Nurse>> findAll() {
        return ResponseEntity.ok().body(nurseService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Nurse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(nurseService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Nurse> save(@RequestBody Nurse nurse) {
        Nurse saved = nurseService.save(nurse);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(uri).body(saved);
    }
}
