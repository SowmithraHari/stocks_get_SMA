package com.medallion.Medallion.config;

import java.util.concurrent.ForkJoinPool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimulationThreadConfig {

 @Value("${simulation.thread.pool.size:0}")
 private int poolSize;

 @Bean("simulationPool")
 public ForkJoinPool simulationPool() {
     int threads = poolSize > 0 ? poolSize : Runtime.getRuntime().availableProcessors();
     return new ForkJoinPool(threads);
 }
}