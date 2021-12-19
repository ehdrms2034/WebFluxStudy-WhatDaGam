package com.example.webfluxstudy.Common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransactionalHelper {

    private final TransactionalOperator transactionalOperator;

    public <T> Mono<T> withRollback(Mono<T> publisher){
        return transactionalOperator.execute(it -> {
            it.setRollbackOnly();
            return publisher;
        }).next();
    }
}
