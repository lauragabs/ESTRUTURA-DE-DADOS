package br.edu.iftm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.model.Transacao;
import br.edu.iftm.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping("/conta/{contaId}")
    public ResponseEntity<List<Transacao>> buscarPorConta(@PathVariable Long contaId) {
        return ResponseEntity.ok(transacaoService.buscarPorConta(contaId));
    }

    @PostMapping
    public ResponseEntity<Transacao> salvar(@RequestBody Transacao transacao) {
        return ResponseEntity.ok(transacaoService.salvar(transacao));
    }

    @DeleteMapping("/conta/{contaId}/cache")
    public ResponseEntity<Void> limparCacheConta(@PathVariable Long contaId) {
        transacaoService.limparCacheConta(contaId);
        return ResponseEntity.noContent().build();
    }
}
