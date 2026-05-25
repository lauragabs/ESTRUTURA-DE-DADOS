package br.edu.iftm.bloco3;

import java.util.Objects;

public class Usuario {
    private final long id;
    private final String perfil;

    public Usuario(long id, String perfil) {
        this.id = id;
        this.perfil = perfil;
    }

    public long getId() {
        return id;
    }

    public String getPerfil() {
        return perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(perfil, usuario.perfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, perfil);
    }
}
