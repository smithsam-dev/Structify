import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {
    @Test void insertLookupAndDuplicate() {
        BinarySearchTree tree = new BinarySearchTree();
        assertTrue(tree.insert(8));
        assertTrue(tree.insert(3));
        assertFalse(tree.insert(8));
        assertTrue(tree.contains(3));
        assertFalse(tree.contains(4));
        assertEquals(2, tree.size());
    }

    @Test void traversalIsSortedAndImmutable() {
        BinarySearchTree tree = new BinarySearchTree();
        for (int value : new int[] {8, 3, 10, 1, 6, 14, 4, 7, 13}) tree.insert(value);
        assertEquals(Arrays.asList(1, 3, 4, 6, 7, 8, 10, 13, 14), tree.inorderTraversal());
        assertThrows(UnsupportedOperationException.class, () -> tree.inorderTraversal().add(99));
    }

    @Test void deleteHandlesMissingLeafAndTwoChildren() {
        BinarySearchTree tree = new BinarySearchTree();
        for (int value : new int[] {8, 3, 10, 1, 6, 14, 4, 7, 13}) tree.insert(value);
        assertFalse(tree.delete(99));
        assertTrue(tree.delete(1));
        assertTrue(tree.delete(3));
        assertTrue(tree.delete(8));
        assertEquals(Arrays.asList(4, 6, 7, 10, 13, 14), tree.inorderTraversal());
        assertEquals(6, tree.size());
    }

    @Test void heightAndClear() {
        BinarySearchTree tree = new BinarySearchTree();
        assertEquals(0, tree.height());
        tree.insert(2); tree.insert(1); tree.insert(3);
        assertEquals(2, tree.height());
        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.height());
    }
}
