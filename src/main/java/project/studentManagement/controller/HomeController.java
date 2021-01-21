package project.studentManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import project.studentManagement.entity.Instructor;
import project.studentManagement.entity.User;
import project.studentManagement.service.UserService;

import java.security.Principal;
import java.util.Locale;

// Controller for the home page
@Controller
public class HomeController {
    // we need this to retrieve the user information
    @Autowired
    private UserService userService;


    @RequestMapping("/home")
    public String getHome(Principal principal, Model theModel){
        // get the logged in username
        String username = principal.getName();
        User user = userService.findById(username);
        String firstName;
        // user is joint with either a instructor, a student or an administrator
        if(user.getInstructor() != null){
            firstName = user.getInstructor().getFirstName();
        }
        else if(user.getStudent() != null)
            firstName = user.getStudent().getFirstName();
        else
            firstName = username;
        theModel.addAttribute("firstName",firstName.toUpperCase(Locale.ROOT));
        return "home";
    }
}
