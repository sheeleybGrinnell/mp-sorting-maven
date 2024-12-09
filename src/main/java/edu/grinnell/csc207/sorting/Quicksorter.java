package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.*;
import java.util.Random;

/**
 * Something that sorts using Quicksort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Benjamin Sheeley
 */

public class Quicksorter<T> implements Sorter<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The way in which elements are ordered.
   */
  Comparator<? super T> order;

  Random rand = new Random(0);

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator.
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public Quicksorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // Quicksorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using Quicksort.
   *
   * @param values
   *   an array to sort.
   *
   * @post
   *   The array has been sorted according to some order (often
   *   one given to the constructor).
   * @post
   *   For all i, 0 &lt; i &lt; values.length,
   *     order.compare(values[i-1], values[i]) &lt;= 0
   */
  @Override
  public void sort(T[] values) {
    sort(values, 0, values.length - 1);
  } // sort(T[])

  public void sort(T[] values, int lowerBound, int upperBound) {
    if (upperBound - lowerBound < 1){ 
      return;
    }
    int pivot = rand.nextInt(lowerBound, upperBound + 1);
    pivot = parition(values, pivot, lowerBound, upperBound);
    sort(values, lowerBound, pivot - 1);
    sort(values, pivot + 1, upperBound);
  }

  public int parition(T[] values, int pivot, int lowerBound, int upperBound) {
    if (upperBound - lowerBound > 0) {
      ArrayUtils.swap(values, lowerBound, pivot);
      int small = lowerBound;
      int large = upperBound;
      while (small <= large) {
        if (order.compare(values[small], values[lowerBound]) > 0) {
          ArrayUtils.swap(values, small, large);
          large--;
        } else {
          small++;
        }
      }
      ArrayUtils.swap(values, lowerBound, large);
      return large;
    } else {
      return lowerBound;
    }
  }
} // class Quicksorter
