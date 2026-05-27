package br.edu.iftm.ratelimit;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter {

    private static final int LIMITE = 10;
    private static final long JANELA_MS = 60_000L;

    private final Map<String, Deque<Long>> contadores = new ConcurrentHashMap<>();

    public boolean permitir(String chave) {
        long agora = System.currentTimeMillis();
        long inicioJanela = agora - JANELA_MS;

        Deque<Long> timestamps = contadores.computeIfAbsent(chave, key -> new ArrayDeque<>());

        synchronized (timestamps) {
            while (!timestamps.isEmpty() && timestamps.peekFirst() < inicioJanela) {
                timestamps.removeFirst();
            }
            if (timestamps.size() < LIMITE) {
                timestamps.addLast(agora);
                return true;
            }
            return false;
        }
    }
}
