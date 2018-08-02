package com.ofonesie.ofonesie.controllers;

import com.ofonesie.ofonesie.models.Listing;
import com.ofonesie.ofonesie.models.data.CategoryDao;
import com.ofonesie.ofonesie.models.data.ListingDao;
import com.ofonesie.ofonesie.models.data.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by Jayke Wells
 *
 * ListingController is responsible for handling user interactions with individual [Listing] objects, including viewing
 * all and viewing by tag.
 */

@Controller
@RequestMapping(value="listing")
public class ListingController {

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private TagDao tagDao;

    @RequestMapping(value="add", method=RequestMethod.GET)
    public String addListing(Model model){
        model.addAttribute("title", "New Listing");
        model.addAttribute(new Listing());
        model.addAttribute("categories", categoryDao.findAll());
        return "listing/add";
    }
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String saveListing(Model model, @ModelAttribute @Valid Listing listing, Errors errors){

        model.addAttribute("categories", categoryDao.findAll());
        if (errors.hasErrors()){
            model.addAttribute("title", "New Listing");
            model.addAttribute("listing", listing);
            return "listing/add";
        }
        listingDao.save(listing);
        return "redirect:view/" + listing.getId();
    }

    @RequestMapping(value="view/{listingId}")
    public String viewListing(Model model, @PathVariable int listingId){
        Listing listing = listingDao.findOne(listingId);
        model.addAttribute("title", listing.getTitle());
        model.addAttribute("listing", listing);

        return "listing/listing";
    }
}
