package org.poc.concurrency;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentStack<E> {

    private AtomicReference<Node<E>> ar = new AtomicReference<>();

    private class Node<E> {
        E item;
        Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    public void push(E item) {
        Node<E> oldNode;
        Node<E> newOld = new Node<>(item, null);
        do {
            oldNode = ar.get();
            newOld.next = oldNode;
        } while (!ar.compareAndSet(oldNode, newOld));
    }

    public E pop() {
        Node<E> oldNode;
        do {
            oldNode = ar.get();
            if (oldNode == null) {
                return null;
            }
        } while (!ar.compareAndSet(oldNode, oldNode.next));
        return oldNode.item;
    }

    public static void main(String[] args) {
        ConcurrentStack stack = new ConcurrentStack();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
