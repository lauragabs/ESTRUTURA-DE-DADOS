package br.edu.iftm.edumetrics.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.edu.iftm.edumetrics.model.DesempenhoDTO;

@Service
public class DesempenhoService {

    @Cacheable("desempenho_estudante")
    public DesempenhoDTO calcularDesempenho(Long alunoId) {
        return new DesempenhoDTO(alunoId, 0.0, 0);
    }
}
