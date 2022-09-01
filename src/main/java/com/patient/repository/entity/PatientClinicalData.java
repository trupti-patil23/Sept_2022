package com.patient.repository.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Patient_Clinical_Data")
public class PatientClinicalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String componentName;
    private String componentValue;
    private LocalDate MeasuredDateTime;
    private int patientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false, insertable = false, updatable = false)
    private Patient patient;

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentValue() {
        return componentValue;
    }

    public void setComponentValue(String componentValue) {
        this.componentValue = componentValue;
    }

    public LocalDate getMeasuredDateTime() {
        return MeasuredDateTime;
    }

    public void setMeasuredDateTime(LocalDate measuredDateTime) {
        MeasuredDateTime = measuredDateTime;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "PatientClinicalData [Id=" + Id + ", componentName=" + componentName + ", componentValue="
                + componentValue + ", MeasuredDateTime=" + MeasuredDateTime + ", patientId=" + patientId + "]";
    }

}
