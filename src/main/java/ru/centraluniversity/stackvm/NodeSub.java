package ru.centraluniversity.stackvm;

public final class NodeSub implements Instruction {
  @Override
  public void eval(OpStack stack) {
    int a = stack.pop();
    int b = stack.pop();
    stack.push(b - a);
  }
}
