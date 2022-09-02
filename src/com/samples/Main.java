package com.samples;

import com.samples.leetcode.*;
import com.samples.slls.FindMid;
import com.samples.stacks.ValidateExpr;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
	    System.out.println("Hello world");
        /*ValidateExpr validateExpr = new ValidateExpr();
        validateExpr.testSamples();*/
        /*FindMid findMid = new FindMid();
        findMid.testSamples();*/
        LeetExamples ex = LeetExamples.getInstance();
        //ex.testSamples();
        LeetListExamples exList = LeetListExamples.getInstance();
        //exList.testSamples();
        LeetTreeExamples exTree = LeetTreeExamples.getInstance();
        //exTree.testSamples();
        LeetArrayExamples exArray = LeetArrayExamples.getInstance();
        exArray.testSamples();
        LeetQueueExamples exQueue = LeetQueueExamples.getInstance();
        //exQueue.testSamples();
        //testStack();
        //System.out.println(divide(10, -3));
        //System.out.println(divide(7, -3));
        //System.out.println(divide2(-2147483648, 2));
        //System.out.println(isAnagram("anagram", "nagaram")); //2147483647
    }

    public static boolean isAnagram(String s, String t) {
        Map<Character,Integer> matchMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int times = matchMap.getOrDefault(s.charAt(i), 0);
            matchMap.put(s.charAt(i), ++times);
        }
        System.out.println(matchMap);
        for (int i = 0; i < t.length(); i++) {
            int times = matchMap.getOrDefault(t.charAt(i), 0);
            if (times == 0) {
                return false;
            }
            --times;
            if (times == 0) {
                matchMap.remove(t.charAt(i));
            } else {
                matchMap.put(t.charAt(i), times);
            }
            System.out.println(matchMap);
        }
        return matchMap.size() == 0;
    }

    public static boolean isPowerOfTwo(int n) {
        for (int i = 0; i < 32; i++) {
            if ((1<<i) > n) {
                break;
            }
            if ((n^(1<<i)) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        Set<Integer> saveSet = new HashSet<>();
        saveSet.add(n);
        int sum;
        do {
            saveSet.add(n);
            sum = 0;
            while (n>0) {
                sum = sum + (n%10)*(n%10);
                n = n/10;
            }
            n = sum;
            //System.out.println(n);
            //System.out.println(saveSet);
        } while (n != 1 && !saveSet.contains(n));
        return (n == 1);
    }

    public static int hammingWeight(int n) {
        int weight = 0;
        while (Math.abs(n) > 0) {
            weight = weight + (n%2);
            n = n/2;
            System.out.println(" %%% " + n + "  " + weight);
        }
        return weight;
    }

    private static String columnHeader(int num) {
        String value = "";
        while (num > 0) {
            value = String.format("%c", 'A'+((num-1)%26)) + value;
            num = (num-1)/26;
        }
        return value;
    }

    public static int titleToNumber2(String columnTitle) {
        int value = 0;
        for (char c: columnTitle.toCharArray()) {
            value = (value*26) + (c-'A'+1);
        }
        return value;
    }

    public static int titleToNumber(String columnTitle) {
        int i = columnTitle.length()-1 , j = 0;
        int value = 0, curValue;
        while (i>=0) {
            curValue = columnTitle.charAt(i)-'A'+1;
            curValue = (j == 0) ? curValue : curValue * (int) Math.pow(26,j);
            value = value + curValue;
            i--;
            j++;
        }
        return value;
    }

    private static void testStack() {

        Queue<Integer> stack = new LinkedList<>();
        stack.size();

        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

    private static int divide2(int dividend, int divisor) {
        int quotient = 0;
        int sign = (dividend < 0) ? -1 : 1;
        sign = (divisor < 0) ? sign*-1 : sign;
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);
        System.out.println(absDividend);
        System.out.println(absDivisor);
        while (absDividend >= absDivisor) {
            quotient++;
            absDividend = absDividend - absDivisor;
        }
        return quotient*sign;
    }

    private static int divide(int dividend, int divisor) {
        // Check for overflow
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        // Sign of result
        int sign = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0) ? -1 : 1;
        // Quotient
        int quotient = 0;
        // Take the absolute value
        long absoluteDividend = Math.abs((long) dividend);
        long absoluteDivisor = Math.abs((long) divisor);
        System.out.println(absoluteDividend);
        System.out.println(absoluteDivisor);
        // Loop until the  dividend is greater than divisor
        while (absoluteDividend >= absoluteDivisor) {
            // This represents the number of bits shifted or
            // how many times we can double the number
            int shift = 0;
            while (absoluteDividend >= (absoluteDivisor << shift)) {
                shift++;
            }
            // Add the number of times we shifted to the quotient
            quotient += (1 << (shift - 1));
            // Update the dividend for the next iteration
            absoluteDividend -= absoluteDivisor << (shift - 1);
        }
        return sign == -1 ? -quotient : quotient;
    }
}
