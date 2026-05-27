package br.edu.iftm.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import br.edu.iftm.config.RabbitMQConfig;

@Component
public class MensagemProducer {

    private final RabbitTemplate rabbitTemplate;

    public MensagemProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviar(String mensagem) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_TOPIC,
                "usuario.notificacao.test",
                mensagem);
    }
}
