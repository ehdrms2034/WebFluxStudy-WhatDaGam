package com.example.webfluxstudy.Model.DTO;

import com.example.webfluxstudy.Model.Entity.StatisticsMtrEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticsMtrDTO {

    private long id;

    private String url;
    private long count;

    static public StatisticsMtrDTO from(StatisticsMtrEntity s) {
        return StatisticsMtrDTO.builder()
                .id(s.getId())
                .count(s.getCount())
                .url(s.getUrl())
                .build();
    }
}
