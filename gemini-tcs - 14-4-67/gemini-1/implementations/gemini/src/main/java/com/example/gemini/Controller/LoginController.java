package com.example.gemini.Controller;
import com.example.gemini.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AstronomerRepository astronomerRepository;
    @Autowired
    private ScienceObserverRepository scienceObserverRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public Object login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("role") String role) {
        User user = userRepository.findByUsername(username);

        if (user.getRole().equalsIgnoreCase("scienceobserver") ) {
//        if (role.equalsIgnoreCase("scienceobserver")) {

            return new ModelAndView("redirect:/getAllData" );
//            return new ModelAndView("scienceobserverHomePage");

        }
        else if (  user.getRole().equalsIgnoreCase("astronomer")) {
//        else if (role.equalsIgnoreCase("astronomer")) {

            return new ModelAndView("redirect:/getAllSP" );
//            return new ModelAndView("astronomerHomePage");
        }
        else {

            return new ModelAndView("notfound");
        }
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @GetMapping("/signup")
    public String showsignupPage() {
        return "signup"; // ชื่อของไฟล์ HTML ที่ต้องการส่งกลับ
    }

    @PostMapping("/signup")
    public Object addNewUser(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("role") String role) {
        // Check role and create appropriate user type
        if (role.equalsIgnoreCase("astronomer")) {
            Astronomer newAstronomer = new Astronomer();
            newAstronomer.setUsername(username);
            newAstronomer.setPassword(password);
            newAstronomer.setRole(role);
            astronomerRepository.save(newAstronomer);
        } else if (role.equalsIgnoreCase("scienceobserver")) {
            ScienceObserver newScienceObserver = new ScienceObserver();
            newScienceObserver.setUsername(username);
            newScienceObserver.setPassword(password);
            newScienceObserver.setRole(role);
            scienceObserverRepository.save(newScienceObserver);
        } else {
            // Handle invalid role
            return "invalid-role";
        }
        return "login";
    }

//    @GetMapping("/getAllAstronomer")
//    public@ResponseBody Iterable<Astronomer> getAllAstronmer()  {
//        return astronomerRepository.findAll();
//    }

    @GetMapping("/getAllSciencObserver")
    public@ResponseBody Iterable<ScienceObserver> getAllSciencObserver()  {
        return scienceObserverRepository.findAll();
    }

//    @GetMapping("/getAllAstronomer")
//    public String getAllSciencePlans(Model model) {
////        ArrayList<SciencePlan> sciencePlans = ocs.getAllSciencePlans();
//        ArrayList<Astronomer> Astronomers = (ArrayList<Astronomer>) astronomerRepository.findAll();
//        model.addAttribute("Astronomers", Astronomers);
//        System.out.println(Astronomers);
//        return "scienceobserverHomePage";
////        return "redirect:/getALSP";
//    }
}