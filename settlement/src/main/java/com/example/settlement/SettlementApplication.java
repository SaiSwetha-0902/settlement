package com.example.settlement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.sushmithashiva04ops.centraleventpublisher.config.OutboxProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
@SpringBootApplication(scanBasePackages = {
    "com.example.settlement",
    "io.github.sushmithashiva04ops.centraleventpublisher"
})
@EnableConfigurationProperties(OutboxProperties.class)
public class SettlementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettlementApplication.class, args);
	}

}
