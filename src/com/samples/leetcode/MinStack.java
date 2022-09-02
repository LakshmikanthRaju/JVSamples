package com.samples.leetcode;

import java.util.Stack;

public class MinStack {

    private final Stack<Integer> minStack;
    private final Stack<Integer> minElemStack;

    public MinStack() {
        minStack = new Stack<>();
        minElemStack = new Stack<>();
    }

    public void push(int val) {
        minStack.push(val);
        if (minElemStack.isEmpty() || val <= minElemStack.peek()) {
            minElemStack.push(val);
        }
        System.out.println(" ++++ " + minStack);
        System.out.println(" ++++ " + minElemStack);
    }

    public void pop() {
        if (minStack.peek().intValue() == minElemStack.peek().intValue()) {
            minElemStack.pop();
        }
        minStack.pop();
        System.out.println(" ---- " + minStack);
        System.out.println(" ---- " + minElemStack);
    }

    public int top() {
        return minStack.peek();
    }

    public int getMin() {
        return minElemStack.peek();
    }
}