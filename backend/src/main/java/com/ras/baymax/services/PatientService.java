package com.ras.baymax.services;

import com.ras.baymax.entities.Patient;
import com.ras.baymax.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient findById(Long id) {
        return patientRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
}
