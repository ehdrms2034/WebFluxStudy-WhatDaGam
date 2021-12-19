package com.example.webfluxstudy.Model.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("StatisticsMtr")
@Builder
public class StatisticsMtrEntity {

    @Id
    private long id;

    private String url;
    private long count;

    @Column("createdDateTime")
    LocalDateTime createdDateTime;
    @Column("modifiedDateTime")
    LocalDateTime modifiedDateTime;

    String userId;
}
