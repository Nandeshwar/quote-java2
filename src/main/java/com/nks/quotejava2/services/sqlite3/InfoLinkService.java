package com.nks.quotejava2.services.sqlite3;

import com.nks.quotejava2.models.sqlite3.InfoLink;

public interface InfoLinkService {
    Iterable<InfoLink> findByInfoLink(int infoLink);
}
