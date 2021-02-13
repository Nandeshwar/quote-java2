package com.nks.quotejava2.models.mysql;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Info {
    @Id
    private int id;
    
    private String title;
    private String info;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
