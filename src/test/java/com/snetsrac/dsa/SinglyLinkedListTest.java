package com.snetsrac.dsa;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SinglyLinkedListTest {
    private SinglyLinkedList<Integer> linkedList;

    @BeforeEach
    public void setUp() {
        linkedList = new SinglyLinkedList<>();
    }

    // addToFront()

    @Test
    public void addToFrontThrowsIfInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            linkedList.addToFront(null);
        });

        assertNull(linkedList.getHead());
        assertNull(linkedList.getTail());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void addToFrontAddsWhenListIsEmpty() {
        linkedList.addToFront(10);

        assertEquals("10", linkedList.toString());
        assertEquals(10, linkedList.getHead().getData());
        assertEquals(10, linkedList.getTail().getData());
        assertSame(linkedList.getHead(), linkedList.getTail());
        assertEquals(1, linkedList.size());
    }

    @Test
    public void addToFrontAddsWhenListIsLengthOne() {
        linkedList.addToFront(20);
        linkedList.addToFront(10);

        assertEquals("10 20", linkedList.toString());
        assertEquals(10, linkedList.getHead().getData());
        assertEquals(20, linkedList.getTail().getData());
        assertEquals(2, linkedList.size());
    }

    @Test
    public void addToFrontAddsWhenListIsLengthTwo() {
        linkedList.addToFront(30);
        linkedList.addToFront(20);
        linkedList.addToFront(10);

        assertEquals("10 20 30", linkedList.toString());
        assertEquals(10, linkedList.getHead().getData());
        assertEquals(30, linkedList.getTail().getData());
        assertEquals(3, linkedList.size());
    }
    // addToBack()

    @Test
    public void addToBackThrowsIfInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            linkedList.addToBack(null);
        });

        assertNull(linkedList.getHead());
        assertNull(linkedList.getTail());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void addToBackAddsWhenListIsEmpty() {
        linkedList.addToBack(10);

        assertEquals("10", linkedList.toString());
        assertEquals(10, linkedList.getHead().getData());
        assertEquals(10, linkedList.getTail().getData());
        assertSame(linkedList.getHead(), linkedList.getTail());
        assertEquals(1, linkedList.size());
    }

    @Test
    public void addToBackAddsWhenListIsLengthOne() {
        linkedList.addToBack(10);
        linkedList.addToBack(20);

        assertEquals("10 20", linkedList.toString());
        assertEquals(10, linkedList.getHead().getData());
        assertEquals(20, linkedList.getTail().getData());
        assertEquals(2, linkedList.size());
    }

    @Test
    public void addToBackAddsWhenListIsLengthTwo() {
        linkedList.addToBack(10);
        linkedList.addToBack(20);
        linkedList.addToBack(30);

        assertEquals("10 20 30", linkedList.toString());
        assertEquals(10, linkedList.getHead().getData());
        assertEquals(30, linkedList.getTail().getData());
        assertEquals(3, linkedList.size());
    }

    // removeFromFront()

    @Test
    public void removeFromFrontThrowsIfListIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            linkedList.removeFromFront();
        });

        assertNull(linkedList.getHead());
        assertNull(linkedList.getTail());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void removeFromFrontRemovesIfListIsLengthOne() {
        linkedList.addToBack(10);

        Integer removed = linkedList.removeFromFront();

        assertEquals(10, removed);
        assertNull(linkedList.getHead());
        assertNull(linkedList.getTail());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void removeFromFrontRemovesIfListIsLengthTwo() {
        linkedList.addToBack(10);
        linkedList.addToBack(20);

        Integer removed = linkedList.removeFromFront();

        assertEquals(10, removed);
        assertEquals(20, linkedList.getHead().getData());
        assertEquals(20, linkedList.getTail().getData());
        assertSame(linkedList.getHead(), linkedList.getTail());
        assertEquals(1, linkedList.size());
    }

    // removeFromBack()

    @Test
    public void removeFromBackThrowsIfListIsEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            linkedList.removeFromBack();
        });

        assertNull(linkedList.getHead());
        assertNull(linkedList.getTail());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void removeFromBackRemovesIfListIsLengthOne() {
        linkedList.addToBack(10);

        Integer removed = linkedList.removeFromBack();

        assertEquals(10, removed);
        assertNull(linkedList.getHead());
        assertNull(linkedList.getTail());
        assertEquals(0, linkedList.size());
    }

    @Test
    public void removeFromBackRemovesIfListIsLengthTwo() {
        linkedList.addToBack(10);
        linkedList.addToBack(20);

        Integer removed = linkedList.removeFromBack();

        assertEquals(20, removed);
        assertEquals(10, linkedList.getHead().getData());
        assertEquals(10, linkedList.getTail().getData());
        assertSame(linkedList.getHead(), linkedList.getTail());
        assertEquals(1, linkedList.size());
    }

    @Test
    public void removeFromBackRemovesIfListIsLengthThree() {
        linkedList.addToBack(10);
        linkedList.addToBack(20);
        linkedList.addToBack(30);

        Integer removed = linkedList.removeFromBack();

        assertEquals(30, removed);
        assertEquals(10, linkedList.getHead().getData());
        assertEquals(20, linkedList.getTail().getData());
        assertEquals(2, linkedList.size());
    }
}
