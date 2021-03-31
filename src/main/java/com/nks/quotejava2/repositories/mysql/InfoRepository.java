package com.nks.quotejava2.repositories.mysql;

import com.nks.quotejava2.models.mysql.Info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InfoRepository extends CrudRepository<Info, Long>, PagingAndSortingRepository<Info, Long> {
    Info findFirstInfoByTitleAndInfo(String title, String Info);

    Info findFirstInfoById(Long id);

}


