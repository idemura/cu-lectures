package ru.centraluniversity.stackvm;

public class Driver {
  public static void main(String[] args) {
    try {
      var lambda = new AsmGen("target/classes/").generateInvokeDynamic();
      System.out.println(lambda.apply(12));
      System.out.println(lambda.apply(13));
    } catch (ReflectiveOperationException e) {
      e.printStackTrace(System.err);
    }
  }
}
