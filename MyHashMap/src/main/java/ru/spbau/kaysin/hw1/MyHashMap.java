package ru.spbau.kaysin.hw1;

public class MyHashMap {

    MyList[] hashMap;
    int size, capacity;

    private MyHashMap(int capacity) {
        this.capacity = capacity;
        size = 0;
        hashMap = new MyList[capacity];
        for (int i = 0; i < capacity; i++) {
            hashMap[i] = new MyList();
        }
    }

    public MyHashMap() {
        this(1);
    }

    private void rehash(int newCapacity) {
        if (newCapacity == 0) {
            return;
        }
        MyHashMap newMap = new MyHashMap(newCapacity);

        for (int i = 0; i < capacity; i++) {
            for (MyNode node = hashMap[i].head; node != null; node = node.next) {
                newMap.put(node.key, node.value);
            }
        }
        capacity = newCapacity;
        hashMap = newMap.hashMap;
    }

    public int size() {
        return size;
    }

    public boolean contains(String key) {
        return hashMap[key.hashCode() % capacity].find(key) != null;
    }

    public String get(String key) {
        if (contains(key)) {
            return hashMap[key.hashCode() % capacity].find(key).value;
        } else {
            return null;
        }
    }

    public String put(String key, String value) {
        // положить по ключу key значение value
        // и вернуть ранее хранимое, либо null
        String old = get(key);
        if (old != null) {
            remove(key);
        }

        hashMap[key.hashCode() % capacity].insert(key, value);
        size++;
        if (size == capacity) {
            rehash(capacity * 2);
        }
        return old;
    }

    public String remove(String key) {
        // забыть про пару key-value для переданного value
        // и вернуть забытое value, либо null, если такой пары не было
        String old = get(key);
        if (contains(key)) {
            hashMap[key.hashCode() % capacity].remove(key);
        } else {
            return null;
        }
        size--;
        if (size * 4 <= capacity) {
            rehash(capacity / 2);
        }
        return old;
    }

    public void clear() {
        // забыть про все пары key-value
        MyHashMap newMap = new MyHashMap();
        hashMap = newMap.hashMap;
        size = newMap.size;
        capacity = newMap.capacity;
    }
}
