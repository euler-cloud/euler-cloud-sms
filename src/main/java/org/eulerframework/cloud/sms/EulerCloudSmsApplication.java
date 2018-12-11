package org.eulerframework.cloud.sms;

import org.eulerframework.cloud.EnableEulerCloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableEulerCloud
public class EulerCloudSmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EulerCloudSmsApplication.class, args);
    }
}
