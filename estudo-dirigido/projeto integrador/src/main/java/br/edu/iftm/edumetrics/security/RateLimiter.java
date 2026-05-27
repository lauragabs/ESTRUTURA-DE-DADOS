package br.edu.iftm.edumetrics.security;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {

    private final Map<String, Deque<Long>> janelas = new ConcurrentHashMap<>();
    private final int limite;
    private final long janelaMs;

    public RateLimiter(int limite, long janelaMs) {
        this.limite = limite;
        this.janelaMs = janelaMs;
    }

    public boolean permitir(String chave) {
        long agora = System.currentTimeMillis();
        long limiteJanela = agora - janelaMs;
        Deque<Long> deque = janelas.computeIfAbsent(chave, k -> new ArrayDeque<>());

        synchronized (deque) {
            while (!deque.isEmpty() && deque.peekFirst() < limiteJanela) {
                deque.removeFirst();
            }
            if (deque.size() < limite) {
                deque.addLast(agora);
                return true;
            }
            return false;
        }
    }
}
