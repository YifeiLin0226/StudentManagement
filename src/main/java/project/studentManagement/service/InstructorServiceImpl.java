
package project.studentManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.studentManagement.dao.InstructorRepository;
import project.studentManagement.entity.Instructor;

import java.util.List;
import java.util.Optional;
@Service
public class InstructorServiceImpl implements InstructorService{

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<Instructor> findAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findById(int theId) {
        Optional<Instructor> result = instructorRepository.findById(theId);
        Instructor theInstructor = null;
        if(result.isPresent())
            theInstructor = result.get();
        return theInstructor;
    }

    @Override
    public void save(Instructor theInstructor) {
        instructorRepository.save(theInstructor);
    }

    @Override
    public void deleteById(int theId) {
        instructorRepository.deleteById(theId);

    }
}
