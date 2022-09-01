package com.patient.repository.service;

import java.util.List;
import com.patient.repository.entity.Patient;

public interface PatientService {

    List<Patient> findAll(); // Gives list of all the available patients

    Patient findById(Integer patientId); // Find particular patient by patientId

    Patient save(Patient patient); // Save patient details to the database

    Patient update(Patient patient, Integer patientId); // Save patient details to the database

    String deleteById(Integer patientId); // Delete patient for given patientId
}
