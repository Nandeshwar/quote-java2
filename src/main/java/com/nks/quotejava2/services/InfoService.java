package com.nks.quotejava2.services;

import com.nks.quotejava2.models.mysql.Info;

public interface InfoService {
    Info findFirstInfoByTitleAndInfo(String title, String info);
}
