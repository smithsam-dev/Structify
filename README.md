# Structify 🧱

**Java Data Structures Toolkit**

Structify is a small, educational Java library for core data structures. It currently includes a binary search tree for integers, with a simple API for insertion, lookup, deletion, ordered traversal, and basic tree metrics.

## Binary search tree

- Duplicate values are ignored. `insert(value)` returns `false` when the value is already present.
- `contains(value)` checks membership.
- `delete(value)` returns whether a value was removed.
- `inorderTraversal()` returns an immutable list in ascending order. The tree can also be used in a for-each loop.
- `size()`, `isEmpty()`, `clear()`, and `height()` provide basic collection and shape information.
- An empty tree has height 0; a one-node tree has height 1.

The tree is not self-balancing. Operations take O(h), where h is the height, and can reach O(n) for data inserted in sorted order. A self-balancing AVL or red-black tree would be a useful future addition.

## Getting started

Clone this repository and compile the source:

```bash
git clone https://github.com/smithsam-dev/Structify.git
cd Structify
javac src/*.java
java -cp src BinarySearchTree
```

## Example

```java
BinarySearchTree tree = new BinarySearchTree();
tree.insert(8);
tree.insert(3);
tree.insert(10);

System.out.println(tree.contains(3));          // true
System.out.println(tree.inorderTraversal());   // [3, 8, 10]
System.out.println(tree.size());               // 3

tree.delete(8);
```

## Contributing

Contributions, issues, and feature requests are welcome. Useful next steps include adding a balanced tree, a generic comparator-based tree, and automated unit tests.

1. Fork the project.
2. Create a feature branch.
3. Commit your changes and open a pull request.

## License

Distributed under the MIT License. See [LICENSE](LICENSE) for details.
