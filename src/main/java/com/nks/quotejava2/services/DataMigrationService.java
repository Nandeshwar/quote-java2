package com.nks.quotejava2.services;

public interface DataMigrationService {
    Boolean migrateDataFromSqlite3ToMySql() throws Exception;
}
