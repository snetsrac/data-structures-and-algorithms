package com.snetsrac.dsa;

import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting {

    /**
     * Implement bubble sort.
     *
     * It should be: in-place stable adaptive
     *
     * Have a worst case running time of: O(n^2) And a best case running time of:
     * O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator are both valid and
     * will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int stopIndex = arr.length - 1;

        while (stopIndex > 0) {
            int lastSwap = 0;

            for (int i = 0; i < stopIndex; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    lastSwap = i;
                }
            }

            stopIndex = lastSwap;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be: in-place unstable not adaptive
     *
     * Have a worst case running time of: O(n^2) And a best case running time of:
     * O(n^2)
     *
     * You may assume that the passed in array and comparator are both valid and
     * will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int swapIndex = arr.length - 1; swapIndex > 0; swapIndex--) {
            int max = 0;

            for (int i = 1; i <= swapIndex; i++) {
                if (comparator.compare(arr[i], arr[max]) > 0) {
                    max = i;
                }
            }

            swap(arr, max, swapIndex);
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be: in-place stable adaptive
     *
     * Have a worst case running time of: O(n^2) And a best case running time of:
     * O(n)
     *
     * You may assume that the passed in array and comparator are both valid and
     * will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int unsortedStart = 1; unsortedStart < arr.length; unsortedStart++) {
            int i = unsortedStart;

            while (i > 0 && comparator.compare(arr[i - 1], arr[i]) > 0) {
                swap(arr, i - 1, i);
                i--;
            }
        }
    }

    private static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}