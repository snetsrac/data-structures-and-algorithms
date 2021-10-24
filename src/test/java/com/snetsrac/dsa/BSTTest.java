package com.snetsrac.dsa;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BSTTest {
  BST<Integer> bst;
  
  @BeforeEach
  public void setUp() {
    bst = new BST<Integer>();
  }

  // add()

  @Test
  public void addThrowsIfDataIsNull() {
    assertThrows(IllegalArgumentException.class, () -> {
      bst.add(null);
    });

    assertEquals(0, bst.size());
    assertEquals("", bst.toString());
  }

  @Test
  public void addAddsIfBstIsEmpty() {
    bst.add(1);

    assertEquals(1, bst.size());
    assertEquals("1", bst.toString());
  }

  @Test
  public void addAdds() {
    setUpBst(bst, Arrays.asList(50, 15, 75, 5, 100, 10));

    bst.add(25);

    assertEquals(7, bst.size());
    assertEquals("50 15 75 5 25 100 10", bst.toString());
  }

  @Test
  public void removeThrowsIfDataIsNull() {
    bst.add(1);

    assertThrows(IllegalArgumentException.class, () -> {
      bst.remove(null);
    });
  }

  @Test
  public void removeThrowsIfBstIsEmpty() {
    assertThrows(NoSuchElementException.class, () -> {
      bst.remove(1);
    });
  }

  @Test
  public void removeThrowsIfDataNotInBst() {
    setUpBst(bst, Arrays.asList(50, 15, 75, 5, 100, 10));

    assertThrows(NoSuchElementException.class, () -> {
      bst.remove(1);
    });
  }

  @Test
  public void removeRemovesZeroChildCase() {
    setUpBst(bst, Arrays.asList(50, 15, 75, 5, 100, 10));

    int result = bst.remove(10);

    assertEquals(10, result);
    assertEquals(5, bst.size());
    assertEquals("50 15 75 5 100", bst.toString());
  }

  @Test
  public void removeRemovesOneChildCase() {
    setUpBst(bst, Arrays.asList(50, 15, 75, 5, 100, 10));

    int result = bst.remove(5);

    assertEquals(5, result);
    assertEquals(5, bst.size());
    assertEquals("50 15 75 10 100", bst.toString());
  }

  @Test
  public void removeRemovesTwoChildCaseUsingSuccessor() {
    setUpBst(bst, Arrays.asList(50, 15, 75, 5, 25, 100, 20));

    int result = bst.remove(15);

    assertEquals(15, result);
    assertEquals(6, bst.size());
    assertEquals("50 20 75 5 25 100", bst.toString());
  }

  @Test
  public void removeRemovesRootNodeUsingSuccessor() {
    setUpBst(bst, Arrays.asList(50, 15, 75, 5, 25, 100, 20));

    int result = bst.remove(50);

    assertEquals(50, result);
    assertEquals(6, bst.size());
    assertEquals("75 15 100 5 25 20", bst.toString());
  }

  private void setUpBst(BST<Integer> bst, List<Integer> list) {
    for (int num : list) {
      bst.add(num);
    }
  }
}
