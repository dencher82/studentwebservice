package telran.ashkelon2020.student.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.NOT_FOUND) // generate error code 404 instead 500
@NoArgsConstructor
public class StudentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentNotFoundException(int id) {
		super("Student with id " + id + " not found");
	}
	
}
