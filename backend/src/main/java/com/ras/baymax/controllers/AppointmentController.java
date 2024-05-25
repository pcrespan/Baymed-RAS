package com.ras.baymax.controllers;

import com.ras.baymax.entities.Appointment;
import com.ras.baymax.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
@PreAuthorize("hasRole('ROLE_NURSE')")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentService.save(appointment);
    }

    @GetMapping("/{id}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable Long id) {
        return appointmentService.findAppointmentsByDoctorId(id);
    }
}
