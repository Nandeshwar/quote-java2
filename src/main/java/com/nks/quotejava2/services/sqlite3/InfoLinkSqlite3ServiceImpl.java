package com.nks.quotejava2.services.sqlite3;

import com.nks.quotejava2.models.sqlite3.InfoLink;
import com.nks.quotejava2.repositories.sqlite3.InfoLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoLinkSqlite3ServiceImpl implements InfoLinkSqlite3Service {
    @Autowired
    InfoLinkRepository infoLinkRepository;

    @Override
    public Iterable<InfoLink> findByInfoLink(int linkId) {
        return infoLinkRepository.findByLinkId(linkId);
    }
}
