package com.mark.algorithm.search;

import com.mark.algorithm.sort.SelectionSort;

import java.util.Arrays;

/**
 * 平衡23查找树
 */
public class TreeOf23Leafs<Key extends Comparable<Key>, Value> {

    TreeOf23Leafs(){}

    private Node root;
    private int size = 0;

    /**
     * 插入新的节点，并返回原值
     * @param key
     * @param value
     * @return
     */
    public void put(Key key, Value value){
        root = put(root, new Item(key, value));
        refreshSize();
    }

    /**
     * 插入节点
     * 1、当前节点不是4叉树，则插入到当前节点；
     * 2、当前节点变为4叉树时，中位数提到父节点，4叉树变两个2叉树
     * 3、父节点变为4叉树时，循环第2步
     * @param current
     * @param item
     * @return
     */
    private Node put(Node current, Item item){
        if (current == null){
            current = new Node(item);
            return current;
        }else {
            current.put(item);
            if (current.isFull())
                return chang4To2(current);
            else
                return current;
        }
    }

    private Node chang4To2(Node node){
        if (! node.isFull()) return node;
        if (node == root){
            node.parent = new Node(null, node.items[1]);
            root = node.parent;
        }
        Node left = new Node(node.parent, node.items[0]);
        Node right = new Node(node.parent, node.items[2]);
        node.parent.put(node.items[1]);
        if (node == node.parent.left){
            node.parent.left = left;
            node.parent.middle =
        }

        left.left = node.left;
    }

    public Value get(Key key){
        Node x = get(root, key);
        return (x != null) ? x.get(key) : null;
    }

    private Node get(Node current, Key key){
        if (current == null){
            return null;
        }else{
            int comp = current.compareTo(key);
            if (comp == -2){
                return get(current.left, key);
            }else if (comp == -1){
                return get(current.middle, key);
            }else if (comp > 0){
                return get(current.right, key);
            }else{
                return current;
            }
        }
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

    final class Item implements Comparable<Item>{

        Key key;
        Value value;

        Item(Key key, Value value){
            this.key = key;
            this.value = value;
        }

        public int compareTo(Item o) {
            return key.compareTo(o.key);
        }
    }

    class DefaultNode implements Comparable<Key>{
        Item[] items;
        Node parent;
        DefaultNode[] children;

        /**
         * 比较
         * @param o
         * @return 小于第一个键=-2，介于两个键之间=-1，包含=0，大于最后一个不为空的键=1
         */
        public int compareTo(Key o) {
            if (isEmpty())
                throw new RuntimeException("当前节点为空，不能参与比较");

            if (items[0].key.compareTo(o) < 0) return -2;
            else if (items[items.length - 1] != null){
                if (contain(o)) return 0;
                else return items[items.length - 1].key.compareTo(o);
            }
            else return items[0].key.compareTo(o);
        }

        public boolean contain(Key key){
            if (isEmpty()) return false;
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) break;
                if (items[i].key.compareTo(key) == 0) return true;
            }
            return false;
        }

        public Value get(Key key){
            if (isEmpty()) return null;
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) break;
                if (items[i].key.compareTo(key) == 0) return items[i].value;
            }
            return null;
        }

        public void put(Item item){
            int max = 0;
            SelectionSort sort = new SelectionSort();
            for (int i = 0; i < items.length; i++,max++) {
                if (items[i] == null) {
                    items[i] = item;
                    break;
                }
            }
            sort.sort(items, 0, max);
        }

        public boolean isEmpty(){
            return (items == null || items.length == 0 || items[0] == null);
        }
    }

    final class Node extends DefaultNode{
        static final int maxLength = 3;

        Node(Node parent, Item... items){
            this.parent = parent;
            this.items = Arrays.copyOf(items, maxLength);
            this.children = new TreeOf23Leafs.DefaultNode[maxLength+1];
        }

        public void put(Item item){
            int max = 0;
            SelectionSort sort = new SelectionSort();
            for (int i = 0; i < items.length; i++,max++) {
                if (items[i] == null) {
                    items[i] = item;
                    break;
                }
            }
            sort.sort(items, 0, max);
        }

    }
}
