package ru.centraluniversity.stackvm;

public class Driver {
  public static void main(String[] args) {
    var stack = new OpStack(10);
    stack.push(2);
    stack.push(3);
    new NodeAdd().eval(stack);
    System.out.println(stack.pop());

    stack.push(3);
    stack.push(2);
    new NodeSub().eval(stack);
    System.out.println(stack.pop());
  }
}
