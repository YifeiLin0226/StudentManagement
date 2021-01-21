package project.studentManagement.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import project.studentManagement.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}
