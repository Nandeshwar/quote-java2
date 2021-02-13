package com.nks.quotejava2.services;

import com.nks.quotejava2.models.sqlite3.InfoSqlite;
import org.springframework.beans.factory.annotation.Autowired;

public class DataMigrationServiceImpl implements DataMigrationService {
    @Autowired
    InfoSqliteService infoSqliteService;

    @Override
    public void MigrateDataFromSqlite3ToMySql() throws Exception {
        infoSqliteService.findAll().forEach(infoSqlite -> migrateToMySql(infoSqlite));
    }

    private void migrateToMySql(InfoSqlite infoSqlite) {

    }
}
