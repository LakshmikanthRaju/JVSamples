package com.samples.slls;

public class FindMid {

    private boolean hasLoop(SLNode head) {
        SLNode slow = head;
        SLNode fast = head;
        while (fast != null && fast.next != null) {
            slow = head.next;
            fast = head.next.next;
            if (slow == fast) {
               return true;
            }
        }
        return false;
    }

    private SLNode getLoopNode(SLNode head) throws InterruptedException {
        SLNode slow = head;
        SLNode fast = head;
        while (fast != null && fast.next != null) {
            //System.out.println(fast.data + "    " + slow.data);
            //Thread.sleep(5000);
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow != fast) {
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public SLNode findMiddle(SLNode head) throws InterruptedException {
        SLNode slow = head;
        SLNode fast = head;
        SLNode loopNode = getLoopNode(head);
        if (loopNode != null) {
            System.out.println("Found loop at " + loopNode.data);
        }

        while (fast != loopNode && fast.next != loopNode) {
        //while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (loopNode != null) {
            slow = slow.next;
            fast = fast.next.next;
            while (fast != loopNode && fast.next != loopNode) {
                //while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
        }

        return slow;
    }

    public void testSamples() {
        try {
            System.out.println("Middle element is " + findMiddle(sample1()).data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private SLNode sample1() {
        SLNode head = new SLNode(1);
        head.next = new SLNode(2);
        head.next.next = new SLNode(3);
        head.next.next.next = new SLNode(4);
        head.next.next.next.next = new SLNode(5);
        head.next.next.next.next.next = new SLNode(6);
        head.next.next.next.next.next.next = new SLNode(7);
        head.next.next.next.next.next.next.next = new SLNode(8);
        head.next.next.next.next.next.next.next.next = head;
        return head;
    }
}
