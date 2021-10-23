package com.snetsrac.dsa;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class TraversalsTest {
    static Traversals<Integer> traversals;
    static TreeNode<Integer> root;

    @BeforeAll
    public static void setUp() {
        traversals = new Traversals<>();
        root = new TreeNode<>(50);
        root.setLeft(new TreeNode<>(25));
        root.setRight(new TreeNode<>(100));
        root.getLeft().setLeft(new TreeNode<>(10));
        root.getRight().setLeft(new TreeNode<>(75));
        root.getRight().setRight(new TreeNode<>(125));
        root.getRight().getRight().setLeft(new TreeNode<>(110));
    }

    // preorder()

    @Test
    public void preorderReturnsEmptyListIfRootIsNull() {
        ArrayList<Integer> expected = new ArrayList<>();

        List<Integer> result = traversals.preorder(null);

        assertIterableEquals(expected, result);
    }

    @Test
    public void preorderTraversesCorrectly() {
        ArrayList<Integer> expected = new ArrayList<>(asList(50, 25, 10, 100, 75, 125, 110));

        List<Integer> result = traversals.preorder(root);

        assertIterableEquals(expected, result);
    }

    // inorder()

    @Test
    public void inorderReturnsEmptyListIfRootIsNull() {
        ArrayList<Integer> expected = new ArrayList<>();

        List<Integer> result = traversals.inorder(null);

        assertIterableEquals(expected, result);
    }

    @Test
    public void inorderTraversesCorrectly() {
        ArrayList<Integer> expected = new ArrayList<>(asList(10, 25, 50, 75, 100, 110, 125));

        List<Integer> result = traversals.inorder(root);

        assertIterableEquals(expected, result);
    }

    // postorder()

    @Test
    public void postorderReturnsEmptyListIfRootIsNull() {
        ArrayList<Integer> expected = new ArrayList<>();

        List<Integer> result = traversals.postorder(null);

        assertIterableEquals(expected, result);
    }

    @Test
    public void postorderTraversesCorrectly() {
        ArrayList<Integer> expected = new ArrayList<>(asList(10, 25, 75, 110, 125, 100, 50));

        List<Integer> result = traversals.postorder(root);

        assertIterableEquals(expected, result);
    }
}
