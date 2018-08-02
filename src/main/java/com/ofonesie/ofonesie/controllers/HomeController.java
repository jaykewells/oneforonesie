package com.ofonesie.ofonesie.controllers;


import com.ofonesie.ofonesie.models.data.ListingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Jayke Wells
 *
 * HomeController is responsible for handling the initial landing page for OfO.
 */

@Controller
@RequestMapping(value="home")
public class HomeController {

    @Autowired
    private ListingDao listingDao;

    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("title", "Home");
        model.addAttribute("listings", listingDao.findAll());
        return "home/index";
    }

}
