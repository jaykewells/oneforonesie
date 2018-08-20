package com.ofonesie.ofonesie.controllers;

import com.ofonesie.ofonesie.models.Listing;
import com.ofonesie.ofonesie.models.Tag;
import com.ofonesie.ofonesie.models.Trade;
import com.ofonesie.ofonesie.models.UserInfo;
import com.ofonesie.ofonesie.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

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

    @Autowired
    private TradeDao tradeDao;

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

    @RequestMapping(value="review", method=RequestMethod.POST)
    public String review(Model model, @CookieValue(value="user", defaultValue = "none") String username,
                         @ModelAttribute @Valid Listing listing, Errors errors){

        model.addAttribute("categories", categoryDao.findAll());
        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        if (errors.hasErrors()){
            model.addAttribute("title", "New Listing");
            model.addAttribute("listing", listing);
            return "listing/add";
        }

        model.addAttribute("listing", listing);
        model.addAttribute("title", "Review Listing");
        HashMap<String, Tag> tags = new HashMap<String, Tag>();
        tags.put("Size", tagDao.findOne(listing.getSize()));
        tags.put("Season", tagDao.findOne(listing.getSeason()));
        tags.put("Color", tagDao.findOne(listing.getColor()));
        tags.put("Theme", tagDao.findOne(listing.getTheme()));
        model.addAttribute("tags", tags);

        return "listing/review";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String saveListing(Model model, @ModelAttribute Listing listing, @CookieValue(value="user", defaultValue="none") String username){

        if(username.equals("none")) {
            return "redirect:/user/login";
        }
        listing.setOwner(userInfoDao.findByUsername(username));
        listingDao.save(listing);
        return "redirect:/listing/view/" + listing.getId();
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
        model.addAttribute("related", listingDao.randomListings());

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

    @PostMapping(value="request/{listingId}")
    public String requestTrade(Model model, @PathVariable int listingId, @CookieValue(value="user", defaultValue="none") String username){
        Listing listing = listingDao.findOne(listingId);
        UserInfo prevOwner = listing.getOwner();
        UserInfo requester = userInfoDao.findByUsername(username);
        Trade trade = new Trade(listingId, prevOwner.getId(), requester.getId());
        listing.setOwner(requester);
        tradeDao.save(trade);
        listingDao.save(listing);

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("listings", listingDao.findAll());
        model.addAttribute("title", "Home");
        model.addAttribute("message", "Successfully requested " + listing.getTitle() + " !");


        return"redirect:/home";


    }
}
