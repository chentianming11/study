package com.chen.study.concurrent.concurrent3.collection.custom;

import java.util.Objects;

/**
 * 可排序的单向链表
 * @author 陈添明
 * @date 2018/11/24
 */
public class PriorityLinkedList<E extends Comparable<E>> {

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

    public static <E extends Comparable<E>> PriorityLinkedList<E> of(E... es){
        PriorityLinkedList<E> linkedList = new PriorityLinkedList<>();
        if (es.length > 0){
            for (E e : es) {
                linkedList.add(e);
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


    public PriorityLinkedList<E> add(E e){
        Node<E> node = new Node<>(e);
        Node<E> previous = null;
        Node<E> current = first;
        while (current != null && e.compareTo(current.value) > 0){
            previous = current;
            current = current.next;
        }

        // 放在第一位
        if (previous == null) {
            node.next = first;
            first = node;
        }else {
           node.next =  previous.next;
           previous.next = node;
        }

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
        PriorityLinkedList<Integer> linkedList = PriorityLinkedList.of(1, 2, 3, 5, 6, 8);
        linkedList.add(4);
        System.out.println(linkedList);
    }


}
