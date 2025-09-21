package ru.centraluniversity.stackvm;

public class OpStack {
  private final int[] stack;
  private int n;

  public OpStack(int maxSize) {
    this.stack = new int[maxSize];
  }

  public void push(int value) {
    if (n == stack.length) {
      throw new RuntimeException("Overflow");
    }
    stack[n++] = value;
  }

  public int pop() {
    if (n == 0) {
      throw new RuntimeException("Empty");
    }
    return stack[--n];
  }
}
