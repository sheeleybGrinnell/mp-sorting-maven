package edu.grinnell.csc207.sorting;

import edu.grinnell.csc207.util.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * Tests of Sorter objects. Please do not use this class directly.
 * Rather, you should subclass it and initialize stringSorter and
 * intSorter in a static @BeforeAll method.
 *
 * @author Benjamin Sheeley
 * @uathor Samuel A. Rebelsky
 */
public class TestSorter {

  // +---------+-----------------------------------------------------
  // | Globals |
  // +---------+

  /**
   * The sorter we use to sort arrays of strings.
   */
  static Sorter<String> stringSorter = null;

  /**
   * The sorter we use to sort arrays of integers.
   */
  static Sorter<Integer> intSorter = null;

  // +-----------+---------------------------------------------------
  // | Utilities |
  // +-----------+

  /**
   * Given a sorted array and a permutation of the array, sort the
   * permutation and assert that it equals the original.
   *
   * @param <T>
   *   The type of values in the array.
   * @param sorted
   *   The sorted array.
   * @param perm
   *   The permuted sorted array.
   * @param sorter
   *   The thing to use to sort.
   */
  public <T> void assertSorts(T[] sorted, T[] perm, Sorter<? super T> sorter) {
    T[] tmp = perm.clone();
    sorter.sort(perm);
    assertArrayEquals(sorted, perm,
      () -> String.format("sort(%s) yields %s rather than %s",
          Arrays.toString(tmp), 
          Arrays.toString(perm), 
          Arrays.toString(sorted)));
  } // assertSorts

  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * A fake test. I've forgotten why I've included this here. Probably
   * just to make sure that some test succeeds.
   */
  @Test
  public void fakeTest() {
    assertTrue(true);
  } // fakeTest()

  /**
   * Ensure that an array that is already in order gets sorted correctly.
   */
  @Test
  public void orderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    String[] expected = original.clone();
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that an array that is ordered backwards gets sorted correctly.
   */
  @Test
  public void reverseOrderedStringTest() {
    if (null == stringSorter) {
      return;
    } // if
    String[] original = { "foxtrot", "delta", "charlie", "bravo", "alpha" };
    String[] expected = { "alpha", "bravo", "charlie", "delta", "foxtrot" };
    assertSorts(expected, original, stringSorter);
  } // orderedStringTest

  /**
   * Ensure that a randomly permuted version of a moderate-sized
   * array sorts correctly.
   */
  @Test 
  public void permutedIntegersTest() { 
    int SIZE = 100; 
    if (null == intSorter) { 
      return; 
    } // if
    Integer[] original = new Integer[SIZE];
    for (int i = 0; i < SIZE; i++) {
      original[i] = i;
    } // for
    Integer[] expected = original.clone();
    ArrayUtils.permute(original);
    assertSorts(expected, original, intSorter);
  } // permutedIntegers



  @Test
  public void testEmpty() {
    if ((intSorter == null) || (stringSorter) == null) {
      return;
    }
    Integer[] emptyIntArr = {};
    String[] emptyStrArr = {};
    assertSorts(emptyIntArr, emptyIntArr, intSorter);
    assertSorts(emptyStrArr, emptyStrArr, stringSorter);
  }

  @Test
  public void testBackwards() {
    if ((intSorter == null) || (stringSorter) == null) {
      return;
    }
    Integer[] testIntArr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    Integer[] sortedTestInt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    String[] testStrArr = {"zebra", "yam", "tuatara", "coconut", "banana", "apple"};
    String[] sortedTestStr = {"apple", "banana", "coconut", "tuatara", "yam", "zebra"};
    assertSorts(sortedTestInt, testIntArr, intSorter);
    assertSorts(sortedTestStr, testStrArr, stringSorter);
  }

  @Test
  public void testUneven() {
    if ((intSorter == null) || (stringSorter) == null) {
      return;
    }
    Integer[] testIntArr = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    Integer[] sortedTestInt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String[] testStrArr = {"zebra", "yam", "tuatara", "banana", "apple"};
    String[] sortedTestStr = {"apple", "banana", "tuatara", "yam", "zebra"};
    assertSorts(sortedTestInt, testIntArr, intSorter);
    assertSorts(sortedTestStr, testStrArr, stringSorter);
  }

  @Test
  public void testSorted() {
    if ((intSorter == null) || (stringSorter) == null) {
      return;
    }
    Integer[] testIntArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Integer[] sortedTestInt = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String[] testStrArr = {"apple", "banana", "tuatara", "yam", "zebra"};
    String[] sortedTestStr = {"apple", "banana", "tuatara", "yam", "zebra"};
    assertSorts(sortedTestInt, testIntArr, intSorter);
    assertSorts(sortedTestStr, testStrArr, stringSorter);
  }

  @Test
  public void testRepeated() {
    if ((intSorter == null) || (stringSorter) == null) {
      return;
    }
    Integer[] testIntArr = {2, 2, 2, 2, 2, 2, 2};
    String[] testStrArr = {"apple", "apple", "apple", "apple"};
    assertSorts(testIntArr, testIntArr, intSorter);
    assertSorts(testStrArr, testStrArr, stringSorter);
  }
} // class TestSorter
