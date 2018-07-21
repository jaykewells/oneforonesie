package com.ofonesie.ofonesie.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jayke Wells
 *
 * HomeController is responsible for handling the initial landing page for OfO.
 */

@Controller
public class HomeController {


    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("title", "home");

        return "index";
    }
    @RequestMapping(value="list", method=RequestMethod.GET)
    public String addListing(Model model){
        model.addAttribute("title", "New Listing");
        return;
    }
}
