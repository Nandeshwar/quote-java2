package com.nks.quotejava2.services.sqlite3;

import com.nks.quotejava2.models.sqlite3.InfoSqlite;
import com.nks.quotejava2.repositories.sqlite3.InfoSqliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("InfoSqliteService")
public class InfoSqliteServiceImpl implements InfoSqliteService {

    @Autowired
    private InfoSqliteRepository repo;

    @Override
    public Iterable<InfoSqlite> findAll() {
        return repo.findAll();
    }
}
