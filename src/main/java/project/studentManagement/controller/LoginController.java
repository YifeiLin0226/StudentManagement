package project.studentManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
/*
Log in controller for assigning the page of login and access denied
 */
@Controller
public class LoginController {

    @RequestMapping("/showMyLoginPage")
    public String showMyLoginPage() {

        return "login";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
