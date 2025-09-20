package ru.centraluniversity.stackvm;

public final class Hasher {
  long newHash(int n) {
    return n * 31L + 1;
  }

  long newHash(long n1, long n2) {
    return 31L * newHash(n1) | newHash(n2);
  }

  long newHash(long n) {
    return n * 31L + 1;
  }

  long newHash(int[] arr) {
    long h = 0;
    for (int i = 0; i < arr.length; i++) {
      h = newHash(h, arr[i]);
    }
    return h;
  }

  long newHash(String s) {
    return newHash(s, 0, s.length());
  }

  long newHash(String s, int first, int last) {
    long h = 0;
    for (int i = first; i < last; i++) {
      h = newHash(h, s.charAt(i));
    }
    return h;
  }
}
