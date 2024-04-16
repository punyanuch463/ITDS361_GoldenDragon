package com.example.gemini.Controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public Object login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role) {
        if (role.equalsIgnoreCase("scienceobserver")) {

            return new ModelAndView("scienceobserver");

        } else if (role.equalsIgnoreCase("astronomer")) {

            return new ModelAndView("redirect:/getAllSP");
        } else {

            return new ModelAndView("notfound");
        }
    }


}