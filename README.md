# Structify 🧱

**Java Data Structures Toolkit**

Structify is an educational Java library with integer binary search tree implementations. Use the basic BST to explore ordered trees, or choose the self-balancing AVL tree when predictable lookup, insertion, and deletion time matters.

## Binary search tree

`BinarySearchTree` ignores duplicate values and provides `insert`, `contains`, `delete`, `size`, `isEmpty`, `clear`, `height`, and sorted `inorderTraversal`. It can also be used in a for-each loop.

Its operations take O(h), where h is tree height, and can take O(n) for sorted insertions. The height of an empty tree is 0.

## AVL tree

`AVLTree` offers the same collection API while rebalancing itself after insertions and deletions. Its lookup, insertion, and deletion take O(log n). Duplicate values are ignored, and traversal returns an immutable ascending snapshot.

```java
AVLTree tree = new AVLTree();
tree.insert(8);
tree.insert(3);
tree.insert(10);

System.out.println(tree.contains(3));        // true
System.out.println(tree.inorderTraversal()); // [3, 8, 10]
System.out.println(tree.height());           // 2

tree.delete(8);
```

## Getting started

Requires Java 8 or newer and Maven. Compile the project and run the automated tests:

```bash
git clone https://github.com/smithsam-dev/Structify.git
cd Structify
mvn test
```

To run the binary search tree demo:

```bash
javac src/*.java
java -cp src BinarySearchTree
```

## Contributing

Contributions, issues, and feature requests are welcome. Useful next steps include a generic comparator-based tree, more structures such as heaps and hash tables, and benchmarks comparing balanced and unbalanced trees.

1. Fork the project.
2. Create a feature branch.
3. Commit your changes and open a pull request.

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for details.
