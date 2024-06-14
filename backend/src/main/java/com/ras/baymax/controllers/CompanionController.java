package com.ras.baymax.controllers;

import com.ras.baymax.entities.Companion;
import com.ras.baymax.services.CompanionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/companions")
@CrossOrigin(origins = "http://localhost:3000")
@PreAuthorize("hasRole('ROLE_NURSE')")
public class CompanionController {

    @Autowired
    private CompanionService companionService;

    @PostMapping
    public ResponseEntity<String> saveCompanion(@RequestBody Companion companion) {
        companionService.save(companion);
        return ResponseEntity.ok().body("Companion registered");
    }

    @GetMapping
    public ResponseEntity<List<Companion>> getAllCompanions() {
        return ResponseEntity.ok().body(companionService.findAll());
    }
}
