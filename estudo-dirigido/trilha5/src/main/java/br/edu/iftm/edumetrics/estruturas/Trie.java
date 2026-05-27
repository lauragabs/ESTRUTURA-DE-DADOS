package br.edu.iftm.edumetrics.estruturas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {

    private static class TrieNode {
        Map<Character, TrieNode> filhos = new HashMap<>();
        boolean fimPalavra = false;
    }

    private final TrieNode raiz = new TrieNode();

    public void inserir(String palavra) {
        TrieNode atual = raiz;
        for (char c : palavra.toLowerCase().toCharArray()) {
            atual = atual.filhos.computeIfAbsent(c, k -> new TrieNode());
        }
        atual.fimPalavra = true;
    }

    public List<String> buscarPorPrefixo(String prefixo) {
        return autocompletar(prefixo, Integer.MAX_VALUE);
    }

    public List<String> autocompletar(String prefixo, int limite) {
        List<String> resultados = new ArrayList<>();
        TrieNode atual = raiz;
        String prefixoMinusculo = prefixo.toLowerCase();
        for (char c : prefixoMinusculo.toCharArray()) {
            atual = atual.filhos.get(c);
            if (atual == null) {
                return resultados;
            }
        }
        buscarTodos(atual, prefixoMinusculo, resultados, limite);
        return resultados;
    }

    private void buscarTodos(TrieNode node, String palavraAtual, List<String> resultados, int limite) {
        if (resultados.size() >= limite) {
            return;
        }
        if (node.fimPalavra) {
            resultados.add(palavraAtual);
            if (resultados.size() >= limite) {
                return;
            }
        }
        for (Map.Entry<Character, TrieNode> entrada : node.filhos.entrySet()) {
            buscarTodos(entrada.getValue(), palavraAtual + entrada.getKey(), resultados, limite);
            if (resultados.size() >= limite) {
                return;
            }
        }
    }
}
