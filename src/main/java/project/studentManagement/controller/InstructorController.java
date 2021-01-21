package project.studentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.studentManagement.entity.Instructor;
import project.studentManagement.service.InstructorService;

import javax.validation.Valid;
import java.util.List;

/*
This controller is mainly for adding, deleting, updating instructors for the user as an adminstrator

Instructor and student can also view the page of the list of courses but no other actions
 */
@Controller
@RequestMapping("/instructors")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;

    // retrieve the instructor list
    @GetMapping("/list")
    public String getInstructors(Model theModel) {
        List<Instructor> instructors = instructorService.findAll();
        theModel.addAttribute("instructors",instructors);
        return "instructors/instructor_list";

    }


    // save a instructor to db
    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute @Valid Instructor instructor, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "instructors/instructor-form";
        instructorService.save(instructor);

        return "redirect:/instructors/list";
    }

    // show the form for adding an instructor
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){

        Instructor theInstructor = new Instructor();
        theModel.addAttribute("instructor",theInstructor);
        return "instructors/instructor-form";
    }

    // show the form for updating an instructor
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam int instructorId, Model theModel) {

        Instructor theInstructor = instructorService.findById(instructorId);
        theModel.addAttribute("instructor", theInstructor);
        return "instructors/instructor-form";
    }

    // delete an instructor according to the instructorID
    @GetMapping("/delete")
    public String deleteById(@RequestParam int instructorId){
        instructorService.deleteById(instructorId);

        return "redirect:/instructors/list";

    }


}
