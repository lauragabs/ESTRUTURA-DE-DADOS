package br.edu.iftm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.edu.iftm.model.Transacao;
import br.edu.iftm.repository.TransacaoRepository;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Cacheable(value = "transacoes_conta", key = "#contaId")
    public List<Transacao> buscarPorConta(Long contaId) {
        return transacaoRepository.findByContaId(contaId);
    }

    @CachePut(value = "transacao_unica", key = "#result.id")
    public Transacao salvar(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    @CacheEvict(value = "transacoes_conta", key = "#contaId")
    public void limparCacheConta(Long contaId) {
        // Método complementar de limpeza de cache
    }
}
