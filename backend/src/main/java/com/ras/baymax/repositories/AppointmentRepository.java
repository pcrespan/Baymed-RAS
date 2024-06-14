package com.ras.baymax.repositories;

import com.ras.baymax.entities.Appointment;
import com.ras.baymax.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query(nativeQuery = true, value = """
            SELECT * FROM TB_APPOINTMENTS
            WHERE DOCTOR_ID = :doctorId
            AND STATUS = :status
            """)
    List<Appointment> findAppointmentsByDoctorId(@Param("doctorId") Long doctorId, @Param("status") Status status);

    @Query(nativeQuery = true, value = """
            UPDATE TB_APPOINTMENTS
            SET STATUS = :status
            WHERE ID = :appointmentId
            """)
    boolean finishAppointment(@Param("appointmentId") Long appointmentId, @Param("status") Status status);
}
