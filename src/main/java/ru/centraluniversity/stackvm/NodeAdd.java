package ru.centraluniversity.stackvm;

public class NodeAdd {
  public void eval(OpStack stack) {
    int a = stack.pop();
    int b = stack.pop();
    stack.push(a + b);
  }
}
