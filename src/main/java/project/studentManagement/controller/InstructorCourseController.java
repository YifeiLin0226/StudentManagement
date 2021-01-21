package project.studentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.studentManagement.entity.Block;
import project.studentManagement.entity.Student;
import project.studentManagement.entity.User;
import project.studentManagement.service.BlockService;
import project.studentManagement.service.UserService;

import java.security.Principal;
import java.util.List;
/*
This controller for the page to show the teaching courses if the user is logged in as an instructor
 */
@Controller
@RequestMapping("/instructorCourses")
public class InstructorCourseController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlockService blockService;


    // to show the courses that this instructor is teaching
    @GetMapping("/list")
    public String courseList(Model theModel, Principal principal){
        // find the user logged in
        User theUser = userService.findById(principal.getName());
        // Add this instructor's block list to the model
        theModel.addAttribute("blocks",theUser.getInstructor().getBlocks());
        return "instructorCourses/course_list";
    }

    // to show the student list of this course
    @GetMapping("/students")
    public String studentList(Model theModel, @RequestParam int blockId){
        // find the corresponding block
        Block theBlock = blockService.findById(blockId);

        // get the course name of the block
        String courseName = theBlock.getCourse().getTitle();

        // get the student list enrolled in this block
        List<Student> studentList = theBlock.getStudents();

        // Add the block to the model
        theModel.addAttribute("studentList", studentList);
        // Add the course title to the model
        theModel.addAttribute("courseName", courseName);

        return "/instructorCourses/student_list";
    }
}
