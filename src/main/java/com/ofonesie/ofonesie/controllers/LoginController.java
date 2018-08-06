package com.ofonesie.ofonesie.controllers;


import com.ofonesie.ofonesie.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class LoginController {
    @Autowired
    private IUserInfoService userInfoService;

    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("title", "Login");

        return "user/login";
    }
}
