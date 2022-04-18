package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Represents a list with sentinel nodes at the beginning 
 * and the end that can be traversed through using an iterator. 
 * Items in the list are added and removed using the head nodes
 * and the tail nodes to increase efficiency. 
 * 
 * @author sophia rubsamen 
 * @version 2022.04.18
 * @param <E> is the type of items that will be stored in the list. 
 * SOURCE OF CODE: LAB 10
 */
public class DLList<E> implements Iterable {

    private int size;
    private Node<E> headNode;
    private Node<E> tailNode;

    /**
     * Create a new DLList object.
     */
    public DLList() {
        init();
    }

    /**
     * Initializes the object to have the headNode and tailNode nodes
     */
    private void init() {
        headNode = new DLList.Node<E>(null);
        tailNode = new DLList.Node<E>(null);
        headNode.setNext(tailNode);
        tailNode.setPrevious(headNode);
        size = 0;
    }
    
    /**
     * Adds a element to the end of the list
     *
     * @param newEntry
     *            the element to add to the end
     */
    public void add(E newEntry) {
        add(size(), newEntry);
    }
    
    /**
     * Adds the object to the position in the list
     *
     * @param index
     *            where to add the object
     * @param obj
     *            the object to add
     * @throws IndexOutOfBoundsException
     *             if index is less than zero or greater than size
     * @throws IllegalArgumentException
     *             if obj is null
     */
    public void add(int index, E obj) {
        if (index < 0 || size < index) {
            throw new IndexOutOfBoundsException();
        }
        if (obj == null) {
            throw new IllegalArgumentException("Cannot add null "
                    + "objects to a list");
        }

        Node<E> nodeAfter;
        if (index == size) {
            nodeAfter = tailNode;
        } 
        else {
            nodeAfter = getNodeAtIndex(index);
        }

        Node<E> addition = new Node<E>(obj);
        addition.setPrevious(nodeAfter.previous());
        addition.setNext(nodeAfter);
        nodeAfter.previous().setNext(addition);
        nodeAfter.setPrevious(addition);
        size++;

    }

    /**
     * Removes the first object in the list that .equals(obj)
     *
     * @param obj
     *            the object to remove
     * @return true if the object was found and removed
     */

    public boolean remove(E obj) {
        Node<E> current = headNode.next();
        while (!current.equals(tailNode)) {
            if (current.getData().equals(obj)) {
                current.previous().setNext(current.next());
                current.next().setPrevious(current.previous());
                size--;
                return true;
            }
            current = current.next();
        }
        return false;
    }
    
    /**
     * Removes the element at the specified index from the list
     *
     * @param index
     *            where the object is located
     * @throws IndexOutOfBoundsException
     *             if there is not an element at the index
     * @return true if successful
     */
    public boolean remove(int index) {
        Node<E> nodeToBeRemoved = getNodeAtIndex(index);
        nodeToBeRemoved.previous().setNext(nodeToBeRemoved.next());
        nodeToBeRemoved.next().setPrevious(nodeToBeRemoved.previous());
        size--;
        return true;
    }
    
    /**
     * Removes all of the elements from the list
     */
    public void clear() {
        init();
    }
    
    /**
     * gets the node at that index
     * 
     * @param index
     * @return node at index
     */
    private Node<E> getNodeAtIndex(int index) {
        if (index < 0 || size() <= index) {
            throw new IndexOutOfBoundsException("No element exists at " 
                    + index);
        }
        Node<E> current = headNode.next(); // as we have a sentinel node
        for (int i = 0; i < index; i++) {
            current = current.next();
        }
        return current;
    }
    
    /**
     * Converts the list to an array object. 
     * 
     * @return an array of items in this list
     */
    public E[] toArray() {
        return null;
    }
    
    /**
     * Gets the object at the given position
     *
     * @param index
     *            where the object is located
     * @return The object at the given position
     * @throws IndexOutOfBoundsException
     *             if there no node at the given index
     */
    public E getEntry(int index) {
        return getNodeAtIndex(index).getData();
    }
    
    /**
     * Checks if the list contains the given object
     *
     * @param obj
     *            the object to check for
     * @return true if it contains the object
     */
    public boolean contains(E obj) {
        return lastIndexOf(obj) != -1;
    }

    /**
     * Gets the number of elements in the list
     *
     * @return the number of elements
     */
    public int size() {
        return size;
    }
    
    /**
     * Checks if the array is empty
     *
     * @return true if the array is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Gets the last time the given object is in the list
     *
     * @param obj
     *            the object to look for
     * @return the last position of it. -1 If it is not in the list
     */
    public int lastIndexOf(E obj) {
        /*
         * We should go from the end of the list as then we an stop once we find
         * the first one
         */
        Node<E> current = tailNode.previous();
        for (int i = size() - 1; i >= 0; i--) {
            if (current.getData().equals(obj)) {
                return i;
            }
            current = current.previous();
        }
        return -1;  //if we do not find it
    }


    /**
     * Returns a string representation of the list If a list contains A, B, and
     * C, the following should be returned "{A, B, C}" (Without the quotations)
     *
     * @return a string representing the list
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        if (!isEmpty()) {
            Node<E> currNode = headNode.next();
            while (currNode != tailNode) {
                E element = currNode.getData();
                builder.append(element.toString());
                if (currNode.next != tailNode) {
                    builder.append(", ");
                }  
                currNode = currNode.next();
            }
        }

        builder.append("}");
        return builder.toString();
    }
    
    /**
     * Iterator method creates Iterator object
     *
     * @return new Iterator object
     */
    public Iterator<E> iterator() {
        return new DLListIterator<E>(this);
    } 

    
    /**
     * 
     * @author 
     * @version 
     *
     * @param <A>
     */
    private class DLListIterator<A> implements Iterator<E> {
         
        private Node<E> next;

        /**
         * Creates a new DLListIterator
         */
        public DLListIterator(DLList<E> list) {
            next = list.headNode;
        }

        /**
         * Checks if there are more elements in the list
         *
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return this.next.next() != tailNode;
            
        }

        /**
         *Gets the next value in the list
         *
         * @return the next value
         * @throws NoSuchElementException
         *             if there are no nodes left in the list
         */
        @Override
        public E next() {
            if (this.hasNext()) {
                E nextInfo = next.next().getData();
                next = next.next();
                return nextInfo;
            }
            else {
                throw new NoSuchElementException();
            }
        }
    }
    /**
     * This represents a node in a doubly linked list. This node stores data, a
     * pointer to the node before it in the list, and a pointer to the node
     * after it in the list
     *
     * @param <E>
     *            This is the type of object that this class will store
     * @author Mark Wiggans (mmw125)
     * @version 4/14/2015
     */
    private static class Node<E> {
        private Node<E> next;
        private Node<E> previous;
        private E data;

        /**
         * Creates a new node with the given data
         *
         * @param d
         *            the data to put inside the node
         */
        public Node(E d) {
            data = d;
        }
        
        /**
         * Represents a node with data and a next component already 
         * set. 
         * 
         * @param data is the data the node will hold
         * @param next is the node after this 
         */
        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
            
        }

        /**
         * Sets the node after this node
         *
         * @param n
         *            the node after this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }

        /**
         * Sets the node before this one
         *
         * @param n
         *            the node before this one
         */
        public void setPrevious(Node<E> n) {
            previous = n;
        }

        /**
         * Gets the next node
         *
         * @return the next node
         */
        public Node<E> next() {
            return next;
        }

        /**
         * Gets the node before this one
         *
         * @return the node before this one
         */
        public Node<E> previous() {
            return previous;
        }

        /**
         * Gets the data in the node
         *
         * @return the data in the node
         */
        public E getData() {
            return data;
        }
    }
}
