package com.aurum.acs_services.shared.domain.core;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    @Bean
    public Queue createVMQueue() {
        return new Queue("create-vm-queue", true);
    }

    @Bean
    public Queue deleteVMQueue() {
        return new Queue("delete-vm-queue", true);
    }
}