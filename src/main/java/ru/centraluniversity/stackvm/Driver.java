package ru.centraluniversity.stackvm;

public class Driver {
  public static void main(String[] args) {
    var h = new Hasher();
    System.out.println(h.newHash(10L));
  }
}
