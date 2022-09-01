package com.patient.repository.RestAPI;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.patient.repository.entity.PatientClinicalData;
import com.patient.repository.service.ClinicalDataServiceImpl;

@RestController
@RequestMapping(value = "/clinicalApi")
public class ClinicalDataController {
    private ClinicalDataServiceImpl clinicalDataServiceImpl;

    @Autowired
    ClinicalDataController(ClinicalDataServiceImpl clinicalDataServiceImpl) {
        this.clinicalDataServiceImpl = clinicalDataServiceImpl;
    }

    @RequestMapping(value = "/getClinicalData/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getClinicalDataForPatient(@PathVariable("id") int patientId) {
        try {
            List<PatientClinicalData> clinicalDataList = clinicalDataServiceImpl.findAll(patientId);
            if (!clinicalDataList.isEmpty()) {
                return ResponseHandler.generateResponse("Successfully retrieved clinical data for given patient!",
                        HttpStatus.OK, clinicalDataList);
            } else {
                return ResponseHandler.generateResponse("No Clinical data exist for given patient!", HttpStatus.OK,
                        clinicalDataList);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @RequestMapping(value = "/saveClinicalData/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> saveClinicalData(@RequestBody List<PatientClinicalData> clinicalDataList, 
                                                    @PathVariable("id") int patientId) {
        try {
            List<PatientClinicalData> patientClinicalDataList = clinicalDataServiceImpl.save(clinicalDataList, patientId);
            return ResponseHandler.generateResponse("Successfully saved clinical data for given patient!",
                    HttpStatus.OK, patientClinicalDataList);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
    
    // Update Clinical data for given Patient
    @RequestMapping(value = "/updateClinicalData/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateClinicalData(@RequestBody List<PatientClinicalData> clinicalDataList, 
                                                    @PathVariable("id") int patientId) {
        try {
            List<PatientClinicalData> patientClinicalDataList = clinicalDataServiceImpl.update(clinicalDataList, patientId);
            return ResponseHandler.generateResponse("Successfully updated patient data!", HttpStatus.OK,
                    patientClinicalDataList);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

}
