package com.example.webfluxstudy.Model.Entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("StatisticsDtl")
@Builder
public class StatisticsDtlEntity {

    @Id

    private long id;

    @Column(value = "mtrId")
    @MappedCollection(idColumn = "statistics_mtr_id")
    private long mtrId;

    @Column("createdDateTime")
    private LocalDateTime createdDateTime;
}
