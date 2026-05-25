package br.edu.iftm.bloco3;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IndiceInvertidoTest {

    @Test
    @DisplayName("indexar e buscar palavras deve armazenar e recuperar IDs corretamente")
    void testIndexarEBuscarPalavras() {
        IndiceInvertido indice = new IndiceInvertido();
        indice.indexar(1L, "cadeira ergonômica");
        indice.indexar(2L, "mesa de escritório");
        indice.indexar(3L, "cadeira gamer");

        assertEquals(List.of(1L, 3L), indice.buscar("cadeira"));
        assertEquals(List.of(1L), indice.buscar("ergonomica"));
        assertEquals(List.of(2L), indice.buscar("escritorio"));
        assertEquals(List.of(3L), indice.buscar("gamer"));
        assertTrue(indice.totalPalavrasIndexadas() >= 5);
    }

    @Test
    @DisplayName("buscarMultiplas deve retornar interseção de IDs para consulta com várias palavras")
    void testBuscarMultiplas() {
        IndiceInvertido indice = new IndiceInvertido();
        indice.indexar(1L, "cadeira ergonômica");
        indice.indexar(2L, "mesa de escritório");
        indice.indexar(3L, "cadeira gamer");

        assertEquals(List.of(3L), indice.buscarMultiplas("cadeira gamer"));
        assertEquals(List.of(), indice.buscarMultiplas("cadeira escritorio"));
    }
}
