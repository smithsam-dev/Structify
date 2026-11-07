public class BinarySearchTree {
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Public method to insert a new node
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // Recursive helper function for insertion
    private Node insertRec(Node root, int data) {
        // If the tree is empty, return a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // Otherwise, traverse down the tree
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    // Public method to print the tree in order
    public void inorder() {
        inorderRec(root);
        System.out.println();
    }

    // Recursive helper function for inorder traversal
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Public method to delete a node
    public void delete(int data) {
        root = deleteRec(root, data);
    }

    // Recursive helper function for deletion
    private Node deleteRec(Node root, int data) {
        // Base case: if the tree is empty
        if (root == null) {
            return root;
        }

        // Traverse down the tree to find the node
        if (data < root.data) {
            root.left = deleteRec(root.left, data);
        } else if (data > root.data) {
            root.right = deleteRec(root.right, data);
        } else {
            // Node to be deleted found!

            // Case 1 & 2: Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 3: Node with two children. 
            // Get the inorder successor (smallest value in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }
        return root;
    }

    // Helper function to find the minimum value in a subtree
    private int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Main method to test the Structify BST
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();

        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Inorder traversal of the Structify BST:");
        tree.inorder();

        System.out.println("\nDeleting node 20 (leaf node)...");
        tree.delete(20);
        tree.inorder();

        System.out.println("\nDeleting node 30 (node with one child)...");
        tree.delete(30);
        tree.inorder();

        System.out.println("\nDeleting node 50 (node with two children)...");
        tree.delete(50);
        tree.inorder();
    }
}
