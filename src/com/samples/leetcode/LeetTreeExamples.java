package com.samples.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LeetTreeExamples {
    private static LeetTreeExamples examples = new LeetTreeExamples();

    private LeetTreeExamples() {}

    public static LeetTreeExamples getInstance() {
        return examples;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void testSamples() throws Exception {
        TreeNode root = getTree2();
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        //System.out.println(lowestCommonAncestor(root2, root2, root2.left).val);
        System.out.println(levelOrder(getTree3()));
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> nextNodes = new Stack<>();
        Stack<TreeNode> tempNodes = new Stack<>();
        nextNodes.push(root);
        while (!nextNodes.isEmpty()) {
            result.add(nextNodes.peek().val);
            while (!nextNodes.isEmpty()) {
                tempNodes.push(nextNodes.pop());
            }
            while (!tempNodes.isEmpty()) {
                nextNodes.push(tempNodes.pop());
            }
        }
        return result;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        leverOrderResult(root, 0, result);
        return result;
    }

    private void leverOrderResult(TreeNode root, int level, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        if (result.size() == level) {
            result.add(new ArrayList<>());
            result.get(level).add(root.val);
            System.out.println("Created: " + result.get(level));
        } else if (result.size() == level + 1) {
            List<Integer> levelResult = result.get(level);
            System.out.print(level + " -> (");
            System.out.print(levelResult + ") = ");
            System.out.println(levelResult.size());
            result.get(level).add(root.val);
            //levelResult.add(root.val);
        } else {
            System.out.println("NOOOOOOOOOOOOO!!!!!!!!!!!!!");
            return;
        }
        leverOrderResult(root.left, level+1, result);
        leverOrderResult(root.right, level+1, result);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode small = p, large = q;
        if (p.val > q.val) {
            large = p;
            small = q;
        }
        return findLCA(root, small, large);
    }

    private TreeNode findLCA(TreeNode temp, TreeNode small, TreeNode large) {
        System.out.printf("%d-%d-%d", temp.val, small.val, large.val);
        System.out.println("");
        if (temp.val < small.val) {
            return findLCA(temp.right, small, large);
        } else if (temp.val > large.val) {
            return findLCA(temp.left, small, large);
        }
        return temp;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        return hasSum(root, targetSum);
    }

    private boolean hasSum(TreeNode root, int targetSum) {
        if (root == null) {
            List<Integer> val = Arrays.asList(1,2,3,4,5);
            return false;
        }
        int newSum = targetSum - root.val;
        if (root.left == null && root.right == null) {
            return (newSum == 0);
        }
        return hasSum(root.left, newSum) || hasSum(root.right, newSum);
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int height1 = getHeight(root.left);
        int height2 = getHeight(root.right);
        System.out.printf("(%d, %d) of %d%n", height1, height2, root.val);
        return Math.abs(height1-height2) <= 1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int height1 = getHeight(root.left);
        int height2 = getHeight(root.right);
        int height = Math.max(height1, height2) + 1;
        System.out.printf("(%d, %d) = %d of %d%n", height1, height2, height, root.val);
        return height;
    }

    private TreeNode getTree1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.left.left.left.right = new TreeNode(7);
        return root;
    }

    private TreeNode getTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(4);
        return root;
    }

    private TreeNode getTree2() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        return root;
    }

    private TreeNode getTree3() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        return root;
    }
}
