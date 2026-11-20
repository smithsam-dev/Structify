import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * An integer binary search tree.
 *
 * <p>Values are kept in ascending order. Duplicate values are ignored.
 * The tree is not self-balancing, so operations take O(h) time, where h is
 * the tree height (O(log n) when reasonably balanced, O(n) in the worst case).
 */
public class BinarySearchTree implements Iterable<Integer> {
    Node root;
    private int size;

    /** Creates an empty tree. */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Inserts {@code data} unless it is already present.
     *
     * @return {@code true} when a value was inserted; {@code false} for a duplicate
     */
    public boolean insert(int data) {
        if (root == null) {
            root = new Node(data);
            size++;
            return true;
        }

        Node current = root;
        while (true) {
            if (data == current.data) {
                return false;
            }
            if (data < current.data) {
                if (current.left == null) {
                    current.left = new Node(data);
                    size++;
                    return true;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new Node(data);
                    size++;
                    return true;
                }
                current = current.right;
            }
        }
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

    /**
     * Removes {@code data} when present.
     *
     * @return {@code true} when a value was removed; {@code false} when absent
     */
    public boolean delete(int data) {
        if (!contains(data)) {
            return false;
        }
        root = deleteRec(root, data);
        size--;
        return true;
    }

    private Node deleteRec(Node current, int data) {
        if (data < current.data) {
            current.left = deleteRec(current.left, data);
        } else if (data > current.data) {
            current.right = deleteRec(current.right, data);
        } else {
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }

            Node successor = current.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            current.data = successor.data;
            current.right = deleteRec(current.right, successor.data);
        }
        return current;
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

    /**
     * Returns an immutable, ascending snapshot of the tree's values.
     */
    public List<Integer> inorderTraversal() {
        List<Integer> values = new ArrayList<>(size);
        inorderRec(root, values);
        return Collections.unmodifiableList(values);
    }

    private void inorderRec(Node current, List<Integer> values) {
        if (current != null) {
            inorderRec(current.left, values);
            values.add(current.data);
            inorderRec(current.right, values);
        }
    }

    /**
     * Returns the tree height measured in nodes. An empty tree has height 0.
     */
    public int height() {
        return heightRec(root);
    }

    private int heightRec(Node current) {
        if (current == null) {
            return 0;
        }
        return 1 + Math.max(heightRec(current.left), heightRec(current.right));
    }

    /**
     * Prints the values in ascending order, preserving the original demo API.
     */
    public void inorder() {
        for (int value : inorderTraversal()) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    /**
     * Iterates over an immutable ascending snapshot of the tree's values.
     */
    @Override
    public Iterator<Integer> iterator() {
        return inorderTraversal().iterator();
    }

    /** Runs a small demonstration of insertion, lookup, and deletion. */
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int value : new int[] {50, 30, 20, 40, 70, 60, 80}) {
            tree.insert(value);
        }

        System.out.println("In-order values: " + tree.inorderTraversal());
        System.out.println("Contains 40: " + tree.contains(40));
        System.out.println("Size: " + tree.size() + ", height: " + tree.height());

        tree.delete(50);
        System.out.println("After deleting 50: " + tree.inorderTraversal());
    }
}
