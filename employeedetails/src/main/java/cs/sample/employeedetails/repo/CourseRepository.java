package cs.sample.employeedetails.repo;

import cs.sample.employeedetails.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository  extends JpaRepository<Course, Integer> {


}
