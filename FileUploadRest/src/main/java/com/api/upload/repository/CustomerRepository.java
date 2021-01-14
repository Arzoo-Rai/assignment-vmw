package com.api.upload.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.upload.document.Employee;



public interface CustomerRepository extends MongoRepository<Employee, String>{
	
}