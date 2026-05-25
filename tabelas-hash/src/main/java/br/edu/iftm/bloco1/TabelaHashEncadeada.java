package br.edu.iftm.bloco1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class TabelaHashEncadeada<K, V> {

    private static final int CAPACIDADE_PADRAO = 16;

    private final LinkedList<Entrada<K, V>>[] buckets;
    private int tamanho;

    @SuppressWarnings("unchecked")
    public TabelaHashEncadeada(int capacidade) {
        buckets = new LinkedList[capacidade];
        for (int i = 0; i < capacidade; i++) {
            buckets[i] = new LinkedList<>();
        }
        this.tamanho = 0;
    }

    public TabelaHashEncadeada() {
        this(CAPACIDADE_PADRAO);
    }

    private int indiceBucket(K k) {
        if (k == null) {
            throw new IllegalArgumentException("Chave não pode ser null");
        }
        int hash = k.hashCode();
        int indice = Math.abs(hash) % buckets.length;
        if (indice < 0) {
            indice += buckets.length;
        }
        return indice;
    }

    public void put(K chave, V valor) {
        int indice = indiceBucket(chave);
        LinkedList<Entrada<K, V>> bucket = buckets[indice];
        for (Entrada<K, V> entrada : bucket) {
            if (Objects.equals(entrada.chave, chave)) {
                entrada.valor = valor;
                return;
            }
        }
        bucket.add(new Entrada<>(chave, valor));
        tamanho++;
    }

    public V get(K chave) {
        int indice = indiceBucket(chave);
        for (Entrada<K, V> entrada : buckets[indice]) {
            if (Objects.equals(entrada.chave, chave)) {
                return entrada.valor;
            }
        }
        return null;
    }

    public boolean remove(K chave) {
        int indice = indiceBucket(chave);
        Iterator<Entrada<K, V>> iter = buckets[indice].iterator();
        while (iter.hasNext()) {
            Entrada<K, V> entrada = iter.next();
            if (Objects.equals(entrada.chave, chave)) {
                iter.remove();
                tamanho--;
                return true;
            }
        }
        return false;
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    private static class Entrada<K, V> {
        final K chave;
        V valor;

        Entrada(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }
    }
}
