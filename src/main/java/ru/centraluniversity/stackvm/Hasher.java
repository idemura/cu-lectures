package ru.centraluniversity.stackvm;

public class Hasher {
  private final long seed;

  Hasher(long seed) {
    this.seed = seed;
  }

  long newHash(int n) {
    return n * 31L + seed;
  }

  long newHash(int n1, int n2) {
    return (n1 * 31L + seed) * 31L + n2;
  }

  long newHash(long n) {
    return n * 31L + seed;
  }

  long newHash(int[] arr) {
    long h = seed;
    for (int i = 0; i < arr.length; i++) {
      h = h * 31L + arr[i];
    }
    return h;
  }

  long newHash(String s) {
    return newHash(s, 0, s.length());
  }

  long newHash(String s, int first, int last) {
    long h = seed;
    for (int i = first; i < last; i++) {
      h = h * 31L + s.charAt(i);
    }
    return h;
  }
}
