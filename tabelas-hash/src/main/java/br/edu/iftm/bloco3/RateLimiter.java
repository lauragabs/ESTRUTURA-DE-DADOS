package br.edu.iftm.bloco3;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private static final int LIMITE = 100;
    private static final long JANELA_MS = 60_000L;

    private final Map<String, Janela> contadores = new HashMap<>();

    public boolean permitir(String ip) {
        long agora = System.currentTimeMillis();
        Janela janela = contadores.get(ip);

        if (janela == null || agora - janela.inicioMs >= JANELA_MS) {
            contadores.put(ip, new Janela(agora, 1));
            return true;
        }

        if (janela.contador < LIMITE) {
            janela.contador++;
            return true;
        }

        return false;
    }

    public void limparExpirados() {
        long agora = System.currentTimeMillis();
        contadores.entrySet().removeIf(entry -> agora - entry.getValue().inicioMs >= JANELA_MS);
    }

    private static class Janela {
        long inicioMs;
        int contador;

        Janela(long inicioMs, int contador) {
            this.inicioMs = inicioMs;
            this.contador = contador;
        }
    }
}
