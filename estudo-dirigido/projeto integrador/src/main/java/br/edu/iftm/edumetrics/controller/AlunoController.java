package br.edu.iftm.edumetrics.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @GetMapping("/{id}/desempenho")
    public ResponseEntity<String> buscarDesempenho(@PathVariable Long id) {
        return ResponseEntity.ok("Desempenho do aluno " + id);
    }
}
