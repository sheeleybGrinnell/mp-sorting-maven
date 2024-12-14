package edu.grinnell.csc207.sorting;

import java.util.Comparator;

/**
 * Something that sorts using merge sort.
 *
 * @param <T>
 *   The types of values that are sorted.
 *
 * @author Samuel A. Rebelsky
 * @author Benjamin Sheeley
 */

public class MergeSorter<T> implements Sorter<T> {
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
  public MergeSorter(Comparator<? super T> comparator) {
    this.order = comparator;
  } // MergeSorter(Comparator)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Sort an array in place using merge sort.
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
    int lowerBound = 0;
    int upperBound = values.length;
    split(values, lowerBound, upperBound);
  } // sort(T[])

  /**
   * A way to split up our array so we can sort and remerge later.
   * @param values
   *  The array we are sorting.
   * @param lowerBound
   *  The lower bound of the portion of values we are sorting.
   * @param upperBound
   *  The upper bound of the portion of values we are sorting.
   */
  public void split(T[] values, int lowerBound, int upperBound) {
    if (upperBound - lowerBound < 2) {
      return;
    } //if
    T[] newArr;
    int midpoint =  lowerBound + ((upperBound - lowerBound) / 2);
    split(values, lowerBound, midpoint);
    split(values, midpoint, upperBound);
    newArr = merge(values, lowerBound, midpoint, upperBound);
    for (int i = lowerBound; i < upperBound; i++) {
      values[i] = newArr[i - lowerBound];
    } //for
  } //split


  /**
   * How we compare, sort, and merge our values back into a new array.
   * @param values
   *  The array of values we are sorting.
   * @param leftLower
   *  The lower bound of our array, notably on the left-hand side.
   * @param midpoint
   *  The midpoint we use to determine where our array is split.
   * @param rightUpper
   *  The upper bound of our array, notably on the right-hand side.
   * @return
   *  A merged and sorted portion of any array.
   */
  public T[] merge(T[] values, int leftLower, int midpoint, int rightUpper) {
    T[] newArr = values.clone();
    for (int i = 0; i < newArr.length; i++) {
      newArr[i] = null;
    } //for
    int newArrIndex = 0;
    int leftIndex = leftLower;
    int rightIndex = midpoint;
    while ((leftIndex < midpoint) && (rightIndex < rightUpper)) {
      if (order.compare(values[leftIndex], values[rightIndex]) < 0) {
        newArr[newArrIndex] = values[leftIndex];
        leftIndex++;
        newArrIndex++;
      } else {
        newArr[newArrIndex] = values[rightIndex];
        rightIndex++;
        newArrIndex++;
      } //else
    } //while
    while (leftIndex < midpoint) {
      newArr[newArrIndex] = values[leftIndex];
      leftIndex++;
      newArrIndex++;
    } //while
    while (rightIndex < rightUpper) {
      newArr[newArrIndex] = values[rightIndex];
      rightIndex++;
      newArrIndex++;
    } //while
    return newArr;
  } //merge(T[], int, int, int)
} // class MergeSorter
