package com.ofonesie.ofonesie.controllers;

import com.ofonesie.ofonesie.models.Onesie;
import com.ofonesie.ofonesie.models.data.OnesieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="listing")
public class ListingController {

    @Autowired
    private OnesieDao onesieDao;


    @RequestMapping(value="add", method=RequestMethod.GET)
    public String addListing(Model model){
        model.addAttribute("title", "New Listing");
        model.addAttribute(new Onesie());
        return "listing/add";
    }
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String saveListing(Model model, @ModelAttribute @Valid Onesie onesie, Errors errors){

        if (errors.hasErrors()){
            model.addAttribute("title", "New Listing");
            model.addAttribute("onesie", onesie);
            return "listing/add";
        }
        onesieDao.save(onesie);
        return "redirect:view/" + onesie.getId();
    }

    @RequestMapping(value="view/{onesieId}")
    public String viewListing(Model model, @PathVariable int onesieId){
        Onesie onesie = onesieDao.findOne(onesieId);
        model.addAttribute("title", onesie.getTitle());
        model.addAttribute("onesie", onesie);

        return "listing/listing";
    }
}
