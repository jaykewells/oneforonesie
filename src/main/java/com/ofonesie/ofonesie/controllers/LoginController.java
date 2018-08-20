package com.ofonesie.ofonesie.controllers;


import com.ofonesie.ofonesie.models.UserInfo;
import com.ofonesie.ofonesie.models.data.CategoryDao;
import com.ofonesie.ofonesie.models.forms.LoginForm;
import com.ofonesie.ofonesie.models.forms.UserInfoForm;
import com.ofonesie.ofonesie.models.data.UserInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class LoginController {

    @Autowired
    private UserInfoDAO userInfoDao;

    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("")
    //TODO: Create a User Page that the user lands on after logging in.
    public String user(Model model, HttpServletRequest request){
        //Nav Info
        model.addAttribute("categories", categoryDao.findAll());
        Cookie user = new Cookie("Failed", "Failed");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if(cookie.getName().equals("user")){
                user = cookie;
            }
        }
        System.out.println("!!! User Name Is: " + user.getValue());
        UserInfo active = userInfoDao.findByUsername(user.getValue());
        if(active != null){
            model.addAttribute("user", active);
            model.addAttribute("title", active.getUsername());
            return"user/user";
        }else{
            model.addAttribute("title", "Login");
            model.addAttribute("form", new LoginForm());
            return"redirect:/user/login/";
        }

    }

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("title", "Login");
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("form", new LoginForm());
        return "user/login";
    }

    @PostMapping("login")
    public String login(Model model, @ModelAttribute LoginForm form, HttpServletResponse response) {
        UserInfo u = userInfoDao.findByUsername(form.getUsername());
        model.addAttribute("categories", categoryDao.findAll());
        if (u == null) {
            model.addAttribute("message", "Invalid Username");
            model.addAttribute("title", "Login");
            model.addAttribute("form", new LoginForm());
            return "user/login";
        }

        if (u.getPassword().equals(form.getPassword())) {

            Cookie c = new Cookie("user", u.getUsername());
            c.setPath("/");
            response.addCookie(c);

            Cookie auth = new Cookie("role", u.getRole());
            auth.setPath("/");
            response.addCookie(auth);
            return "redirect:/user/";
        } else {

            model.addAttribute("message", "Invalid Password");
            model.addAttribute("title", "Login");
            model.addAttribute("form", new LoginForm());
            return "user/login";
        }
    }

    @GetMapping("register")
    public String register(Model model){

        model.addAttribute("title", "Register");
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("form", new UserInfoForm());

        return "user/register";
    }
    @PostMapping("register")
    public String register(Model model, UserInfoForm form, Error errors){

        String pass = form.getPassword();
        String ver = form.getVerify();

        model.addAttribute("title", "Register");
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("form", form);
      if(pass == ver){
            model.addAttribute("message", "Passwords don't match!");
            return "user/register";
        }
       if(form.getUsername().isEmpty()){
            model.addAttribute("message", "Must have a Username!");
            return "user/register";
        }
        if(form.getPassword().isEmpty()){
            model.addAttribute("message", "Must have a Password!");
            return "user/register";
        }

        if(form.getAddress1().isEmpty()){
            model.addAttribute("message", "Must have a Street Address");
            return "user/register";
        }

        UserInfo user = new UserInfo(form.getUsername(), form.getPassword(),
                form.getFirstName(), form.getLastName(), form.getAddress1(),
                form.getCity(), form.getState(), form.getZip());
        userInfoDao.save(user);

        model.addAttribute("title", user.getUsername());
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("user", user);
        model.addAttribute("message", "Successfully Registered!");
        return "redirect:/user/";
    }

    @GetMapping("logout")
    public String logout(Model model, HttpServletRequest request, HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie c: cookies){
                c.setMaxAge(0);
                c.setPath("/");
                response.addCookie(c);
                model.addAttribute("title", "Home");
                model.addAttribute("categories", categoryDao.findAll());
                model.addAttribute("message", "Logged Out!");
            }
        }else{
            model.addAttribute("title", "Home");
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("message", "Not Currently Logged In!");
        }
        return "redirect:/home/";
    }
}
