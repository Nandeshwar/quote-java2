package com.nks.quotejava2.services;

import com.nks.quotejava2.models.sqlite3.InfoSqlite;
import com.nks.quotejava2.services.sqlite3.InfoLinkService;
import com.nks.quotejava2.services.sqlite3.InfoSqliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataMigrationServiceImpl implements DataMigrationService {
    @Autowired
    InfoSqliteService infoSqliteService;

    @Autowired
    InfoLinkService infoLinkService;

    @Override
    public void migrateDataFromSqlite3ToMySql() throws Exception {
        infoSqliteService.findAll().forEach(infoSqlite -> migrateToMySql(infoSqlite));
    }

    private void migrateToMySql(InfoSqlite infoSqlite) {
        System.out.println("-------------");
        System.out.println(String.format("info=%s", infoSqlite.getTitle()));
        infoLinkService.findByInfoLink((int) infoSqlite.getId()).forEach(infoLink -> {
                    System.out.println(String.format("infoLink=%s", infoLink.getLink()));
                }
        );
    }
}
