package com.javaelementary;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class IntLinkedList implements IntList, IntQueue, IntStack {
    private int size;
    private Entry first;
    private Entry last;

    private static class Entry {
        int value;
        Entry previous;
        Entry next;

        public Entry(int value) {
            this.value = value;
        }
    }

    /**
     * Appends the specified element to the end of this list.
     * @param value - element to be appended to this list
     * @return true if this collection changed as a result of the call
     */
    @Override
    public boolean add(int value) {
        Entry newElement = new Entry(value);
        if (size == 0) {
            first = newElement;
            last = newElement;
        } else {
            last.next = newElement;
            newElement.previous = last;
            last = newElement;
        }
        size++;
        return true;
    }

    /**
     * Retrieves and removes the head (first element) of this list.
     * @return the head of this list
     * @throws NoSuchElementException - if this list is empty
     */
    @Override
    public int remove() {
        isEmptyList();
        int result = first.value;
        first = first.next;
        first.previous = null;
        size--;
        return result;
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     * @return the head of this list
     * @throws NoSuchElementException - if this list is empty
     */
    @Override
    public int element() {
        isEmptyList();
        return first.value;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * @param index   - index at which the specified element is to be inserted
     * @param element - element to be inserted
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
     */
    @Override
    public void add(int index, int element) {
        Entry newElement = new Entry(element);
        Entry tmp = getEntry(index);
        newElement.next = tmp;
        newElement.previous = tmp.previous;
        tmp.previous.next = newElement;
        tmp.previous = newElement;
        size++;
    }

    /**
     * Removes all of the elements from this list. The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index - index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
     */
    @Override
    public int get(int index) {
        checkIndex(index);
        return getEntry(index).value;
    }

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * @param index - the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
     */
    @Override
    public int remove(int index) {
        checkIndex(index);
        Entry tmp = getEntry(index);
        int result = tmp.value;
        tmp.previous.next = tmp.next;
        tmp.next.previous = tmp.previous;
        size--;
        return result;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present.
     * @param value - element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean removeByValue(int value) {
        Entry tmp = first;
        for (int i = 0; i < size; i++) {
            if (tmp.value == value) {
                tmp.previous.next = tmp.next;
                tmp.next.previous = tmp.previous;
                size--;
                return true;
            }
            tmp = tmp.next;
        }
        return false;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index   - index of the element to replace
     * @param element - element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
     */
    @Override
    public int set(int index, int element) {
        checkIndex(index);
        int result = getEntry(index).value;
        getEntry(index).value = element;
        return result;
    }

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * @param fromIndex - low endpoint (inclusive) of the subList
     * @param toIndex   - high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
     * @throws IllegalArgumentException - if the endpoint indices are out of order (fromIndex > toIndex)
     */
    @Override
    public IntList subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size);
        return new SubList(this, fromIndex, toIndex);
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * @return an array containing all of the elements in this list in proper sequence
     */
    @Override
    public int[] toArray() {
        int[] result = new int[size];
        Entry tmp = first;
        for (int i = 0; i < size; i++) {
            result[i] = tmp.value;
            tmp = tmp.next;
        }
        return result;
    }

    /**
     * Pushes an element onto the stack represented by this list. In other words, inserts the element at the front of this list.
     * @param value - the element to push
     */
    @Override
    public void push(int value) {
        Entry newElement = new Entry(value);
        if (size == 0) {
            first = newElement;
            last = newElement;
        } else {
            first.previous = newElement;
            newElement.next = first;
            first = newElement;
        }
        size++;
    }

    /**
     * Pops an element from the stack represented by this list. In other words, removes and returns the first element of this list.
     * @return the element at the front of this list (which is the top of the stack represented by this list)
     * @throws NoSuchElementException - if this list is empty
     */
    @Override
    public int pop() {
        isEmptyList();
        int result = first.value;
        first = first.next;
        size--;
        return result;
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     * @return the head of this list, or null if this list is empty
     */
    @Override
    public int peek() {
        int result = 0;
        if (first != null) {
            result = first.value;
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    private Entry getEntry(int index) {
        Entry tmp;
        if (index < size / 2) {
            tmp = first;
            for (int i = 0; i < index; i++) {
                tmp = tmp.next;
            }
        } else {
            tmp = last;
            for (int i = size - 1; i > index; i--) {
                tmp = tmp.previous;
            }
        }
        return tmp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of range");
        }
    }

    private static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
    }

    private void isEmptyList() {
        if (size == 0) {
            throw new NoSuchElementException("This queue is empty");
        }
    }

    private static class SubList implements IntList {
        private final IntLinkedList intLinkedList;
        private final SubList subList;
        private final int offset;
        private int size;

        public SubList(IntLinkedList intLinkedList, int fromIndex, int toIndex) {
            this.intLinkedList = intLinkedList;
            this.subList = null;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
        }

        public SubList(SubList subList, int fromIndex, int toIndex) {
            this.intLinkedList = subList.intLinkedList;
            this.subList = subList;
            this.offset = subList.offset + fromIndex;
            this.size = toIndex - fromIndex;
        }

        @Override
        public boolean add(int element) {
            intLinkedList.add(offset + size, element);
            return true;
        }

        @Override
        public void add(int index, int element) {
            checkSubListIndex(index);
            intLinkedList.add(offset + index, element);
        }

        @Override
        public void clear() {
            Entry clearFirst = intLinkedList.getEntry(offset - 1);
            Entry clearLast = intLinkedList.getEntry(offset + size);
            clearFirst.next = clearLast;
            clearLast.previous = clearFirst;
            intLinkedList.size = intLinkedList.size - this.size;
            this.size = 0;
        }

        @Override
        public int get(int index) {
            checkSubListIndex(index);
            return intLinkedList.get(offset + index);
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }

        @Override
        public int remove(int index) {
            checkSubListIndex(index);
            return intLinkedList.remove(offset + index);
        }

        @Override
        public boolean removeByValue(int value) {
            Entry tmp = intLinkedList.getEntry(offset);
            for (int i = 0; i < size; i++) {
                if (tmp.value == value) {
                    tmp.previous.next = tmp.next;
                    tmp.next.previous = tmp.previous;
                    intLinkedList.size--;
                    return true;
                }
                tmp = tmp.next;
            }
            return false;
        }

        @Override
        public int set(int index, int element) {
            checkSubListIndex(index);
            return intLinkedList.set(offset + index, element);
        }

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public IntList subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new SubList(this, fromIndex, toIndex);
        }

        @Override
        public int[] toArray() {
            int[] result = new int[size];
            Entry tmp = intLinkedList.getEntry(offset);
            for (int i = 0; i < size; i++) {
                result[i] = tmp.value;
                tmp = tmp.next;
            }
            return result;
        }

        @Override
        public String toString() {
            return Arrays.toString(toArray());
        }

        private void checkSubListIndex(int index) {
            if (index < 0 || index > this.size) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size);
            }
        }
    }
}