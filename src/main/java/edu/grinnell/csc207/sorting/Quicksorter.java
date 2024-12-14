package edu.grinnell.csc207.sorting;

import java.util.Comparator;
import edu.grinnell.csc207.util.ArrayUtils;
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

  /**
   * Random field to determine our next pivot.
   */
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

  /**
   * Sort helper function. Partitions and sorts based on our bounds.
   * @param values
   *  The array we are sorting.
   * @param lowerBound
   *  The lower bound of the array portion we are sorting.
   * @param upperBound
   *  The upper bound of the array portion we are sorting.
   */
  public void sort(T[] values, int lowerBound, int upperBound) {
    if (upperBound - lowerBound < 1) {
      return;
    } //if
    int pivot = rand.nextInt(lowerBound, upperBound + 1);
    pivot = partition(values, pivot, lowerBound, upperBound);
    sort(values, lowerBound, pivot - 1);
    sort(values, pivot + 1, upperBound);
  } //sort(T[], int, int)

  /**
   * Partitions out our array so we can determine which nonsorted and sorted portions of values.
   * @param values
   *  The array of values we are sorting.
   * @param pivot
   *  The pivot we are basing our partition off of.
   * @param lowerBound
   *  The lower bound of our partition, determines values less than the pivot.
   * @param upperBound
   *  The upper bound of our partition, any thing between this and pivot is larger than the pivot.
   * @return
   *  an integer value for the next value to partition by.
   */
  public int partition(T[] values, int pivot, int lowerBound, int upperBound) {
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
        } //if/else
      } //while
      ArrayUtils.swap(values, lowerBound, large);
      return large;
    } else {
      return lowerBound;
    } //if/else
  } //partition(T[], int, int, int)
} // class Quicksorter
