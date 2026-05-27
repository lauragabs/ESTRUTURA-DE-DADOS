package br.edu.iftm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.messaging.MensagemProducer;

@RestController
@RequestMapping("/test")
public class TestController {

    private final MensagemProducer mensagemProducer;

    public TestController(MensagemProducer mensagemProducer) {
        this.mensagemProducer = mensagemProducer;
    }

    @PostMapping("/mensagem")
    public ResponseEntity<String> enviarMensagem(@RequestBody MensagemDTO dto) {
        mensagemProducer.enviar(dto.getTexto());
        return ResponseEntity.ok("Mensagem publicada: " + dto.getTexto());
    }

    public static class MensagemDTO {
        private String texto;

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }
    }
}
