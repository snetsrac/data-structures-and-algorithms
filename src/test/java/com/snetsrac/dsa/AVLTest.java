package com.snetsrac.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

public class AVLTest {
    AVL<Integer> avl;

    @BeforeEach
    public void setUp() {
        avl = new AVL<Integer>();
    }

    // add()

    @Test
    public void addThrowsIfDataIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            avl.add(null);
        });

        assertEquals(0, avl.size());
        assertEquals("", avl.toString());
    }

    @Test
    public void addAdds() {
        avl.add(1);

        assertEquals(1, avl.size());
        assertEquals("1", avl.toString());
    }

    @Test
    public void addAddsAndRebalances() {
        avl.add(2);
        avl.add(0);
        avl.add(7);
        avl.add(1);
        avl.add(4);
        avl.add(8);
        avl.add(3);
        avl.add(6);

        avl.add(5);

        assertEquals(9, avl.size());
        assertEquals("2 0 6 1 4 7 3 5 8", avl.toString());
    }

    // remove()

    @Test
    public void removeThrowsIfAVLIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            avl.remove(1);
        });

        assertEquals(0, avl.size());
        assertEquals("", avl.toString());
    }

    @Test
    public void removeThrowsIfDataIsNotInAVL() {
        avl.add(2);

        assertThrows(NoSuchElementException.class, () -> {
            avl.remove(1);
        });

        assertEquals(1, avl.size());
        assertEquals("2", avl.toString());
    }

    @Test
    public void removeThrowsIfDataIsNull() {
        avl.add(1);

        assertThrows(IllegalArgumentException.class, () -> {
            avl.remove(null);
        });

        assertEquals(1, avl.size());
        assertEquals("1", avl.toString());
    }

    @Test
    public void removeRemoves() {
        avl.add(2);
        avl.add(1);
        avl.add(3);

        int result = avl.remove(2);

        assertEquals(2, result);
        assertEquals(2, avl.size());
        assertEquals("3 1", avl.toString());
    }

    @Test
    public void removeRemovesAndRebalances() {
        avl.add(2);
        avl.add(0);
        avl.add(4);
        avl.add(1);
        avl.add(3);
        avl.add(6);
        avl.add(5);

        int result = avl.remove(3);

        assertEquals(3, result);
        assertEquals(6, avl.size());
        assertEquals("2 0 5 1 4 6", avl.toString());
    }

    @Test
    public void removeRemovesAndRebalancesTwoChildSuccessorCase() {
        avl.add(7);
        avl.add(4);
        avl.add(10);
        avl.add(2);
        avl.add(6);
        avl.add(8);
        avl.add(11);
        avl.add(0);
        avl.add(3);
        avl.add(5);
        avl.add(9);
        avl.add(1);

        int result = avl.remove(4);

        assertEquals(4, result);
        assertEquals(11, avl.size());
        assertEquals("7 2 10 0 5 8 11 1 3 6 9", avl.toString());
    }
}