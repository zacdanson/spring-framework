package com.example.controller;

import com.example.domain.PersonDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class Hello {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(Model model) {
        return "helloView";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String sayHelloWithDetails(
            Model model,
            @Valid PersonDetails personDetails,
            BindingResult bindingResult) {

        if ( bindingResult.hasErrors() ) {
            model.addAttribute("errors", "You have errors!");
            return "helloView";
        }

        model.addAttribute("details", personDetails);

        return "helloView";
    }
}


