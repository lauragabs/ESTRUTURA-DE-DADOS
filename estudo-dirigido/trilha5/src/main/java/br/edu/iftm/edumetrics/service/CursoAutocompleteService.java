package br.edu.iftm.edumetrics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.edumetrics.estruturas.Trie;
import br.edu.iftm.edumetrics.repository.CursoRepository;
import jakarta.annotation.PostConstruct;

@Service
public class CursoAutocompleteService {

    @Autowired
    private CursoRepository cursoRepository;

    private final Trie trieCursos = new Trie();

    @PostConstruct
    public void carregarCursosNaTrie() {
        List<String> nomesCursos = cursoRepository.buscarApenasNomes();
        for (String nome : nomesCursos) {
            trieCursos.inserir(nome);
        }
        System.out.println("⚡ Trie populada com sucesso com " + nomesCursos.size() + " cursos.");
    }

    public List<String> sugerirCursos(String prefixo) {
        return trieCursos.buscarPorPrefixo(prefixo);
    }
}
