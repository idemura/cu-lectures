package ru.centraluniversity.stackvm;

public class Driver {
  public static void main(String[] args) {
    var s = new OpStack(10);
    s.push(2);
    s.push(3);
    System.out.println(s.pop());
    System.out.println(s.pop());
  }
}
