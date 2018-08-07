package com.ofonesie.ofonesie.controllers;


import com.ofonesie.ofonesie.models.UserInfo;
import com.ofonesie.ofonesie.models.UserInfoForm;
import com.ofonesie.ofonesie.models.data.UserInfoDAO;
import com.ofonesie.ofonesie.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class LoginController {
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private UserInfoDAO userInfoDao;

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("title", "Login");

        return "user/login";
    }

    //TODO: Add @PostMapping("login") to actually save the user to the active session.

    @GetMapping("register")
    public String register(Model model){

        model.addAttribute("title", "Register");
        model.addAttribute("form", new UserInfoForm());

        return "user/register";
    }
    @PostMapping("register")
    public String register(Model model, @Valid UserInfoForm form){

        model.addAttribute("title", "Register");
        String pass = form.getPassword();
        String ver = form.getVerify();

        if(pass != ver){

            form.setPassError("Passwords don't match!");
            model.addAttribute("form", form);
        }

        UserInfo user = new UserInfo(form.getUsername(), form.getPassword(),
                form.getFirstName(), form.getLastName(), form.getAddress1(),
                form.getAddress2(), form.getState(), form.getZip());
        userInfoDao.save(user);

        return "redirect:/home";
    }

    @GetMapping("logout")
    public String logout(Model model){

        return "redirect:home";
    }
}
