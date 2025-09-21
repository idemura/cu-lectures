package ru.centraluniversity.stackvm;

public class Driver {
  public static void main(String[] args) {
    System.out.println(new AsmGen("target/classes/").generateLambda().apply(5));
  }
}
