package com.janbabak.binarySearchTree;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BinarySearchTreeTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("BinarySearchTree<Integer>")
    class BinarySearchTreeIntegerTest {

        BinarySearchTree<Integer> binarySearchTree;

        @BeforeEach
        void setUp() {
            this.binarySearchTree = new BinarySearchTree<>();
        }

        @Test
        @DisplayName("empty tree should be empty")
        void isEmpty() {
            assertTrue(binarySearchTree.empty());
        }

        @Test
        @DisplayName("tree should not be empty")
        void notEmpty() {
            binarySearchTree.insert(0);
            assertFalse(binarySearchTree.empty());
        }

        @Test
        @DisplayName("insert value more times, delete it")
        void insertSameValueMoreTimes() {
            binarySearchTree.insert(5);
            binarySearchTree.insert(5);
            binarySearchTree.insert(5);

            assertTrue(binarySearchTree.contain(5));
            binarySearchTree.delete(5);
            assertFalse(binarySearchTree.contain(5));
            assertTrue(binarySearchTree.empty());
        }

        @ParameterizedTest
        @MethodSource("listsOfIntegers")
        @DisplayName("insert values, test contain for all values")
        void insertAndContain(List<Integer> data) {
            for (Integer value : data) {
                binarySearchTree.insert(value);
            }
            for (Integer value : data) {
                assertTrue(binarySearchTree.contain(value));
            }
        }

        @ParameterizedTest
        @MethodSource("listsOfIntegers")
        @DisplayName("delete that values in same order and test empty")
        void deleteAndEmptyInOrder(List<Integer> data) {
            for (Integer value : data) {
                binarySearchTree.insert(value);
            }
            for (Integer value : data) {
                binarySearchTree.delete(value);
            }
            assertTrue(binarySearchTree.empty());
        }

        @ParameterizedTest
        @MethodSource("listsOfIntegers")
        @DisplayName("delete that values in reverse order and test empty")
        void deleteAndEmptyInReverseOrder(List<Integer> data) {
            for (Integer value : data) {
                binarySearchTree.insert(value);
            }
            for (int i = data.size() -1; i >= 0; i--) {
                binarySearchTree.delete(data.get(i));
            }
            assertTrue(binarySearchTree.empty());
        }

        @ParameterizedTest
        @MethodSource("listsOfIntegers")
        @DisplayName("test contain and find and delete and contain and find once again")
        void deleteAndContainAndFind(List<Integer> data) {
            for (Integer value : data) {
                binarySearchTree.insert(value);
            }
            for (Integer value : data) {
                assertTrue(binarySearchTree.contain(value));
                assertEquals(value, binarySearchTree.findByValue(value).getValue());
            }
            for (Integer value : data) {
                binarySearchTree.delete(value);
            }
            for (Integer value : data) {
                assertFalse(binarySearchTree.contain(value));
                assertNull(binarySearchTree.findByValue(value));
            }
        }

        @ParameterizedTest
        @MethodSource("listsOfIntegers")
        @DisplayName("max value")
        void max(List<Integer> data) {
            int max = Collections.max(data);
            for (Integer value : data) {
                binarySearchTree.insert(value);
            }
            assertEquals(max, binarySearchTree.max());
        }

        @Test
        @DisplayName("test max of empty tree")
        void maxOfEmptyTree() {
            assertNull(binarySearchTree.max());
        }

        @ParameterizedTest
        @MethodSource("listsOfIntegers")
        @DisplayName("min value")
        void min(List<Integer> data) {
            int min = Collections.min(data);
            for (Integer value : data) {
                binarySearchTree.insert(value);
            }
            assertEquals(min, binarySearchTree.min());
        }

        @Test
        @DisplayName("test min of empty tree")
        void minOfEmptyTree() {
            assertNull(binarySearchTree.min());
        }

        @ParameterizedTest
        @MethodSource("listsOfIntegers")
        @DisplayName("print in order, shouldn't throw exception")
        void printInOrderNotThrow(List<Integer> data) {
            for (Integer value : data) {
                binarySearchTree.insert(value);
            }
            assertDoesNotThrow(() -> {
                binarySearchTree.printInOrder();
                System.out.println();
            });
        }

        @ParameterizedTest
        @MethodSource("listsOfHeightsAndIntegers")
        @DisplayName("test height of tree")
        void height(HeightAndDataInput data) {
            for (Integer value : data.inputs) {
                binarySearchTree.insert(value);
            }
            assertEquals(data.height, binarySearchTree.height());
        }

        /**
         * data source of inputs
         * @return list of lists of Integers
         */
        private static List<List<Integer>> listsOfIntegers() {
            return Arrays.asList(
                    Arrays.asList(1, 2, 3, 4, 5),
                    Arrays.asList(-1, -2, -3, -4, -5, -6),
                    Arrays.asList(5, 3, 2, 1, 6, 10, 9),
                    Arrays.asList(50, 25, 75, 10, 26, 66, -5, 15, 30, 69, 0, 29, 31),
                    Arrays.asList(-15, 20, 0, 1, 44, 1000, 999, 997, 998, -99, 5, 8, 123)
            );
        }

        /**
         * wrapper for input data to test height
         */
        class HeightAndDataInput {

            /** expected height of tree */
            public int height;

            /** list of input values */
            public List<Integer> inputs;

            public HeightAndDataInput(int height, List<Integer> inputs) {
                this.height = height;
                this.inputs = inputs;
            }
        }

        /**
         * data source of inputs to height test
         * @return pairs of height and input list
         */
        private List<HeightAndDataInput> listsOfHeightsAndIntegers() {
            return Arrays.asList(
                    new HeightAndDataInput(0, new ArrayList<>()),
                    new HeightAndDataInput(1, List.of(1)),
                    new HeightAndDataInput(5, Arrays.asList(1, 2, 3, 4, 5)),
                    new HeightAndDataInput(5, Arrays.asList(1, 2, 3, 4, 5, -5)),
                    new HeightAndDataInput(5, Arrays.asList(1, 2, 3, 4, -5, -10, -3, -4, -2, -20, -6, -30, -19))
            );
        }
    }

    @Nested
    @DisplayName("BinarySearchTree<String>")
    class BinarySearchTreeStringTest {

        BinarySearchTree<String> binarySearchTree;

        @BeforeEach
        void setUp() {
            this.binarySearchTree = new BinarySearchTree<>();
        }

        @ParameterizedTest
        @MethodSource("listsOfStrings")
        @DisplayName("insert values, test contain, delete values, contain values")
        void insertDeleteContain(List<String> data) {
            for (String value : data) {
                binarySearchTree.insert(value);
            }
            for (String value : data) {
                assertTrue(binarySearchTree.contain(value));
            }
            for (String value : data) {
                binarySearchTree.delete(value);
            }
            for (String value : data) {
                assertFalse(binarySearchTree.contain(value));
            }
        }

        @ParameterizedTest
        @MethodSource("listsOfStrings")
        @DisplayName("print in order, shouldn't throw exception")
        void printInOrderNotThrow(List<String> data) {
            for (String value : data) {
                binarySearchTree.insert(value);
            }
            assertDoesNotThrow(() -> {
                binarySearchTree.printInOrder();
                System.out.println();
            });
        }

        /**
         * data source
         * @return list of lists of Strings
         */
        private static List<List<String>> listsOfStrings() {
            return Arrays.asList(
                    Arrays.asList("one", "two", "three", "four", "five", "eleven"),
                    Arrays.asList("p", "e", "q", "a", "c", "b", "d", "r", "s", "u", "t", "z")
            );
        }
    }

    @Nested
    @DisplayName("BinarySearchTree<Double>")
    class BinarySearchTreeDoubleTest {

        BinarySearchTree<Double> binarySearchTree;

        @BeforeEach
        void setUp() {
            this.binarySearchTree = new BinarySearchTree<>();
        }

        @Test
        @DisplayName("double comparison")
        void doubleComparison() {
            binarySearchTree.insert(3.1415);
            assertTrue(binarySearchTree.contain(3.1415));
        }

        @ParameterizedTest
        @MethodSource("listsOfDoubles")
        @DisplayName("insert values, test contain, delete values, contain values")
        void insertDeleteContain(List<Double> data) {
            for (Double value : data) {
                binarySearchTree.insert(value);
            }
            for (Double value : data) {
                assertTrue(binarySearchTree.contain(value));
            }
            for (Double value : data) {
                binarySearchTree.delete(value);
            }
            for (Double value : data) {
                assertFalse(binarySearchTree.contain(value));
            }
        }

        @ParameterizedTest
        @MethodSource("listsOfDoubles")
        @DisplayName("print in order, shouldn't throw exception")
        void printInOrderNotThrow(List<Double> data) {
            for (Double value : data) {
                binarySearchTree.insert(value);
            }
            assertDoesNotThrow(() -> {
                binarySearchTree.printInOrder();
                System.out.println();
            });
        }

        /**
         * data source
         * @return list of lists of Doubles
         */
        private static List<List<Double>> listsOfDoubles() {
            return Arrays.asList(
                    Arrays.asList(115.0, 5.5, 6.0, 155.9, 115.62),
                    Arrays.asList(
                            1.512159,
                            1.512155,
                            1.512153,
                            1.512156,
                            1.512154,
                            1.5121515,
                            1.5121515,
                            1.5121512,
                            1.5121510,
                            1.5121513,
                            1.5121516
                    )
            );
        }
    }
}
