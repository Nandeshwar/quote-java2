package com.nks.quotejava2.models.sqlite3;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "info")
public class InfoSqlite {
    @Id
    private long id;

    private String title;
    private String info;
    @Column(name = "created_at")
    private String createdAt;
    @Column(name = "updated_at")
    private String updatedAt;

    @Transient
    private List<InfoLink> infoLinks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<InfoLink> getInfoLinks() {
        return infoLinks;
    }

    public void setInfoLinks(List<InfoLink> infoLinks) {
        this.infoLinks = infoLinks;
    }
}
