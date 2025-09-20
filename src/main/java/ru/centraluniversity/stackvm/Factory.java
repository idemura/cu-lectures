package ru.centraluniversity.stackvm;

public class Factory {
  static Instruction makeAdd() {
    return new NodeAdd();
  }
  static Instruction makeSub() {
    return new NodeSub();
  }
  static Instruction makeMul() {
    return new NodeMul();
  }
}
