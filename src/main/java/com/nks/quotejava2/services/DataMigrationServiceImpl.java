package com.nks.quotejava2.services;

import com.nks.quotejava2.models.mysql.Info;
import com.nks.quotejava2.models.sqlite3.InfoLink;
import com.nks.quotejava2.models.sqlite3.InfoSqlite;
import com.nks.quotejava2.repositories.mysql.InfoRepository;
import com.nks.quotejava2.services.sqlite3.InfoLinkSqlite3Service;
import com.nks.quotejava2.services.sqlite3.InfoSqliteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class DataMigrationServiceImpl<copySqlite3InfoToMySqlInfo> implements DataMigrationService {
    Logger logger = LoggerFactory.getLogger(DataMigrationServiceImpl.class);

    @Autowired
    InfoSqliteService infoSqliteService;

    @Autowired
    InfoLinkSqlite3Service infoLinkSqlite3Service;

    @Autowired
    InfoRepository infoRepository;

    @Autowired
    InfoService infoService;

    @Override
    public void migrateDataFromSqlite3ToMySql() throws Exception {
        Stream<InfoSqlite> allInfoFrmoSqlite3 = StreamSupport.stream(infoSqliteService.findAll().spliterator(), false);
        List<Info> InfoListForMySql = allInfoFrmoSqlite3
                .map((infoSqlite3) -> copySqlite3InfoToMySqlInfo(infoSqlite3))
                .filter(infoSqlite3 -> infoSqlite3 != null)
                .collect(Collectors.toList());


        // Save all to database
        infoRepository.saveAll(InfoListForMySql);
    }

    Info copySqlite3InfoToMySqlInfo(InfoSqlite infoSqlite) {
        Info infoInDB = infoService.findFirstInfoByTitleAndInfo(infoSqlite.getTitle(), infoSqlite.getInfo());
        if (infoInDB != null && infoInDB.getTitle().length() > 0) {
            logger.info("record already exist in mysql =" + infoInDB.getTitle());
            return null;
        }
        Info info = new Info();
        info.setTitle(infoSqlite.getTitle());
        info.setInfo(infoSqlite.getInfo());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime createdAt = LocalDateTime.parse(infoSqlite.getCreatedAt().substring(0, 16), formatter);

        ZonedDateTime createdAtZoneTime = createdAt.atZone(ZoneId.systemDefault());
        ZonedDateTime createdAtUtc = createdAtZoneTime.withZoneSameInstant(ZoneId.of("UTC"));

        info.setCreatedAt(createdAtUtc);

        LocalDateTime updatedAt = LocalDateTime.parse(infoSqlite.getUpdatedAt().substring(0, 16), formatter);

        ZonedDateTime updatedAtZoneTime = updatedAt.atZone(ZoneId.systemDefault());
        ZonedDateTime updatedAtUtc = createdAtZoneTime.withZoneSameInstant(ZoneId.of("UTC"));

        info.setCreatedAt(createdAtUtc);
        info.setUpdatedAt(updatedAtUtc);

        Stream<com.nks.quotejava2.models.sqlite3.InfoLink> infoLinkListSqlite3 = StreamSupport.stream(infoLinkSqlite3Service.findByInfoLink((int) infoSqlite.getId()).spliterator(), false);
        List<com.nks.quotejava2.models.mysql.InfoLink> infoLinkMySql = infoLinkListSqlite3
                .map((infoLinkSqlite3) -> copyInfoLinkFromSqlite3ToMySql(info, infoLinkSqlite3))
                .collect(Collectors.toList());

        info.setInfoLink(infoLinkMySql);
        return info;
    }

    private com.nks.quotejava2.models.mysql.InfoLink copyInfoLinkFromSqlite3ToMySql(Info info, InfoLink infoLinkSqlite3) {
        com.nks.quotejava2.models.mysql.InfoLink infoLink = new com.nks.quotejava2.models.mysql.InfoLink();
        infoLink.setLink(infoLinkSqlite3.getLink());

        // Getting created date from InfoLink Sqlite3
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime createdAt = LocalDateTime.parse(infoLinkSqlite3.getCreatedAt().substring(0, 16), formatter);

        ZonedDateTime createdAtZoneTime = createdAt.atZone(ZoneId.systemDefault());
        ZonedDateTime createdAtUtc = createdAtZoneTime.withZoneSameInstant(ZoneId.of("UTC"));

        // Getting updated date from infoLink from sqlite3
        LocalDateTime updatedAt = LocalDateTime.parse(infoLinkSqlite3.getUpdatedAt().substring(0, 16), formatter);

        ZonedDateTime updatedAtZoneTime = updatedAt.atZone(ZoneId.systemDefault());
        ZonedDateTime updatedAtUtc = updatedAtZoneTime.withZoneSameInstant(ZoneId.of("UTC"));

        // setting data for InfoLink SQL
        infoLink.setLink(infoLinkSqlite3.getLink());
        infoLink.setCreatedAt(createdAtUtc);
        infoLink.setUpdatedAt(updatedAtUtc);
        infoLink.setInfo(info);

        return infoLink;
    }
}
