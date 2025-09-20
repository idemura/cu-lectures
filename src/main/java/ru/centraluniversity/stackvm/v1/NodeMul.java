package ru.centraluniversity.stackvm.v1;

public class NodeMul implements Instruction {
  @Override
  public void eval(OpStack stack) {
    int a = stack.pop();
    int b = stack.pop();
    stack.push(a * b);
  }
}
