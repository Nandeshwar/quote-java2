package com.nks.quotejava2.services.sqlite3;

import com.nks.quotejava2.models.sqlite3.InfoSqlite;

public interface InfoSqliteService {
    Iterable<InfoSqlite> findAll();
}
