package br.edu.iftm;

public class ChaveColisora {
    private final int id;

    public ChaveColisora(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        // valor fixo para provocar colisão 
        return 42;
    }

    @Override
    //método equals, para armazenar corretamente as chaves na tabela hash e evitar que sejam consideradas iguais apenas por causa do hashCode fixo
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChaveColisora)) return false;
        ChaveColisora that = (ChaveColisora) o;
        return id == that.id;
    }

    @Override
    public String toString() {
        return "ChaveColisora{" + "id=" + id + '}';
    }
}