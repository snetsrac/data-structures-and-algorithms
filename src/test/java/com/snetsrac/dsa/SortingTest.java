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
        sorted = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
        arr = new Integer[] { 4, 3, 1, 5, 2, 6, 7 };
        comparator = new TestComparator<>();
    }

    // bubbleSort()

    @Test
    public void bubbleSortSorts() {
        Sorting.bubbleSort(arr, comparator);

        assertArrayEquals(sorted, arr);
        assertEquals(12, comparator.getCount());
    }

    // selectionSort()

    @Test
    public void selectionSortSorts() {
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
        assertEquals(10, comparator.getCount());
    }

    @Test
    public void insertionSortSorts2() {
        sorted = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        arr = new Integer[] { 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1 };

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

    // mergeSort()

    @Test
    public void mergeSortSorts() {
        Sorting.mergeSort(arr, comparator);

        assertArrayEquals(arr, sorted);
        assertEquals(11, comparator.getCount());
    }

    // lsdRadixSort()

    @Test
    public void lsdRadixSortSorts() {
        int[] radixSorted = new int[] { -9403, -207, -92, -1, 53, 231, 634, 1000, 2014 };
        int[] radixArr = new int[] { 1000, 2014, 231, 53, -1, -92, -9403, 634, -207 };

        Sorting.lsdRadixSort(radixArr);
        
        assertArrayEquals(radixSorted, radixArr);
    }

    @Test
    public void lsdRadixSortSorts2() {
        int[] radixSorted = new int[] { -2147483648, -800, -796, -785, -138, 104, 151, 258, 543, 908 };
        int[] radixArr = new int[] { 908, 543, 258, 151, 104, -138, -785, -796, -800, -2147483648 };

        Sorting.lsdRadixSort(radixArr);
        
        assertArrayEquals(radixSorted, radixArr);
    }

    @Test
    public void lsdRadixSortSorts3() {
        int[] radixSorted = new int[] { -2147483648, -2147483648, -9 };
        int[] radixArr = new int[] { -9, -2147483648, -2147483648 };

        Sorting.lsdRadixSort(radixArr);
        
        assertArrayEquals(radixSorted, radixArr);
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
