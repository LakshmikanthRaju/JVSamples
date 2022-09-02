package com.samples.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetListExamples {

    private static LeetListExamples examples = new LeetListExamples();

    private LeetListExamples() {}

    public static LeetListExamples getInstance() {
        return examples;
    }

    public static class ListNode {
        int val;
        LeetListExamples.ListNode next;
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public void testSamples() throws Exception {
        //ListNode head = buildList();
        //printList(head);
        //head = removeNthFromEnd(head, 2);
        //head = swapPairs(head);
        //head = mergeKLists(buildLists());
        //printList(head);
        //ListNode head2 = buildList(head);
        ListNode head3 = buildList3();
        printList(head3);
        ListNode mid = partition(head3, 3);
        printList(mid);
        //System.out.println("Mid: " + mid);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode xNode = null;
        ListNode xLeft = head;
        ListNode xRight = null;
        ListNode temp = head;
        while (temp != null) {
            if (temp.val == x) {
                xNode = temp;
                xRight = temp.next;
                break;
            }
            temp = temp.next;
        }
        if (xNode == null || xRight == null) {
            return head;
        }
        if (head == xNode) {
            xLeft = null;
        }
        temp = xRight;
        xRight = null;
        ListNode prev = xNode;
        while (temp != null) {
            if (temp.val < xNode.val) {
                ListNode temp1 = temp.next;
                xLeft = putNode(xLeft, temp, xNode);
                temp = temp1;
                prev.next = temp;
            } else {
                if (xRight == null) {
                    xRight = temp;
                }
                prev = temp;
                temp = temp.next;
            }
        }
        return xLeft;
    }

    public ListNode putNode(ListNode xLeft, ListNode xRight, ListNode xNode) {
        if (xLeft == null) {
            xRight.next = xNode;
            return xRight;
        }
        if (xLeft.val > xRight.val) {
            xRight.next = xLeft;
            return xRight;
        }
        ListNode temp = xLeft;
        while (temp.next != xNode && temp.next.val < xRight.val) {
            temp = temp.next;
        }
        xRight.next = temp.next;
        temp.next = xRight;
        return xLeft;
    }
    public ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = new ListNode(-10000, head);
        ListNode temp = newHead;
        ListNode prev = null;
        while (temp != null &&temp.next != null) {
            if (temp.val != temp.next.val) {
                prev = temp;
                temp = temp.next;
            } else {
                while (temp.next != null && temp.val == temp.next.val) {
                    temp = temp.next;
                }
                prev.next = temp.next;
                temp = temp.next;
            }
        }
        return newHead.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode head1 = headA;
        ListNode head2 = headB;
        while (head1 != head2) {
            head1 = (head1.next == null) ? headB : head1.next;
            head2 = (head2.next == null) ? headA : head2.next;
        }
        return head1;
    }

    public ListNode mergeKLists(ListNode[] lists) throws Exception {
        if (lists.length == 0) return null;
        int last = lists.length-1;
        while (last != 0) {
            int i = 0;
            int j = last;
            while (i < j) {
                lists[i] = sortedMerge(lists[i], lists[j]);
                i++;
                j--;
                if (i>=j) {
                    last =j;
                }
            }
        }
        return lists[0];
    }

    private ListNode sortedMerge(ListNode list1, ListNode list2) throws Exception {
        ListNode result = null;
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        printList(list1);
        printList(list2);
        //Thread.sleep(5000);
        if (list1.val < list2.val) {
            result = list1;
            result.next = sortedMerge(list1.next, list2);
        } else {
            result = list2;
            result.next = sortedMerge(list1, list2.next);
        }
        return result;
    }

    public ListNode swapPairs(ListNode head) throws Exception {
        if (head == null || head.next == null) return head;

        ListNode result = new ListNode(0);
        ListNode value = result;
        int i = 0;
        while (head != null && head.next != null) {
            //result.next = swap(head);
            result.next = head.next;
            ListNode end = result.next.next;
            result.next.next = head;
            head.next = end;

            result = result.next.next;
            head = result.next;

        }
        return value.next;
    }

    private ListNode swap(ListNode temp) {
        ListNode head = temp.next;
        ListNode end = head.next;
        head.next = temp;
        temp.next = end;
        return head;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode first = head;
        ListNode second = head;
        int i = 0;
        while (i < n) {
            first = first.next;
            i++;
        }
        if (first == null) return head.next;
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode temp = head;
        removeNth(head, n);
        return head;
    }

    private int removeNth(ListNode head,int n) {
        if (head.next != null) {
            n = removeNth(head.next, n);
        }
        System.out.println("=> " + head.val + " : " + n);
        if (n == 0) {
            head.next = head.next.next;
        }
        return n-1;
    }

    private ListNode buildList() {
        ListNode head = new ListNode(4);
        head.next = new ListNode(1);
        head.next.next = new ListNode(8);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        //head.next.next.next.next.next = new ListNode(6);
        return head;
    }

    private ListNode buildList(ListNode head2) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(6);
        head.next = head2.next.next;
        return head;
    }

    private ListNode[] buildLists() {
        ListNode[] arr = new ListNode[3];

        arr[0] = new ListNode(2);
        arr[0].next = new ListNode(5);
        arr[0].next.next = new ListNode(8);

        arr[1] = new ListNode(1);
        arr[1].next = new ListNode(4);
        arr[1].next.next = new ListNode(7);
        arr[1].next.next.next = new ListNode(10);

        arr[2] = new ListNode(3);
        arr[2].next = new ListNode(6);
        arr[2].next.next = new ListNode(9);

        return arr;
    }

    private ListNode buildList3() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(0);
        head.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(2);
        return head;
    }

    private void printList(ListNode head) {
        while (head != null) {
            System.out.print("=> " + head.val);
            head = head.next;
        }
        System.out.println(" --------- ");
    }
}
