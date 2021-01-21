package project.studentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.studentManagement.entity.Instructor;
import project.studentManagement.entity.Student;
import project.studentManagement.service.InstructorService;
import project.studentManagement.service.StudentService;

import javax.validation.Valid;
import java.util.List;
/*
This controller is mainly for administrators to add, delete, update students

Instructor and student can also view the page of the list of courses but no other actions
 */
@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // retrieve the list of all the students
    @GetMapping("/list")
    public String getStudents(Model theModel) {
        List<Student> students = studentService.findAll();
        theModel.addAttribute("students",students);
        return "students/student_list";

    }

    // save or update one student to the db
    @PostMapping("/save")
    public String saveStudent(@ModelAttribute @Valid Student student, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "students/student-form";
        studentService.save(student);

        return "redirect:/students/list";
    }


    // show the form of adding a new student
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        Student theStudent = new Student();
        theModel.addAttribute("student",theStudent);
        return "students/student-form";
    }

    // show the form of updating a student
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam int studentId, Model theModel) {

        Student theStudent = studentService.findById(studentId);
        theModel.addAttribute("student", theStudent);
        return "students/student-form";
    }

    // delelete a student according to the studentID
    @GetMapping("/delete")
    public String deleteById(@RequestParam int studentId){
        studentService.deleteById(studentId);

        return "redirect:/students/list";

    }


}