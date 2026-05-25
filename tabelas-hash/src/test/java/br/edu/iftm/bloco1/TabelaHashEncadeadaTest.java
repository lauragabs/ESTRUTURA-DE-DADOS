package br.edu.iftm.bloco1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TabelaHashEncadeadaTest {

    private TabelaHashEncadeada<String, Integer> tabela;

    @BeforeEach
    void setUp() {
        tabela = new TabelaHashEncadeada<>(4);
    }

    @Test
    @DisplayName("put e get: chave inserida deve ser recuperada")
    void testPutGet() {
        tabela.put("produto-42", 100);
        assertEquals(1, tabela.tamanho(), "Tamanho deve ser 1 após a inserção");
        assertEquals(100, tabela.get("produto-42"), "get deve retornar o valor associado");
    }

    @Test
    @DisplayName("put com atualização: tamanho não deve aumentar")
    void testAtualizacaoNaoIncrementaTamanho() {
        tabela.put("produto-42", 100);
        tabela.put("produto-42", 200);
        assertEquals(1, tabela.tamanho(), "Tamanho deve permanecer 1 após atualização");
        assertEquals(200, tabela.get("produto-42"), "get deve retornar o valor mais recente");
    }

    @Test
    @DisplayName("colisão: duas chaves no mesmo bucket devem ser recuperadas corretamente")
    void testColisao() {
        tabela.put("Aa", 1);
        tabela.put("BB", 2);

        assertEquals(1, tabela.get("Aa"), "get deve recuperar corretamente o primeiro valor");
        assertEquals(2, tabela.get("BB"), "get deve recuperar corretamente o segundo valor");
        assertEquals(2, tabela.tamanho(), "Tamanho deve ser 2 quando duas entradas com colisão são inseridas");
    }
}
