package ru.centraluniversity.stackvm;

public class GenericPair<EType> {
  final EType a;
  final EType b;

  public GenericPair(EType a, EType b) {
    this.a = a;
    this.b = b;
  }

  EType getA() {
    return a;
  }

  EType getB() {
    return b;
  }
}
