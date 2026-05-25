package br.edu.iftm.bloco3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GerenciadorSessaoTest {

    private GerenciadorSessao gerenciador;

    @BeforeEach
    void setUp() {
        gerenciador = new GerenciadorSessao();
    }

    @Test
    @DisplayName("registrar e buscar sessão devem funcionar em tempo O(1) amortizado")
    void testRegistrarEBuscarSessao() {
        Usuario usuario = new Usuario(1L, "admin");
        gerenciador.registrar("token-uuid", usuario);

        Usuario encontrado = gerenciador.buscar("token-uuid");
        assertNotNull(encontrado);
        assertEquals(1L, encontrado.getId());
        assertEquals("admin", encontrado.getPerfil());
        assertTrue(gerenciador.eValido("token-uuid"));
    }

    @Test
    @DisplayName("invalidar remove sessão e eValido retorna false")
    void testInvalidarSessao() {
        Usuario usuario = new Usuario(2L, "user");
        gerenciador.registrar("token-xyz", usuario);
        assertTrue(gerenciador.eValido("token-xyz"));

        assertTrue(gerenciador.invalidar("token-xyz"));
        assertFalse(gerenciador.eValido("token-xyz"));
        assertNull(gerenciador.buscar("token-xyz"));
    }
}
