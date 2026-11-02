package cs.sample.employeedetails.repo;

import cs.sample.employeedetails.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
