package ru.centraluniversity.stackvm;

public class Lambdas {
  private final int data;

  Lambdas(int data) {
    this.data = data;
  }

  static long withLambda(LambdaFunc f) {
    return f.apply(12);
  }

  void test() {
    int capture = 12;
    withLambda((n) -> n + 42);
    withLambda((n) -> n * data);
    withLambda((n) -> n - capture);
  }
}
