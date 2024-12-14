package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;

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

  //Fields

  /**
   * Gives us a baseline for comparison.
   */
  Comparator<? super T> order;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a sorter using a particular comparator (included for
   * consistency with other sorters).
   *
   * @param comparator
   *   The order in which elements in the array should be ordered
   *   after sorting.
   */
  public SheeleyBenjaminSorter(Comparator<? super T> comparator) {
    this.order = comparator;
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
    oddEvenSort(values, 0);
    oddEvenSort(values, 1);
  } // sort(T[])


  /**
   * Sorts based on odd and even indices.
   * @param values
   *  The list of values to sort.
   * @param startIndex
   *  The index we start at, determines if we sort evenly or oddly.
   */
  public void oddEvenSort(T[] values, int startIndex) {
    boolean sorted = true;
    while (!sorted) {
      sorted = true;
      for (int i = startIndex; i < values.length; i += 2) {
        if (this.order.compare(values[i], values[i + 2]) > 0) {
          ArrayUtils.swap(values, i, (i + 2));
          sorted = false;
        } //if
      } //for
    } //while
  } //oddEvenSort(T[], int)

} //SheeleyBenjaminSort
