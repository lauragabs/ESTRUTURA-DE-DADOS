package br.edu.iftm.bloco3;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IndiceInvertido {

    private final Map<String, List<Long>> indice = new HashMap<>();

    public void indexar(long idProduto, String nomeProduto) {
        if (nomeProduto == null || nomeProduto.isBlank()) {
            return;
        }
        String texto = normalizar(nomeProduto);
        for (String palavra : texto.split("\\s+")) {
            if (palavra.isBlank()) {
                continue;
            }
            List<Long> ids = indice.computeIfAbsent(palavra, k -> new ArrayList<>());
            if (!ids.contains(idProduto)) {
                ids.add(idProduto);
            }
        }
    }

    public List<Long> buscar(String palavra) {
        if (palavra == null || palavra.isBlank()) {
            return List.of();
        }
        String chave = normalizar(palavra);
        return List.copyOf(indice.getOrDefault(chave, List.of()));
    }

    public List<Long> buscarMultiplas(String consulta) {
        if (consulta == null || consulta.isBlank()) {
            return List.of();
        }
        String texto = normalizar(consulta);
        String[] palavras = texto.split("\\s+");
        List<Long> resultado = null;
        for (String palavra : palavras) {
            if (palavra.isBlank()) {
                continue;
            }
            List<Long> ids = indice.get(palavra);
            if (ids == null) {
                return List.of();
            }
            if (resultado == null) {
                resultado = new ArrayList<>(ids);
            } else {
                resultado.retainAll(ids);
                if (resultado.isEmpty()) {
                    return List.of();
                }
            }
        }
        return resultado == null ? List.of() : List.copyOf(resultado);
    }

    public int totalPalavrasIndexadas() {
        return indice.size();
    }

    private String normalizar(String texto) {
        String semAcento = Normalizer.normalize(texto.toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return semAcento.trim();
    }
}
