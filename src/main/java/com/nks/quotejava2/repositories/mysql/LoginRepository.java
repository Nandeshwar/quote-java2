package com.nks.quotejava2.repositories.mysql;

import com.nks.quotejava2.models.mysql.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Long> {
    Login findFirstByUserAndPassword(String username, String password);
}
