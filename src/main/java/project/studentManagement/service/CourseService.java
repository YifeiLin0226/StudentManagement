package project.studentManagement.service;



import project.studentManagement.entity.Course;

import java.util.List;

public interface CourseService {
    public List<Course> findAll();

    public Course findById(int theId);

    public void save(Course theCourse);

    public void deleteById(int theId);
}
