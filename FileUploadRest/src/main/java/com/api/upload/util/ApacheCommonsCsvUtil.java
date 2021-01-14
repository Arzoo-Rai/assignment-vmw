package com.api.upload.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import org.springframework.web.multipart.MultipartFile;

import com.api.upload.document.Employee;

public class ApacheCommonsCsvUtil {
	private static String csvExtension = "csv";
	
	public static void customersToCsv(Writer writer, List<Employee> employees) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("id", "name", "age"));) {
			for (Employee employee : employees) {
				List<String> data = Arrays.asList(String.valueOf(employee.getId()), employee.getName(),
						String.valueOf(employee.getAge()));

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}

	public static List<Employee> parseCsvFile(InputStream is) {
		BufferedReader fileReader = null;
		CSVParser csvParser = null;

		List<Employee> employees = new ArrayList<Employee>();

		try {
			fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				HashMap<String,String> map =(HashMap<String, String>) csvRecord.toMap();
			//	Long id = Long.parseLong(map.get("id"));
			String name = csvRecord.get("name");
			Integer age = Integer.parseInt(csvRecord.get("age"));
			//Long id=Long.parseLong(csvRecord.get(null))
			Employee employee = new Employee(name,age);
			
		//	Employee employee = new Employee(, ,Integer.parseInt(csvRecord.get("age")));
//
				employees.add(employee);
			}

		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvParser.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}

		return employees;
	}
	
	public static boolean isCSVFile(MultipartFile file) {
		String extension = file.getOriginalFilename().split("\\.")[1];
		
		if(!extension.equals(csvExtension)) {
			return false;
		}
		
		return true;
	}

}