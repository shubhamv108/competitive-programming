package code.collections;

import java.util.Objects;

public class ArrayList<T> {

    int size = 0;
    int capacity = 8;

    Object[] arr = new Object[capacity];

    void add(T t) {
        if (size == capacity)
            increaseCapacity();
        arr[size++] = t;
    }
    void increaseCapacity() {
        capacity *= 2;
        Object[] arrCopy = new Object[capacity];
        for (int i = 0; i < capacity; i++)
            arrCopy[i] = arr[i];
        arr = arrCopy;
    }

    T get(int i) {
      return (T) arr[i];
    }

}
