package br.edu.iftm.bloco3;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RateLimiterTest {

    @Test
    @DisplayName("permitir deve aplicar limite de 100 requisições por minuto")
    void testPermitirLimite() {
        RateLimiter limiter = new RateLimiter();
        String ip = "192.168.0.1";

        for (int i = 0; i < 100; i++) {
            assertTrue(limiter.permitir(ip));
        }
        assertFalse(limiter.permitir(ip));
    }

    @Test
    @DisplayName("limparExpirados remove IPs cuja janela expirou")
    void testLimparExpirados() throws Exception {
        RateLimiter limiter = new RateLimiter();
        String ip = "10.0.0.1";
        assertTrue(limiter.permitir(ip));

        Field contadoresField = RateLimiter.class.getDeclaredField("contadores");
        contadoresField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Map<String, Object> contadores = (Map<String, Object>) contadoresField.get(limiter);
        Object janela = contadores.get(ip);

        Field inicioField = janela.getClass().getDeclaredField("inicioMs");
        inicioField.setAccessible(true);
        inicioField.setLong(janela, System.currentTimeMillis() - 61_000L);

        limiter.limparExpirados();
        assertFalse(contadores.containsKey(ip));
    }
}
