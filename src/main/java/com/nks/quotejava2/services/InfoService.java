package com.nks.quotejava2.services;

import com.nks.quotejava2.models.mysql.Info;

import java.util.List;

public interface InfoService {
    Info findFirstInfoByTitleAndInfo(String title, String info);

    List<Info> findAllInfo(int page, int size);

    Info findInfoById(Long id);
}
