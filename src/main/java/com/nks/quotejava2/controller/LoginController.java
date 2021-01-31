package com.nks.quotejava2.controller;

import com.nks.quotejava2.model.mysql.Login;
import com.nks.quotejava2.services.InfoSqliteService;
import com.nks.quotejava2.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    InfoSqliteService infoSqliteService;

    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("This is login page");
        Login loginInfo = loginService.firstByUserAndPassword("Radha", "Krishna");
        System.out.println("login info user=" + loginInfo.getUser());


        System.out.println("Begin.........Sqlite3 info......");
        infoSqliteService.findAll().forEach(v ->
                System.out.println(String.format("title = %s", v.getTitle())));

        System.out.println("End.........Sqlite3 info......");

        return "login";
    }
}
