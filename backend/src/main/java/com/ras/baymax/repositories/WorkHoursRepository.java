package com.ras.baymax.repositories;

import com.ras.baymax.entities.WorkHours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkHoursRepository extends JpaRepository<WorkHours, Integer> {
}
