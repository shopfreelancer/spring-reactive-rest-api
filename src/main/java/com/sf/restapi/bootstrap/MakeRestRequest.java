package com.sf.restapi.bootstrap;

import com.sf.restapi.client.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MakeRestRequest implements ApplicationListener<ContextRefreshedEvent>  {

    protected RestClient restClient;

    public MakeRestRequest(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.makeRequest();
    }

    public void makeRequest(){
        restClient.damSearch();
    }
}
