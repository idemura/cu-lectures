package ru.centraluniversity.stackvm;

public class Trivia {
  public Trivia() {}

  // 4, 3
  public static int energy(int m, int v) {
    return m * v * v / 2;
  }

  // -13
  public static int countDigits(int n) {
    int d = 0;
    if (n <= 0) {
      d++;
    }
    while (n != 0) {
      d++;
      n /= 10;
    }
    return d;
  }

  // [5, 3, 2, 3, 2, 3]
  public static int average(int[] m) {
    long s = 0;
    for (int i = 1; i < m.length; i++) {
      s += m[i];
    }
    return (int) (s / m.length);
  }

  // [3, 0, 2, -1, 2, 0, -5]
  public static int jump(int[] m) {
    int i = 0;
    while (m[i] != 0) {
      i += m[i];
    }
    return i;
  }

  // 19
  public static boolean isPrime(int n) {
    if (n % 2 == 0) {
      return false;
    }
    if (n % 3 == 0) {
      return false;
    }
    int p = 0;
    do {
      p += 6;
      if (n % (p - 1) == 0) {
        return false;
      }
      if (n % (p + 1) == 0) {
        return false;
      }
    } while (p * p <= n);
    return true;
  }
}
