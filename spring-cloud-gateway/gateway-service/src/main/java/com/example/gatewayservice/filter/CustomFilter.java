package com.example.gatewayservice.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.Config> {
    public CustomFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        // custom pre-filter. suppose we can extract jwt and perform authentication
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("custom pre filter: request uri - {}", request.getId());
            // custom post-filter. suppose we can call error resppnse handler based on error code.
            return chain
                    .filter(exchange)
                    .then(Mono.fromRunnable(() -> {
                        log.info("Custom POST filter: response code -> {}", response.getStatusCode());
                    }));
        });
    }

    public static class Config {
        // Put the configuration properties
    }
}
