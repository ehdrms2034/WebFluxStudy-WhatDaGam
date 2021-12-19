package com.example.webfluxstudy.Repository;

import com.example.webfluxstudy.Common.TransactionalHelper;
import com.example.webfluxstudy.Model.BusinessException;
import com.example.webfluxstudy.Model.Entity.StatisticsDtlEntity;
import com.example.webfluxstudy.Model.ErrCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class StatisticsDtlRepositoryTest {

    @Autowired
    private StatisticsDtlRepository statisticsDtlRepository;

    @Autowired
    private TransactionalHelper transactionalHelper;

    @Test
    public void save() {

        StatisticsDtlEntity dtlEntity = StatisticsDtlEntity.builder()
                .mtrId(1)
                .createdDateTime(LocalDateTime.now())
                .build();

        Mono<StatisticsDtlEntity> newDtl = statisticsDtlRepository.save(dtlEntity);

        Mono<StatisticsDtlEntity> transactionMono = transactionalHelper.withRollback(newDtl);

        StepVerifier.create(transactionMono)
                .assertNext(it -> {
                    Assertions.assertTrue(it.getId() > 0);
                    Assertions.assertEquals(dtlEntity.getMtrId(), it.getMtrId());
                    Assertions.assertEquals(dtlEntity.getCreatedDateTime(), it.getCreatedDateTime());
                })
                .verifyComplete();
    }

    @Test
    public void findById() {

        LocalDateTime now = LocalDateTime.now();

        StatisticsDtlEntity dtlEntity = StatisticsDtlEntity.builder()
                .mtrId(1)
                .createdDateTime(now)
                .build();

        Mono<StatisticsDtlEntity> searchedDtl =
                statisticsDtlRepository.save(dtlEntity)
                        .doOnNext(savedDtl -> statisticsDtlRepository.findById(savedDtl.getId()));

        Mono<StatisticsDtlEntity> transactionMono = transactionalHelper.withRollback(searchedDtl);

        StepVerifier.create(transactionMono)
                .assertNext(it -> {
                    Assertions.assertEquals(dtlEntity.getMtrId(), it.getMtrId());
                    Assertions.assertEquals(now , it.getCreatedDateTime());
                })
                .verifyComplete();
    }
}
