package project.studentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.studentManagement.entity.Block;
import project.studentManagement.entity.Student;
import project.studentManagement.entity.User;
import project.studentManagement.service.BlockService;
import project.studentManagement.service.UserService;

import java.security.Principal;
/*
This controller for the page to show the enrolled courses if the user is logged in as a student
 */
@Controller
@RequestMapping("/studentCourses")
public class StudentCourseController {
    @Autowired
    private UserService userService;

    @Autowired
    private BlockService blockService;

    // show the list of courses the student has enrolled in
    @RequestMapping("/list")
    public String courseList(Model theModel, Principal principal){
        // find the user logged in
        User theUser = userService.findById(principal.getName());
        // Add this student's block list to the model
        theModel.addAttribute("blocks",theUser.getStudent().getBlocks());
        return "studentCourses/course_list";
    }

    // unenroll from a course for the student
    @RequestMapping("/unenroll")
    public String unenroll(@RequestParam int blockId, Model theModel, Principal principal){
        // find the user logged in
        User theUser = userService.findById(principal.getName());
        // retrieve the student entity from the user
        Student theStudent = theUser.getStudent();

        // retrieve the block entity
        Block theBlock = blockService.findById(blockId);

        // Add the course title to the model
        theModel.addAttribute("courseTitle", theBlock.getCourse().getTitle());

        // delete the student from the block
        try{
            theBlock.getStudents().remove(theStudent);
            blockService.save(theBlock);
        }
        catch(Exception exc){
            return "studentCourses/unsuccessful_unenrollment";
        }
        return "studentCourses/successful_unenrollment";
    }
}
