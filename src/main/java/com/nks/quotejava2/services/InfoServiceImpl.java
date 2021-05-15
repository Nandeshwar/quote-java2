package com.nks.quotejava2.services;

import com.nks.quotejava2.models.mysql.Info;
import com.nks.quotejava2.models.mysql.InfoLink;
import com.nks.quotejava2.repositories.mysql.InfoRepository;
import com.nks.quotejava2.repositories.sqlite3.InfoLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    InfoRepository infoRepository;

    @Autowired
    InfoLinkRepository infoLinkRepository;

    @Override
    public Info findFirstInfoByTitleAndInfo(String title, String info) {
        return infoRepository.findFirstInfoByTitleAndInfo(title, info);
    }

    @Override
    public List<Info> findAllInfo(int page, int size) {
        Page<Info> pageableInfoList = infoRepository.findAll(PageRequest.of(page, size));
        return pageableInfoList.getContent();
    }

    @Override
    public Info findInfoById(Long id) {
        return infoRepository.findFirstInfoById(id);
    }

    @Override
    public Long updateInfo(Long id, Info info) {
        Optional<Info> infoDB = infoRepository.findById(id);
        if (infoDB.isPresent()) {
            LocalDateTime now = LocalDateTime.now();

            ZonedDateTime updatedAtZoneTime = now.atZone(ZoneId.systemDefault());
            ZonedDateTime updatedAtUtc = updatedAtZoneTime.withZoneSameInstant(ZoneId.of("UTC"));


            Info _info = infoDB.get();
            _info.setTitle(info.getTitle());
            _info.setInfo(info.getInfo());

            _info.setUpdatedAt(updatedAtUtc);

            for (InfoLink infoLink : info.getInfoLink()) {
                infoLink.setInfo(_info);
            }

            _info.getInfoLink().addAll(info.getInfoLink());

            Info updatedInfo = infoRepository.save(_info);
            return updatedInfo.getId();
        }
        return 0l;
    }
}
