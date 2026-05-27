package br.edu.iftm.edumetrics.cache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class LRUCacheTest {

    @Test
    void shouldEvictLeastRecentlyUsedEntryWhenCapacityIsExceeded() {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);

        // Access "a" to make it recently used.
        assertEquals(1, cache.get("a"));

        // Insert a new entry and force eviction.
        cache.put("d", 4);

        assertFalse(cache.containsKey("b"), "Expected 'b' to be evicted as the least recently used entry");
        assertTrue(cache.containsKey("a"));
        assertTrue(cache.containsKey("c"));
        assertTrue(cache.containsKey("d"));
        assertEquals(3, cache.size());
    }

    @Test
    void shouldRetainMostRecentlyUsedEntriesWhenNewElementAdded() {
        LRUCache<String, String> cache = new LRUCache<>(2);
        cache.put("x", "first");
        cache.put("y", "second");

        cache.get("x");
        cache.put("z", "third");

        assertTrue(cache.containsKey("x"));
        assertFalse(cache.containsKey("y"));
        assertTrue(cache.containsKey("z"));
    }
}
