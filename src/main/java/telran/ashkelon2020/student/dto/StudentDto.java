package telran.ashkelon2020.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
public class StudentDto {
	Integer id;
	String name;
	String password;
	
}
