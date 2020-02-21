package com.sf.restapi.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sf.restapi.client.exception.DamLoginException;
import com.sf.restapi.client.model.LoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
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

    public String getDamLogin() throws Exception{

        String url = DAM_ELVIS_URL + "/services/apilogin";
        LoginResponse response = webClient
                .post()
                .uri(url)
                .body(BodyInserters.fromFormData("username", DAM_ELVIS_USER)
                        .with("password", DAM_ELVIS_PASSWORD))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response1 -> {
                    return Mono.error(new DamLoginException("Dam Login Response failed!"));
                })
                .bodyToMono(LoginResponse.class)
                .block();

        return validateLoginResponse(response);

    }

    public String validateLoginResponse(LoginResponse loginResponse) throws Exception{
        if(loginResponse.getLoginSuccess() == true){
            throw new DamLoginException("Dam login failed!");
        }
        return loginResponse.getAuthToken();
    }

}
