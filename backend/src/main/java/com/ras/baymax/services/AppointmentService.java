package com.ras.baymax.services;

import com.ras.baymax.entities.Appointment;
import com.ras.baymax.entities.Symptom;
import com.ras.baymax.entities.enums.Status;
import com.ras.baymax.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAppointmentsByDoctorId(Long id) {
        List<Appointment> appointments = appointmentRepository.findAppointmentsByDoctorId(id, Status.PENDING);
        Collections.sort(appointments);
        return appointments;
    }

    public boolean finishAppointment(Long id) {
        return appointmentRepository.finishAppointment(id, Status.FINISHED);
    }

    public List<String> extractSymptoms(Appointment appointment) {
        List<String> symptoms = new ArrayList<>();
        appointment.getSymptoms().forEach(symptom -> symptoms.add(symptom.getEn()));
        return symptoms;
    }
}
