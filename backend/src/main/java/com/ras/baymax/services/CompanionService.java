package com.ras.baymax.services;

import com.ras.baymax.entities.Companion;
import com.ras.baymax.repositories.CompanionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanionService {
    @Autowired
    private CompanionRepository companionRepository;

    public List<Companion> findAll() {
        return companionRepository.findAll();
    }

    public Companion findById(Long id) {
        return companionRepository.findById(id).orElseThrow(()->new RuntimeException("Companion Not Found"));
    }

    public Companion save(Companion companion) {
        return companionRepository.save(companion);
    }
}
