package io.atulandjava.org.Coronavirustrackerbeta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home(Model model){
        model.addAttribute("TestString", "Hello! This is a coronavirus tracking application.");
        return "home";
    }
}
