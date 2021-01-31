package com.nks.quotejava2.repositories.sqlite3;

import com.nks.quotejava2.model.sqlite3.InfoSqlite;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface InfoSqliteRepository extends CrudRepository<InfoSqlite, BigInteger> {
}
