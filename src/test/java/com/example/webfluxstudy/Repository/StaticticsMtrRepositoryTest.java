package com.example.webfluxstudy.Repository;

import com.example.webfluxstudy.Common.TransactionalHelper;
import com.example.webfluxstudy.Model.Entity.StatisticsMtrEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

@SpringBootTest
public class StaticticsMtrRepositoryTest {

    @Autowired
    private StatisticsMtrRepository statisticsMtrRepository;

    @Autowired
    private TransactionalHelper transactionalHelper;

    @Test
    public void save() {

        LocalDateTime now = LocalDateTime.now();

        StatisticsMtrEntity statisticsMtrEntity = StatisticsMtrEntity.builder()
                .url("http://www.example.com")
                .createdDateTime(now)
                .modifiedDateTime(now)
                .count(0)
                .userId(null)
                .build();

        Mono<StatisticsMtrEntity> fluxStatisticsMtrEntity =
                transactionalHelper.withRollback(statisticsMtrRepository.save(statisticsMtrEntity));

        StepVerifier.create(fluxStatisticsMtrEntity)
                .assertNext(it -> {
                    Assertions.assertEquals(statisticsMtrEntity.getUrl(), it.getUrl());
                    Assertions.assertEquals(0, it.getCount());
                    Assertions.assertEquals(now, it.getCreatedDateTime());
                    Assertions.assertEquals(now, it.getModifiedDateTime());
                }).verifyComplete();
    }

    @Test
    public void findById() {
        LocalDateTime now = LocalDateTime.now();

        StatisticsMtrEntity statisticsMtrEntity = StatisticsMtrEntity.builder()
                .url("http://www.example.com")
                .createdDateTime(now)
                .modifiedDateTime(now)
                .count(0)
                .userId(null)
                .build();

        Mono<StatisticsMtrEntity> mono = statisticsMtrRepository
                .save(statisticsMtrEntity)
                .doOnNext(it -> statisticsMtrRepository.findById(it.getId()));

        Mono<StatisticsMtrEntity> fluxStatisticsMtrEntity =
                transactionalHelper.withRollback(mono);

        StepVerifier.create(fluxStatisticsMtrEntity)
                .assertNext(it -> Assertions.assertTrue(it.getId() > 0))
                .verifyComplete();
    }
}
