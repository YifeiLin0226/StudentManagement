package project.studentManagement.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.studentManagement.dao.StudentRepository;
import project.studentManagement.entity.Student;

import java.util.List;
import java.util.Optional;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int theId) {
        Optional<Student> result = studentRepository.findById(theId);
        Student theStudent = new Student();
        if(result.isPresent())
            theStudent = result.get();
        return theStudent;
    }

    @Override
    public void save(Student theStudent) {
       studentRepository.save(theStudent);


    }

    @Override
    public void deleteById(int theId) {
        studentRepository.deleteById(theId);

    }
}
