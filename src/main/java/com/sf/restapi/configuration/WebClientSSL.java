package com.sf.restapi.configuration;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;
import reactor.netty.tcp.TcpClient;

import javax.net.ssl.SSLException;

@Configuration
public class WebClientSSL {
    @Bean
    public WebClient createWebClient() throws SSLException {

        SslProvider sslProvider = SslProvider.builder().sslContext(
                SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
        )
                .defaultConfiguration(SslProvider.DefaultConfigurationType.NONE).build();

        TcpClient tcpClient = TcpClient.create().secure(sslProvider);
        HttpClient httpClient = HttpClient.from(tcpClient);
        ClientHttpConnector httpConnector = new ReactorClientHttpConnector(httpClient);

        WebClient webClient = WebClient.builder().clientConnector(httpConnector).build();
        return webClient;
    }
}
