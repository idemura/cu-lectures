package ru.centraluniversity.stackvm;

public class Driver {
  public static void main(String[] args) {
    var stack = new OpStack(10);

    stack.push(2);
    stack.push(3);
    Factory.makeAdd().eval(stack);

    stack.push(1);
    Factory.makeSub().eval(stack);

    stack.push(3);
    Factory.makeMul().eval(stack);

    System.out.println(stack.pop());
  }
}
