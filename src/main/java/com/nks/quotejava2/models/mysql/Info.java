package com.nks.quotejava2.models.mysql;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Info {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    private String title;
    private String info;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "info")
    private List<InfoLink> infoLink = new ArrayList<>();

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<InfoLink> getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(List<InfoLink> infoLink) {
        this.infoLink = infoLink;
    }
}
