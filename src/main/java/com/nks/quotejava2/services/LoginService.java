package com.nks.quotejava2.services;

import com.nks.quotejava2.models.mysql.Login;

public interface LoginService {
    Login firstByUserAndPassword(String name, String password);
}
