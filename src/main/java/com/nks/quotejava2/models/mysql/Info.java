package com.nks.quotejava2.models.mysql;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "info")
public class Info implements Serializable, Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String title;
    @Column(length = 10000)
    private String info;
    @Column(name = "created_at", insertable = false, updatable = false)
    private Date createdAt;
    @Column(name = "updated_at", insertable = false, updatable = false)
    private Date updatedAt;

    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL)
    private Collection<InfoLink> infoLink = new ArrayList<>();

    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public void setId(Long id) {
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

    public Collection<InfoLink> getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(Collection<InfoLink> infoLink) {
        this.infoLink = infoLink;
    }
}
