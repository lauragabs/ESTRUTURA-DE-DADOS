package br.edu.iftm;

import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SimuladorTreeify {

    public static void main(String[] args) throws Exception {
        executarExperimento();
    }

    public static void executarExperimento() throws Exception {
        int initialCapacity = 128; // garantir que a tabela seja grande o suficiente
        int reps = 1000;

        HashMap<ChaveColisora, String> map = new HashMap<>(initialCapacity);
        List<ChaveColisora> keys = new ArrayList<>();

        // insere menos que TREEIFY_THRESHOLD (8)
        // hashCode fixo (42), com id diferente, formando lista encadeada 
        for (int i = 0; i < 6; i++) {
            ChaveColisora k = new ChaveColisora(i);
            keys.add(k);
            map.put(k, "v" + i);
        }

        System.out.println("Antes da treeification:");
        printFirstBucketElementClass(map);// imprimi se o bucket usa Node (lista) ou TreeNode (árvore)
        warmupGets(map, keys);
        double antes = avgGetTimeNs(map, keys, reps);
        System.out.println(String.format("Tempo médio get antes: %.2f ns", antes));

        // inserir mais chaves para ultrapassar TREEIFY_THRESHOLD (8)
        // hashCode fixo (42), com id diferente, ultrapassando o limite de 8, forçando a transformação em árvore
        for (int i = 6; i < 20; i++) {
            ChaveColisora k = new ChaveColisora(i);
            keys.add(k);
            map.put(k, "v" + i);
        }

        System.out.println("Depois da treeification:");
        printFirstBucketElementClass(map);
        warmupGets(map, keys);
        double depois = avgGetTimeNs(map, keys, reps);
        System.out.println(String.format("Tempo médio get depois: %.2f ns", depois));

        System.out.println(String.format("Melhora: %.2fx", antes / depois));
    }

    // Faz a CPU "acostumar" com o código, pra processar mais rapido depois
    private static void warmupGets(HashMap<ChaveColisora, String> map, List<ChaveColisora> keys) {
        // aquecimento para JVM/JIT
        for (int r = 0; r < 200; r++) {
            for (ChaveColisora k : keys) {
                map.get(k);
            }
        }
    }

    // Calcula o tempo médio de busca (Get) para as chaves fornecidas, repetindo cada busca 'reps' vezes para obter uma média mais estável
    public static double avgGetTimeNs(HashMap<ChaveColisora, String> map, List<ChaveColisora> keys, int reps) {
        long total = 0L;
        for (ChaveColisora k : keys) {
            long t0 = System.nanoTime();
            for (int r = 0; r < reps; r++) {
                map.get(k);
            }
            long t1 = System.nanoTime();
            total += (t1 - t0);
        }
        return total / (double) (keys.size() * reps);
    }

    // Método que usa reflexão para acessar o campo interno do HashMap e imprimir a classe do primeiro elemento encontrado, indicando se é uma lista (Node) ou árvore (TreeNode)
    public static void printFirstBucketElementClass(HashMap<?, ?> map) throws Exception {
        try {
            Field tableField = HashMap.class.getDeclaredField("table"); // table é o array principal do HashMap onde os buckets são armazenados
            tableField.setAccessible(true); //quebra a regra de encapsulamento para acessar o campo privado
            Object[] table = (Object[]) tableField.get(map); // extrai o array de buckets feito do HashMap
            if (table == null) {
                System.out.println("table == null");
                return;
            }
            for (Object entry : table) { 
                if (entry != null) { 
                    System.out.println("Primeiro elemento do bucket: " + entry.getClass().getName()); // Imprime a classe do primeiro elemento encontrado (Node ou TreeNode)
                    return;
                }
            }
            System.out.println("Nenhum bucket não-nulo encontrado.");
        } catch (InaccessibleObjectException e) {
            System.err.println("Erro: não foi possível acessar o campo interno HashMap.table via reflexão.");
            System.err.println("Execute o programa com a opção JVM:");
            System.err.println("  --add-opens=java.base/java.util=ALL-UNNAMED");
            System.err.println("Exemplo no Windows PowerShell:");
            System.err.println("  java --add-opens=java.base/java.util=ALL-UNNAMED -cp target/classes br.edu.iftm.SimuladorTreeify");
            System.exit(1);
        }
    }
}
