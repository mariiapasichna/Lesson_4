package com.javaelementary;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        IntList list1 = new IntLinkedList();
        Queue<Integer> queue = new LinkedList<>();
        IntQueue queue1 = new IntLinkedList();
        Deque<Integer> stack = new LinkedList<>();
        IntStack stack1 = new IntLinkedList();
        System.out.println(stack.peek());
        System.out.println(stack1.peek());
        for (int i = 0; i <10 ; i++) {
            list.add(i);
            list1.add(i);
            queue.add(i);
            queue1.add(i);
            stack.push(i);
            stack1.push(i);
        }
        System.out.println(queue);
        System.out.println(queue1);
        System.out.println(queue.remove());
        System.out.println(queue1.remove());
        System.out.println(queue);
        System.out.println(queue1);
        System.out.println(queue.element());
        System.out.println(queue1.element());
        System.out.println(queue);
        System.out.println(queue1);

        System.out.println(stack);
        System.out.println(stack1);
        System.out.println(stack.pop());
        System.out.println(stack1.pop());
        System.out.println(stack);
        System.out.println(stack1);
        System.out.println(stack.peek());
        System.out.println(stack1.peek());

        System.out.println(list);
        System.out.println(list1);
        System.out.println(list.add(10));
        System.out.println(list1.add(10));
        System.out.println(list);
        System.out.println(list1);
        list.add(1,22);
        list1.add(1,22);
        System.out.println(list);
        System.out.println(list1);
//        list.clear();
//        list1.clear();
//        System.out.println(list);
//        System.out.println(list1);
        System.out.println(list.get(8));
        System.out.println(list1.get(8));
        System.out.println(list.isEmpty());
        System.out.println(list1.isEmpty());
        System.out.println(list.remove(2));
        System.out.println(list1.remove(2));
        System.out.println(list);
        System.out.println(list1);
        Integer i = 5;
        System.out.println(list.remove(i));
        System.out.println(list1.removeByValue(5));
        System.out.println(list);
        System.out.println(list1);
        System.out.println(list.set(0,i));
        System.out.println(list1.set(0,5));
        System.out.println(list);
        System.out.println(list1);
        System.out.println(list.size());
        System.out.println(list1.size());

        List subList = list.subList(1,7);
        IntList subList1 = list1.subList(1,7);
        System.out.println(subList);
        System.out.println(subList1);
        subList.add(33);
        subList1.add(33);
        System.out.println(list);
        System.out.println(list1);
        subList.add(1,44);
        subList1.add(1,44);
        System.out.println(list);
        System.out.println(list1);
        System.out.println(subList.get(0));
        System.out.println(subList1.get(0));
        System.out.println(subList.remove(1));
        System.out.println(subList1.remove(1));
        System.out.println(list);
        System.out.println(list1);
        System.out.println(subList.isEmpty());
        System.out.println(subList1.isEmpty());
        Integer x = 2;
        System.out.println(subList.remove(x));
        System.out.println(subList1.removeByValue(2));
        System.out.println(list);
        System.out.println(list1);
        System.out.println(subList.set(0,55));
        System.out.println(subList1.set(0,55));
        System.out.println(list);
        System.out.println(list1);
        System.out.println(subList.size());
        System.out.println(subList1.size());
        System.out.println(subList.subList(1,3));
        System.out.println(subList1.subList(1,3));
        subList.clear();
        subList1.clear();
        System.out.println(list);
        System.out.println(list1);
    }
}