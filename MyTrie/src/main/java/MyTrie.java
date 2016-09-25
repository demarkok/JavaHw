import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyTrie implements Trie, StreamSerializable {

    private Node root = new Node();
    private int size = 0;

    private void getAllStrings(StringBuilder sb, ArrayList<String> result, Node n) {
        if (n.isTerminal) {
            result.add(sb.toString());
        }
        for (Map.Entry<Character, Node> c : n.arrows.entrySet()) {
            sb.append(c.getKey());
            getAllStrings(sb, result, c.getValue());
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public void serialize(OutputStream out) throws IOException {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> strings = new ArrayList<String>();
        getAllStrings(sb, strings, root);

        PrintWriter pw = new PrintWriter(out);
        for (String s : strings) {
            pw.write(s);
            pw.write(" ");
        }
        pw.close();
    }

    public void deserialize(InputStream in) throws IOException {
        MyTrie newTrie = new MyTrie();
        root = newTrie.root;
        size = newTrie.size;

        java.util.Scanner scanner = new java.util.Scanner(in).useDelimiter(" ");
        while (scanner.hasNext()) {
            String s = scanner.next();
            add(s);
        }
        scanner.close();
    }

    private Node extend(Node start, char letter) {
        if (start.arrows.containsKey(letter)) {
            return start.arrows.get(letter);
        } else {
            Node n = new Node();
            start.arrows.put(letter, n);
            return n;
        }
    }

    public boolean add(String element) {
        if (contains(element))
            return false;
        Node currNode = root;
        for (int i = 0; i < element.length(); i++) {
            currNode.termDescendants++;
            currNode = extend(currNode, element.charAt(i));
        }
        currNode.termDescendants++;
        currNode.isTerminal = true;
        size++;
        return true;
    }

    public boolean contains(String element) {
        Node n = getNode(element);
        return (n != null) && n.isTerminal;
    }


    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }
        recursiveRemove(root, element, 0);
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    public int howManyStartsWithPrefix(String prefix) {
        if (getNode(prefix) == null) {
            return 0;
        }
        return getNode(prefix).termDescendants;
    }

    private boolean recursiveRemove(Node n, String element, int letterPos) {
        if (letterPos == element.length()) {
            n.termDescendants--;
            n.isTerminal = false;
            return n.arrows.isEmpty();
        }
        char letter = element.charAt(letterPos);
        if (recursiveRemove(n.arrows.get(letter), element, letterPos + 1)) {
            n.arrows.remove(letter);
        }
        n.termDescendants--;

        // true = allow my parent to remove me
        return n.isTerminal && n.arrows.isEmpty();
    }

    private Node getNode(String element) {
        Node currNode = root;
        for (int i = 0; i < element.length(); i++) {
            if (currNode.arrows.containsKey(element.charAt(i))) {
                currNode = currNode.arrows.get(element.charAt(i));
            } else {
                return null;
            }
        }
        return currNode;
    }

    private class Node {
        final HashMap<Character, Node> arrows = new HashMap<Character, Node>();
        Boolean isTerminal = false;
        int termDescendants = 0;

    }
}
