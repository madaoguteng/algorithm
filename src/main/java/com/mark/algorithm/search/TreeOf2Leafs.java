package com.mark.algorithm.search;

import com.sun.istack.internal.NotNull;

/**
 * 查找二叉树
 */
public class TreeOf2Leafs<Key extends Comparable<Key>, Value> {

    TreeOf2Leafs(){}

    private Node root;
    private int size = 0;

    /**
     * 插入新的节点，并返回原值
     * @param key
     * @param value
     * @return
     */
    public Value put(Key key, Value value){
        Value oldValue = put(root, new Node(key, value));
        if (oldValue == null){
            refreshSize();
        }
        return oldValue;
    }

    private Value put(Node parent, Node item){
        if (parent == null){
            parent = item;
            return null;
        }else {
            int comp = parent.compareTo(item);
            if (comp < 0) {
                return put(parent.left, item);
            }else if (comp > 0){
                return put(parent.right, item);
            }else {
                Value oldValue = parent.value;
                parent.value = item.value;
                return oldValue;
            }
        }
    }

    public Value get(@NotNull Key key){
        return get(root, key);
    }

    private Value get(Node parent, @NotNull Key key){
        if (parent == null){
            return null;
        }else{
            int comp = key.compareTo(parent.key);
            if (comp < 0){
                return get(parent.left, key);
            }else if (comp > 0){
                return get(parent.right, key);
            }else{
                return parent.value;
            }
        }
    }

    private Node search(Node current, Key key){
        while (current != null){
            int comp = key.compareTo(current.key);
            if (comp < 0){
                current = current.left;
            }else if (comp > 0){
                current = current.right;
            }else{
                break;
            }
        }
        return current;
    }

    public Key max(){
        if (root == null){
            return null;
        }

        Node parent = root;
        while (parent.right != null){
            parent = parent.right;
        }
        return parent.key;
    }

    public Key min(){
        if (root == null){
            return null;
        }

        Node parent = root;
        while (parent.left != null){
            parent = parent.left;
        }
        return parent.key;
    }

    private Node min(Node parent){
        if (parent == null){
            return null;
        }

        while (parent.left != null){
            parent = parent.left;
        }
        return parent;
    }

    public int size(){
        return size;
    }

    private int size(Node x){
        int s = 0;
        if (x != null){
            return size(x.left) + size(x.right) + 1;
        }
        return s;
    }

    private synchronized void refreshSize(){
        size = size(root);
    }

    public void deleteMin(){
        root = deleteMin(root);
        refreshSize();
    }

    private Node deleteMin(Node current){
        if (current == null){
            return null;
        }
        if (current.left == null){
            return current.right;
        }
        current.left = deleteMin(current.left);
        return current;
    }

    public void delete(Key key){
        root = delete(root, key);
        refreshSize();
    }

    private Node delete(Node current, Key key){
        if (current == null){
            return null;
        }
        int comp = current.key.compareTo(key);
        if (comp<0){
            current.left = delete(current.left, key);
        }else if (comp>0){
            current.right = delete(current.right, key);
        }else{
            if (current.left == null){
                return current.right;
            }else if (current.right == null){
                return current.left;
            }

            Node rightMin = min(current.right);
            rightMin.left = current.left;
            rightMin.right = deleteMin(current.right);

            return rightMin;
        }
        return current;
    }

    final class Node implements Comparable<Node>{
        Key key;
        Value value;
        Node left;
        Node right;

        Node(Key key, Value value){
            this.key = key;
            this.value = value;
        }

        public int compareTo(Node o) {
            return this.key.compareTo(o.key);
        }
    }
}
