package com.chen.study.concurrent.concurrent3.collection.custom;

import java.util.Objects;

/**
 * 单向链表
 * @author 陈添明
 * @date 2018/11/24
 */
public class LinkedList<E> {

    private Node<E> first;

    private int size;


    private static class Node<E> {
        E value;
        Node<E> next;

        public Node (E value){
            this.value = value;
        }

        @Override
        public String toString() {
           return value == null ? "null" : value.toString();
        }
    }

    public static <E> LinkedList<E> of(E... es){
        LinkedList<E> linkedList = new LinkedList<>();
        if (es.length > 0){
            for (E e : es) {
                linkedList.addFirst(e);
            }
        }
        return linkedList;
    }

    public E removeFirst() {
        if (isEmpty()){
            throw new RuntimeException("链表为空");
        }
        E result = first.value;
        first = first.next;
        size--;
        return result;
    }

    public boolean contains(E e){
        Node<E> current = first;
        while (current != null){
            if (Objects.equals(e, current.value)){
                return true;
            }
            current = current.next;
        }
        return false;
    }


    public LinkedList<E> addFirst(E e){
        Node<E> node = new Node<>(e);
        node.next = first;
        first = node;
        size++;
        return this;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public String toString() {
        if (isEmpty()){
            return "[]";
        }

        StringBuilder builder = new StringBuilder("[");
        Node<E> current = first;
        while (current != null){
            builder.append(current.toString() + ",");
            current = current.next;
        }
       builder.replace(builder.length()-1, builder.length(), "]");
        return builder.toString();

    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = LinkedList.of("hello", "world", "java", "thinking", "chan");
        System.out.println(linkedList.size());
        System.out.println(linkedList.contains("java"));
        System.out.println(linkedList.toString());
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.removeFirst());
        System.out.println(linkedList.removeFirst());
    }


}
