package br.edu.iftm.edumetrics.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    @PostMapping
    public ResponseEntity<String> solicitarRelatorio(@RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok("Relatório solicitado");
    }
}
