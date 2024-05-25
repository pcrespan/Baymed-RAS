package com.ras.baymax.controllers;

import com.ras.baymax.entities.Appointment;
import com.ras.baymax.entities.Doctor;
import com.ras.baymax.services.AppointmentService;
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

    @Autowired
    private AppointmentService appointmentService;

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

    @GetMapping("/{id}/appointments")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(appointmentService.findAppointmentsByDoctorId(id));
    }

    @GetMapping("/{id}/appointments/{appointmentId}/finish")
    public ResponseEntity finishAppointment(@PathVariable Long appointmentId, @PathVariable Long id) {
        appointmentService.finishAppointment(appointmentId);
        return ResponseEntity.ok().build();
    }
}
