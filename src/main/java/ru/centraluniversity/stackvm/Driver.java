package ru.centraluniversity.stackvm;

public class Driver {
  public static void main(String[] args) {
    new AsmGen("target/classes/").generateInvokeDynamic();
  }
}
