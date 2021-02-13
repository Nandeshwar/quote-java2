package com.nks.quotejava2.controllers;

import com.nks.quotejava2.models.mysql.Login;
import com.nks.quotejava2.services.LoginService;
import com.nks.quotejava2.services.sqlite3.InfoSqliteService;
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
                System.out.println(String.format("title = %s, creationDate=%s", v.getTitle(), v.getCreatedAt())));

        System.out.println("End.........Sqlite3 info......");

        return "login";
    }
}
