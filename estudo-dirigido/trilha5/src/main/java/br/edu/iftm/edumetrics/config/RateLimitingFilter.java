package br.edu.iftm.edumetrics.config;

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, Deque<Long>> janelasClientes = new ConcurrentHashMap<>();
    private static final int LIMITE_REQUISICOES = 100;
    private static final long JANELA_UM_MINUTO_MS = 60_000;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String ipCliente = request.getRemoteAddr();
        long timestampAtual = System.currentTimeMillis();

        Deque<Long> timestamps = janelasClientes.computeIfAbsent(ipCliente, k -> new LinkedList<>());

        synchronized (timestamps) {
            while (!timestamps.isEmpty() && (timestampAtual - timestamps.peekFirst() > JANELA_UM_MINUTO_MS)) {
                timestamps.pollFirst();
            }

            if (timestamps.size() < LIMITE_REQUISICOES) {
                timestamps.addLast(timestampAtual);
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(429);
                response.getWriter().write("🚫 Limite de requisicoes excedido. Tente novamente em um minuto.");
            }
        }
    }
}
