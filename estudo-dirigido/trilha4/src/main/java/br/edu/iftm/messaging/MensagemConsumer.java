package br.edu.iftm.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import br.edu.iftm.config.RabbitMQConfig;

@Component
public class MensagemConsumer {

    @RabbitListener(queues = RabbitMQConfig.FILA_NOTIFICACOES)
    public void receber(String mensagem) {
        System.out.println("[Consumer] mensagem recebida = '" + mensagem + "'" +
                " thread=" + Thread.currentThread().getName());
    }
}
