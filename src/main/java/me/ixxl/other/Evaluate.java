package me.ixxl.other;

import java.util.Stack;

public class Evaluate {

    public static double calc(String exp) {
        Stack<String> ops = new Stack<>();
        Stack<Double> vals = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            String s = exp.substring(i, i + 1);
            if (s.equals("(")) {
            } else if (s.equals("+")) {
                ops.push(s);
            } else if (s.equals("-")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals("/")) {
                ops.push(s);
            } else if (s.equals("sqrt")) {
                ops.push(s);
            } else if (s.equals(")")) {
                String op = ops.pop();
                double v = vals.pop();
                if (op.equals("+")) {
                    v = vals.pop() + v;
                } else if (op.equals("-")) {
                    v = vals.pop() - v;
                } else if (op.equals("*")) {
                    v = vals.pop() * v;
                } else if (op.equals("/")) {
                    v = vals.pop() / v;
                } else if (op.equals("sqrt")) {
                    v = Math.sqrt(v);
                }
                vals.push(v);
            } else {
                vals.push(Double.parseDouble(s));
            }
        }
        return vals.pop();
    }

    public static void main(String[] args) {
        String exp = "(1+((2+3)*(4*5)))";
        double res = calc(exp);
        System.out.println(res);
    }
}
