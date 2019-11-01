package top.zy.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.retry.annotation.Retryable;

@EnableZuulProxy
@SpringBootApplication
@Retryable
@EnableHystrixDashboard
@EnableCircuitBreaker
public class CloudmallGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudmallGatewayApplication.class, args);
    }

}
