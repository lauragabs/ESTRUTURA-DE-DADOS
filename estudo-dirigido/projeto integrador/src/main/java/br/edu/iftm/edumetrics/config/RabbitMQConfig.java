package br.edu.iftm.edumetrics.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "relatorios.exchange";
    public static final String QUEUE = "relatorios.pdf";
    public static final String ROUTING_KEY = "relatorio.pdf";

    @Bean
    public Queue relatorioQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public TopicExchange relatorioExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding relatorioBinding(Queue relatorioQueue, TopicExchange relatorioExchange) {
        return BindingBuilder.bind(relatorioQueue).to(relatorioExchange).with(ROUTING_KEY);
    }
}
