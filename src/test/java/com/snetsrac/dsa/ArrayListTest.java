package com.snetsrac.dsa;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest {
    private ArrayList<Integer> arrayList;

    @BeforeEach
    public void setUp() {
        arrayList = new ArrayList<Integer>();
    }

    @Test
    public void throwsOnNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            arrayList.addToBack(null);
            arrayList.addToFront(null);
        });
    }

    @Test
    public void throwsIfListEmpty() {
        assertThrows(NoSuchElementException.class, () -> {
            arrayList.removeFromBack();
            arrayList.removeFromFront();
        });
    }

    @Test
    public void addToBackSuccessfullyAdds() {
        Integer[] expectedResult = { 1, 2, 3 };

        arrayList.addToBack(1);
        arrayList.addToBack(2);
        arrayList.addToBack(3);

        assertArrayEquals(expectedResult, arrayList.getArray());
        assertEquals(3, arrayList.size());
    }

    @Test
    public void addToFrontSuccessfullyAdds() {
        Integer[] expectedResult = { 3, 2, 1 };

        arrayList.addToFront(1);
        arrayList.addToFront(2);
        arrayList.addToFront(3);

        assertArrayEquals(expectedResult, arrayList.getArray());
        assertEquals(3, arrayList.size());
    }

    @Test
    public void backingArrayCorrectlyResizes() {
        int startingLength = arrayList.getCapacity();

        for (int i = 0; i <= startingLength; i++) {
            arrayList.addToBack(1);
        }

        assertEquals(startingLength * 2, arrayList.getCapacity());
        assertEquals(startingLength + 1, arrayList.size());
    }

    @Test
    public void removeFromBackSuccessfulyRemoves() {
        Integer[] expectedResult = { 1, 2 };

        arrayList.addToBack(1);
        arrayList.addToBack(2);
        arrayList.addToBack(3);
        int returned = arrayList.removeFromBack();

        assertArrayEquals(expectedResult, arrayList.getArray());
        assertEquals(3, returned);
    }

    @Test
    public void removeFromFrontSuccessfulyRemoves() {
        Integer[] expectedResult = { 2, 3 };

        arrayList.addToBack(1);
        arrayList.addToBack(2);
        arrayList.addToBack(3);
        int returned = arrayList.removeFromFront();

        assertArrayEquals(expectedResult, arrayList.getArray());
        assertEquals(1, returned);
    }
}
