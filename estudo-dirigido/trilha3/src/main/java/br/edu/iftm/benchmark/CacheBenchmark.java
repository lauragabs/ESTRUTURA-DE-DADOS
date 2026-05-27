package br.edu.iftm.benchmark;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import br.edu.iftm.cache.LRUCache;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 10, time = 1)
@Fork(2)
public class CacheBenchmark {

    private static final int CACHE_SIZE = 10_000;
    private LRUCache<Long, String> cache;
    private List<Long> ids;

    @Setup
    public void setup() {
        cache = new LRUCache<>(CACHE_SIZE);
        ids = LongStream.rangeClosed(1, CACHE_SIZE).boxed().collect(Collectors.toList());
        for (Long id : ids) {
            cache.put(id, "valor-" + id);
        }
    }

    @Benchmark
    public String buscarComCache(Blackhole blackhole) {
        String valor = cache.get(1L);
        blackhole.consume(valor);
        return valor;
    }

    @Benchmark
    public String buscarSemCache(Blackhole blackhole) throws InterruptedException {
        Thread.sleep(5);
        String valor = "banco-1";
        blackhole.consume(valor);
        return valor;
    }
}
