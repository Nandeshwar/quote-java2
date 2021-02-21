package com.nks.quotejava2;

import com.nks.quotejava2.services.DataMigrationService;
import com.nks.quotejava2.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    @Autowired
    LoginService loginService;

    @Autowired
    DataMigrationService dataMigrationService;

    @Override
    public void run(String... args) {
//        Login login = loginService.firstByUserAndPassword("Radha", "Krishna");
//        System.out.println("--------> " + login.getUser());
//
        try {
            dataMigrationService.migrateDataFromSqlite3ToMySql();
        } catch (Exception e) {
            logger.error("error occured in data migration from sqlite3 to MySql=" + e.getMessage());
            e.printStackTrace();
        }
    }
}
