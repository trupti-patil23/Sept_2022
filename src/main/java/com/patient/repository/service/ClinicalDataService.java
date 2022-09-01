package com.patient.repository.service;

import java.util.List;
import com.patient.repository.entity.PatientClinicalData;

public interface ClinicalDataService {
    
    List<PatientClinicalData> findAll(Integer patientId); //Get all clinical data for given PatientId

    List<PatientClinicalData> save(List<PatientClinicalData> clinicalDataList, Integer patientId); //Save Clinical data to the database

    List<PatientClinicalData> update(List<PatientClinicalData> clinicalDataList, Integer patientId); //Update Clinical data to the database

}
