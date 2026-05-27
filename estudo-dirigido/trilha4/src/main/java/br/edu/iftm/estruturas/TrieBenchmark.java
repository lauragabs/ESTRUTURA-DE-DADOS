package br.edu.iftm.estruturas;

import java.util.ArrayList;
import java.util.List;

public class TrieBenchmark {

    public static void main(String[] args) {
        Trie trie = new Trie();
        String[] palavras = {"java", "javascript", "jpa", "json", "spring", "spring-boot", "redis", "rabbitmq"};
        for (String palavra : palavras) {
            trie.inserir(palavra);
        }

        System.out.println("autocompletar(\"ja\", 5) -> " + trie.autocompletar("ja", 5));
        System.out.println("autocompletar(\"sp\", 5) -> " + trie.autocompletar("sp", 5));
        System.out.println("autocompletar(\"re\", 5) -> " + trie.autocompletar("re", 5));
        System.out.println("autocompletar(\"xyz\", 5) -> " + trie.autocompletar("xyz", 5));

        System.out.println();
        System.out.println("Medindo tempo de busca para prefixo de 2 caracteres");

        long tempo10k = medirTempo(10_000, "ab", 5);
        long tempo100k = medirTempo(100_000, "ab", 5);

        System.out.println("10.000 palavras: " + tempo10k + " ns");
        System.out.println("100.000 palavras: " + tempo100k + " ns");
    }

    private static long medirTempo(int tamanho, String prefixo, int limite) {
        Trie trie = new Trie();
        List<String> palavras = gerarPalavras(tamanho);
        for (String palavra : palavras) {
            trie.inserir(palavra);
        }
        trie.inserir(prefixo + "-extra-1");
        trie.inserir(prefixo + "-extra-2");

        long antes = System.nanoTime();
        List<String> resultados = trie.autocompletar(prefixo, limite);
        long depois = System.nanoTime();

        System.out.println("Prefixo '" + prefixo + "' em " + tamanho + " palavras -> " + resultados);
        return depois - antes;
    }

    private static List<String> gerarPalavras(int tamanho) {
        List<String> palavras = new ArrayList<>(tamanho);
        for (int i = 0; i < tamanho; i++) {
            palavras.add("palavra-" + i + "-" + (char) ('a' + (i % 26)));
        }
        return palavras;
    }
}
