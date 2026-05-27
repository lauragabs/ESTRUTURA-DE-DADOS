package br.edu.iftm.edumetrics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.edumetrics.cache.Trie;
import br.edu.iftm.edumetrics.repository.DisciplinaRepository;
import jakarta.annotation.PostConstruct;

@Service
public class CursoAutocompleteService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    private final Trie trie = new Trie();

    @PostConstruct
    public void init() {
        disciplinaRepository.findAllNomes().forEach(trie::inserir);
    }

    public List<String> sugerir(String prefixo) {
        return trie.autocompletar(prefixo, 10);
    }
}
