package br.edu.iftm.edumetrics.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @GetMapping("/autocompletar")
    public ResponseEntity<List<String>> autocompletar(@RequestParam String q) {
        return ResponseEntity.ok(List.of("disciplina1", "disciplina2"));
    }
}
