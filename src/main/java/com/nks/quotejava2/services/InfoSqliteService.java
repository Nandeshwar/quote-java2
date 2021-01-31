package com.nks.quotejava2.services;

import com.nks.quotejava2.model.sqlite3.InfoSqlite;

public interface InfoSqliteService {
    Iterable<InfoSqlite> findAll();
}
