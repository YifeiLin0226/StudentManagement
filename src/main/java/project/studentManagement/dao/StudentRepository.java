package project.studentManagement.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import project.studentManagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
