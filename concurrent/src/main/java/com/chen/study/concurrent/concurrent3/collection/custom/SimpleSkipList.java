package com.chen.study.concurrent.concurrent3.collection.custom;

import java.util.Random;

/**
 * 简单跳跃表
 * @author 陈添明
 * @date 2018/11/25
 */
public class SimpleSkipList {

    // 头结点
    private final static byte HEAD_NODE = -1;
    // 数据节点
    private final static byte DATA_NODE = 0;
    // 尾节点
    private final static byte TAIL_NODE = 1;

    private Node head;
    private Node tail;
    private int size;
    private int height = 1;
    private static Random random;

    private static class Node {
        private Integer value;
        private Node up, down, left, right;
        private byte bit;

        public Node(Integer value, byte bit){
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value){
            this(value, DATA_NODE);
        }
    }

    public SimpleSkipList(){
        head = new Node(null, HEAD_NODE);
        tail = new Node(null, TAIL_NODE);
        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());
    }


    /**
     * 插入元素
     * @param element
     */
    public void add(Integer element){

       // 找到第0层的临近节点
        Node nearNode = this.find(element);
        /**
         * 将新的节点插入到临近节点的右边
         */
        Node newNode = new Node(element);
        newNode.left = nearNode;
        newNode.right = nearNode.right;
        nearNode.right.left = newNode;
        nearNode.right = newNode;

        int currentLevel = 1;

        // 抛硬币决定是否向上提升一层
        Node current = nearNode;
        while (random.nextDouble() < 0.5d){

            currentLevel++;

            if (currentLevel > height){
                height ++;

                Node dumyHead = new Node(null, HEAD_NODE);
                Node dumyTail = new Node(null, TAIL_NODE);
                dumyHead.right = dumyTail;
                dumyHead.down = head;
                head.up = dumyHead;


                dumyTail.left = dumyHead;
                dumyTail.down = tail;
                tail.up = dumyTail;

                head = dumyHead;
                tail = dumyTail;
            }

            // 左移， 直到找到第一个存在up节点的节点
            while ((current != null) && current.up == null){
                current = current.left;
            }

            // 该节点的up节点作为下一层的临近节点
            current = current.up;
            // 将新节点插入临近节点的右边  newNode的上边
            Node upNode = new Node(element);
            upNode.left = current;
            upNode.right = current.right;
            upNode.down = newNode;

            current.right.left = upNode;
            current.right = upNode;

            newNode.up = upNode;

            newNode = upNode;


        }

        size++;


    }

    public void dunmpSkipList(){
        Node temp = head;

        for (int i = height; i > 0; i--) {
            System.out.printf("总层数：%d, 当前层数: %d", height, i);
            Node node = temp.right;
            while (node.bit == DATA_NODE){
                System.out.printf(" -> %d", node.value);
                node = node.right;
            }
            System.out.println();
            temp = temp.down;
        }
        System.out.println("====================");

    }



    /**
     * 查找某个元素
     * 如果存在，则返回对应的节点，否则返回比该元素值小的第一个节点
     * @param element
     * @return
     */
    private Node find (Integer element){
        Node current = head;
        for (;;){
            while (current.right.bit != TAIL_NODE && current.right.value <= element){
                current = current.right;
            }

            if (current.down != null){
                current = current.down;
            }else {
                break;
            }
        }
        return current;
    }

    public Integer get(Integer element){
        Node node = this.find(element);
        return node.value == element ? element : null;
    }

    /**
     * 是否包含
     * @param element
     * @return
     */
    public boolean contains(Integer element){
        Node node = this.find(element);
        return node.value == element;
    }


    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public static void main(String[] args) {
        SimpleSkipList skipList = new SimpleSkipList();


        for (int i = 0; i < 100; i++) {
            skipList.add(random.nextInt(1000));
        }

        skipList.dunmpSkipList();
    }
}
