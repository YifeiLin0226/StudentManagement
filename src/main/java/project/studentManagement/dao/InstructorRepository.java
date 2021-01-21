package project.studentManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.studentManagement.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor,Integer> {

}
