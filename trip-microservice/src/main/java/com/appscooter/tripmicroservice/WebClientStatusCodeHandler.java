package com.appscooter.tripmicroservice;

import com.appscooter.tripmicroservice.services.exception.NotFoundException;
import com.appscooter.tripmicroservice.services.exception.WebClientConflict;
import com.appscooter.tripmicroservice.services.exception.WebClientNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

public class WebClientStatusCodeHandler {

    public static Mono<ClientResponse> exchangeFilterResponseProcessor(ClientResponse response) {
        HttpStatus status = (HttpStatus) response.statusCode();
        if (HttpStatus.NOT_FOUND.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new WebClientNotFound(body)));
        }
        if (HttpStatus.CONFLICT.equals(status)) {
            return response.bodyToMono(String.class)
                    .flatMap(body -> Mono.error(new WebClientConflict(body)));
        }
        return Mono.just(response);
    }
}
