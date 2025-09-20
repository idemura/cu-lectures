package ru.centraluniversity.stackvm.v1;

public final class NodeAdd implements Instruction {
  public void eval(OpStack stack) {
    int a = stack.pop();
    int b = stack.pop();
    stack.push(a + b);
  }
}
