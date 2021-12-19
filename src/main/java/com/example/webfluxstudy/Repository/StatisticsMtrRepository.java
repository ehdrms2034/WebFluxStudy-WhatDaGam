package com.example.webfluxstudy.Repository;

import com.example.webfluxstudy.Model.Entity.StatisticsMtrEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsMtrRepository extends ReactiveCrudRepository<StatisticsMtrEntity, Long> {
}
