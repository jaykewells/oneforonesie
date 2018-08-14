package com.ofonesie.ofonesie.controllers;

import com.ofonesie.ofonesie.models.Listing;
import com.ofonesie.ofonesie.models.Tag;
import com.ofonesie.ofonesie.models.data.CategoryDao;
import com.ofonesie.ofonesie.models.data.ListingDao;
import com.ofonesie.ofonesie.models.data.TagDao;
import com.ofonesie.ofonesie.models.data.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

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

    @Autowired
    private UserInfoDAO userInfoDao;

    @RequestMapping(value="add", method=RequestMethod.GET)
    public String addListing(Model model, @CookieValue(value="user", defaultValue="none") String username){
        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        model.addAttribute("title", "New Listing");
        model.addAttribute(new Listing());
        model.addAttribute("categories", categoryDao.findAll());
        return "listing/add";
    }
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String saveListing(Model model, @ModelAttribute @Valid Listing listing, Errors errors, @CookieValue(value="user", defaultValue="none") String username){

        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        model.addAttribute("categories", categoryDao.findAll());
        if (errors.hasErrors()){
            model.addAttribute("title", "New Listing");
            model.addAttribute("listing", listing);
            return "listing/add";
        }
        listing.setOwner(userInfoDao.findByUsername(username));
        listingDao.save(listing);
        return "redirect:view/" + listing.getId();
    }

    @RequestMapping(value="view/{listingId}")
    public String viewListing(Model model, @PathVariable int listingId, @CookieValue(value="user", defaultValue="none") String username){
        model.addAttribute("categories", categoryDao.findAll());
        if(username.equals("none")) {
            return "redirect:/user/login";
        }

        Listing listing = listingDao.findOne(listingId);
        HashMap<String, Tag> tags = new HashMap<String, Tag>();
        model.addAttribute("title", listing.getTitle());
        model.addAttribute("listing", listing);
        tags.put("Size", tagDao.findOne(listing.getSize()));
        tags.put("Season", tagDao.findOne(listing.getSeason()));
        tags.put("Color", tagDao.findOne(listing.getColor()));
        tags.put("Theme", tagDao.findOne(listing.getTheme()));
        model.addAttribute("tags", tags);
        return "listing/listing";
    }

    @RequestMapping(value="tag/{tagId}")
    public String viewTag(Model model, @PathVariable int tagId, @CookieValue(value="user", defaultValue="none") String username){
        model.addAttribute("title", "Viewing all " + tagDao.findOne(tagId).getTitle() + " Onesies");
        model.addAttribute("categories", categoryDao.findAll());
        if(!listingDao.findByColor(tagId).isEmpty()){
            model.addAttribute("listings", listingDao.findByColor(tagId));
            return "home/index";
        }else if(!listingDao.findBySeason(tagId).isEmpty()){
            model.addAttribute("listings", listingDao.findBySeason(tagId));
            return "home/index";
        }else if(!listingDao.findBySize(tagId).isEmpty()){
            model.addAttribute("listings", listingDao.findBySize(tagId));
            return "home/index";
        }else if(!listingDao.findByTheme(tagId).isEmpty()){
            model.addAttribute("listings", listingDao.findByTheme(tagId));
            return "home/index";
        }

        model.addAttribute("message", "No " + tagDao.findOne(tagId).getTitle() + " Onesies Found!");
        model.addAttribute("listings", listingDao.findAll());
        return "home/index";
    }
}
