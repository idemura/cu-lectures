package ru.centraluniversity.stackvm;

import java.util.function.IntSupplier;

public class Lambdas {
  private final int data;

  Lambdas(int data) {
    this.data = data;
  }

  static int withLambda(IntSupplier supplier) {
    return supplier.getAsInt() * 13;
  }

  void test() {
    withLambda(() -> 42);
    withLambda(() -> data);
  }
}
