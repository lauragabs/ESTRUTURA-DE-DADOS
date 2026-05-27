package br.edu.iftm.edumetrics.repository;

import br.edu.iftm.edumetrics.model.DesempenhoDTO;

public interface NotaRepository {
    DesempenhoDTO gerarMetricasConsolidadas(Long estudanteId);
    void salvarNovaNota(Long estudanteId, Double novaNota);
}
