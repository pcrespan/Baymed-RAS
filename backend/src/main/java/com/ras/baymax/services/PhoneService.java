package com.ras.baymax.services;

import com.ras.baymax.entities.Phone;
import com.ras.baymax.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public Phone findById(Long id) {
        return phoneRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
    }

    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }
}
