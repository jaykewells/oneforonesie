package com.ofonesie.ofonesie.controllers;

import com.ofonesie.ofonesie.models.Category;
import com.ofonesie.ofonesie.models.Tag;
import com.ofonesie.ofonesie.models.data.CategoryDao;
import com.ofonesie.ofonesie.models.data.ListingDao;
import com.ofonesie.ofonesie.models.data.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="admin")
public class AdminController {

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value="")
    public String adminindex(Model model){

        model.addAttribute("title", "Admin Functions");
        return "admin/index";
    }

    @RequestMapping(value="category", method=RequestMethod.GET)
    public String category(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("title", "New Category");
        model.addAttribute("categories", categoryDao.findAll());
        return "admin/category";
    }

    @RequestMapping(value="category", method=RequestMethod.POST)
    public String category(Model model, @Valid @ModelAttribute Category category, Errors errors){

        if (!errors.hasErrors()){
            categoryDao.save(category);
            model.addAttribute("title", "Admin Functions");
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute(new Category());
            return "admin/category";
        }
        model.addAttribute("title", "New Category");
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("category", category);
        return "admin/category";
    }

    @RequestMapping(value="tag", method=RequestMethod.GET)
    public String tag(Model model){
        model.addAttribute("tag", new Tag());
        model.addAttribute("title", "New Tag");
        model.addAttribute("categories", categoryDao.findAll());
        return "admin/tag";
    }

    @RequestMapping(value="tag", method=RequestMethod.POST)
    public String tag (Model model, @Valid @ModelAttribute Tag tag, Errors errors){

        if (!errors.hasErrors() ){
            tagDao.save(tag);
            model.addAttribute("title", "Admin Functions");
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute(new Tag());
            return "admin/tag";
        }
        model.addAttribute("title", "New Category");
        model.addAttribute("tag", tag);
        model.addAttribute("categories", categoryDao.findAll());
        return "admin/tag";
    }

}
