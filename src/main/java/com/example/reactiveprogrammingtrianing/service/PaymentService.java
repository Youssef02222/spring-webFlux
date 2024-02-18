package com.example.reactiveprogrammingtrianing.service;

import com.example.reactiveprogrammingtrianing.model.CreateAccountRequest;
import com.example.reactiveprogrammingtrianing.model.CreateAccountResponse;
import com.example.reactiveprogrammingtrianing.model.GenericResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Service
public class PaymentService {
    private final WebClient webClient;

    public PaymentService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public Mono<CreateAccountResponse> createAccount(CreateAccountRequest createAccountRequest) {
        ObjectMapper objectMapper = new ObjectMapper();
        return this.webClient
                .post()
                .uri("http://localhost:8888/create-account")
                .bodyValue(createAccountRequest)
                .retrieve()
                .bodyToMono(GenericResponse.class)
                .timeout(Duration.ofSeconds(5))
                .log()
                // .map(genericResponse -> (CreateAccountResponse) genericResponse.getData())   // gives mapping error
                .map(genericResponse -> {
                    if (genericResponse.isSuccess()) {
                        return objectMapper.convertValue(genericResponse.getData(), CreateAccountResponse.class);
                    } else {
                        throw new RuntimeException("runtime exception happen in mapping");
                    }
                })
                .onErrorResume(TimeoutException.class, e -> Mono.just(new CreateAccountResponse("fail email","time out")))
                .onErrorResume(Exception.class,e->Mono.just(new CreateAccountResponse("error in mapping","general error happen")));


    }

}
