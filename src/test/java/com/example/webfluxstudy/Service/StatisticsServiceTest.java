package com.example.webfluxstudy.Service;

import com.example.webfluxstudy.Model.DTO.StatisticsMtrDTO;
import com.example.webfluxstudy.Model.Entity.StatisticsDtlEntity;
import com.example.webfluxstudy.Model.Entity.StatisticsMtrEntity;
import com.example.webfluxstudy.Repository.StatisticsDtlRepository;
import com.example.webfluxstudy.Repository.StatisticsMtrRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StatisticsServiceTest {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private StatisticsMtrRepository statisticsMtrRepository;

    @Mock
    private StatisticsDtlRepository statisticsDtlRepository;

    @Test
    public void addNewStatistics() {

        LocalDateTime now = LocalDateTime.now();

        StatisticsMtrEntity entity = StatisticsMtrEntity.builder()
                .id(1)
                .count(1)
                .url("www.example.com")
                .createdDateTime(now)
                .modifiedDateTime(now)
                .build();

        when(statisticsMtrRepository.save(any())).thenReturn(Mono.just(entity));

        Mono<StatisticsMtrDTO> mono = statisticsService.addNewStatistics(1, "http://www.example.com");

        StepVerifier.create(mono)
                .expectNext(StatisticsMtrDTO.from(entity))
                .verifyComplete();

        verify(statisticsMtrRepository, times(1)).save(any());
    }

    @Test
    public void testLookStatistics() {

        LocalDateTime now = LocalDateTime.now();

        StatisticsMtrEntity entity = StatisticsMtrEntity.builder()
                .id(1)
                .count(0)
                .url("www.example.com")
                .createdDateTime(now)
                .modifiedDateTime(now)
                .build();

        StatisticsMtrDTO dto = StatisticsMtrDTO.from(entity);
        dto.setCount(dto.getCount() + 1);

        StatisticsDtlEntity dtlEntity = StatisticsDtlEntity.builder()
                .id(1)
                .mtrId(1)
                .build();

        when(statisticsDtlRepository.existsByMtrId(1L)).thenReturn(Mono.just(false));
        when(statisticsDtlRepository.save(any())).thenReturn(Mono.just(dtlEntity));
        when(statisticsMtrRepository.findById(1L)).thenReturn(Mono.just(entity));
        when(statisticsMtrRepository.save(entity)).thenReturn(Mono.just(entity));

        StepVerifier.create(statisticsService.lookStatistics(entity.getId()))
                .expectNext(dto)
                .verifyComplete();

    }


}