package code.linkedlist;

import java.util.*;

class LinkedListFunctions
{
    LLNode last = null;
    void printList(LLNode head)
    {
        LLNode temp = head;
        while (temp != null)
        {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
        System.out.println();
    }

    void printListWithoutSpace(LLNode head)
    {
        LLNode temp = head;
        while (temp != null)
        {
            System.out.print(temp.data);
            temp = temp.next;
        }
        System.out.println();
    }


    LLNode InsertAtEnd(LLNode head, int data)
    {
        if(head == null) return new LLNode(data);
        LLNode curNode = head;
        while(curNode.next != null) curNode = curNode.next;
        curNode.next = new LLNode(data);
        return head;
    }

    LLNode deleteNode(LLNode h, int data)
    {
        LLNode head = h;
        if(head.data == data) return null;
        while(head.next != null) {
            if(head.next.data == data) {
                LLNode tempNode = head.next;
                head.next = head.next.next;
                tempNode.next = null;
                tempNode = null;
                break;
            }
            head = head.next;
        }
        return h;
    }

    void deleteNodeWithoutHead(LLNode nodeToDelete)
    {
        nodeToDelete.data = nodeToDelete.next.data;
        LLNode tempNode = nodeToDelete.next;
        nodeToDelete.next = nodeToDelete.next.next;
        tempNode.next = null;
        tempNode = null;
    }

    LLNode getMiddleNode(LLNode h) {
        LLNode slow = h, fast = h;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            if(fast == null) {
                return slow;
            }
            slow = slow.next;
        }
        return slow;
    }

    LLNode reverse(LLNode h) {
        LLNode prev = null;
        LLNode cur = h;
        while(cur != null) {
            LLNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    boolean compare(LLNode a, LLNode b) {
        while(b != null) {
            if(a.data != b.data) return false;
            a = a.next;
            b = b.next;
        }
        return true;
    }

    boolean isPalindromicList(LLNode  head)
    {
        if(head == null || head.next == null) return true;
        LLNode middleNode = getMiddleNode(head);
        return compare(head, reverse(middleNode.next));

    }

    LLNode detectCycle(LLNode head) {
        LLNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }
        return fast;
    }

    LLNode getStartCycleNode(LLNode head, LLNode meetingNode) {
        while(head != meetingNode) {
            head = head.next;
            meetingNode = meetingNode.next;
        }
        return meetingNode;
    }

    void removeCycle (LLNode head)
    {
        if(head == null || head.next == null) return;

        LLNode meetingNode = detectCycle(head);

        if(meetingNode == null || meetingNode.next == null) return;

        LLNode startCycleNode = getStartCycleNode(head, meetingNode);

        while(meetingNode.next != startCycleNode) {
            meetingNode = meetingNode.next;
        }

        meetingNode.next = null;
    }

    public static LLNode mergeSortedHelper(LLNode h1, LLNode h2) {
        LLNode head = h1;
        while(h2 != null && h1.next != null) {
            if(h1.next.data >= h2.data) {
                LLNode temp = h1.next;
                h1.next = h2;
                h2 = h2.next;
                h1.next.next = temp;
            }
            h1 = h1.next;
        }
        while(h2 != null) {
            h1.next = h2;
            h1 = h1.next;
            h2 = h2.next;
        }
        return head;
    }

    public static LLNode mergeSorted(LLNode h1, LLNode h2)
    {
        if(h1 == null && h2 == null) return null;
        if(h1 != null && h2 == null) return h1;
        if(h1 == null && h2 != null) return h2;
        if(h1.data <= h2.data) return mergeSortedHelper(h1, h2);
        if(h1.data >  h2.data) return mergeSortedHelper(h2, h1);
        return null;
    }

    LLNode alternateMerge(LLNode h1, LLNode h2)
    {
        if(h1 == null && h2 == null) return null;
        if(h1 == null && h2 != null) return h2;
        if(h1 != null && h2 == null) return h1;
        LLNode head = h1;
        while(h2 != null && h1.next != null) {
            LLNode temp = h1.next;
            h1.next = h2;
            h2= h2.next;
            h1.next.next = temp;
            h1 = h1.next.next;
        }
        while(h2 != null) {
            h1.next = h2;
            h2 = h2.next;
            h1 = h1.next;
        }
        return head;
    }

    void frontLastRearrange1(LLNode head)
    {
        Deque<LLNode> deque = new LinkedList<>();
        while(head != null) {
            deque.offerLast(head);
            head = head.next;
        }
        LLNode temp = deque.pollFirst();
        temp = temp.next = deque.pollLast();
        while(deque.size() > 1) {
            temp.next             = deque.pollFirst();
            temp = temp.next.next = deque.pollLast();
        }
        if(deque.size() == 1) temp = deque.pollFirst();
    }

    LLNode ReadLinkedList(LLNode head, int num)
    {
        if(num == 0) return null;
        if(num > -10 && num < 10) return new LLNode(num);
        int negative = 1;
        if(num < 0) {
            num *= -1;
            negative = -1;
        }
        LLNode h;
        head = new LLNode(-1);
        h = head;
        int div = (int) Math.pow (10, (int) (Math.floor (Math.log10 (num) ) ) );
        System.out.println("div:: " + div);
        while(div > 0) {
            head.next = new LLNode((num / div) * negative);
            num %=  div;
            div /= 10;
            head = head.next;
        }
        return h.next;
    }

    int size(LLNode node) {
        int size = 0;
        while(node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    int sum(LLNode h1, LLNode h2, int diff) {
        int sum = 0, carry = 0;
        if(diff == 0) {
            if(h1 == null && h2 == null) return 0;
            if(h2 != null) sum = h2.data;
            carry = sum(h1.next, h2.next, diff);
        } else carry = sum(h1.next, h2, diff - 1);
        sum += h1.data + carry;
        if(sum < 0) { sum *= -1; }
        h1.data = sum % 10;
        return sum/10;
    }

    public LLNode sumOfTwoNumbers(LLNode h1, LLNode h2)
    {
        if(h1 == null && h2 == null) return new LLNode(0);
        if(h1 != null && h2 == null) return h1;
        if(h1 == null && h2 != null) return h2;
        LLNode head = h1;
        int h1size = size(h1);
        int h2size = size(h2);
        int diff = Math.abs(h1size - h2size);
        if(h1size < h2size) {
            LLNode temp = h1;
            h1 = h2;
            h2 = temp;
        }
        int neg = 1;
        if(h1.data < 0 && h2.data < 0) neg = -1;
        else if(h1.data < 0 && Math.abs(h1.data) > h2.data) neg = -1;
        else if(h2.data < 0 && Math.abs(h2.data) > h1.data) neg = -1;
        int carry = sum(h1, h2, diff);
        if(carry > 0) {
            LLNode temp = new LLNode(carry);
            temp.next = h1;
            head = temp;
        }
        head.data *= neg;
        if(head.data == 0) return new LLNode(0);
        return head;
    }

    public LLNode reverseInGroupOfK (LLNode head, int k) {
        LLNode curr = head;
        int count = 0;
        LLNode prev = null;
        LLNode next = null;
        while (count < k && curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }
        if (null != next) head.next = reverseInGroupOfK (next, k);
        return prev;
    }

    public static List<LLNode> connectAlternativelyFirstLast (List<LLNode> l) {
        Map<Integer, LLNode> posMap = new HashMap<>();
        Iterator<LLNode> itr = l.iterator();
        int i = 0;
        posMap.put(i, itr.next());
        while (itr.hasNext()) posMap.put(++i, itr.next());
        int top = 0;
        int bottom = i;
        List<LLNode> altList = new LinkedList<>();
        while (top < bottom) {
            altList.add(posMap.get(top++));
            altList.add(posMap.get(bottom--));
        }
        return altList;
    }

    private static List<LLNode> createAndGetLinkedList (int... a) {
        List<LLNode> l = new LinkedList<LLNode>() {
            public void finalize() {
                try {
                    finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        };
        return Arrays.stream(a).mapToObj(e -> new LLNode(e)).collect(() -> l, List::add, List::addAll);
    }

//    public static void main(String[] args) {
//        List<LLNode> l = createAndGetLinkedList (1, 2, 3, 4);
//        connectAlternativelyFirstLast(l).forEach(e -> System.out.print(e.data + " "));
//    }

    public static LLNode mid (LLNode h) {
        if (null == h || null == h.next) return h;
        LLNode slow = h;
        LLNode fast = h.next;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    public static LLNode sortedMerge (LLNode l, LLNode r) {
        LLNode result;
        if (null == l) return r;
        if (null == r) return l;
        if (l.data <= r.data) {
            result = l;
            result.next = sortedMerge(l.next, r);
        } else {
            result = r;
            result.next = sortedMerge(l, r.next);
        }
        return result;
    }

    public static LLNode mergeSort (LLNode h) {
        if (Objects.isNull(h) || h.next == null) return h;
        LLNode m = mid(h);
        LLNode midNext = m.next;
        m.next = null;
        LLNode l = mergeSort(h);
        LLNode r = mergeSort(midNext);
        return sortedMerge(l, r);
    }

    public static LLNode createAndGetNew (int... n) {
        if (n.length == 0) return null;
        LLNode h = new LLNode(n[0]);
        LLNode l = h;
        for (int i = 0; i < n.length; i++) {
            l.next = new LLNode(n[i]);
            l = l.next;
        }
        return h;
    }

    public static void print (LLNode h) {
        while (null != h) { System.out.print(h.data + " | "); h = h.next; }
    }

    public static void main (String[] args) {
        LLNode l = createAndGetNew(15, 10, 5, 2, 3);
        print(mergeSort(l));
    }

    /**
     * A = 4
     *
     * Output:
     * 4 4 4 4 4 4 4
     * 4 3 3 3 3 3 4
     * 4 3 2 2 2 3 4
     * 4 3 2 1 2 3 4
     * 4 3 2 2 2 3 4
     * 4 3 3 3 3 3 4
     * 4 4 4 4 4 4 4
     *
     *
     * @param A
     * @return
     */
    public static ArrayList<ArrayList<Integer>> prettyPrint(int A) {
        ArrayList<ArrayList<Integer>> a = new ArrayList();
        int k = A;
        A = (2*A)-1;
        for (int i=0;i<A;i++) {
            ArrayList<Integer> aa = new ArrayList<>();
            for (int j=0;j<A;j++) {
                aa.add(0);
            }
            a.add(aa);
        }
        int R = A - 1;
        int C = A - 1;
        int r = 0;
        int c = 0;

        while (c <= C && r <= R) {
            for (int i=c;i<=C;i++)     a.get(r).set(i, k);
            r++;
            for (int i=r;i<=R;i++)     a.get(i).set(C, k);
            C--;
            if (r<=R) {
                for (int i=C;i>=c;i--) a.get(R).set(i, k);
                R--;
            }
            if (c<=C) {
                for (int i=R;i>=r;i--) a.get(i).set(c, k);
                c++;
            }
            k--;
        }
        return a;
    }

}
