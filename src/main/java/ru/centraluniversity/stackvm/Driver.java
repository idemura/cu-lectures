package ru.centraluniversity.stackvm;

public class Driver {
  public static void main(String[] args) {
    var stack = new OpStack(10);

    stack.push(2);
    stack.push(3);
    new NodeAdd().eval(stack);

    stack.push(1);
    new NodeSub().eval(stack);

    stack.push(3);
    new NodeMul().eval(stack);

    System.out.println(stack.pop());
  }
}
