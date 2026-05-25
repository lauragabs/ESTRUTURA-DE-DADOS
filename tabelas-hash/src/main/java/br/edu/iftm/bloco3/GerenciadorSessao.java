package br.edu.iftm.bloco3;

import br.edu.iftm.bloco1.TabelaHashEncadeada;

public class GerenciadorSessao {

    private final TabelaHashEncadeada<String, Usuario> sessoes;

    public GerenciadorSessao() {
        // Usar capacidade inicial moderada para manter o fator de carga baixo
        // e evitar muitas colisões em uma tabela de sessões em memória.
        this.sessoes = new TabelaHashEncadeada<>(64);
    }

    public void registrar(String token, Usuario usuario) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("Token não pode ser nulo ou vazio");
        }
        sessoes.put(token, usuario);
    }

    public Usuario buscar(String token) {
        if (token == null || token.isBlank()) {
            return null;
        }
        return sessoes.get(token);
    }

    public boolean invalidar(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        return sessoes.remove(token);
    }

    public boolean eValido(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        return sessoes.get(token) != null;
    }
}
