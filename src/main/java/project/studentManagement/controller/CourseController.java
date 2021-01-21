package project.studentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.studentManagement.entity.Block;
import project.studentManagement.entity.Course;
import project.studentManagement.entity.Instructor;
import project.studentManagement.entity.User;
import project.studentManagement.service.BlockService;
import project.studentManagement.service.CourseService;
import project.studentManagement.service.InstructorService;
import project.studentManagement.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/*
This controller is mainly for adding, deleting, updating courses and blocks for the user as an adminstrator
Also support students to enroll in the block for different courses
Instructor can also view the page of the list of courses but no other actions
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private BlockService blockService;

    @Autowired
    private UserService userService;

    // retrieve the list of all courses
    @RequestMapping("/list")
    public String getCourses(Model theModel){
        List<Course> courses = courseService.findAll();
        theModel.addAttribute("courses",courses);
        return "courses/course_list";
    }

    // save or update a course and store in db
    @PostMapping("/save")
    public String saveCourse(@ModelAttribute @Valid Course course, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "courses/course-form";
        courseService.save(course);

        return "redirect:/courses/list";
    }

    // show the form for entering information for a new course
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        Course theCourse = new Course();
        theModel.addAttribute("course",theCourse);
        return "courses/course-form";
    }

    // show the form for updating an existing course
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam int courseId, Model theModel) {

        Course theCourse = courseService.findById(courseId);
        theModel.addAttribute("course", theCourse);
        return "courses/course-form";
    }

    // delete a course according to the courseId
    @GetMapping("/delete")
    public String deleteById(@RequestParam int courseId){
        courseService.deleteById(courseId);

        return "redirect:/courses/list";

    }

    // get the detailed block information for one course
    @GetMapping("/details")
    public String courseDetails(@RequestParam int courseId, Model theModel) {
        Course theCourse = courseService.findById(courseId);
        theModel.addAttribute("course",theCourse);
        return "courses/course_details";
    }

    // The form of adding a new block to the course
    @GetMapping("/details/showFormForAddBlock")
    public String showFormForAddBlock(@RequestParam int courseId, Model theModel) {
        Block theBlock = new Block();
        Course theCourse = courseService.findById(courseId);
        List<Instructor> instructorList = instructorService.findAll();
        theModel.addAttribute("course",theCourse);
        theModel.addAttribute("block", theBlock);
        theModel.addAttribute("instructorList",instructorList);
        return "courses/block-form";
    }

    //  The form of updating a block to the course
    @GetMapping("/details/showFormForUpdateBlock")
    public String showFormForUpdateBlock(@RequestParam int blockId, @RequestParam int courseId, Model theModel){
        Block theBlock = blockService.findById(blockId);
        Course theCourse = courseService.findById(courseId);
        List<Instructor> instructorList = instructorService.findAll();
        theModel.addAttribute("block",theBlock);
        theModel.addAttribute("course",theCourse);
        theModel.addAttribute("instructorList",instructorList);
        return "courses/block-form";
    }

    // For students to enroll in the course or check the detail of the course
    @GetMapping("/enroll")
    public String enrollCourse(@RequestParam int courseId, Model theModel){
        Course theCourse = courseService.findById(courseId);
        theModel.addAttribute("course",theCourse);
        return "courses/course_details";
    }

    // enroll in a block of the course
    @GetMapping("/details/enroll")
    public String enrollBlock(@RequestParam int courseId,  @RequestParam int blockId,Model theModel, Principal principal){
        theModel.addAttribute("courseTitle", courseService.findById(courseId).getTitle());
        User theUser = userService.findById(principal.getName());
        List<Block> blocks = theUser.getStudent().getBlocks();

        for (Block tempBlock:blocks){
            if(tempBlock.getCourse().getId() == courseId){

                return "courses/already_enrolled";
            }

        }

        Block theBlock = blockService.findById(blockId);
        if (theBlock.getSeats()<= theBlock.getStudents().size())
            return "courses/full_block";

        theUser.getStudent().getBlocks().add(theBlock);
        userService.save(theUser);
        return "courses/successfully_enrolled";
    }

    // save or update a block of the course
    @PostMapping("/details/save")
    public String saveBlock(@ModelAttribute @Valid Block block,@RequestParam int courseId , BindingResult bindingResult){
        // if the form doesn't pass the validation, return to that page and ask for re-enter
        if(bindingResult.hasErrors()){
            return "courses/block-form";
        }
        // find the course in db
        Course theCourse = courseService.findById(courseId);
        // find the corresponding instructor
        Instructor theInstructor = instructorService.findById(block.getInstructor().getId());
        // setup the block
        block.setCourse(theCourse);
        block.setInstructor(theInstructor);
        // save the block to db, also cascade occurs in other tables
        blockService.save(block);
        return "redirect:/courses/details?courseId=" + courseId;


    }

    // delete a block of the course
    @GetMapping("/details/delete")
    public String deleteBlock(@RequestParam int blockId,@RequestParam int courseId){

        // remove the block from db
        blockService.deleteById(blockId);
        return "redirect:/courses/details?courseId=" + courseId;
    }


}
