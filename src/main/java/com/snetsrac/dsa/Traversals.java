package com.snetsrac.dsa;

import java.util.List;
import java.util.ArrayList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        ArrayList<T> list = new ArrayList<T>();
        traversePreorder(root, list);
        return list;
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        ArrayList<T> list = new ArrayList<T>();
        traverseInorder(root, list);
        return list;
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        ArrayList<T> list = new ArrayList<T>();
        traversePostorder(root, list);
        return list;
    }

    private void traversePreorder(TreeNode<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            traversePreorder(node.getLeft(), list);
            traversePreorder(node.getRight(), list);
        }
    }

    private void traverseInorder(TreeNode<T> node, List<T> list) {
        if (node != null) {
            traverseInorder(node.getLeft(), list);
            list.add(node.getData());
            traverseInorder(node.getRight(), list);
        }
    }

    private void traversePostorder(TreeNode<T> node, List<T> list) {
        if (node != null) {
            traversePostorder(node.getLeft(), list);
            traversePostorder(node.getRight(), list);
            list.add(node.getData());
        }
    }
}