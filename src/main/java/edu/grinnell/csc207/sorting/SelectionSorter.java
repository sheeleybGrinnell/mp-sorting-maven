package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

/**
 * Something that sorts using selection sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Benjamin Sheeley
 */

public class SelectionSorter<T> implements Sorter<T> {
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
  public SelectionSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // SelectionSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using selection sort.
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
    int smallestValIndex = 0;
    // initial loop, exited once sorted
    for (int i = 0; i < values.length; i++) {
      smallestValIndex = indexOfSmallest(values, i);
      ArrayUtils.swap(values, smallestValIndex, i);
    } //for
  } // sort(T[])

  /**
   * Finds the smallest item's index to swap our compareVal with.
   * @param values
   *  The array of values that we are sorting.
   * @param compareVal
   *  the index of the value we use to compare to our smallest val.
   * @return
   *  the index of the smallest value in our array so far.
   */
  int indexOfSmallest(T[] values, int compareVal) {
    int smallestSoFar = compareVal;
    for (int j = compareVal; j < values.length; j++) {
      if (this.order.compare(values[smallestSoFar], values[j]) > 0) {
        smallestSoFar = j;
      } //if
    } //for
    return smallestSoFar;
  } //indexOfSmallest(T[], int)
} // class SelectionSorter
