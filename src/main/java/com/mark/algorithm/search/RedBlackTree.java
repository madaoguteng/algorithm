package com.mark.algorithm.search;

/**
 * 红黑树
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {

    RedBlackTree(){}

    private Node root;
    private int size = 0;

    /**
     * 插入新的节点，并返回原值
     * @param key
     * @param value
     * @return
     */
    public void put(Key key, Value value){
        root = put(root, new Node(key, value, true));
        root.isRed = false;
        refreshSize();
    }

    /**
     * 红黑树插入
     * 1、树根为空直接植入，否则遍历找到相应的叶子节点
     * 2、设置插入节点为红节点，并放到叶子节点的左右链接上
     * 3、判断叶子节点状态：
     * a、叶子节点为左黑右红，则左旋转
     * b、插入节点及叶子节点为连续左红节点，则右旋转
     * c、叶子节点左右链接都为红色，叶子节点上移
     * 4、向上遍历直到根节点，逐个判断节点状态，并做第3步处理
     * @param current
     * @param child
     * @return
     */
    private Node put(Node current, Node child){
        if (current == null){
            return child;
        }

        int comp = current.compareTo(child);
        if (comp < 0) {
            current.left = put(current.left, child);
        }else if (comp > 0){
            current.right = put(current.right, child);
        }else {
            current.value = child.value;
            return current;
        }

        if (! current.left.isRed && current.right.isRed) current = leftCycle(current);
        if (current.isRed && current.left.isRed) current = rightCycle(current);
        if (current.left.isRed && current.right.isRed) flipColors(current);
        return current;
    }

    /**
     * 左旋转
     * @param node
     */
    private Node leftCycle(Node node){
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.isRed = node.isRed;
        node.isRed = true;
        return x;
    }

    /**
     * 右旋转
     * @param node
     */
    private Node rightCycle(Node node){
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.isRed = node.isRed;
        node.isRed = true;
        return x;
    }

    /**
     * 变换颜色
     * @param node
     */
    private void flipColors(Node node){
        node.left.isRed = false;
        node.right.isRed = false;
        node.isRed = true;
    }

    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node parent, Key key){
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

    private void refreshSize(){
        size = size(root);
    }

    public void deleteMin(){
        root = deleteMin(root);
        refreshSize();
    }

    /**
     * 删除最小节点
     * 为了平衡，需保证左子树最底层叶子节点为3-4节点
     * 1、从根节点向左下方遍历，直到左子树最小叶子节点
     * 2、遍历过程中，将当前节点由2节点变为3-4节点
     *     a、根节点为2节点，左右子节点都为2节点，则将这3个节点合为一个4节点；
     *     b、当前节点为3-4节点，左子节点和亲兄弟节点都为2节点，从根节点借一颗最小值移到左节点,并与相邻亲兄弟节点合并为4节点
     *     c、右节点为3-4节点，则从右节点借一颗最小值移到左节点
     * 3、删除最小值
     * 4、从下向上遍历，将4节点转换为2-3节点
     * @param current
     * @return
     */
    private Node deleteMin(Node current){
        if (current == null){
            return null;
        }
        if (nodeType(current) == 2 && nodeType()) current.left = deleteMin(current.left);
        else

        if (! current.left.isRed && current.right.isRed) current = leftCycle(current);
        if (current.isRed && current.left.isRed) current = rightCycle(current);
        if (current.left.isRed && current.right.isRed) flipColors(current);

        if (current.left == null){
            return current.right;
        }
        current.left = deleteMin(current.left);
        return current;
    }

    /**
     * 判断节点类别
     * @param node
     * @return -1：null节点， 2：2节点， 3：三节点， 4: 4节点
     */
    private int nodeType(Node node){
        if (node == null) return -1;
        if (node.left!=null && node.left.isRed
                && node.left.left!=null && ! node.left.left.isRed){
            return 4;
        }else if (node.left!=null && node.left.isRed){
            return 3;
        } else {
            return 2;
        }
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
        boolean isRed;
        Node left;
        Node right;

        Node(Key key, Value value){
            this.key = key;
            this.value = value;
            this.isRed = false;
        }

        Node(Key key, Value value, boolean isRed){
            this.key = key;
            this.value = value;
            this.isRed = isRed;
        }

        public int compareTo(Node o) {
            return this.key.compareTo(o.key);
        }
    }
}
