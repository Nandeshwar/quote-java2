package com.nks.quotejava2.controller;

import com.nks.quotejava2.model.Login;
import com.nks.quotejava2.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("This is login page");
        Login loginInfo = loginService.firstByUserAndPassword("Radha", "Krishna");
        System.out.println("login info user="+ loginInfo.getUser());

        return "login";
    }
}
