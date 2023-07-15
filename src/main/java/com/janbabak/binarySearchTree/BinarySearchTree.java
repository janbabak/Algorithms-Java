package com.janbabak.binarySearchTree;

/**
 * generic binary search tree
 * @param <T> type of node value,it has to extend Comparable
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    /**
     * root node of the tree
     */
    private Node<T> root;

    /**
     * constructor - create empty binary tree
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * insert value, if vale already is there, do nothing
     * @param value value to insert
     */
    public void insert(T value) {
        root = insertHelper(value, root, null);
    }

    /**
     * delete value from tree, if value isn't there, do nothing
     * @param value value to delete
     */
    public void delete(T value) {
        deleteHelper(value, root);
    }

    /**
     * check if tree contains value
     * @param value needle
     * @return true, if tree contains value, otherwise false
     */
    public boolean contain(T value) {
        return findByValue(value) != null;
    }

    /**
     * check if tree is empty
     * @return true if tree is empty, otherwise false
     */
    public boolean empty() {
        return root == null;
    }

    /**
     * return max value from tree
     * @return max value or null if tree is empty
     */
    public T max() {
        Node<T> mostRight = getMostRight(root);
        if (mostRight != null) {
            return mostRight.getValue();
        }
        return null;
    }

    /**
     * return min value from tree
     * @return min value or null if tree is empty
     */
    public T min() {
        Node<T> mostLeft = getMostLeft(root);
        if (mostLeft != null) {
            return mostLeft.getValue();
        }
        return null;
    }

    /**
     * count number of layers of tree
     * @return height of tree (number of layers)
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * find node by value
     * @param value value of searched node
     * @return node, which has selected value or null, if such node doesn't exist
     */
    public Node<T> findByValue(T value) {
        return findByValueHelper(value, root);
    }

    /**
     * print tree in order (left subtree, value, right subtree)
     */
    public void printInOrder() {
        printInOrderHelper(root);
    }

    /**
     * recursive helper, which inserts value into node
     * @param value value to insert
     * @param node node, where to insert value
     * @return created node
     */
    private Node<T> insertHelper(T value, Node<T> node, Node<T> parent) {
        // stop recursion
        if (node == null) {
            return new Node<>(value, parent);
        }
        // insert value into left subtree
        if (value.compareTo(node.getValue()) < 0) {
            node.setLeft(insertHelper(value, node.getLeft(), node));
        }
        // insert value into right subtree
        if (value.compareTo(node.getValue()) > 0) {
            node.setRight(insertHelper(value, node.getRight(), node));
        }
        // value == node.value
        return node;
    }

    /**
     * recursive helper, which delete value from tree
     * @param value value to delete
     * @param node node, from which to delete
     */
    private void deleteHelper(T value, Node<T> node) {
        // tree doesn't contain value
        if (node == null) {
            return;
        }

        // value can be in left subtree
        if (value.compareTo(node.getValue()) < 0) {
            deleteHelper(value, node.getLeft());
            return;
        }

        // value can be in right subtree
        if (value.compareTo(node.getValue()) > 0) {
            deleteHelper(value, node.getRight());
            return;
        }

        // value == node value

        // node doesn't have descendants -> delete it like leaf (set it null)
        if (node.getLeft() == null && node.getRight() == null) {
            deleteLeaf(node);
            return;
        }

        // node has only one descendant -> connect parent and descendant, delete node
        // left is null, right is not null
        if (node.getLeft() == null) {
            deleteNodeWithRightDescendant(node);
            return;
        }
        // right is null, left is not null
        if (node.getRight() == null) {
            deleteNodeWithLeftDescendant(node);
            return;
        }

        // node has both descendants ->  swap it with successor and delete node
        Node<T> successor = successor(node);
        if (successor != null) {
            T tmpValue = successor.getValue();
            successor.setValue(node.getValue());
            node.setValue(tmpValue);
            deleteHelper(value, successor);
        }
    }

    /**
     * recursive helper, which find node by value
     * @param value value of searched node
     * @param node node to search in
     * @return node, which has selected value or null, if such node doesn't exist
     */
    private Node<T> findByValueHelper(T value, Node<T> node) {
        // stop recursion
        if (node == null) {
            return null;
        }
        // search in left subtree
        if (value.compareTo(node.getValue()) < 0) {
            return findByValueHelper(value, node.getLeft());
        }
        // search in right subtree
        if (value.compareTo(node.getValue()) > 0) {
            return findByValueHelper(value, node.getRight());
        }
        // value of this node is equal to value
        return node;
    }

    /**
     * recursive helper for printing tree in order (left subtree, value, right subtree)
     * @param node node to print
     */
    private void printInOrderHelper(Node<T> node) {
        // stop recursion
        if (node == null) {
            return;
        }

        printInOrderHelper(node.getLeft());  // print left subtree
        System.out.print(node.getValue() + " ");  // print value
        printInOrderHelper(node.getRight());  // print right subtree
    }

    /**
     * return successor of node or null of doesn't exist
     * @param node predecessor of successor
     * @return successor or null
     */
    private Node<T> successor(Node<T> node) {
        if (node == null) {
            return null;
        }

        // node has right descendant -> return most left descendant of right descendant, if right haven't left subtree
        if (node.getRight() != null) {
            return getMostLeft(node.getRight());
        }

        //node hasn't right descendant, successor is first parent, which right isn't next
        Node<T> parent = node.getParent();
        Node<T> next = node;
        while (parent != null && parent.getRight() == next) {
            next = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    /**
     * return the most left descendant of node
     * @param node node of most left is returned
     * @return most left descendant of node or node if left subtree is null
     */
    private Node<T> getMostLeft(Node<T> node) {
        // node doesn't have left subtree
        if (node == null) {
            return null;
        }

        Node<T> left = node;
        while (true) {
            if (left.getLeft() == null) {
                return left;
            }
            left = left.getLeft();
        }
    }

    /**
     * return the most right descendant of node
     * @param node node of most right is returned
     * @return most right descendant of node or node if right subtree is null
     */
    private Node<T> getMostRight(Node<T> node) {
        if (node == null) {
            return null;
        }

        Node<T> right = node;
        while (true) {
            if (right.getRight() == null) {
                return right;
            }
            right = right.getRight();
        }
    }

    /**
     * recursive helper to find height of node
     * @param node height of this node is returned
     * @return height of node
     */
    private int heightHelper(Node<T> node) {
        // stop recursion
        if (node == null) {
            return 0;
        }

        int leftHeight = heightHelper(node.getLeft());
        int rightHeight = heightHelper(node.getRight());

        // return greater height + 1 because of this node
        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        }
        return rightHeight + 1;
    }

    /**
     * delete leaf (node with no descendant)
     * @param node node to delete
     */
    private void deleteLeaf(Node<T> node) {
        if (node == null) {
            return;
        }

        Node<T> parent = node.getParent();
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }
        if (root == node) {
            root = null;
        }
    }

    /**
     * delete node, which has left descendant - connect descendant with parent
     * @param node node to delete
     */
    private void deleteNodeWithLeftDescendant(Node<T> node) {
        if (node == null) {
            return;
        }

        Node<T> parent = node.getParent();
        Node<T> left = node.getLeft();
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(left);
            } else {
                parent.setRight(left);
            }
            left.setParent(parent);
        } else {
            root = left;  // parent is null -> deleting root
            root.setParent(null);
        }
    }

    /**
     * delete node, which has right descendant - connect descendant with parent
     * @param node node to delete
     */
    private void deleteNodeWithRightDescendant(Node<T> node) {
        if (node == null) {
            return;
        }

        Node<T> parent = node.getParent();
        Node<T> right = node.getRight();
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(right);
            } else {
                parent.setRight(right);
            }
            right.setParent(parent);
        } else {
            root = right;  // parent is null -> deleting root
            root.setParent(null);
        }
    }
}


