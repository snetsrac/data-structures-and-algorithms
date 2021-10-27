package com.snetsrac.dsa;

import java.util.NoSuchElementException;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation, so
     * instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    @SuppressWarnings("unchecked")
    public MinHeap() {
        // DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for index 0)
     * and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to MinHeap.");
        }

        size++;

        if (size + 1 > backingArray.length) {
            resize();
        }

        backingArray[size] = data;
        upheap();
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from an empty MinHeap.");
        }

        T data = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downheap();

        return data;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since you
     * have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since you
     * have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArray = (T[]) new Comparable[backingArray.length * 2];

        for (int i = 0; i < backingArray.length; i++) {
            newArray[i] = backingArray[i];
        }

        backingArray = newArray;
    }

    private void swap(int indexA, int indexB) {
        T temp = backingArray[indexA];
        backingArray[indexA] = backingArray[indexB];
        backingArray[indexB] = temp;
    }

    private boolean isSmaller(int indexA, int indexB) {
        return backingArray[indexA].compareTo(backingArray[indexB]) < 0;
    }
    
    private void upheap() {
        for (int child = size; child > 1; child /= 2) {
            int parent = child / 2;

            if (isSmaller(child, parent)) {
                swap(child, parent);
            } else {
                break;
            }
        }
    }

    private boolean hasLeftChild(int index) {
        return (index * 2) <= size;
    }

    private boolean hasRightChild(int index) {
        return (index * 2) + 1 <= size;
    }

    private void downheap() {
        int parent = 1;

        while (hasLeftChild(parent)) {
            int leftChild = parent * 2;
            int rightChild = (parent * 2) + 1;
            int smallerChild = leftChild;

            if (hasRightChild(parent) && isSmaller(rightChild, leftChild)) {
                smallerChild = rightChild;
            }

            if (isSmaller(smallerChild, parent)) {
                swap(smallerChild, parent);
            } else {
                break;
            }

            parent = smallerChild;
        }
    }

    public String toString() {
        String string = "";

        for (T data : backingArray) {
            if (data == null) {
                string += "n ";
            } else {
                string += data.toString() + " ";
            }
        }

        return string.stripTrailing();
    }
}