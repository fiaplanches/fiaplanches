package br.com.fiaphexa.infra.rest;

import br.com.fiaphexa.infra.gateways.WebhookGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpConfig {

    @Value("${fiap.webhook.url}")
    private String fiapWebhookUrl;
    @Bean
    WebhookGateway webhookGateway() {
        WebClient client = WebClient.builder().baseUrl(fiapWebhookUrl).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();

        return factory.createClient(WebhookGateway.class);

    }

}
