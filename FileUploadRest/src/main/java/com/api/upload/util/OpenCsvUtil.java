package com.api.upload.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.api.upload.document.Employee;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

/**
 * Copyright by https://loizenai.com
 * 
 * @author loizenai.com
 */
public class OpenCsvUtil {

	public static List<Employee> parseCsvFile(InputStream is) {
		String[] CSV_HEADER = { "id", "name","age" };
		Reader fileReader = null;
		CsvToBean<Employee> csvToBean = null;
	
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
			fileReader = new InputStreamReader(is);
	
			ColumnPositionMappingStrategy<Employee> mappingStrategy = new ColumnPositionMappingStrategy<Employee>();
	
			mappingStrategy.setType(Employee.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
	
			csvToBean = new CsvToBeanBuilder<Employee>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();
	
			employees = csvToBean.parse();
			
			return employees;
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}
		
		return employees;
	}

	public static void customersToCsv(Writer writer, List<Employee> employees) {
		String[] CSV_HEADER = { "id", "name", "age" };
	    
	    StatefulBeanToCsv<Employee> beanToCsv = null;
	 
	    try {
	      // write List of Objects
	      ColumnPositionMappingStrategy<Employee> mappingStrategy = 
	                new ColumnPositionMappingStrategy<Employee>();
	      
	      mappingStrategy.setType(Employee.class);
	      mappingStrategy.setColumnMapping(CSV_HEADER);
	      
	      beanToCsv = new StatefulBeanToCsvBuilder<Employee>(writer)
	          .withMappingStrategy(mappingStrategy)
	                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                    .build();
	 
	      beanToCsv.write(employees);
	    } catch (Exception e) {
	      System.out.println("Writing CSV error!");
	      e.printStackTrace();
	    }
	}
}