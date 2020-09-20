package code.linkedlist;

import java.util.*;

class   LinkedListFunctions {
    LLNode last = null;
    void printList(LLNode head) {
        LLNode temp = head;
        while (temp != null) {
            System.out.print(temp.data+" ");
            temp = temp.next;
        }
        System.out.println();
    }

    void printListWithoutSpace(LLNode head) {
        LLNode temp = head;
        while (temp != null) {
            System.out.print(temp.data);
            temp = temp.next;
        }
        System.out.println();
    }


    LLNode InsertAtEnd(LLNode head, int data) {
        if(head == null) return new LLNode(data);
        LLNode curNode = head;
        while(curNode.next != null) curNode = curNode.next;
        curNode.next = new LLNode(data);
        return head;
    }

    LLNode deleteNode(LLNode h, int data) {
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

    void deleteNodeWithoutHead(LLNode nodeToDelete) {
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
            if(fast == null)
                return slow;
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
        if (fast == null || fast.next == null) return null;
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
        for (int i = 1; i < n.length; i++) {
            l.next = new LLNode(n[i]);
            l = l.next;
        }
        return h;
    }

    public static Node createAndGetNewLL (int... n) {
        if (n.length == 0) return null;
        Node h = new Node(n[0]);
        Node l = h;
        for (int i = 1; i < n.length; i++) {
            l.n = new Node(n[i]);
            l = l.n;
        }
        return h;
    }

    public static ListNode createAndGetNewLN (int... n) {
        if (n.length == 0) return null;
        ListNode h = new ListNode(n[0]);
        ListNode l = h;
        for (int i = 1; i < n.length; i++) {
            l.next = new ListNode(n[i]);
            l = l.next;
        }
        return h;
    }

    public static void print (LLNode h) {
        while (null != h) { System.out.print(h.data + " | "); h = h.next; }
    }

    public static void print (ListNode h) {
        while (null != h) { System.out.print(h.val + " "); h = h.next; }
    }

    public static void print (Node h) {
        while (null != h) { System.out.print(h.data + " | "); h = h.n; }
    }

//    public static void main (String[] args) {
//        LLNode l = createAndGetNew(15, 10, 5, 2, 3);
//        print(mergeSort(l));
//    }

    private static Node l = null;
    private static boolean a = false;
    public static Node subtract (Node A) {
        if (A == null || A.n == null) return A;
        if (A.n.n == null) {
            A.data = A.n.data - A.data;
            return A;
        }
        l = A;
        Node mid = mid (l);
        // recurse(mid);
        Stack<Node> s = new Stack<>();
        while(mid != null) {
            s.push(mid);
            mid=mid.n;
        }
        while (!s.isEmpty()) { l.data = s.pop().data - l.data; l = l.n; }
        return A;
    }

    private static void recurse (Node r) {
        if (r == null) return;
        recurse (r.n);
        if (l == r || a)  {  a = true; return; }
        if (r == l.n) {
            l.data = r.data - l.data;
            return;
        }
        l.data = r.data - l.data;
        l = l.n;
    }

    private static Node mid (Node n) {
        int count = 0;
        Node mid = n;
        while (n != null) {
            if (count != 0 && (count & 1) == 0) mid = mid.n;
            count++;
            n = n.n;

        }
        return mid.n;
    }

//    public static void main(String[] args) {
//        Node l = createAndGetNewLL(95 , 59 , 26 , 16 , 31 , 39 , 29 , 8 , 74 , 14 , 41 , 41 , 64 , 88 , 34 , 21 , 67 , 23 , 17 , 41 , 3 , 38 , 4 , 45 , 93 , 92 , 99 , 24);
//        print(subtract(l));
//    }

    private static Node left;

    public static boolean isPalindrome (Node node) {
        if (node == null)           return true;
        if (!isPalindrome(node.n))  return false;
        if (node == left)           return true;
        if (node.data != left.data) return false;
        left = left.n;
        return true;
    }

//    public static void main(String[] args) {
//        Node head     = new Node(1);
//        head.n        = new Node(2);
//        head.n.n      = new Node(3);
//        head.n.n.n    = new Node(2);
//        head.n.n.n.n  = new Node(1);
//        left = head;
//        System.out.println(isPalindrome(mid(head)));
//    }

    public static ListNode reverseBetween (ListNode A, int B, int C) {
        ListNode temp = null;
        ListNode r = null;
        ListNode p = null;
        ListNode head = A;
        ListNode rstart = null;
        int c = 0;
        while (A != null) {
            c++;
            if (c == B-1) {
                p = A;
            }
            if (c >= B && c <= C) {
                temp = A.next;
                A.next = r;
                r = A;
                if (r.next == null) {
                    rstart = r;
                }
                A = temp;
                if (c == C) {
                    rstart.next = A;
                    break;
                }
            } else {
                A = A.next;
            }
        }
        if (p == null) return r;
        else { p.next = r; return head; }
    }

//    public static void main(String[] args) {
//        ListNode l = createAndGetNewLN(1, 2, 3, 4, 3);
//        l = reverseBetween(l, 2, 3);
//        print(l);
//
//    }

    public static ListNode reverseListInGroupOfSizeB2 (ListNode head, int B) {
        if (head == null) return null;
        ListNode current = head;
        ListNode prev = null;
        ListNode tmp = null;

        int count = B;
        while (count-- > 0 && current != null) {
            tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }

        head.next = reverseListInGroupOfSizeB2(current, B);

        return prev;
    }
    public static ListNode reverseListInGroupOfSizeB (ListNode A, int B) {
        ListNode temp;
        ListNode r = null;
        ListNode prev = null;
        ListNode rStart = A;
        ListNode head = null;
        if (B <= 1) return A;
        int k = B;
        while (A != null) {
            k--;
            temp = A.next;
            A.next = r;
            r = A;
            A = temp;
            if (k == 0) {
                if (head == null) head = r;
                rStart.next = A;
                k = B;
                if (prev != null)
                    prev.next = r;
                prev = rStart;
                if (A == null) break;
                rStart = A;
            }

        }
        return head;

    }



//    public static void main(String[] args) {
//        ListNode l = createAndGetNewLN(6, 10, 0, 3, 4, 8, 9, 8);
//        l = reverseListInGroupOfSizeB2(l, 3);
//        print(l);
//    }

    public static ListNode swapPairs (ListNode A) {
        ListNode prev = null;
        ListNode head = null;
        ListNode temp = null;
        while (A != null && A.next != null) {
            temp = A.next;
            A.next = A.next.next;
            temp.next = A;
            if (prev != null) {
                prev.next = temp;
            }
            if (head == null) {
                head = temp;
            }
            prev = A;
            A = A.next;
        }
        if (head == null && A != null) return A;
        return head;
    }

//    public static void main(String[] args) {
//        ListNode l = createAndGetNewLN(6, 10, 0, 3, 4, 8);
//        l = swapPairs(l);
//        print(l);
//    }

    public static ListNode removeNthFromEnd (ListNode A, int B) {
        if (B <= 0) return A;
        ListNode head = A;
        ListNode prev = A;
        ListNode cur  = A;
        while (B-- > 0) {
            if (cur == null) return head.next;
            cur = cur.next;
        }
        if (cur == null) return head.next;
        while (cur.next != null) {
            prev = prev.next;
            cur  = cur.next;
        }
        prev.next = prev.next.next;
        return head;
    }

//    public static void main(String[] args) {
//        ListNode l = createAndGetNewLN(1, 2, 3, 4, 5);
//        l = removeNthFromEnd(l, 6);
//        print(l);
//    }

    public static  ListNode detectCycle (ListNode a) {
        ListNode b = cycleNode(a);
        if (b == null) return null;
        while (b != a) {
            b = b.next;
            a = a.next;
        }
        return a;
    }

    public static ListNode cycleNode(ListNode a) {
        ListNode slow = a;
        ListNode fast = a;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;
        return fast;
    }

//    public static void main(String[] args) {
//        ListNode l = createAndGetNewLN(1, 2, 3, 4, 3);
//        l = detectCycle(l);
//        print(l);
//    }

    public static ListNode rotateRight(ListNode A, int B) {
        if (A == null || A.next == null) return A;
        ListNode head = A;
        ListNode AA   = A;
        while (B-- > 0) {
            AA = AA.next;
            if (AA == null) AA = A;
        }
        if (head == AA) return head;
        while (AA.next != null) {
            A  = A.next;
            AA = AA.next;
        }
        ListNode temp = A.next;
        A.next = null;
        AA.next = head;
        return temp;
    }

    public static void main(String[] args) {
        ListNode l = LinkedListFunctions.createAndGetNewLN(68, 86, 13, 16, 5, 75);
        LinkedListFunctions.print(rotateRight(l, 5));
    }

    public static ListNode deleteDuplicates (ListNode A) {
        ListNode head = new ListNode(0);
        head.next = A;
        ListNode prev = head;
        while (A != null) {
            while (A.next != null && A.next.val == prev.next.val) A = A.next;
            if (prev.next == A) prev = prev = prev.next;
            else prev.next = A.next;
            A = A.next;
        }
        return head.next;
    }

//    public static void main(String[] args) {
//        ListNode l = LinkedListFunctions.createAndGetNewLN(1, 1, 2, 2, 3, 3);
//        print(deleteDuplicates(l));
//    }

    private static ListNode reverseBetweenListAlternate (ListNode A, int k) {
        ListNode head = A;
        ListNode r = null;
        ListNode prev = null;
        ListNode rEnd = null;
        ListNode t;
        int i = k;
        int j = 0;
        while (A != null) {
            if (j == 0) {
                i--;
                if (i == 0) {
                    prev = A;
                    j = k;
                }
                A = A.next;
            } else if (i == 0) {
                j--;
                if (rEnd == null) rEnd = A;
                t = A.next;
                A.next = r;
                r = A;
                A = t;
                if (j == 0) {
                    prev.next = r;
                    rEnd.next = A;
                    rEnd = null;
                    i = k;
                    j = 0;
                }
            }

        }
        return head;
    }

//    public static void main(String[] args) {
//        ListNode l = createAndGetNewLN(6, 10, 0, 3, 4, 8, 4, 3, 7);
//        l = reverseBetweenListAlternate(l, 2);
//        print(l);
//    }

    public static ListNode swapCombinationsOfSizeK (ListNode A, final int K) {
        if (A == null || A.next == null) return A;
        ListNode prevStart = A;
        ListNode prevEnd = A;
        ListNode curStart = null;
        ListNode curEnd = null;
        ListNode head = null;
        ListNode headEnd = null;
        int k = K - 1;
        while (A != null && A.next != null) {
            if (k > 0) {
                A = A.next;
               k--;
               continue;
            } else if (k == 0) {
                prevEnd = A;
                curStart = prevEnd.next;
                k = K - 1;
                curEnd = curStart;
                while (k > 0 && curEnd.next != null) {
                    curEnd = curEnd.next;
                    k--;
                }

                A = curEnd.next;
                curEnd.next = prevStart;
                if (head == null)
                    head = curStart;

                if (headEnd == null) {
                    headEnd = prevEnd;
                } else {
                    headEnd.next = curStart;
                }
                headEnd = prevEnd;
                prevEnd.next = null;
                k = K - 1;
                prevStart = A;
            }
        }
        if (A != null) headEnd.next = A;
        return head;
    }

//    public static void main(String[] args) {
//        ListNode l = createAndGetNewLN(6, 10, 0);
//        l = swapCombinationsOfSizeK(l, 2);
//        print(l);
//    }
}
