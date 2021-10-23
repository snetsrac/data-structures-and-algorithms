package com.snetsrac.dsa;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayQueueTest {
    ArrayQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new ArrayQueue<Integer>();
    }

    // enqueue()
    
    @Test
    public void enqueueThrowsIfDataIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            queue.enqueue(null);
        });

        assertEquals(0, queue.size());
        assertEquals("n n n n n n n n n", queue.toString());
    }

    @Test
    public void enqueueEnqueues() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals(3, queue.size());
        assertEquals("1 2 3 n n n n n n", queue.toString());
    }

    @Test
    public void enqueueResizesArrayIfCapacityExceeded() {
        for (int i = 0; i <= 9; i++) {
            queue.enqueue(i);
        }

        assertEquals(10, queue.size());
        assertEquals("0 1 2 3 4 5 6 7 8 9 n n n n n n n n", queue.toString());
    }

    @Test
    public void enqueueResizesArrayIfCapacityExceededAndFrontIsNot0() {
        for (int i = 1; i <= 9; i++) {
            queue.enqueue(i);
        }

        assertEquals("1 2 3 4 5 6 7 8 9", queue.toString());

        for (int i = 0; i < 4; i++) {
            queue.dequeue();
        }

        assertEquals("n n n n 5 6 7 8 9", queue.toString());

        for (int i = 0; i < 4; i++) {
            queue.enqueue(i);
        }

        assertEquals("0 1 2 3 5 6 7 8 9", queue.toString());

        queue.enqueue(4);

        assertEquals(10, queue.size());
        assertEquals("5 6 7 8 9 0 1 2 3 4 n n n n n n n n", queue.toString());
    }

    // dequeue()

    @Test
    public void dequeueThrowsIfQueueIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });

        assertEquals(0, queue.size());
        assertEquals("n n n n n n n n n", queue.toString());
    }

    @Test
    public void dequeueDequeues() {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(1, queue.dequeue());
        assertEquals(1, queue.size());
        assertEquals("n 2 n n n n n n n", queue.toString());

        assertEquals(2, queue.dequeue());
        assertEquals(0, queue.size());
        assertEquals("n n n n n n n n n", queue.toString());

        assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });
    }
}
