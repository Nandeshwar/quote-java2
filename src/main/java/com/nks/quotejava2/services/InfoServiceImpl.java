package com.nks.quotejava2.services;

import com.nks.quotejava2.repositories.mysql.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    InfoRepository infoRepository;

    @Override
    public int saveInfo() {
        //infoRepository.sa
        return 0;
    }
}