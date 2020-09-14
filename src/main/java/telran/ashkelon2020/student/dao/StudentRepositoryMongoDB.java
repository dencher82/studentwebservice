package telran.ashkelon2020.student.dao;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.ashkelon2020.student.model.Student;

public interface StudentRepositoryMongoDB extends MongoRepository<Student, Integer> { // PagingAndSortingRepository<Student, Integer> {
	
	Stream<Student> findByName(String name); // spring data реализует этот метод сам, если правильно задано имя
	
	Stream<Student> findByNameAndIdGreaterThan(String name, int minId);
	
	long countByNameIn(List<String> names);
	
	Stream<Student> findBy();
	
	@Query("{'scores.?0':{'$gte':?1}}")
	Stream<Student> findByExamAndScoreGreaterThanEqual(String exam, int score);
	
}
