package com.personalbudget.wheel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Index<T extends Comparable<T>, U> {
    private class Node {
        T key;
        List<U> values;
        Node leftNode;
        Node rightNode;
        int height;

        public Node(T key) {
            this.key = key;
            this.values = new ArrayList<>();
            this.height = 1;
        }
    }

    private Node root;

    public Index() {}

    public List<U> get(T index) {
        Node node = root;
        while (true) {
            if (node == null)
                return Collections.emptyList();
            int direction = index.compareTo(node.key);
            if (direction == 0)
                return node.values;
            if (direction < 0)
                node = node.leftNode;
            else
                node = node.rightNode;
        }
    }

    public void add(T index, U value) {
        insertNode(root, index).values.add(value);
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }
    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.leftNode) - getHeight(node.rightNode);
    }

    private Node rightRotate(Node y) {
        Node x = y.leftNode; 
        Node y2 = x.rightNode; 
        
        x.rightNode = y; y.leftNode = y2; 
        
        y.height = Math.max(getHeight(y.leftNode), getHeight(y.rightNode)) + 1; 
        x.height = Math.max(getHeight(x.leftNode), getHeight(x.rightNode)) + 1;
        
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.leftNode; 
        Node x2 = y.rightNode; 
        
        y.leftNode = x; x.rightNode = x2; 
        
        x.height = Math.max(getHeight(x.leftNode), getHeight(x.rightNode)) + 1;
        y.height = Math.max(getHeight(y.leftNode), getHeight(y.rightNode)) + 1; 
        
        return x;
    }

    private Node insertNode(Node node, T index) 
    {
        if (node == null) { return new Node(index); }
        
        int direction = index.compareTo(node.key);
        if (direction < 0) { 
            node.leftNode = insertNode(node.leftNode, index); 
        } else if (direction > 0) { 
            node.rightNode = insertNode(node.rightNode, index); 
        } else { 
            return node; 
        }
            
        node.height = Math.max(getHeight(node.leftNode), getHeight(node.rightNode)) + 1;

        int balance = getBalance(node);

        if (balance > 1) {
            if (index.compareTo(node.leftNode.key) < 0) {
                return rightRotate(node);
            } else {
                node.leftNode = leftRotate(node.leftNode);
                return rightRotate(node);
            }
        } else if (balance < -1) {
            if (index.compareTo(node.rightNode.key) > 0) {
                return leftRotate(node);
            } else {
                node.rightNode = rightRotate(node.rightNode);
                return leftRotate(node);
            }
        }

        return node;
    }
}
