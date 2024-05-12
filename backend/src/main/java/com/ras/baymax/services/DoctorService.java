package com.ras.baymax.services;

import com.ras.baymax.entities.Doctor;
import com.ras.baymax.entities.DoctorRegister;
import com.ras.baymax.entities.Phone;
import com.ras.baymax.entities.User;
import com.ras.baymax.entities.enums.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ras.baymax.repositories.DoctorRepository;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor saveDoctorRegister(DoctorRegister doctorRegister) {
        Phone phone = new Phone(null, doctorRegister.getPhone().getArea(), doctorRegister.getPhone().getPhone(), null);
        User user = new User(null, doctorRegister.getUsername(), doctorRegister.getPassword(), Profession.DOCTOR, phone);
        phone.setUser(user);

        userService.save(user);
        phoneService.save(phone);
        return doctorRepository.save(new Doctor(
                null,
                doctorRegister.getName(),
                doctorRegister.getCrm(),
                doctorRegister.getSpecialty(),
                doctorRegister.getWorkHours(),
                null,
                user));
    }
}
