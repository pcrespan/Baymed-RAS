package com.ras.baymax.services;

import com.ras.baymax.entities.WorkHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ras.baymax.repositories.WorkHoursRepository;

import java.util.List;

@Service
public class WorkHoursService {
    @Autowired
    private WorkHoursRepository workHoursRepository;

    public List<WorkHours> findAll() {
        return workHoursRepository.findAll();
    }

    public WorkHours findById(Integer id) {
        return workHoursRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public WorkHours save(WorkHours workHours) {
        return workHoursRepository.save(workHours);
    }
}
