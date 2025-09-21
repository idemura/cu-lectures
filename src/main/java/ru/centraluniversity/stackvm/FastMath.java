package ru.centraluniversity.stackvm;

public class FastMath {
  // Computes a^2 - b^2 fast.
  static int squareDiff(int a, int b) {
    int x = a - b;
    int y = a + b;
    return x * y;
  }

  static long makeLong(int b0, int b1, int b2, int b3, int b4, int b5, int b6, int b7) {
    b0 = b0 | (b1 << 8);
    b2 = b2 | (b3 << 8);
    b4 = b4 | (b5 << 8);
    b6 = b6 | (b7 << 8);
    b0 = b0 | (b2 << 16);
    b4 = b4 | (b6 << 16);
    long b4l = b4;
    return b0 | (b4l << 32);
  }
}
