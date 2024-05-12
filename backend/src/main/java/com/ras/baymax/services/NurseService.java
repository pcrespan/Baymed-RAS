package com.ras.baymax.services;

import com.ras.baymax.entities.Nurse;
import com.ras.baymax.entities.NurseRegister;
import com.ras.baymax.entities.Phone;
import com.ras.baymax.entities.User;
import com.ras.baymax.entities.enums.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ras.baymax.repositories.NurseRepository;

import java.util.List;

@Service
public class NurseService {
    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    public Nurse findById(Long id) {
        return nurseRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public List<Nurse> findAll() {
        return nurseRepository.findAll();
    }

    public Nurse save(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public Nurse saveNurseRegister(NurseRegister nurseRegister) {
        Phone phone = new Phone(null, nurseRegister.getPhone().getArea(), nurseRegister.getPhone().getPhone(), null);
        User user = new User(null, nurseRegister.getUsername(), nurseRegister.getPassword(), Profession.NURSE, phone);
        phone.setUser(user);

        userService.save(user);
        phoneService.save(phone);
        return nurseRepository.save(new Nurse(
                null,
                nurseRegister.getName(),
                nurseRegister.getWorkHours(),
                nurseRegister.getCoren(),
                user));
    }
}
