import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class AVLTreeTest {
    @Test void allInsertionRotationShapesRemainBalanced() {
        int[][] orders = {{30, 20, 10}, {10, 20, 30}, {30, 10, 20}, {10, 30, 20}};
        for (int[] order : orders) {
            AVLTree tree = new AVLTree();
            for (int value : order) assertTrue(tree.insert(value));
            assertEquals(Arrays.asList(10, 20, 30), tree.inorderTraversal());
            assertEquals(2, tree.height());
            assertFalse(tree.insert(20));
            assertEquals(3, tree.size());
        }
    }

    @Test void deleteMaintainsOrderingAndSize() {
        AVLTree tree = new AVLTree();
        for (int value : new int[] {9, 5, 10, 0, 6, 11, -1, 1, 2}) tree.insert(value);
        assertFalse(tree.delete(42));
        assertTrue(tree.delete(10));
        assertTrue(tree.delete(9));
        assertEquals(Arrays.asList(-1, 0, 1, 2, 5, 6, 11), tree.inorderTraversal());
        assertEquals(7, tree.size());
        assertTrue(tree.contains(6));
        assertFalse(tree.contains(10));
    }

    @Test void sortedInsertionsStayLogarithmicAndClearWorks() {
        AVLTree tree = new AVLTree();
        for (int value = 0; value < 1000; value++) tree.insert(value);
        assertTrue(tree.height() <= 12);
        assertEquals(1000, tree.size());
        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.height());
    }

    @Test void traversalSnapshotIsImmutable() {
        AVLTree tree = new AVLTree();
        tree.insert(2); tree.insert(1); tree.insert(3);
        assertThrows(UnsupportedOperationException.class, () -> tree.inorderTraversal().add(4));
    }
}
