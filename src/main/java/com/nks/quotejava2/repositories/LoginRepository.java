package com.nks.quotejava2.repositories;

import com.nks.quotejava2.model.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {
}
