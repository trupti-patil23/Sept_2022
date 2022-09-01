package com.patient.repository.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.repository.entity.Patient;
import com.patient.repository.entity.PatientClinicalData;
import com.patient.repository.repo.ClinicalDataRepo;
import com.patient.repository.repo.PatientRepo;

@Service
public class ClinicalDataServiceImpl implements ClinicalDataService {

    @Autowired
    private ClinicalDataRepo clinicalDataRepo;

    @Autowired
    private PatientRepo patientRepo;

    // Get Clinical Data for Patient Id
    @Override
    public List<PatientClinicalData> findAll(Integer patientId) {
        Patient patient = patientRepo.findById(patientId).get();
        return patient.getClinicalData();
    }

    // Save the clinical Data
    @Override
    public List<PatientClinicalData> save(List<PatientClinicalData> clinicalDataList, Integer patientId) {

        PatientClinicalData clinicalDataHeight = new PatientClinicalData();
        PatientClinicalData clinicalDataWeight = new PatientClinicalData();
        PatientClinicalData clinicalDataBMI = new PatientClinicalData();

        for (PatientClinicalData patientClinicalData : clinicalDataList) {
            if (patientClinicalData.getComponentName().equalsIgnoreCase("Height")) {
                clinicalDataHeight = patientClinicalData;
            }
            if (patientClinicalData.getComponentName().equalsIgnoreCase("Weight")) {
                clinicalDataWeight = patientClinicalData;
            }
        }

        // Calculate BMI Value and create ClinicalData object for BMI
        String bmiValue = calculateBMI(clinicalDataHeight.getComponentValue(), clinicalDataWeight.getComponentValue());
        clinicalDataBMI.setComponentName("BMI");
        clinicalDataBMI.setComponentValue(bmiValue);
        clinicalDataBMI.setMeasuredDateTime(LocalDate.now());
        clinicalDataBMI.setPatientId(patientId);

        clinicalDataList.add(clinicalDataBMI);

        // Save all clinicalData objects to the database
        clinicalDataRepo.save(clinicalDataHeight);
        clinicalDataRepo.save(clinicalDataWeight);
        clinicalDataRepo.save(clinicalDataBMI);

        return clinicalDataList;
    }

    // Update the clinical Data
    @Override
    public List<PatientClinicalData> update(List<PatientClinicalData> clinicalDataList, Integer patientId) {

        Patient patient = patientRepo.findById(patientId).get();
        List<PatientClinicalData> clinicalDataListForUpdate = patient.getClinicalData(); // Get Existing Clinical data
                                                                                       // from Database
        PatientClinicalData clinicalDataHeight = new PatientClinicalData();
        PatientClinicalData clinicalDataWeight = new PatientClinicalData();

        for (PatientClinicalData patientClinicalData : clinicalDataList) {
            if (patientClinicalData.getComponentName().equalsIgnoreCase("Height")) {
                clinicalDataHeight = patientClinicalData;
            }
            if (patientClinicalData.getComponentName().equalsIgnoreCase("Weight")) {
                clinicalDataWeight = patientClinicalData;
            }
        }        
        // Calculate BMI Value
        String bmiValue = calculateBMI(clinicalDataHeight.getComponentValue(), clinicalDataWeight.getComponentValue());
        
        PatientClinicalData clinicalDataHeightUpdate = new PatientClinicalData();
        PatientClinicalData clinicalDataWeightUpdate = new PatientClinicalData();
        PatientClinicalData clinicalDataBMIUpdate = new PatientClinicalData();

        for (PatientClinicalData patientClinicalData : clinicalDataListForUpdate) {
            if (patientClinicalData.getComponentName().equalsIgnoreCase("Height")) {
                clinicalDataHeightUpdate = patientClinicalData;
                clinicalDataHeightUpdate.setComponentValue(clinicalDataHeight.getComponentValue());
                clinicalDataHeightUpdate.setMeasuredDateTime(LocalDate.now());
            }
            if (patientClinicalData.getComponentName().equalsIgnoreCase("Weight")) {
                clinicalDataWeightUpdate = patientClinicalData;
                clinicalDataWeightUpdate.setComponentValue(clinicalDataWeight.getComponentValue());
                clinicalDataWeightUpdate.setMeasuredDateTime(LocalDate.now());
            }
            if (patientClinicalData.getComponentName().equalsIgnoreCase("BMI")) {
                clinicalDataBMIUpdate = patientClinicalData;
                clinicalDataBMIUpdate.setComponentValue(bmiValue);
                clinicalDataBMIUpdate.setMeasuredDateTime(LocalDate.now());
            }
        }
        List<PatientClinicalData> clinicalDataListFinal = new ArrayList<>();
        clinicalDataListFinal.add(clinicalDataHeightUpdate);
        clinicalDataListFinal.add(clinicalDataWeightUpdate);
        clinicalDataListFinal.add(clinicalDataBMIUpdate);

        // Save all clinicalData objects to the database
        clinicalDataRepo.save(clinicalDataHeightUpdate);
        clinicalDataRepo.save(clinicalDataWeightUpdate);
        clinicalDataRepo.save(clinicalDataBMIUpdate);

        return clinicalDataListFinal;
    }

    // Calculate BMI Value
    public String calculateBMI(String height, String weight) {
        double heightInMeters = Double.parseDouble(height) * 0.01; // convert from CentiMeter to meters
        double weightInKg = Double.parseDouble(weight);
        double bmiValue = weightInKg / (heightInMeters * heightInMeters);
        return String.valueOf(bmiValue);
    }

}
