package com.nks.quotejava2.repositories.sqlite3;

import com.nks.quotejava2.models.sqlite3.InfoLink;
import org.springframework.data.repository.CrudRepository;


public interface InfoLinkRepository extends CrudRepository<InfoLink, Integer> {
    Iterable<InfoLink> findByLinkId(int linkId);
}
