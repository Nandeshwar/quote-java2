package com.nks.quotejava2;

import com.nks.quotejava2.models.mysql.Login;
import com.nks.quotejava2.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    LoginService loginService;

    @Override
    public void run(String... args) {
        Login login = loginService.firstByUserAndPassword("Radha", "Krishna");
        System.out.println("--------> " + login.getUser());
    }
}
