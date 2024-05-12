package com.ras.baymax.configs;

import com.ras.baymax.entities.Doctor;
import com.ras.baymax.entities.WorkHours;
import com.ras.baymax.entities.enums.Specialty;
import com.ras.baymax.repositories.DoctorRepository;
import com.ras.baymax.repositories.WorkHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private WorkHoursRepository workHoursRepository;

    @Override
    public void run(String... args) throws Exception {

        LocalTime t = LocalTime.now();
        LocalTime time = LocalTime.MIDNIGHT;
        WorkHours w = new WorkHours(null, t, time);

        workHoursRepository.save(w);

        Doctor d = new Doctor(null, "Uriel", "123", Specialty.SURGEON, w, new ArrayList<>(), null);
        doctorRepository.save(d);
    }
}
