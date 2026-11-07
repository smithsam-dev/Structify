import java.util.Stack;

class ExprNode {
    char value;
    ExprNode left, right;

    public ExprNode(char item) {
        value = item;
        left = right = null;
    }
}

public class ExpressionTree {
    
    // Evaluates a postfix expression and builds the tree
    public ExprNode constructTree(char[] postfix) {
        Stack<ExprNode> st = new Stack<>();
        
        for (int i = 0; i < postfix.length; i++) {
            // If operand, push to stack
            if (!isOperator(postfix[i])) {
                st.push(new ExprNode(postfix[i]));
            } else {
                // If operator, pop two nodes and combine
                ExprNode t = new ExprNode(postfix[i]);
                t.right = st.pop();
                t.left = st.pop();
                st.push(t);
            }
        }
        return st.peek();
    }

    private boolean isOperator(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/' || c == '^');
    }

    // Inorder traversal to print the standard infix expression
    public void inorder(ExprNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.value + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        ExpressionTree et = new ExpressionTree();
        String postfix = "ab+ef*g*-";
        char[] charArray = postfix.toCharArray();
        
        ExprNode root = et.constructTree(charArray);
        System.out.println("Infix Expression (Inorder Traversal):");
        et.inorder(root);
        System.out.println();
    }
}
