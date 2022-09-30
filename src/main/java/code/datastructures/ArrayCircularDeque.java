package code.datastructures;

public class ArrayCircularDeque {

    int k, Q[], front, rear, size;
    public ArrayCircularDeque(int k) {
        this.k = k;
        Q = new int[k];
    }

    public boolean insertFront(int value) {
        if (isFull())
            return false;

        if (!isEmpty())
            front = (front - 1) == -1 ? k-1 : front-1;

        Q[front] = value;
        this.size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull())
            return false;

        if (!isEmpty())
            rear = (rear + 1) % k;

        Q[rear] = value;
        this.size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty())
            return false;

        if (size != 1)
            front = (front + 1) % k;

        this.size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty())
            return false;

        if (size != 1)
            rear = (rear - 1) == -1 ? k-1 : rear-1;

        this.size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : Q[front];
    }

    public int getRear() {
        return isEmpty() ? -1 : Q[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == k;
    }
}

/**
 * Your ArrayCircularDeque object will be instantiated and called as such:
 * ArrayCircularDeque obj = new ArrayCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
