package ru.centraluniversity.stackvm.v0;

public class NodeSub {
  public void eval(OpStack stack) {
    int a = stack.pop();
    int b = stack.pop();
    stack.push(b - a);
  }
}
