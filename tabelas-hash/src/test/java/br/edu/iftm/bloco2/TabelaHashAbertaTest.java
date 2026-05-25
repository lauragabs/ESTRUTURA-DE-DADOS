package br.edu.iftm.bloco2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TabelaHashAbertaTest {

    @Test
    @DisplayName("rehash deve ser disparado quando fator de carga >= 0.70")
    void testRehashDisparadoQuandoFatorDeCargaAlcancaLimiar() {
        TabelaHashAberta<String, Integer> tabela = new TabelaHashAberta<>(10);
        for (int i = 1; i <= 6; i++) {
            tabela.put("chave" + i, i);
        }
        assertEquals(10, tabela.capacidade(), "Capacidade não deve mudar antes do limiar");

        tabela.put("chave7", 7);

        assertEquals(20, tabela.capacidade(), "Capacidade deve dobrar após rehash");
        assertEquals(7, tabela.tamanho(), "Tamanho deve ser 7 após rehash e inserção");
        assertEquals(0.35, tabela.fatorDeCarga(), 1e-6, "Fator de carga deve ser recalculado após rehash");
    }

    @Test
    @DisplayName("todos os pares são migrados corretamente após rehash")
    void testTodosParesMigradosAposRehash() {
        TabelaHashAberta<String, Integer> tabela = new TabelaHashAberta<>(10);
        for (int i = 1; i <= 7; i++) {
            tabela.put("chave" + i, i * 10);
        }

        for (int i = 1; i <= 7; i++) {
            assertEquals(i * 10, tabela.get("chave" + i), "Valor deve ser mantido após rehash");
        }
        assertEquals(7, tabela.tamanho(), "Tamanho deve ser preservado após rehash");
    }
}
