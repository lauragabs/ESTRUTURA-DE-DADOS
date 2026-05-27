package br.edu.iftm.repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import br.edu.iftm.model.Transacao;

@Repository
public class InMemoryTransacaoRepository implements TransacaoRepository {

    private final Map<Long, Transacao> armazenamento = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public List<Transacao> findByContaId(Long contaId) {
        if (contaId == null) {
            return Collections.emptyList();
        }
        return armazenamento.values().stream()
                .filter(transacao -> contaId.equals(transacao.getContaId()))
                .collect(Collectors.toList());
    }

    @Override
    public Transacao save(Transacao transacao) {
        if (transacao.getId() == null) {
            transacao.setId(sequence.getAndIncrement());
        }
        armazenamento.put(transacao.getId(), transacao);
        return transacao;
    }
}
