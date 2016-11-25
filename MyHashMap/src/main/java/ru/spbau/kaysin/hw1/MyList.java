package ru.spbau.kaysin.hw1;

public class MyList {
    MyNode head;
    int size;

    public MyList() {
        size = 0;
    }

    public void insert(String key, String value){
        if (size == 0) {
            size = 1;
            head = new MyNode(key, value);
        } else {
            size++;
            head = head.insertBefore(new MyNode(key, value));
        }
    }

    public MyNode find(String key) {
        for (MyNode node = head; node != null; node = node.next) {
            if (node.key.equals(key)) {
                return node;
            }
        }
        return null;
    }

    public void remove(String key) {
        if (size == 1) {
            head = null;
            size = 0;
        } else {
            find(key).remove();
        }
    }
}
