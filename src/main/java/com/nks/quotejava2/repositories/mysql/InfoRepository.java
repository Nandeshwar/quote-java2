package com.nks.quotejava2.repositories.mysql;

import com.nks.quotejava2.models.mysql.Info;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface InfoRepository extends CrudRepository<Info, BigInteger> {
}
