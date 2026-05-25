package br.edu.iftm.desafio1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimuladorTreeifyTest {

    @Test
    @DisplayName("Desafio 1: forçar e verificar treeification no HashMap")
    void testTreeifyDetection() throws Exception {
        int initialCapacity = 128;
        HashMap<ChaveColisora, String> map = new HashMap<>(initialCapacity);
        List<ChaveColisora> keys = new ArrayList<>();

        // insere menos que TREEIFY_THRESHOLD (8)
        for (int i = 0; i < 6; i++) {
            ChaveColisora k = new ChaveColisora(i);
            keys.add(k);
            map.put(k, "v" + i);
        }

        // inspeciona primeiro elemento do bucket
        String antes = firstNonNullBucketClass(map);
        assertTrue(antes != null && (antes.contains("Node") || antes.contains("Entry")), "Antes deve ser Node/Entry (lista)");

        // inserir mais chaves para ultrapassar TREEIFY_THRESHOLD (8)
        for (int i = 6; i < 20; i++) {
            ChaveColisora k = new ChaveColisora(i);
            keys.add(k);
            map.put(k, "v" + i);
        }

        String depois = firstNonNullBucketClass(map);
        assertTrue(depois != null && (depois.contains("TreeNode") || depois.contains("Tree")), "Depois deve ser TreeNode (árvore)");

        // medir tempos (apenas para validar que medição roda sem exceção)
        double antesTempo = SimuladorTreeify.avgGetTimeNs(map, keys.subList(0, 6), 1000);
        double depoisTempo = SimuladorTreeify.avgGetTimeNs(map, keys, 1000);

        // não assertamos ordem rígida do tempo porque pode variar por ambiente, apenas checamos números finitos
        assertTrue(Double.isFinite(antesTempo));
        assertTrue(Double.isFinite(depoisTempo));
    }

    private String firstNonNullBucketClass(HashMap<?, ?> map) throws Exception {
        Field tableField = HashMap.class.getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] table = (Object[]) tableField.get(map);
        if (table == null) return null;
        for (Object e : table) {
            if (e != null) return e.getClass().getName();
        }
        return null;
    }
}
