package br.edu.iftm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(2)
public class MapBenchmark {

    private static final int TAMANHO = 100_000;
    private HashMap<String, Integer> hashMap;
    private TreeMap<String, Integer> treeMap;
    private List<String> chaves;
    private ArrayList<Integer> arrayList;
    private LinkedList<Integer> linkedList;
    private final int TAMANHO_LISTA = 10_000;
    private final int INDICE_MEIO = 5_000;

    @Setup(Level.Trial)
    public void setup() {
        hashMap = new HashMap<>(TAMANHO);
        treeMap = new TreeMap<>();
        chaves = new ArrayList<>(TAMANHO);
        Random rand = new Random(42);

        for (int i = 0; i < TAMANHO; i++) {
            String chave = "produto-" + rand.nextInt(TAMANHO * 10);
            chaves.add(chave);
            hashMap.put(chave, i);
            treeMap.put(chave, i);
        }
        arrayList = new ArrayList<>(TAMANHO_LISTA);
        linkedList = new LinkedList<>();

        for (int i = 0; i < TAMANHO_LISTA; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
    }

    @Benchmark
    public Integer hashMapGet(Blackhole bh) {
        String chave = chaves.get(ThreadLocalRandom.current().nextInt(chaves.size()));
        Integer val = hashMap.get(chave);
        bh.consume(val);
        return val;
    }

    @Benchmark
    public Integer treeMapGet(Blackhole bh) {
        String chave = chaves.get(ThreadLocalRandom.current().nextInt(chaves.size()));
        Integer val = treeMap.get(chave);
        bh.consume(val);
        return val;
    }

    @Benchmark
    public NavigableMap<String, Integer> treeMapRange(Blackhole bh) {
        NavigableMap<String, Integer> sub = treeMap.subMap("produto-1000", true, "produto-2000", true);
        bh.consume(sub);
        return sub;
    }

    @Benchmark
    public Integer arrayListGet(Blackhole bh) {
        Integer val = arrayList.get(INDICE_MEIO);
        bh.consume(val);
        return val;
    }

    @Benchmark
    public Integer linkedListGet(Blackhole bh) {
        Integer val = linkedList.get(INDICE_MEIO);
        bh.consume(val);
        return val;
    }
}
