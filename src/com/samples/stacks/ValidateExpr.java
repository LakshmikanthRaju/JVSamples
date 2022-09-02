package com.samples.stacks;

import java.util.HashMap;
import java.util.Stack;

public class ValidateExpr {
    private static final HashMap<Character,Character> BRACES = new HashMap<>();

    static {
        BRACES.put('{', '}');
        BRACES.put('(', ')');
        BRACES.put('[', ']');
        BRACES.put('<', '>');
    }

    public boolean isValid(String expr) {
        Stack<Character> checker = new Stack<>();

        for (int i =0; i < expr.length(); i++) {
            if (BRACES.containsKey(expr.charAt(i))) {
                checker.push(expr.charAt(i));
            } else if (BRACES.values().contains(expr.charAt(i))) {
                if (checker.isEmpty()) return false;
                Character top = checker.pop();
                if (expr.charAt(i) != BRACES.get(top)) {
                    return false;
                }
            }
        }
        return checker.empty();
    }

    public void testSamples() {
        System.out.println("Expression is " + isValid("]"));
    }
}
