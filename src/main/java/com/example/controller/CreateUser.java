package com.example.controller;
import com.example.domain.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class CreateUser {

    @RequestMapping(value = "/createUser", method = RequestMethod.GET)
    public String createUser(Model model) {
        return "createUserView";
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public String sayHelloWithDetails(
            Model model,
            @Valid UserDetails details,
            BindingResult bindingResult) {

        if ( bindingResult.hasErrors() ) {
            model.addAttribute("errors", "You have errors!");
            return "createUserView";
        }

        model.addAttribute("details", details);

        return "createUserView";
    }

}


