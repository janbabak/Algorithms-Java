package com.janbabak.binarySearchTree;

/**
 * node of binary tree
 * @param <T> type of node value has to extend Comparable
 */
public class Node<T extends Comparable<? super T>> {

    /**
     * node value
     */
    private T value;

    /**
     * left subtree
     */
    private Node<T> left;

    /**
     * right subtree
     */
    private Node<T> right;

    /**
     * parent node
     */
    private Node<T> parent;

    /**
     * constructor
     * @param value value stored in node
     */
    public Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

    /**
     * value getter
     * @return value
     */
    public T getValue() {
        return value;
    }

    /**
     * value setter
     * @param value new value
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * left subtree getter
     * @return left subtree
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * left subtree setter
     * @param left new left subtree
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * right subtree getter
     * @return right subtree
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     * right subtree setter
     * @param right new right subtree
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }

    /**
     * parent getter
     * @return parent
     */
    public Node<T> getParent() {
        return parent;
    }

    /**
     * parent setter
     * @param parent new parent
     */
    public void setParent(Node<T> parent) {
        this.parent = parent;
    }
}
