package com.patient.repository.RestAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.patient.repository.entity.Patient;
import com.patient.repository.service.PatientServiceImpl;

@RestController
@RequestMapping(value = "/patientApi")
public class PatientController {

    private PatientServiceImpl patientServiceImpl;

    @Autowired
    PatientController(PatientServiceImpl patientServiceImpl) {
        this.patientServiceImpl = patientServiceImpl;
    }

    // Get all the Patients
    @RequestMapping(value = "/getAllPatients", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllPatientData() {
        try {
            List<Patient> patientList = patientServiceImpl.findAll();
            return ResponseHandler.generateResponse("Successfully retrieved data for all patients!", HttpStatus.OK,
                    patientList);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    // Get patient data by it's id
    @RequestMapping(value = "/getPatient/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPatientDataById(@PathVariable("id") int patientId) {
        try {
            Patient patient = patientServiceImpl.findById(patientId);
            return ResponseHandler.generateResponse("Successfully retrieved data for given patient!", HttpStatus.OK, patient);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // Save patient data
    @RequestMapping(value = "/savePatient", method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody Patient patient) {
        try {
            Patient patientForSave = patientServiceImpl.save(patient);
            return ResponseHandler.generateResponse("Successfully saved patient data into the database!", HttpStatus.OK,
                    patientForSave);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // Delete Patient data by it's id
    @RequestMapping(value = "/deletePatient/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteById(@PathVariable("id") int patientId) {
        try {
            String result = patientServiceImpl.deleteById(patientId);
            return ResponseHandler.generateResponse("Successfully deleted patient data!", HttpStatus.OK, result);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    // Update Patient data by it's id
    @RequestMapping(value = "/updatePatient/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateById(@RequestBody Patient patient, @PathVariable("id") int patientId) {
        try {
            Patient patientForUpdate = patientServiceImpl.update(patient, patientId);
            return ResponseHandler.generateResponse("Successfully updated patient data!", HttpStatus.OK,
                    patientForUpdate);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }
}
