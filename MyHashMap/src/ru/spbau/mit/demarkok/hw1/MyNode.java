package ru.spbau.mit.demarkok.hw1;

public class MyNode {
    String key, value;
    MyNode prev, next;

    public MyNode(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public MyNode(String key, String value, MyNode prev, MyNode next) {
        this.key = key;
        this.value = value;
        this.prev = prev;
        this.next = next;
    }

    MyNode insertBefore(String key, String value) {
        MyNode n = new MyNode(key, value);

        n.next = this;
        n.prev = prev;
        prev = n;
        if (n.prev != null)
            n.prev.next = n;
        return n;
    }

    void remove(){
        if (prev != null) {
            prev.next = next;
        }
        if (next != null) {
            next.prev = prev;
        }
    }
}
