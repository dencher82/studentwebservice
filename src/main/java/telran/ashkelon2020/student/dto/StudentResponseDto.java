package telran.ashkelon2020.student.dto;

import java.util.Map;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDto {
	Integer id;
	String name;
	Map<String, Integer> scores;
	
}
