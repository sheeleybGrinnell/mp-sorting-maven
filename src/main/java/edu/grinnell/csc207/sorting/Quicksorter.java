package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.*;

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
    int pivot = 0;
    DNF(values, pivot);
  } // sort(T[])

  public void DNF(T[] values, int pivot) {
    int lowerBound = 0;
    int upperBound = values.length - 1;
    while (lowerBound < upperBound) {
      int i = lowerBound + 1;
      if (this.order.compare(values[i], values[pivot]) > 0) {
        ArrayUtils.swap(values, i, lowerBound);
        lowerBound++;
      } else if (this.order.compare(values[i], values[pivot]) < 0) {
        ArrayUtils.swap(values, i, upperBound);
        upperBound--;
      }
    }
  }
} // class Quicksorter
