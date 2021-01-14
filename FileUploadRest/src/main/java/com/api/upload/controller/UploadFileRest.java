package com.api.upload.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.upload.document.Employee;
import com.api.upload.errorhandler.ResourceNotFoundException;
import com.api.upload.message.Message;
import com.api.upload.message.Response;
import com.api.upload.repository.CustomerRepository;
import com.api.upload.service.FileUploadService;
import com.api.upload.util.ApacheCommonsCsvUtil;



@RestController
@RequestMapping("/api")
public class UploadFileRest {
	@Autowired
	FileUploadService fileUploadService;
	
	@Autowired
	CustomerRepository customerRepository;

	@PostMapping("/upload")
	public Response uploadSingleCSVFile(@RequestParam("csvfile") MultipartFile csvfile) {
	
		Response response = new Response();
	
		// Checking the upload-file's name before processing
		if (csvfile.getOriginalFilename().isEmpty()) {
			response.addMessage(new Message(csvfile.getOriginalFilename(),
					"No selected file to upload! Please do the checking", "fail"));
	
			return response;
		}
	
		// checking the upload file's type is CSV or NOT
		
		if(!ApacheCommonsCsvUtil.isCSVFile(csvfile)) { 
		    response.addMessage(new Message(csvfile.getOriginalFilename(), "Error: this is not a CSV file!", "fail")); 
	        return response; 
		}
		  
		 
		try {
			// save file data to database
			fileUploadService.store(csvfile.getInputStream());
			response.addMessage(new Message(csvfile.getOriginalFilename(), "Upload File Successfully!", "ok"));
		} catch (Exception e) {
			response.addMessage(new Message(csvfile.getOriginalFilename(), e.getMessage(), "fail"));
		}
	
		return response;
	}
	
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getProductById(@PathVariable(value = "id") String priceid)
			throws ResourceNotFoundException {
		Employee emp = customerRepository.findById(priceid)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + priceid));
		return ResponseEntity.ok().body(emp);
	}
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateProduct(@PathVariable(value = "id") String pId,
												 @RequestBody Employee employee) throws ResourceNotFoundException {
		Employee emp = customerRepository.findById(pId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));

		
		emp.setName(employee.getName());
		emp.setAge(employee.getAge());
		final Employee updatedEmpdata = customerRepository.save(emp);
		return ResponseEntity.ok(updatedEmpdata);
	}
	
	@DeleteMapping("/employee/{id}")
	public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") String pId)
			throws ResourceNotFoundException {
		Employee emp = customerRepository.findById(pId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + pId));

		customerRepository.delete(emp);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}