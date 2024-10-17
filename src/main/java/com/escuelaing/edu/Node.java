package com.escuelaing.edu;

public class Node<T extends Comparable<T>> {
    Node<T> right;
    Node<T> left;
    public T value;
    
    public Node(T value){
        this.value = value;
        this.right = null;
        this.left = null;
    }
}
