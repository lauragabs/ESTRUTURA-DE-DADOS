package br.edu.iftm.edumetrics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.edu.iftm.edumetrics.model.DesempenhoDTO;
import br.edu.iftm.edumetrics.repository.NotaRepository;

@Service
public class DesempenhoService {

    @Autowired
    private NotaRepository notaRepository;

    @Cacheable(value = "desempenho_estudante", key = "#estudanteId")
    public DesempenhoDTO calcularMediasEstudante(Long estudanteId) {
        return notaRepository.gerarMetricasConsolidadas(estudanteId);
    }

    @CacheEvict(value = "desempenho_estudante", key = "#estudanteId")
    public void atualizarNotaEstudante(Long estudanteId, Double novaNota) {
        notaRepository.salvarNovaNota(estudanteId, novaNota);
    }
}
