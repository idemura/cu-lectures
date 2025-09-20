package ru.centraluniversity.stackvm;

public class OpStack {
  private final int[] stack;
  private int top;

  public OpStack(int n) {
    this.stack = new int[n];
  }

  public void push(int n) {
    if (n == stack.length) {
      throw new RuntimeException("Stack overflow");
    }
    stack[top++] = n;
  }

  public int pop() {
    if (top == 0) {
      throw new RuntimeException("Empty stack");
    }
    return stack[--top];
  }
}
