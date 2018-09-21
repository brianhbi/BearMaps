package byog.Core.Utilities;

import java.io.Serializable;

public class ArrayDeque<T> implements Serializable {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    private void lengthenFront(int newLength) {
        T[] newArray = (T[]) new Object[newLength];
        int bias = newLength - items.length;

        System.arraycopy(items, 0, newArray, nextFirst + bias + 1, size);
        nextFirst += bias;
        nextLast += bias;
        items = newArray;
    }

    public void addFirst(T item) {
        if (nextFirst < 0) {
            lengthenFront(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        size += 1;
    }

    private void lengthenBack(int newLength) {
        T[] newArray = (T[]) new Object[newLength];
        System.arraycopy(items, 0, newArray, 0, items.length);
        items = newArray;
    }

    public void addLast(T item) {
        if (nextLast == items.length) {
            lengthenBack(items.length * 2);
        }
        items[nextLast] = item;
        nextLast += 1;
        size += 1;
    }


    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = nextFirst + 1; i <= nextLast - 1; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.println();
    }

    private void halfArraySize() {
        T[] newArray = (T[]) new Object[items.length / 2];
        int copyStartPosition = (newArray.length / 2) - (size / 2);
        System.arraycopy(items, nextFirst + 1, newArray, copyStartPosition, size);

        nextFirst = copyStartPosition - 1;
        nextLast = nextFirst + 1 + size;
        items = newArray;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst += 1;
        T output = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;

        if ((double) size / (double) items.length < .25 && items.length >= 16) {
            halfArraySize();
        }

        return output;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast -= 1;
        T output = items[nextLast];
        items[nextLast] = null;
        size -= 1;

        if ((double) size / (double) items.length < .25 && items.length >= 16) {
            halfArraySize();
        }

        return output;
    }

    public T get(int index) {
        return items[index + nextFirst + 1];
    }

    public boolean inDeque(T item) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(item)) {
                return true;
            }
        }
        return false;
    }

    public void removeElem(T elem) {
        int size2 = size();
        for (int i = 0; i < size; i++) {
            T temp = removeFirst();
            if (!temp.equals(elem)) {
                addLast(temp);
            }
        }
    }
}
