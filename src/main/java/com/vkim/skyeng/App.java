package com.vkim.skyeng;

public class App {

  /**
   * The maximum capacity, used if a higher value is implicitly specified
   * by either of the constructors with arguments.
   * MUST be a power of two <= 1<<30.
   */
  static final int MAXIMUM_CAPACITY = 1 << 30;

  static final int tableSizeFor(int cap) {
    int n = cap - 1;
    System.out.println(Integer.toBinaryString(n));
    n |= n >>> 1;
    System.out.println(Integer.toBinaryString(n));
    n |= n >>> 2;
    System.out.println(Integer.toBinaryString(n));
    n |= n >>> 4;
    System.out.println(Integer.toBinaryString(n));
    n |= n >>> 8;
    System.out.println(Integer.toBinaryString(n));
    n |= n >>> 16;
    System.out.println(Integer.toBinaryString(n));
    return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
  }

  public static void main(String[] args) {

    System.out.println(tableSizeFor(16));

    System.out.println(5 & 4);
  }
}
