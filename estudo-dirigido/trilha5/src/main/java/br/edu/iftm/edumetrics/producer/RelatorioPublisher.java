package br.edu.iftm.edumetrics.producer;

import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RelatorioPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "edu.direct.exchange";
    private static final String ROUTING_KEY = "relatorio.solicitado";

    public void solicitarRelatorioTurma(Long turmaId, String emailSolicitante) {
        Map<String, Object> mensagem = Map.of(
                "turmaId", turmaId,
                "email", emailSolicitante,
                "timestamp", System.currentTimeMillis()
        );

        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, mensagem);
        System.out.println("📬 Solicitação de relatório enviada para a fila de background.");
    }
}
