package com.patient.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.patient.repository.entity.Patient;
import com.patient.repository.entity.PatientClinicalData;
import com.patient.repository.service.ClinicalDataServiceImpl;
import com.patient.repository.service.PatientServiceImpl;

@SpringBootTest
class PatientClinicalApiApplicationTests {

    @Autowired
    PatientServiceImpl patientServiceImpl;
    @Autowired
    ClinicalDataServiceImpl clinicalDataServiceImpl;

    @Test
    void contextLoads() {
    }

    // create Patient object and save data to the table Patient_Data
    @Test
    public void testCreatePatient() {

        Patient patient1 = new Patient();
        patient1.setPatientFirstName("Peter");
        patient1.setPatientLastName("Parker");
        patient1.setPatientAge(22);

        Patient patient2 = new Patient();
        patient2.setPatientFirstName("Rohit");
        patient2.setPatientLastName("Sharma");
        patient2.setPatientAge(40);

        // Saving Patient information to the database
        patientServiceImpl.save(patient1);
        patientServiceImpl.save(patient2);

        System.out.println("testCreatePatient() ran successfully and added Patient data to database");

    }

    // Get the patient data based on patientId
    @Test
    public void getPatientDataById() {
        int patient_id = 1;
        try {
            Patient patient = patientServiceImpl.findById(patient_id);
            System.out.println("PatientId :" + patient.getPatientId());
            System.out.println("PatientFirstName :" + patient.getPatientFirstName());
            System.out.println("PatientLastName:" + patient.getPatientLastName());
            System.out.println("PatientAge :" + patient.getPatientAge());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Get The all Patient Data present in the database
    @Test
    public void getAllPatientData() {
        List<Patient> patientList = patientServiceImpl.findAll();
        System.out.println("getAllPatientData() ran successfully and List of all available Patients" + patientList);
    }

    // Delete the patient data by its patientId
    @Test
    public void deletePatientById() {
        try {
            int patient_id = 3;
            String result = patientServiceImpl.deleteById(patient_id);
            System.out.println("deletePatientById() ran successfully " + result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Update patient data by its patientId
    @Test
    public void updatePatientById() {
        int patientId = 1;
        int age = 100;
        String firstName = "Zoya";
        String lastName = "Datar";

        Patient patient = new Patient();
        patient.setPatientAge(age);
        patient.setPatientFirstName(firstName);
        patient.setPatientLastName(lastName);
        try {
            patientServiceImpl.update(patient, patientId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("updatePatientById() ran successfully and updated patient entry for PatientId " + patientId);
    }

    /*-----------------------------------------------------------------------------------------------------------------------
     * JUNIT Test Cases for Patient Clinical Data
     * -----------------------------------------------------------------------------------------------------------------------
     */

    // Get ClinicalData for given Patient Id
    @Test
    public void getClinicalData() {
        int patientId = 1;
        try {
            List<PatientClinicalData> clinicalDatalist = clinicalDataServiceImpl.findAll(patientId);
            for (PatientClinicalData patientClinicalData : clinicalDatalist) {
                System.out.println("Clinical Data for patient id : " + patientId + " " + patientClinicalData);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Create CriticalData object and save to the table patient_clinical_data
    @Test
    public void saveCriticalData() {

        int patientId = 2;
        String height = "100";// in cm
        String weight = "50";// IN Kg

        List<PatientClinicalData> clinicalDataList = new ArrayList<>();
        PatientClinicalData clinicalaDataHeight = new PatientClinicalData();
        PatientClinicalData clinicalaDataWeight = new PatientClinicalData();

        clinicalaDataHeight.setComponentName("Height");
        clinicalaDataHeight.setComponentValue(height);
        clinicalaDataHeight.setMeasuredDateTime(LocalDate.now());
        clinicalaDataHeight.setPatientId(patientId);

        clinicalaDataWeight.setComponentName("Weight");
        clinicalaDataWeight.setComponentValue(weight);
        clinicalaDataWeight.setMeasuredDateTime(LocalDate.now());
        clinicalaDataWeight.setPatientId(patientId);

        clinicalDataList.add(clinicalaDataHeight);
        clinicalDataList.add(clinicalaDataWeight);
        try {
            List<PatientClinicalData> patientClinicalDataList = clinicalDataServiceImpl.save(clinicalDataList,
                    patientId);
            System.out.println("saveCriticalData() ran succeessfully. " + patientClinicalDataList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Create CriticalData object and save to the table patient_clinical_data
    @Test
    public void updateCriticalData() {
        int patintId = 2;
        String height = "300";
        String weight = "100";
        String bmiValue = null;
        List<PatientClinicalData> clinicalaDataList = clinicalDataServiceImpl.findAll(patintId);

        for (PatientClinicalData patientClinicalData : clinicalaDataList) {

            if (patientClinicalData.getComponentName().equalsIgnoreCase("Height")) {
                patientClinicalData.setComponentValue(height);
                patientClinicalData.setMeasuredDateTime(LocalDate.now());
            }

            if (patientClinicalData.getComponentName().equalsIgnoreCase("Weight")) {
                patientClinicalData.setComponentValue(weight);
                patientClinicalData.setMeasuredDateTime(LocalDate.now());
            }

            // If height or Weight changes, BMI will also change for that patient
            if (patientClinicalData.getComponentName().equalsIgnoreCase("bmi")) {

                bmiValue = clinicalDataServiceImpl.calculateBMI(height, weight);
                patientClinicalData.setComponentValue(bmiValue);
                patientClinicalData.setMeasuredDateTime(LocalDate.now());
            }
            try {
                List<PatientClinicalData> clinicalaDataListUpdated = clinicalDataServiceImpl.update(clinicalaDataList,
                        patintId);
                System.out.println(
                        "updateCriticalData() ran succeessfully and list of updated data " + clinicalaDataListUpdated);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
