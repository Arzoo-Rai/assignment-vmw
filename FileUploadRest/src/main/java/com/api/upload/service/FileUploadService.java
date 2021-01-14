package com.api.upload.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.upload.document.Employee;
import com.api.upload.repository.CustomerRepository;
import com.api.upload.util.ApacheCommonsCsvUtil;


@Service
public class FileUploadService {
	
	@Autowired
	CustomerRepository customerRepository;

	
	public void store(InputStream file) {
		try {
			
			List<Employee> lstCustomers = ApacheCommonsCsvUtil.parseCsvFile(file);
		
			for(Employee emp:lstCustomers) {
				emp.setId(UUID.randomUUID().toString());
				
			}
			customerRepository.saveAll(lstCustomers);
		} catch(Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
	// Load Data to CSV File
    public void loadFile(Writer writer) throws IOException {
    	try {
        	List<Employee> employees = (List<Employee>) customerRepository.findAll();
        	
   
             ApacheCommonsCsvUtil.customersToCsv(writer, employees);
        	  		
    	} catch(Exception e) {
    		throw new RuntimeException("Fail! -> Message = " + e.getMessage());
    	}
    }
}