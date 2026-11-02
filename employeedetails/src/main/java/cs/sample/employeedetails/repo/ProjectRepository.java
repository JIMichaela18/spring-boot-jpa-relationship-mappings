package cs.sample.employeedetails.repo;

import cs.sample.employeedetails.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
