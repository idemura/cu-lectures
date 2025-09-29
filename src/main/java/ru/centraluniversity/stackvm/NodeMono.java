package ru.centraluniversity.stackvm;

public class NodeMono extends EvalNode {
  @Override
  public int eval(int x) {
    return x + 3;
  }
}
