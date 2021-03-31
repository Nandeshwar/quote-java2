package com.nks.quotejava2.services;

import com.nks.quotejava2.models.mysql.Info;
import com.nks.quotejava2.repositories.mysql.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    InfoRepository infoRepository;

    @Override
    public Info findFirstInfoByTitleAndInfo(String title, String info) {
        return infoRepository.findFirstInfoByTitleAndInfo(title, info);
    }

    @Override
    public List<Info> findAllInfo(int page, int size) {
        Page<Info> pageableInfoList = infoRepository.findAll(PageRequest.of(page, size));
        return pageableInfoList.getContent();
    }

    @Override
    public Info findInfoById(Long id) {
        return infoRepository.findFirstInfoById(id);
    }
}
