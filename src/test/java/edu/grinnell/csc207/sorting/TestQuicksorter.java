package edu.grinnell.csc207.sorting;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests of our Quicksorter.
 */
public class TestQuicksorter extends TestSorter {
  /**
   * Set up the sorters.
   */
  @BeforeAll
  static void setup() {
    stringSorter = new Quicksorter<String>((x,y) -> x.compareTo(y));
    intSorter = new Quicksorter<Integer>((x,y) -> x.compareTo(y));
  } // setup()

} // class TestQuicksorter
