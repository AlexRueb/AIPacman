package aipacman;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class QueueOrStack<E> implements Iterable<E> {

    private Deque<E> container = new ArrayDeque<>();
    private boolean isQueue;

    public QueueOrStack(boolean isQueue) {
        this.isQueue = isQueue;
    }

    public E pop() {
        return isQueue ? container.removeFirst() : container.removeLast();
    }

    public void push(E element) {
        container.addLast(element);
    }

    public void pushAll(E... element) {
        for (E e : element)
            container.addLast(e);
    }

    public boolean isQueue() {
        return isQueue;
    }

    public void setQueue(boolean isQueue) {
        this.isQueue = isQueue;
    }

    public boolean toggleQueue() {
        return isQueue = !isQueue;
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}