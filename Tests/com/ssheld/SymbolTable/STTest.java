package com.ssheld.SymbolTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class STTest {

    private ST<String, Integer> st;
    @BeforeEach
    void setUp() {
        st = new ST<>();
    }

    @Test
    void putValueIntoSymbolTable() {
        // Act
        st.put("S", 0);

        // Assert
        int value = st.get("S");
        assertEquals(0, value);
    }

    @Test
    void getValueFromSymbolTable() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);

        // Act
        int value = st.get("A");

        // Assert
        assertEquals(3, value);
    }

    @Test
    void deleteCorrectlyDeletesAEntryFromSymbolTable() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);

        // Act
        st.delete("E");

        // Assert
        assertEquals(null, st.get("E"));
    }

    @Test
    void containsReturnsTheCorrectTrueOrFalseWhenPassedAKey() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);

        // Act
        boolean contain1 = st.contains("E");
        boolean contain2 = st.contains("G");

        // Assert
        assertEquals(true, contain1);
        assertEquals(false, contain2);

    }

    @Test
    void isEmptyReturnsTrueOrFalseCorrectly() {

        // Act
        Boolean empty = st.isEmpty();

        // Assert
        assertEquals(true, empty);
    }

    @Test
    void sizeReturnsTheSizeOfTheSymbolTable() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);

        // Act
        int size = st.size();

        // Assert
        assertEquals(3, size);
    }

    @Test
    void minReturnsTheMinimumKeyInSymbolTable() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);

        // Act
        String s = st.min();

        // Assert
        assertEquals("A", s);
    }

    @Test
    void maxReturnsTheMaxKeyInSymbolTable() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);

        // Act
        String s = st.max();

        // Assert
        assertEquals("S", s);
    }

    @Test
    void floorReturnsTheHighestKeyLowerOrEqualToKey() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);
        st.put("C", 4);
        st.put("H", 5);

        // Act
        String s = st.floor("Q");
        String t = st.floor("D");

        // Assert
        assertEquals("H", s);
        assertEquals("C", t);
    }

    @Test
    void ceilingReturnsTheLowestKeyGreaterOrEqualToKey() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);
        st.put("C", 4);
        st.put("H", 5);

        // Act
        String s = st.ceiling("F");
        String t = st.ceiling("D");

        // Assert
        assertEquals("H", s);
        assertEquals("E", t);
    }

    @Test
    void deleteMinDeletesTheSmallestKey() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);
        st.put("C", 4);
        st.put("H", 5);

        // Act
        st.deleteMin();

        // Assert
        assertEquals(false, st.contains("A"));
    }

    @Test
    void deleteMaxDeletesTheLargestKey() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);
        st.put("C", 4);
        st.put("H", 5);

        // Act
        st.deleteMax();

        // Assert
        assertEquals(false, st.contains("S"));
    }

    @Test
    void rankReturnsNumberOfKeysLessThanKey() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);
        st.put("C", 4);
        st.put("H", 5);

        // Act
        int rank = st.rank("E");

        // Assert
        assertEquals(2, rank);
    }

    @Test
    void selectReturnsTheKeyOfValueK() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);
        st.put("C", 4);
        st.put("H", 5);

        // Act
        String s = st.select(4);
        String t = st.select(0);

        // Assert
        assertEquals("S", s);
        assertEquals("A", t);
    }

    @Test
    void sizeOverloadedReturnsNumberOfKeysBetweenTwoKeyPairs() {
        // Arrange
        st.put("S", 0);
        st.put("E", 1);
        st.put("A", 3);
        st.put("C", 4);
        st.put("H", 5);

        // Act
        int size = st.size("C", "S");

        // Assert
        assertEquals(2, size);
    }
}