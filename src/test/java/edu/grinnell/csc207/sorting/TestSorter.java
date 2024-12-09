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
  public void insertionTests() {
    Sorter<Integer> testInsert = new InsertionSorter<Integer>(new Comparator<Integer>() {
      public int compare(Integer i1, Integer i2) {      
        return i1 - i2;
      }
    });
    Integer[] sortedArray = new Integer[] {0, 1, 2, 3, 4};
    assertSorts(sortedArray, new Integer[] {0, 1, 2, 3, 4}, testInsert);
    assertSorts(sortedArray, new Integer[] {1, 2, 4, 3, 0}, testInsert);
    assertSorts(sortedArray, new Integer[] {4, 3, 2, 1, 0}, testInsert);
    assertSorts(new Integer[] {}, new Integer[] {}, testInsert);
  }

  @Test
  public void selectionTests() {
    Sorter<Integer> testSelect = new SelectionSorter<Integer>(new Comparator<Integer>() {
      public int compare(Integer i1, Integer i2) {      
        return i1 - i2;
      }
    });
    Integer[] sortedArray = new Integer[] {0, 1, 2, 3, 4};
    assertSorts(sortedArray, new Integer[] {0, 1, 2, 3, 4}, testSelect);
    assertSorts(sortedArray, new Integer[] {1, 2, 4, 3, 0}, testSelect);
    assertSorts(sortedArray, new Integer[] {4, 3, 2, 1, 0}, testSelect);
    assertSorts(new Integer[] {}, new Integer[] {}, testSelect);
  }

  @Test
  public void quickTests() {
    Sorter<Integer> testQuick = new Quicksorter<Integer>(new Comparator<Integer>() {
      public int compare(Integer i1, Integer i2) {      
        return i1 - i2;
      }
    });
    Integer[] sortedArray = new Integer[] {0, 1, 2, 3, 4};
    assertSorts(sortedArray, new Integer[] {0, 1, 2, 3, 4}, testQuick);
    assertSorts(sortedArray, new Integer[] {1, 2, 4, 3, 0}, testQuick);
    assertSorts(sortedArray, new Integer[] {4, 3, 2, 1, 0}, testQuick);
    assertSorts(new Integer[] {}, new Integer[] {}, testQuick);
  }

  @Test
  public void mergeTests() {
    Sorter<Integer> testMerge = new MergeSorter<Integer>(new Comparator<Integer>() {
      public int compare(Integer i1, Integer i2) {      
        return i1 - i2;
      }
    });
    Integer[] sortedArray = new Integer[] {0, 1, 2, 3, 4};
    assertSorts(sortedArray, new Integer[] {0, 1, 2, 3, 4}, testMerge);
    assertSorts(sortedArray, new Integer[] {1, 2, 4, 3, 0}, testMerge);
    assertSorts(sortedArray, new Integer[] {4, 3, 2, 1, 0}, testMerge);
    assertSorts(new Integer[] {}, new Integer[] {}, testMerge);
  }

  @Test
  public void sheeleySortTests() {
    Sorter<Integer> testSheeleySort = new SheeleyBenjaminSorter<Integer>(new Comparator<Integer>() {
      public int compare(Integer i1, Integer i2) {      
        return i1 - i2;
      }
    });
    Integer[] sortedArray = new Integer[] {0, 1, 2, 3, 4};
    assertSorts(sortedArray, new Integer[] {0, 1, 2, 3, 4}, testSheeleySort);
    assertSorts(sortedArray, new Integer[] {1, 2, 4, 3, 0}, testSheeleySort);
    assertSorts(sortedArray, new Integer[] {4, 3, 2, 1, 0}, testSheeleySort);
    assertSorts(new Integer[] {}, new Integer[] {}, testSheeleySort);
  }
} // class TestSorter
