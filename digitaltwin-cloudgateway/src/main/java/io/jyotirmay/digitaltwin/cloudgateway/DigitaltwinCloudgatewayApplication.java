package io.jyotirmay.digitaltwin.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DigitaltwinCloudgatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitaltwinCloudgatewayApplication.class, args);
    }


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(p -> p.path("/v1/cloud/**")
                .uri("http://localhost:8081"))
                .build();
    }

}
