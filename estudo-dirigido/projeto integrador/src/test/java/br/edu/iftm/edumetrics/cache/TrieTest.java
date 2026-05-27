package br.edu.iftm.edumetrics.cache;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TrieTest {

    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
        trie.inserir("Algoritmos");
        trie.inserir("Arvore");
        trie.inserir("Array");
        trie.inserir("Banco");
    }

    @Test
    void shouldReturnSuggestionsForPrefix() {
        List<String> resultados = trie.autocompletar("Ar", 10);

        assertEquals(2, resultados.size());
        assertTrue(resultados.contains("arvore"));
        assertTrue(resultados.contains("array"));
    }

    @Test
    void shouldReturnEmptyListWhenPrefixDoesNotExist() {
        List<String> resultados = trie.autocompletar("Z", 10);

        assertTrue(resultados.isEmpty());
    }

    @Test
    void shouldRespectResultLimit() {
        trie.inserir("Aplicacao");
        trie.inserir("Aceleracao");

        List<String> resultados = trie.autocompletar("A", 3);

        assertEquals(3, resultados.size());
    }
}
