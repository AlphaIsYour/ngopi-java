package algorithms;

import java.util.Stack;

/**
 * Implementasi Stack dan aplikasi konversi ekspresi infix ke postfix
 */
public class stack {
    public static class MyStack {
        private int maxSize;
        private int[] stackArray;
        private int top;

        public MyStack(int size) {
            maxSize = size;
            stackArray = new int[maxSize];
            top = -1;
        }

        public void push(int value) {
            if (!isFull()) {
                stackArray[++top] = value;
            } else {
                System.out.println("Stack sudah penuh");
            }
        }

        public int pop() {
            if (!isEmpty()) {
                return stackArray[top--];
            } else {
                System.out.println("Stack kosong");
                return -1;
            }
        }

        public int peek() {
            if (!isEmpty()) {
                return stackArray[top];
            } else {
                System.out.println("Stack kosong");
                return -1;
            }
        }

        public boolean isEmpty() {
            return (top == -1);
        }

        public boolean isFull() {
            return (top == maxSize - 1);
        }
    }

    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        expression = expression.replaceAll("\\s+", "");

        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return "Invalid Expression";
                }
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                return "Invalid Expression";
            }
            result.append(stack.pop());
        }
        return result.toString();
    }

    public static int evaluatePostfix(String exp) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int val1 = stack.pop();
                int val2 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '/':
                        if (val1 == 0) {
                            System.out.println("Error: Pembagian dengan nol!");
                            return Integer.MIN_VALUE;
                        }
                        stack.push(val2 / val1);
                        break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.println("\n===== DEMONSTRASI STACK =====");
        MyStack stack = new MyStack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Elemen teratas: " + stack.peek());

        System.out.println("\n===== KONVERSI INFIX KE POSTFIX =====");
        String expression = "3 + 4 * 2 / (1 - 5)";
        System.out.println("Ekspresi Infix: " + expression);
        String postfix = infixToPostfix(expression);
        System.out.println("Ekspresi Postfix: " + postfix);
        
        if (!postfix.equals("Invalid Expression")) {
            int result = evaluatePostfix(postfix);
            System.out.println("Hasil Evaluasi: " + result);
        } else {
            System.out.println("Kesalahan: Ekspresi tidak valid!");
        }
    }
}
