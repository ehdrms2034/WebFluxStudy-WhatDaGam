package com.example.webfluxstudy.Repository;

import com.example.webfluxstudy.Model.Entity.StatisticsDtlEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface StatisticsDtlRepository extends ReactiveCrudRepository<StatisticsDtlEntity, Long> {

    Mono<Boolean> existsByMtrId(long mtrId);
}
