package edu.brown.cs.student;

import java.util.Random;

public class FuzzTestingHelpers {
  private static final Random randomNum = new Random();
  /** Generates Random Number */
  public static int rng(int min, int max) {
    return randomNum.nextInt(max) + min;
  }
  /**
   * Generates random string of certain length. Comment out <int randomAscii = randomNum.nextInt(45,
   * 127);> and write in <int randomAscii = randomNum.nextInt(96) + 32;> in order to test with
   * random spacing in between.
   */
  public String generateRandomString(int length) {
    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      // int randomAscii = randomNum.nextInt(96) + 32;
      // no spaces
      int randomAscii = randomNum.nextInt(45, 127);
      sb.append((char) randomAscii);
    }
    return sb.toString();
  }
}
