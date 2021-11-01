package com.snetsrac.dsa;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExternalChainingHashMapTest {
    private ExternalChainingHashMap<Integer, Integer> hashMap;

    @BeforeEach
    public void setUp() {
        hashMap = new ExternalChainingHashMap<>();
    }

    // put()

    @Test
    public void putThrowsIfKeyOrValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            hashMap.put(null, 1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            hashMap.put(1, null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            hashMap.put(null, null);
        });

        assertEquals(0, hashMap.size());
        assertEquals("n n n n n n n n n n n n n", hashMap.toString());
    }

    @Test
    public void putPuts() {
        assertNull(hashMap.put(5, 5));

        assertEquals(1, hashMap.size());
        assertEquals("n n n n n (5,5) n n n n n n n", hashMap.toString());
    }

    @Test
    public void putResolvesCollisionsWithExternalChaining() {
        hashMap.put(19, 19);
        hashMap.put(6, 6);
        hashMap.put(8, 8);
        hashMap.put(11, 11);
        hashMap.put(25, 25);

        assertNull(hashMap.put(32, 32));

        assertEquals(6, hashMap.size());
        assertEquals("n n n n n n (32,32)-(6,6)-(19,19) n (8,8) n n (11,11) (25,25)", hashMap.toString());
    }

    @Test
    public void putReplacesExistingValueForDuplicateKeys() {
        hashMap.put(6, 6);
        hashMap.put(20, 20);
        hashMap.put(8, 8);
        hashMap.put(37, 37);
        hashMap.put(24, -1);
        hashMap.put(11, 11);

        int result = hashMap.put(24, 24);

        assertEquals(-1, result);
        assertEquals(6, hashMap.size());
        assertEquals("n n n n n n (6,6) (20,20) (8,8) n n (11,11)-(24,24)-(37,37) n", hashMap.toString());
    }

    @Test
    public void putResizesTableIfLoadFactorWouldBeExceeded() {
        for (int i = 0; i < 8; i++) {
            hashMap.put(i, i);
        }

        hashMap.put(8, 8);

        assertEquals(9, hashMap.size());
        assertEquals("(0,0) (1,1) (2,2) (3,3) (4,4) (5,5) (6,6) (7,7) (8,8) n n n n n n n n n n n n n n n n n n", hashMap.toString());
    }

    // remove()

    @Test
    public void removeThrowsIfKeyIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            hashMap.remove(null);
        });
    }

    @Test
    public void removeThrowsIfKeyNotInHashMap() {
        assertThrows(NoSuchElementException.class, () -> {
            hashMap.remove(1);
        });
    }

    @Test
    public void removeRemoves() {
        hashMap.put(4, -1);

        int result = hashMap.remove(4);

        assertEquals(-1, result);
        assertEquals(0, hashMap.size());
        assertEquals("n n n n n n n n n n n n n", hashMap.toString());
    }

    @Test
    public void removeRemovesFromHeadOfExternalChain() {
        hashMap.put(6, 6);
        hashMap.put(20, 20);
        hashMap.put(8, 8);
        hashMap.put(37, 37);
        hashMap.put(24, 24);
        hashMap.put(11, -1);

        int result = hashMap.remove(11);

        assertEquals(-1, result);
        assertEquals(5, hashMap.size());
        assertEquals("n n n n n n (6,6) (20,20) (8,8) n n (24,24)-(37,37) n", hashMap.toString());
    }
}