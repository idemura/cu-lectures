package ru.centraluniversity.stackvm;

import java.util.ArrayList;
import java.util.List;

public class Generics {
  private List<String> names = new ArrayList<>();

  void add(String s) {
    names.add(s);
  }

  String get(int n) {
    return names.get(n);
  }

  static String pair(GenericPair<String> p) {
    return p.getA().substring(0, 10);
  }
}
