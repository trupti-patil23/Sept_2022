package com.patient.repository.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patient.repository.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Integer> {

}
