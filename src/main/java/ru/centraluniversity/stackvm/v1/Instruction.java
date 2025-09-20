package ru.centraluniversity.stackvm.v1;

public interface Instruction {
  void eval(OpStack stack);
}
