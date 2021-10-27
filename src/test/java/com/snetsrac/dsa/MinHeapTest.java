package com.snetsrac.dsa;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MinHeapTest {
    private MinHeap<Integer> minHeap;

    @BeforeEach
    public void setUp() {
        minHeap = new MinHeap<Integer>();
    }

    // add()

    @Test
    public void addThrowsIfDataIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            minHeap.add(null);
        });

        assertEquals(0, minHeap.size());
        assertEquals("n n n n n n n n n n n n n", minHeap.toString());
    }

    @Test
    public void addAddsToEmptyMinHeap() {
        minHeap.add(1);

        assertEquals(1, minHeap.size());
        assertEquals("n 1 n n n n n n n n n n n", minHeap.toString());
    }

    @Test
    public void addAdds() {
        for (int i = 1; i <= 9; i++) {
            minHeap.add(i);
        }

        minHeap.add(0);

        assertEquals(10, minHeap.size());
        assertEquals("n 0 1 3 4 2 6 7 8 9 5 n n", minHeap.toString());
    }

    @Test
    public void addResizesIfNecessary() {
        for (int i = 1; i <= 12; i++) {
            minHeap.add(i);
        }

        minHeap.add(0);

        assertEquals(13, minHeap.size());
        assertEquals("n 0 2 1 4 5 3 7 8 9 10 11 12 6 n n n n n n n n n n n n", minHeap.toString());
    }

    // remove()

    @Test
    public void removeThrowsIfMinHeapIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            minHeap.remove();
        });

        assertEquals(0, minHeap.size());
        assertEquals("n n n n n n n n n n n n n", minHeap.toString());
    }

    @Test
    public void removeRemovesFromMinHeapOfSize1() {
        minHeap.add(1);

        int result = minHeap.remove();

        assertEquals(1, result);
        assertEquals(0, minHeap.size());
        assertEquals("n n n n n n n n n n n n n", minHeap.toString());
    }

    @Test
    public void removeRemoves() {
        minHeap.add(0);
        minHeap.add(1);
        minHeap.add(2);
        minHeap.add(4);
        minHeap.add(3);
        minHeap.add(5);
        minHeap.add(7);
        minHeap.add(6);
        minHeap.add(8);
        minHeap.add(9);
        minHeap.add(10);

        int result = minHeap.remove();

        assertEquals(0, result);
        assertEquals(10, minHeap.size());
        assertEquals("n 1 3 2 4 9 5 7 6 8 10 n n", minHeap.toString());
    }
}