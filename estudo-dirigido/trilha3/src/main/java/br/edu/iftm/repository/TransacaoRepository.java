package br.edu.iftm.repository;

import java.util.List;

import br.edu.iftm.model.Transacao;

public interface TransacaoRepository {
    List<Transacao> findByContaId(Long contaId);
    Transacao save(Transacao transacao);
}
