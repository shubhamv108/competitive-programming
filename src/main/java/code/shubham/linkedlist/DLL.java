package code.shubham.linkedlist;

public class DLL {

    DLLNode clone(DLLNode h) {
        if(h == null) return null;
        DLLNode temp = new DLLNode(h.data);
        temp.d = clone(h.d);
        temp.t = clone(h.t);
        return temp;
    }

    public static void main(String[] args) {
        DLLNode head = null;

    }
}
