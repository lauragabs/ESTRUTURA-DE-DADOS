package br.edu.iftm.edumetrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.edumetrics.messaging.RelatorioProducer;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioProducer relatorioProducer;

    public void solicitarRelatorio(Long turmaId, String email) {
        relatorioProducer.solicitarRelatorioTurma(turmaId, email);
    }
}
