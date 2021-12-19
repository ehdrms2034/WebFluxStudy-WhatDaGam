package com.example.webfluxstudy.Service;

import com.example.webfluxstudy.Model.BusinessException;
import com.example.webfluxstudy.Model.DTO.StatisticsMtrDTO;
import com.example.webfluxstudy.Model.Entity.StatisticsDtlEntity;
import com.example.webfluxstudy.Model.Entity.StatisticsMtrEntity;
import com.example.webfluxstudy.Repository.StatisticsDtlRepository;
import com.example.webfluxstudy.Repository.StatisticsMtrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsMtrRepository statisticsMtrRepository;
    private final StatisticsDtlRepository statisticsDtlRepository;

    Mono<StatisticsMtrDTO> addNewStatistics(long id, String url) {

        LocalDateTime now = LocalDateTime.now();

        StatisticsMtrEntity statisticsMtrEntity = StatisticsMtrEntity.builder()
                .count(0)
                .url(url)
                .createdDateTime(now)
                .modifiedDateTime(now)
                .build();

        @SuppressWarnings("UnnecessaryLocalVariable")
        Mono<StatisticsMtrDTO> statisticsMtrDTOMono =
                statisticsMtrRepository.save(statisticsMtrEntity)
                        .map(StatisticsMtrDTO::from);

        return statisticsMtrDTOMono;
    }

    @Transactional
    Mono<StatisticsMtrDTO> lookStatistics(long id) {

        StatisticsDtlEntity newDtl = StatisticsDtlEntity.builder()
                .mtrId(id)
                .createdDateTime(LocalDateTime.now())
                .build();

        Mono<StatisticsMtrDTO> mono = statisticsDtlRepository
                .existsByMtrId(id)
                .map(isExist -> {
                    if (!isExist) {
                        return Mono.error(new BusinessException());
                    }
                    return Mono.empty();
                })
                .then(statisticsDtlRepository.save(newDtl))
                .then(statisticsMtrRepository.findById(id))
                .doOnNext(it -> {
                    it.setCount(it.getCount() + 1);
                    statisticsMtrRepository.save(it);
                }).map(StatisticsMtrDTO::from);

        return mono;

    }

}
