package br.edu.iftm.bloco2;

import java.util.Objects;

public class TabelaHashAberta<K, V> {

    // Marcador de slot deletado (lazy deletion)
    private static final Object DELETADO = new Object();
    private static final double LIMIAR_FATOR_CARGA = 0.70;

    private Object[] chaves;
    private Object[] valores;
    private int tamanho;
    private int capacidade;

    @SuppressWarnings("unchecked")
    public TabelaHashAberta(int capacidade) {
        if (capacidade <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser positiva");
        }
        this.capacidade = capacidade;
        this.chaves = new Object[capacidade];
        this.valores = new Object[capacidade];
        this.tamanho = 0;
    }

    private int sonda(K chave, int i) {
        return (Math.abs(chave.hashCode()) + i) % capacidade;
    }

    public void put(K chave, V valor) {
        if (chave == null) {
            throw new IllegalArgumentException("Chave não pode ser null");
        }
        if ((tamanho + 1) / (double) capacidade >= LIMIAR_FATOR_CARGA) {
            rehash();
        }

        for (int i = 0; i < capacidade; i++) {
            int idx = sonda(chave, i);
            if (chaves[idx] == null || chaves[idx] == DELETADO) {
                chaves[idx] = chave;
                valores[idx] = valor;
                tamanho++;
                return;
            }
            if (Objects.equals(chaves[idx], chave)) {
                valores[idx] = valor;    // atualização
                return;
            }
        }
        throw new IllegalStateException("Tabela cheia");
    }

    public V get(K chave) {
        if (chave == null) {
            throw new IllegalArgumentException("Chave não pode ser null");
        }
        for (int i = 0; i < capacidade; i++) {
            int idx = sonda(chave, i);
            if (chaves[idx] == null) {
                return null;
            }
            if (chaves[idx] == DELETADO) {
                continue;
            }
            if (Objects.equals(chaves[idx], chave)) {
                @SuppressWarnings("unchecked")
                V valor = (V) valores[idx];
                return valor;
            }
        }
        return null;
    }

    private void rehash() {
        Object[] chavesAntigas = chaves;
        Object[] valoresAntigos = valores;
        int capacidadeAntiga = capacidade;

        capacidade *= 2;
        chaves = new Object[capacidade];
        valores = new Object[capacidade];
        tamanho = 0;

        for (int i = 0; i < capacidadeAntiga; i++) {
            Object chaveAntiga = chavesAntigas[i];
            if (chaveAntiga != null && chaveAntiga != DELETADO) {
                @SuppressWarnings("unchecked")
                K chave = (K) chaveAntiga;
                @SuppressWarnings("unchecked")
                V valor = (V) valoresAntigos[i];
                put(chave, valor);
            }
        }
    }

    public double fatorDeCarga() {
        return (double) tamanho / capacidade;
    }

    public int tamanho() {
        return tamanho;
    }

    public int capacidade() {
        return capacidade;
    }
}
