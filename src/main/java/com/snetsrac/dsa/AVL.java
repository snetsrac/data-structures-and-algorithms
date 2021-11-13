package com.snetsrac.dsa;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL.
 */
public class AVL<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private AVLNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null to AVL.");
        }

        root = _add(root, data);
    }

    private AVLNode<T> _add(AVLNode<T> node, T data) {
        if (node == null) {
            node = new AVLNode<>(data);
            size++;
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(_add(node.getLeft(), data));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(_add(node.getRight(), data));
        }

        updateHeightAndBF(node);
        node = balance(node);

        return node;
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     *    simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     *    replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     *    replace the data, NOT predecessor. As a reminder, rotations can occur
     *    after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary. This is as simple as calling the balance() method on the
     * current node, before returning it (assuming that your balance method
     * is written correctly from part 1 of this assignment).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If the data is null.
     * @throws java.util.NoSuchElementException   If the data is not found.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null from AVL.");
        }

        ArrayList<T> dataAndSuccessor = new ArrayList<>(2);
        dataAndSuccessor.add(null);
        dataAndSuccessor.add(null);
        root = _remove(root, data, dataAndSuccessor);

        return dataAndSuccessor.get(0);
    }

    private AVLNode<T> _remove(AVLNode<T> node, T data, ArrayList<T> dataAndSuccessor) {
        if (node == null) {
            throw new NoSuchElementException("Cannot remove from AVL: data not found.");
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(_remove(node.getLeft(), data, dataAndSuccessor));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(_remove(node.getRight(), data, dataAndSuccessor));
        } else {
            dataAndSuccessor.set(0, node.getData());
            size--;

            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else {
                node.setRight(_removeSuccessor(node.getRight(), dataAndSuccessor));
                node.setData(dataAndSuccessor.get(1));
            }
        }

        node = balance(node);

        return node;
    }

    private AVLNode<T> _removeSuccessor(AVLNode<T> node, ArrayList<T> dataAndSuccessor) {
        if (node.getLeft() == null) {
            dataAndSuccessor.set(1, node.getData());
            return node.getRight();
        } else {
            node.setLeft(_removeSuccessor(node.getLeft(), dataAndSuccessor));
        }

        node = balance(node);

        return node;
    }

    /**
     * Updates the height and balance factor of a node using its
     * setter methods.
     *
     * Recall that a null node has a height of -1. If a node is not
     * null, then the height of that node will be its height instance
     * data. The height of a node is the max of its left child's height
     * and right child's height, plus one.
     *
     * The balance factor of a node is the height of its left child
     * minus the height of its right child.
     *
     * This method should run in O(1).
     * You may assume that the passed in node is not null.
     *
     * This method should only be called in rotateLeft(), rotateRight(),
     * and balance().
     *
     * @param currentNode The node to update the height and balance factor of.
     */
    private void updateHeightAndBF(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        int leftHeight = -1;
        int rightHeight = -1;

        if (currentNode.getLeft() != null) {
            leftHeight = currentNode.getLeft().getHeight();
        }

        if (currentNode.getRight() != null) {
            rightHeight = currentNode.getRight().getHeight();
        }

        currentNode.setHeight(Math.max(leftHeight, rightHeight) + 1);
        currentNode.setBalanceFactor(leftHeight - rightHeight);
    }

    /**
     * Method that rotates a current node to the left. After saving the
     * current's right node to a variable, the right node's left subtree will
     * become the current node's right subtree. The current node will become
     * the right node's left subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is right heavy. Therefore, you do not need to
     * perform any preliminary checks, rather, you can immediately perform a
     * left rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateLeft(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        AVLNode<T> rightNode = currentNode.getRight();
        currentNode.setRight(rightNode.getLeft());
        rightNode.setLeft(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(rightNode);
        return rightNode;
    }

    /**
     * Method that rotates a current node to the right. After saving the
     * current's left node to a variable, the left node's right subtree will
     * become the current node's left subtree. The current node will become
     * the left node's right subtree.
     *
     * Don't forget to recalculate the height and balance factor of all
     * affected nodes, using updateHeightAndBF().
     *
     * This method should run in O(1).
     *
     * You may assume that the passed in node is not null and that the subtree
     * starting at that node is left heavy. Therefore, you do not need to perform
     * any preliminary checks, rather, you can immediately perform a right
     * rotation on the passed in node and return the new root of the subtree.
     *
     * This method should only be called in balance().
     *
     * @param currentNode The current node under inspection that will rotate.
     * @return The parent of the node passed in (after the rotation).
     */
    private AVLNode<T> rotateRight(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        AVLNode<T> leftNode = currentNode.getLeft();
        currentNode.setLeft(leftNode.getRight());
        leftNode.setRight(currentNode);
        updateHeightAndBF(currentNode);
        updateHeightAndBF(leftNode);
        return leftNode;
    }

    /**
     * Method that balances out the tree starting at the node passed in.
     * This method should be called in your add() and remove() methods to
     * facilitate rebalancing your tree after an operation.
     *
     * The height and balance factor of the current node is first recalculated.
     * Based on the balance factor, a no rotation, a single rotation, or a
     * double rotation takes place. The current node is returned.
     *
     * You may assume that the passed in node is not null. Therefore, you do
     * not need to perform any preliminary checks, rather, you can immediately
     * check to see if any rotations need to be performed.
     *
     * This method should run in O(1).
     *
     * @param currentNode The current node under inspection.
     * @return The AVLNode that the caller should return.
     */
    private AVLNode<T> balance(AVLNode<T> currentNode) {
        // COPY YOUR CODE FROM PART 1 OF THE ASSIGNMENT!
        updateHeightAndBF(currentNode);

        if (currentNode.getBalanceFactor() < -1) {
            if (currentNode.getRight().getBalanceFactor() >= 1) {
                currentNode.setRight(rotateRight(currentNode.getRight()));
            }
            currentNode = rotateLeft(currentNode);
        } else if (currentNode.getBalanceFactor() > 1) {
            if (currentNode.getLeft().getBalanceFactor() <= -1) {
                currentNode.setLeft(rotateLeft(currentNode.getLeft()));
            }
            currentNode = rotateRight(currentNode);
        }

        return currentNode;
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The root of the tree.
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the tree.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    // Prints the level-order traversal, separated by spaces and ignoring nulls
    @Override
    public String toString() {
        if (root == null) {
            return "";
        }

        String string = "";
        ArrayQueue<AVLNode<T>> q = new ArrayQueue<>();
        q.enqueue(root);

        while (q.size() != 0) {
            AVLNode<T> cur = q.dequeue();

            if (cur.getLeft() != null) {
                q.enqueue(cur.getLeft());
            }

            if (cur.getRight() != null) {
                q.enqueue(cur.getRight());
            }

            string += cur.getData() + " ";
        }

        return string.stripTrailing();
    }
}