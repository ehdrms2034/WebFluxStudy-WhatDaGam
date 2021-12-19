package com.example.webfluxstudy.Model.DTO;

import com.example.webfluxstudy.Model.Entity.StatisticsDtlEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StatisticsDtlDTO {
    private long id;
    private long mtrId;
    private LocalDateTime createdDateTime;

    static public StatisticsDtlDTO from(StatisticsDtlEntity e) {

        return StatisticsDtlDTO.builder()
                .id(e.getId())
                .mtrId(e.getMtrId())
                .createdDateTime(e.getCreatedDateTime())
                .build();

    }
}
