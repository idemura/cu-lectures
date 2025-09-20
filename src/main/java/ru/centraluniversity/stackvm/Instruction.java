package ru.centraluniversity.stackvm;

public interface Instruction {
  void eval(OpStack stack);
}
