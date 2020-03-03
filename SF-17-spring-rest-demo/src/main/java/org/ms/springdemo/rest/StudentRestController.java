package org.ms.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import org.ms.springdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	//define endpoint for /students - return list of students
	
	
	@GetMapping("/students")
	public List<Student> getStudents(){
		List<Student> theStudents = new ArrayList<>();
		theStudents.add(new Student("Mesut", "Baltaci"));
		theStudents.add(new Student("John", "Smith"));
		theStudents.add(new Student("Mary", "Ross"));
		
		return theStudents;
	}

}
