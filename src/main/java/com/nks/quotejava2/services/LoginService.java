package com.nks.quotejava2.services;

import com.nks.quotejava2.model.mysql.Login;
import com.nks.quotejava2.repositories.mysql.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    public Login firstByUserAndPassword(String name, String password) {
        Login login = loginRepository.findFirstByUserAndPassword(name, password);
        return login;
    }
}
