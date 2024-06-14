package com.ras.baymax.controllers;

import com.ras.baymax.entities.Appointment;
import com.ras.baymax.entities.Symptom;
import com.ras.baymax.services.AppointmentService;
import com.ras.baymax.services.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
@CrossOrigin()
@PreAuthorize("hasRole('ROLE_NURSE')")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private SymptomService symptomService;

    @PostMapping
    public Appointment saveAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.save(appointment);
        for (Symptom symptom : appointment.getSymptoms()) {
            symptom.setAppointment(savedAppointment);
            symptomService.save(symptom);
        }
        return savedAppointment;
    }

    @GetMapping("/{id}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable Long id) {
        return appointmentService.findAppointmentsByDoctorId(id);
    }
}
