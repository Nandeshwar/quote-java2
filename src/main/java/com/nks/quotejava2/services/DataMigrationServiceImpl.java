package com.nks.quotejava2.services;

import com.nks.quotejava2.models.mysql.Info;
import com.nks.quotejava2.models.sqlite3.InfoLink;
import com.nks.quotejava2.models.sqlite3.InfoSqlite;
import com.nks.quotejava2.services.sqlite3.InfoLinkSqlite3Service;
import com.nks.quotejava2.services.sqlite3.InfoSqliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class DataMigrationServiceImpl<copySqlite3InfoToMySqlInfo> implements DataMigrationService {
    @Autowired
    InfoSqliteService infoSqliteService;

    @Autowired
    InfoLinkSqlite3Service infoLinkSqlite3Service;

    @Override
    public void migrateDataFromSqlite3ToMySql() throws Exception {
        //infoSqliteService.findAll().forEach(infoSqlite -> migrateToMySql(infoSqlite));
        Stream<InfoSqlite> allInfoFrmoSqlite3 = StreamSupport.stream(infoSqliteService.findAll().spliterator(), false);
        List<Info> InfoListForMySql = allInfoFrmoSqlite3
                .map((infoSqlite3) -> copySqlite3InfoToMySqlInfo(infoSqlite3))
                .collect(Collectors.toList());

        // TODO: Collect data to Info List
        System.out.println("collected data for mysql");
        InfoListForMySql.forEach(info ->
        {
            System.out.println(info.getInfo());
            System.out.println(info.getInfoLink());
        });
        // Save all to database
    }

    Info copySqlite3InfoToMySqlInfo(InfoSqlite infoSqlite) {
        Info info = new Info();
        info.setTitle(infoSqlite.getTitle());
        info.setInfo(infoSqlite.getInfo());


        Stream<com.nks.quotejava2.models.sqlite3.InfoLink> infoLinkListSqlite3 = StreamSupport.stream(infoLinkSqlite3Service.findByInfoLink((int) infoSqlite.getId()).spliterator(), false);
        List<com.nks.quotejava2.models.mysql.InfoLink> infoLinkMySql = infoLinkListSqlite3
                .map((infoLinkSqlite3) -> copyInfoLinkFromSqlite3ToMySql(infoLinkSqlite3))
                .collect(Collectors.toList());

        info.setInfoLink(infoLinkMySql);
        return info;
    }

    private com.nks.quotejava2.models.mysql.InfoLink copyInfoLinkFromSqlite3ToMySql(InfoLink infoLinkSqlite3) {
        com.nks.quotejava2.models.mysql.InfoLink infoLink = new com.nks.quotejava2.models.mysql.InfoLink();
        infoLink.setLink(infoLinkSqlite3.getLink());
        return infoLink;
    }


    private void migrateToMySql(InfoSqlite infoSqlite) {
        System.out.println("-------------");
        System.out.println(String.format("info=%s", infoSqlite.getTitle()));
        infoLinkSqlite3Service.findByInfoLink((int) infoSqlite.getId()).forEach(infoLink -> {
                    System.out.println(String.format("infoLink=%s", infoLink.getLink()));
                }
        );
    }
}