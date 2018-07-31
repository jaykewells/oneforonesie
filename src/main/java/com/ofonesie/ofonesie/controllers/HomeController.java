package com.ofonesie.ofonesie.controllers;


import com.ofonesie.ofonesie.models.Onesie;
import com.ofonesie.ofonesie.models.data.OnesieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Jayke Wells
 *
 * HomeController is responsible for handling the initial landing page for OfO.
 */

@Controller
@RequestMapping(value="home")
public class HomeController {

    @Autowired
    private OnesieDao onesieDao;

    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("title", "Home");
        model.addAttribute("onesies", onesieDao.findAll());
        return "home/index";
    }

}
