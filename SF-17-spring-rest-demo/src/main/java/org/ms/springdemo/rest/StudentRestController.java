package org.ms.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.ms.springdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	List<Student> theStudents;
	//define endpoint for /students - return list of students
	
	//define @PostConstrucy to load the student data  .. only one
	@PostConstruct
	public void loadData() {
		theStudents = new ArrayList<>();
		theStudents.add(new Student("Mesut", "Baltaci"));
		theStudents.add(new Student("John", "Smith"));
		theStudents.add(new Student("Mary", "Ross"));
	}
	
	@GetMapping("/students")
	public List<Student> getStudents(){
				
		return theStudents;
	}

	@GetMapping("/students/{studentId}")
	public Student getStudent (@PathVariable int studentId) {
		if (studentId>=theStudents.size() || studentId<0) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
			
		}
		return theStudents.get(studentId); //just keep it simple for now returns index
	}
	
	//Add an exception handler using @ExceptionHandler
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc){ //genereic exception handler
		StudentErrorResponse error = new StudentErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}
}
