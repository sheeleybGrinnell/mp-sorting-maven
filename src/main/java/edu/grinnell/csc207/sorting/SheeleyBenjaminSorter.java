package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using insertion sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Benjamin Sheeley
 */
public class SheeleyBenjaminSorter<T> implements Sorter<T> {
  
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter.
   */
  public SheeleyBenjaminSorter() {
  } // FakeSorter()

  /**
   * Create a sorter using a particular comparator (included for
   * consistency with other sorters).
   *
   * @param order
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public SheeleyBenjaminSorter(Comparator<? super T> order) {
  } // FakeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place.
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
    // STUB
  } // sort(T[])
}
