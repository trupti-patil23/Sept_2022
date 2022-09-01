package com.patient.repository.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.patient.repository.entity.PatientClinicalData;

public interface ClinicalDataRepo extends JpaRepository<PatientClinicalData, Integer> {

}
