package com.snetsrac.dsa;

import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortingTest {
    private Integer[] sorted;
    private Integer[] arr;
    private TestComparator<Integer> comparator;

    @BeforeEach
    public void setUp() {
        arr = new Integer[] { 4, 3, 1, 5, 2, 6, 7 };
        comparator = new TestComparator<>();
    }

    // bubbleSort()

    @Test
    public void bubbleSortSorts() {
        sorted = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };

        Sorting.bubbleSort(arr, comparator);

        assertArrayEquals(sorted, arr);
        assertEquals(12, comparator.getCount());
    }

    // selectionSort()

    @Test
    public void selectionSortSorts() {
        sorted = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };

        Sorting.selectionSort(arr, comparator);

        assertArrayEquals(sorted, arr);
        assertEquals(21, comparator.getCount());
    }

    // insertionSort()

    @Test
    public void insertionSortSorts() {
        sorted = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };

        Sorting.insertionSort(arr, comparator);

        assertArrayEquals(sorted, arr);
        assertEquals(9, comparator.getCount());
    }

    @Test
    public void insertionSortSorts2() {
        sorted = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        arr = new Integer[] { 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1};

        Sorting.insertionSort(arr, comparator);

        assertArrayEquals(sorted, arr);
    }

    @Test
    public void insertionSortSorts3() {
        sorted = new Integer[] { 0, 1 };
        arr = new Integer[] { 1, 0 };

        Sorting.insertionSort(arr, comparator);

        assertArrayEquals(sorted, arr);
    }

    private class TestComparator<T extends Comparable<? super T>> implements Comparator<T> {
        private int count;

        public int compare(T a, T b) {
            count++;
            return a.compareTo(b);
        }

        public int getCount() {
            return count;
        }
    }
}
