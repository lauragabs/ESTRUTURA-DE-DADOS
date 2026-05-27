package br.edu.iftm.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String FILA_NOTIFICACOES = "fila.notificacoes";
    public static final String EXCHANGE_TOPIC = "exchange.topicos";
    public static final String ROUTING_KEY = "usuario.notificacao.#";

    @Bean
    public Queue minhaFila() {
        return QueueBuilder.durable(FILA_NOTIFICACOES)
                .withArgument("x-dead-letter-exchange", "dlx.exchange")
                .build();
    }

    @Bean
    public TopicExchange minhaExchange() {
        return new TopicExchange(EXCHANGE_TOPIC);
    }

    @Bean
    public Binding meuBinding(Queue minhaFila, TopicExchange minhaExchange) {
        return BindingBuilder.bind(minhaFila).to(minhaExchange).with(ROUTING_KEY);
    }
}
