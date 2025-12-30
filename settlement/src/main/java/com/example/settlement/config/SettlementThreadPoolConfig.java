package com.example.settlement.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class SettlementThreadPoolConfig {

    @Bean("fundHouseExecutor")
    public ExecutorService fundHouseExecutor() {
        return Executors.newFixedThreadPool(5);
    }

    @Bean("distributorExecutor")
    public ExecutorService distributorExecutor() {
        return Executors.newFixedThreadPool(5);
    }
}
