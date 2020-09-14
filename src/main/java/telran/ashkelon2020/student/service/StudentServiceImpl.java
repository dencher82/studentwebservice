package telran.ashkelon2020.student.service;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import telran.ashkelon2020.student.dao.StudentRepositoryMongoDB;
import telran.ashkelon2020.student.dto.ScoreDto;
import telran.ashkelon2020.student.dto.StudentDto;
import telran.ashkelon2020.student.dto.StudentResponseDto;
import telran.ashkelon2020.student.dto.StudentUpdateDto;
import telran.ashkelon2020.student.dto.exceptions.StudentNotFoundException;
import telran.ashkelon2020.student.model.Student;

@Component
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepositoryMongoDB studentRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean addStudent(StudentDto studentDto) {
//		Student student = new Student(studentDto.getId(), studentDto.getName(), studentDto.getPassword());
		Student student = modelMapper.map(studentDto, Student.class);
		if (studentRepository.existsById(student.getId())) {
			return false;
		} else {
			studentRepository.save(student);
			return true;
		}
	}

	@Override
	public StudentResponseDto findStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//		return convertStudentToStudentResponseDto(student);
		return modelMapper.map(student, StudentResponseDto.class);
	}

	@SuppressWarnings("unused")
	private StudentResponseDto convertStudentToStudentResponseDto(Student student) {
		return StudentResponseDto.builder().id(student.getId()).name(student.getName()).scores(student.getScores())
				.build();
	}

	@Override
	public StudentResponseDto deleteStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//		return convertStudentToStudentResponseDto(student);
		studentRepository.delete(student);
		return modelMapper.map(student, StudentResponseDto.class);
	}

	@Override
	public StudentDto updateStudent(int id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		String name = studentUpdateDto.getName();
		if (name == null) {
			name = student.getName();
		}
		String password = studentUpdateDto.getPassword();
		if (password == null) {
			password = student.getPassword();
		}
		student.setName(name);
		student.setPassword(password);
		studentRepository.save(student);
//		return convertStudentToStudentDto(student);
		return modelMapper.map(student, StudentDto.class);
	}

	@SuppressWarnings("unused")
	private StudentDto convertStudentToStudentDto(Student student) {
		return StudentDto.builder().id(student.getId()).name(student.getName()).password(student.getPassword()).build();
	}

	@Override
	public boolean addScore(int id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return res;
	}

	@Override
	public List<StudentResponseDto> findStudentsByName(String name) { // реализация в StudentServiceImpl плохая, так как мы
																	// вытягиваем для обработки всю БД, что может
																	// положить аппликацию. Поэтому правильнее добавлять
																	// нужные кастомные методы в интерфейс
																	// StudentRepositoryMongoDB
		return studentRepository.findByName(name)
				.map(s->modelMapper.map(s,StudentResponseDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<StudentResponseDto> findByNameAndIdGreaterThan(String name, int minId) {
		return studentRepository.findByNameAndIdGreaterThan(name, minId)
				.map(s->modelMapper.map(s,StudentResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public long studentsQuantity(List<String> names) {
		return studentRepository.countByNameIn(names);
	}

	@Override
	public List<StudentResponseDto> findStudentsByExamScore(String exam, int score) {
//		return studentRepository.findBy()
//				.filter(s -> s.getScores().containsKey(exam))
//				.filter(s -> s.getScores().get(exam) >= score)
//				.map(s -> modelMapper.map(s, StudentResponseDto.class))
//				.collect(Collectors.toList());
		return studentRepository.findByExamAndScoreGreaterThanEqual(exam, score)
				.map(s -> modelMapper.map(s, StudentResponseDto.class))
				.collect(Collectors.toList());
	}
	
	
}
