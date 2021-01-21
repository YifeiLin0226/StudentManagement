package project.studentManagement.service;

import project.studentManagement.entity.Instructor;
import project.studentManagement.entity.Student;

import java.util.List;

public interface StudentService {

    public List<Student> findAll();

    public Student findById(int theId);

    public void save(Student theStudent);

    public void deleteById(int theId);
}
