package com.escuelaing.edu;


public class BinarySearchTree<T extends Comparable<T>> {

    public Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insert(T value) {
        root = insertNode(root, value);
    }

    private Node<T> insertNode(Node<T> root, T value) {
        if (root == null) {
            root = new Node<>(value);
            return root;
        }
        if (value.compareTo(root.value) < 0) {
            root.left = insertNode(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = insertNode(root.right, value);
        }
        return root;
    }

    public Node<T> search(T value){
        return searcV(root, value);
    }
    
    private Node<T> searcV(Node<T> root, T value) {
        if (root == null || root.value.equals(value)) {
            return root;
        }
        if (value.compareTo(root.value) < 0) {
            return searcV(root.left, value);
        } else {
            return searcV(root.right, value);
        }
    }
    

    public void delete(T value) {
        root = deleteRec(root, value);
    }

    private Node<T> deleteRec(Node<T> root, T value) {
        if (root == null) {
            return root;
        }

        if (value.compareTo(root.value) < 0) {
            root.left = deleteRec(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = deleteRec(root.right, value);
        } else {
            // Caso 1: Nodo hoja
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            Node<T> successor = findMin(root.right);
            root.value = successor.value;
            root.right = deleteRec(root.right, successor.value);
        }
        return root;
    }

    public Node<T> findMin(Node<T> root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public Node<T> findMax(Node<T> root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }
}

