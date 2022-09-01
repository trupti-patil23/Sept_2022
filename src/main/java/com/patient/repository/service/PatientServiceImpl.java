package com.patient.repository.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.patient.repository.entity.Patient;
import com.patient.repository.repo.PatientRepo;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepo patientRepo;

    // Get all patients
    @Override
    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    // Get Patient by it's id
    @Override
    public Patient findById(Integer patientId) {
        return patientRepo.findById(patientId).get();
    }

    // Save patient data to the database
    @Override
    public Patient save(Patient patient) {
        return patientRepo.save(patient);
    }

    // Update patient data
    @Override
    public Patient update(Patient patient, Integer patientId) {
        Patient patientForUpdate = patientRepo.findById(patientId).get();
        patientForUpdate.setPatientAge(patient.getPatientAge());
        patientForUpdate.setPatientFirstName(patient.getPatientFirstName());
        patientForUpdate.setPatientLastName(patient.getPatientLastName());
        return patientRepo.save(patientForUpdate);
    }

    // Delete patient data by it's id.
    @Override
    public String deleteById(Integer patientId) {
        patientRepo.deleteById(patientId);
        return "Patient data deleted for patient_id " + patientId;
    }

}
