package com.api.upload.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Document(collection = "employee")
public class Employee {
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	  @Id
	  @Field("_id")
	  @JsonIgnore
	  private String id;

	

	private String name;

	private Integer age;
	
	public Employee(String name,Integer age){
		//this.id=;
		this.name=name;
		this.age=age;
		
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	


}