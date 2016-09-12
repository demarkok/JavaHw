package ru.spbau.mit.demarkok.hw1;

public class Main {
    public static void main(String[] args) {
        MyHashMap x = new MyHashMap();
        x.put("1", ":)");
        x.put("2", ":))");
        x.put("3", ":)))");
        x.put("0", ")");
        System.out.println(x.get("1"));
        x.remove("1");
        System.out.println(x.get("1"));
        System.out.println(x.put("2", ":(("));
        System.out.println(x.get("2"));
        x.clear();
        System.out.println(x.contains("0"));
    }
}
