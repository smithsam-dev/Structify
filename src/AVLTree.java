import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An integer AVL tree: a binary search tree that rebalances itself after
 * insertion and deletion.
 *
 * <p>Duplicate values are ignored. Lookup, insertion, and deletion take O(log n)
 * time. Height is measured in nodes, so an empty tree has height 0.
 */
public class AVLTree implements Iterable<Integer> {
    private Node root;
    private int size;

    private static final class Node {
        private int data;
        private int height = 1;
        private Node left;
        private Node right;

        private Node(int data) {
            this.data = data;
        }
    }

    /** Inserts {@code data} unless it is already present. */
    public boolean insert(int data) {
        boolean[] inserted = {false};
        root = insertRec(root, data, inserted);
        if (inserted[0]) {
            size++;
        }
        return inserted[0];
    }

    private Node insertRec(Node node, int data, boolean[] inserted) {
        if (node == null) {
            inserted[0] = true;
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertRec(node.left, data, inserted);
        } else if (data > node.data) {
            node.right = insertRec(node.right, data, inserted);
        } else {
            return node;
        }
        return rebalance(node);
    }

    /** Returns whether the tree contains {@code data}. */
    public boolean contains(int data) {
        Node current = root;
        while (current != null) {
            if (data == current.data) {
                return true;
            }
            current = data < current.data ? current.left : current.right;
        }
        return false;
    }

    /** Removes {@code data} when present and reports whether it was found. */
    public boolean delete(int data) {
        boolean[] removed = {false};
        root = deleteRec(root, data, removed);
        if (removed[0]) {
            size--;
        }
        return removed[0];
    }

    private Node deleteRec(Node node, int data, boolean[] removed) {
        if (node == null) {
            return null;
        }

        if (data < node.data) {
            node.left = deleteRec(node.left, data, removed);
        } else if (data > node.data) {
            node.right = deleteRec(node.right, data, removed);
        } else {
            removed[0] = true;
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node successor = minimum(node.right);
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data, new boolean[1]);
        }
        return rebalance(node);
    }

    private Node minimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** Returns the number of values in the tree. */
    public int size() {
        return size;
    }

    /** Returns whether the tree has no values. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Removes every value from the tree. */
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the current tree height measured in nodes. */
    public int height() {
        return nodeHeight(root);
    }

    /** Returns an immutable, ascending snapshot of the tree's values. */
    public List<Integer> inorderTraversal() {
        List<Integer> values = new ArrayList<>(size);
        inorderRec(root, values);
        return Collections.unmodifiableList(values);
    }

    private void inorderRec(Node node, List<Integer> values) {
        if (node != null) {
            inorderRec(node.left, values);
            values.add(node.data);
            inorderRec(node.right, values);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return inorderTraversal().iterator();
    }

    private Node rebalance(Node node) {
        updateHeight(node);
        int balance = balanceFactor(node);

        if (balance > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node top) {
        Node pivot = top.left;
        Node middle = pivot.right;
        pivot.right = top;
        top.left = middle;
        updateHeight(top);
        updateHeight(pivot);
        return pivot;
    }

    private Node rotateLeft(Node top) {
        Node pivot = top.right;
        Node middle = pivot.left;
        pivot.left = top;
        top.right = middle;
        updateHeight(top);
        updateHeight(pivot);
        return pivot;
    }

    private static int nodeHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private static int balanceFactor(Node node) {
        return nodeHeight(node.left) - nodeHeight(node.right);
    }

    private static void updateHeight(Node node) {
        node.height = 1 + Math.max(nodeHeight(node.left), nodeHeight(node.right));
    }
}
