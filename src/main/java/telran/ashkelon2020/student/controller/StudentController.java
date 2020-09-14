package telran.ashkelon2020.student.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import telran.ashkelon2020.student.dto.MessageDto;
import telran.ashkelon2020.student.dto.ScoreDto;
import telran.ashkelon2020.student.dto.StudentDto;
import telran.ashkelon2020.student.dto.StudentResponseDto;
import telran.ashkelon2020.student.dto.StudentUpdateDto;
import telran.ashkelon2020.student.service.MessagingService;
import telran.ashkelon2020.student.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	List<MessagingService> messagingService;
	
	@PostMapping("/student")
	public boolean addStudent(@RequestBody StudentDto studentDto) {
		return studentService.addStudent(studentDto);
	}
	
	@GetMapping("/student/{id}")
	public StudentResponseDto findStudent(@PathVariable Integer id) {
		return studentService.findStudent(id);
	}
	
	@DeleteMapping("/student/{id}")
	public StudentResponseDto deleteStudent(@PathVariable Integer id) {
		return studentService.deleteStudent(id);
	}
	
	@PutMapping("/student/{id}")
	public StudentDto updateStudent(@PathVariable Integer id, @RequestBody StudentUpdateDto studentUpdateDto) {
		return studentService.updateStudent(id, studentUpdateDto);
	}
	
	@PutMapping("/score/student/{id}")
	public boolean addScore(@PathVariable Integer id, @RequestBody ScoreDto scoreDto) {
		return studentService.addScore(id, scoreDto);
	}
	
	@PostMapping("/student/message/{message}")
	public List<MessageDto> sendMessage(@PathVariable String message) {
		return messagingService.stream().map(s -> s.sendMessage(message))
			.collect(Collectors.toList());
	}
	
	@GetMapping("/student/name/{name}")
	public List<StudentResponseDto> findStudentsByName(@PathVariable String name){
		return studentService.findStudentsByName(name);
	}
	
	@GetMapping("/student/name/{name}/id/{minId}")
	public List<StudentResponseDto> findByNameAndIdGreaterThan(@PathVariable String name, @PathVariable int minId) {
		return studentService.findByNameAndIdGreaterThan(name, minId);
	}
	
	@PostMapping("/quantity/students")
	public Long findStudentsQuantity(@RequestBody List<String> names) {
		return studentService.studentsQuantity(names);
	}
	
	@GetMapping("/students/exam/{exam}/minscore/{score}")
	public List<StudentResponseDto> findStudentsByExamScore(@PathVariable String exam, @PathVariable int score) {
		return studentService.findStudentsByExamScore(exam, score);		
	}
	
}
