package com.snetsrac.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternMatchingTest {
    CharacterComparator comparator;

    @BeforeEach
    public void setUp() {
        comparator = new CharacterComparator();
    }

    // buildLastTable()

    @Test
    public void buildLastTableReturnsEmptyMapForEmptyPattern() {
        CharSequence pattern = "";
        Map<Character, Integer> expected = new HashMap<>();

        Map<Character, Integer> result = PatternMatching.buildLastTable(pattern);

        assertEquals(expected, result);
        assertEquals(0, result.size());
    }

    @Test
    public void buildLastTableReturnsCorrectly() {
        CharSequence pattern = "octocat";
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('o', 3);
        expected.put('c', 4);
        expected.put('t', 6);
        expected.put('a', 5);

        Map<Character, Integer> result = PatternMatching.buildLastTable(pattern);

        assertEquals(expected, result);
        assertEquals(4, result.size());
    }

    // boyerMoore()

    @Test
    public void boyerMooreReturnsEmptyListIfPatternIsLongerThanText() {
        CharSequence pattern = "octocat";
        CharSequence text = "no";
        List<Integer> expected = new ArrayList<>();

        List<Integer> result = PatternMatching.boyerMoore(pattern, text, comparator);

        assertEquals(expected, result);
    }

    @Test
    public void boyerMooreReturnsCorrectly1() {
        CharSequence pattern = "happy";
        CharSequence text = "because im happy";
        List<Integer> expected = new ArrayList<>();
        expected.add(11);

        List<Integer> result = PatternMatching.boyerMoore(pattern, text, comparator);

        assertEquals(expected, result);
        assertEquals(8, comparator.getComparisonCount());
    }

    @Test
    public void boyerMooreReturnsCorrectly2() {
        CharSequence pattern = "bbbb";
        CharSequence text = "aaaaaaaaaaaaa";
        List<Integer> expected = new ArrayList<>();

        List<Integer> result = PatternMatching.boyerMoore(pattern, text, comparator);

        assertEquals(expected, result);
        assertEquals(3, comparator.getComparisonCount());
    }

    @Test
    public void boyerMooreReturnsCorrectly3() {
        CharSequence pattern = "aaab";
        CharSequence text = "aaaaaaaaaaaab";
        List<Integer> expected = new ArrayList<>();
        expected.add(9);

        List<Integer> result = PatternMatching.boyerMoore(pattern, text, comparator);

        assertEquals(expected, result);
        assertEquals(13, comparator.getComparisonCount());
    }

    @Test
    public void boyerMooreReturnsCorrectly4() {
        CharSequence pattern = "baaa";
        CharSequence text = "aaaaaaaaaaaaa";
        List<Integer> expected = new ArrayList<>();

        List<Integer> result = PatternMatching.boyerMoore(pattern, text, comparator);

        assertEquals(expected, result);
        assertEquals(40, comparator.getComparisonCount());
    }

    @Test
    public void boyerMooreReturnsCorrectly5() {
        CharSequence pattern = "baaa";
        CharSequence text = "aaaaaaaabaaa";
        List<Integer> expected = new ArrayList<>();
        expected.add(8);

        List<Integer> result = PatternMatching.boyerMoore(pattern, text, comparator);

        assertEquals(expected, result);
        assertEquals(25, comparator.getComparisonCount());
    }
}
