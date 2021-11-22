package com.snetsrac.dsa;

import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;

/**
 * Your implementation of various iterative and divide & conquer sorting algorithms.
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

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    @SuppressWarnings("unchecked")
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr.length <= 1) {
            return;
        }

        int midpoint = arr.length / 2;
        T[] left = (T[]) new Object[midpoint];
        T[] right = (T[]) new Object[arr.length - midpoint];
    
        for (int i = 0; i < midpoint; i++) {
            left[i] = arr[i];
        }

        for (int i = midpoint; i < arr.length; i++) {
            right[i - midpoint] = arr[i];
        }

        mergeSort(left, comparator);
        mergeSort(right, comparator);

        int i = 0;
        int j = 0;

        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }

        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }

        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    @SuppressWarnings("unchecked")
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int absMax = 0;

        for (int num : arr) {
            // Handle edge case of minimum possible signed int, the absolute
            // value of which cannot be stored in an int
            if (num == Integer.MIN_VALUE) {
                absMax = Integer.MAX_VALUE;
                break;
            } else if (Math.abs(num) > absMax) {
                absMax = Math.abs(num);
            }
        }

        int tenToTheK = 1;
        absMax /= 10;

        while (absMax != 0) {
            tenToTheK *= 10;
            absMax /= 10;
        }

        List<Integer>[] buckets = (LinkedList<Integer>[]) new LinkedList[19];

        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<Integer>();
        }

        for (int digitPlace = 1; digitPlace <= tenToTheK; digitPlace *= 10) {
            for (int num : arr) {
                int digit = (num / digitPlace) % 10;
                buckets[digit + 9].add(num);
            }

            int i = 0;

            for (List<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[i++] = bucket.remove(0);
                }
            }
        }
    }

    private static <T> void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}