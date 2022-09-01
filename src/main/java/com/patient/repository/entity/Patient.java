package com.patient.repository.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Patient_Data")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String patientFirstName;
    private String patientLastName;
    private int patientAge;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "patient")
    private List<PatientClinicalData> clinicalData;

    public Patient() {
        super();
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public List<PatientClinicalData> getClinicalData() {
        return clinicalData;
    }

    public void setClinicalData(List<PatientClinicalData> clinicalData) {
        this.clinicalData = clinicalData;
    }

    @Override
    public String toString() {
        return "Patient [patientId=" + patientId + ", patientFirstName=" + patientFirstName + ", patientLastName="
                + patientLastName + ", patientAge=" + patientAge + "]";
    }

}
