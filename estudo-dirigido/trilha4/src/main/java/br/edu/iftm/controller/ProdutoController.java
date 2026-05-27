package br.edu.iftm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @GetMapping("/{id}")
    public ResponseEntity<String> buscarProduto(@PathVariable String id) {
        return ResponseEntity.ok("Produto " + id + " encontrado");
    }
}
