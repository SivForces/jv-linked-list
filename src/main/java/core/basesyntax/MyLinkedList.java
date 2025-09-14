package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(T value) {
        if (isEmpty()) {
            Node<T> item = new Node<>(null, value, null);
            head = item;
            tail = item;
            size++;
        } else {
            Node<T> item = new Node<>(tail, value, null);
            tail.next = item;
            tail = item;
            size++;
        }
    }

    @Override
    public void add(T value, int index) {
        checkIndex(index);
        Node<T> current = head;
        int i = 0;
        if (index == 0) {
            if (isEmpty()) {
                addStartEmpty(value);
                return;
            } else {
                addStart(value);
                return;
            }
        } else if (index == size) {
            addEnd(value);
            return;
        }
        while (current != null) {
            if (i == index) {
                if (current.prev == null) {
                    throw new NullPointerException("Index: " + (index - 1) + " is null");
                }
                Node<T> item = new Node<>(current.prev, value, current.next);
                item.prev = current.prev;
                item.next = current;
                current.prev.next = item;
                current.prev = item;
                size++;
                break;
            }
            i = i + 1;
            current = current.next;
        }
    }

    @Override
    public void addAll(List<T> list) {
        for (T element : list) {
            addEnd(element);
        }
    }

    @Override
    public T get(int index) {
        checkAccessIndex(index);
        int i = 0;
        Node<T> current = head;
        while (current != null) {
            if (i == index) {
                return current.item;
            } else {
                i++;
            }
            current = current.next;
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public T set(T value, int index) {
        checkAccessIndex(index);
        int i = 0;
        Node<T> current = head;
        while (current != null) {
            if (i == index) {
                T deletedItem = current.item;
                current.item = value;
                return deletedItem;
            } else {
                i++;
            }
            current = current.next;
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public T remove(int index) {
        checkAccessIndex(index);
        int i = 0;
        Node<T> current = head;
        while (current != null) {
            if (index == 0) {
                T removedItem = head.item;
                removeStart();
                return removedItem;
            } else if (index == size - 1) {
                T removedItem = tail.item;
                removeEnd();
                return removedItem;
            } else if (i == index) {
                final T removedItem = current.item;
                current.next.prev = current.prev;
                current.prev.next = current.next;
                size--;
                return removedItem;
            }
            i++;
            current = current.next;
        }
        throw new IndexOutOfBoundsException("Index: " + index);
    }

    @Override
    public boolean remove(T object) {
        Node<T> current = head;
        while (current != null) {
            object == null ? node.item == null : object.equals(node.item) {
                current.next.prev = current.prev;
                current.prev.next = current.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void checkAccessIndex(int index) {
        if (index < 0 || index >= size - 1) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void addStartEmpty(T value) {
        Node<T> item = new Node<>(null, value, null);
        head = item;
        tail = item;
        size++;
    }

    public void addStart(T value) {
        Node<T> item = new Node<>(null, value, head);
        item.next = head;
        head.prev = item;
        head = item;
        size++;
    }

    public void addEnd(T value) {
        Node<T> item = new Node<>(tail, value, null);
        tail.next = item;
        item.prev = tail;
        tail = item;
        size++;
    }

    private T unlink(Node<T> node) {
        T deletedItem = node.item;
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == head) {
            head = node.next;
        }
        if (node == tail) {
            tail = node.prev;
        }
        node.prev = null;
        node.next = null;
        size--;
        return deletedItem;
    }


}
