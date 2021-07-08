package io.jyotirmay.digitaltwin.cloudapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "io.jyotirmay")
public class DigitaltwinCloudapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitaltwinCloudapiApplication.class, args);
    }

}
