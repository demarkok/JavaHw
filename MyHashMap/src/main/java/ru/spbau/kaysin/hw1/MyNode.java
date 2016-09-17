package ru.spbau.kaysin.hw1;

public class MyNode {
    final String key, value;
    MyNode prev = null, next = null;

    public MyNode(String key, String value) {
        this.key = key;
        this.value = value;
    }

    MyNode insertBefore(MyNode n) {
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

