package code.stacksqueues;

import code.datastructures.Stack;

class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        boolean flag;
        for (int i = 0; i < tokens.length; i++) {
            flag = false;
            int a = 0, b = 0;
            switch (tokens[i]) {
                case "[+-*/]":
                case "+": stack.push(stack.pop() + stack.pop()); flag = true; break;
                case "-": a = stack.pop(); b = stack.pop();
                          stack.push(b - a); flag = true; break;
                case "*": stack.push(stack.pop() * stack.pop()); flag = true; break;
                case "/": a = stack.pop(); b = stack.pop();
                          stack.push(b / a); flag = true; break;
            }
            if (!flag) {
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println(
            new ReversePolishNotation().evalRPN(new String[] { "4","13","5","/","+" })
        );
    }
}