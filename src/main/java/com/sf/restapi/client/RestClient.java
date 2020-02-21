package com.sf.restapi.client;

import com.sf.restapi.client.exception.DamLoginException;
import com.sf.restapi.client.model.LoginResponse;
import com.sf.restapi.client.model.SearchResults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class RestClient {

    @Value("${dam.elvis.url}")
    private String DAM_ELVIS_URL;

    @Value("${dam.elvis.user}")
    private String DAM_ELVIS_USER;

    @Value("${dam.elvis.password}")
    private String DAM_ELVIS_PASSWORD;

    protected WebClient webClient;

    public RestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getDamLogin() throws DamLoginException{

        String url = DAM_ELVIS_URL + "/services/apilogin";
        LoginResponse response = webClient
                .post()
                .uri(url)
                .body(BodyInserters.fromFormData("username", DAM_ELVIS_USER)
                        .with("password", DAM_ELVIS_PASSWORD))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response1 -> {
                    return Mono.error(new DamLoginException("Dam Login Response failed! " + response1.toString()));
                })
                .bodyToMono(LoginResponse.class)
                .block();

        return getAuthToken(response);
    }

    public String getAuthToken(LoginResponse loginResponse) throws DamLoginException{
        if(loginResponse.getLoginSuccess() != true){
            throw new DamLoginException("Dam login failed!");
        }
        return loginResponse.getAuthToken();
    }

    public void damSearch() throws DamLoginException{

        String authToken = getDamLogin();


        String url = DAM_ELVIS_URL + "/services/search";
        SearchResults response = webClient
                .post()
                .uri(url)
                //.filter(logRequest())
                .body(BodyInserters.fromFormData("q", "").with("num", "3"))
                .headers(h -> h.setBearerAuth(authToken))

                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response1 -> {
                    return Mono.error(new Exception("Request failed"));
                })
                .bodyToMono(SearchResults.class)
                .block();

        log.info(response.toString());
    }


    // This method returns filter function which will log request data
    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

}
