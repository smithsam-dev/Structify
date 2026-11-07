class SparseNode {
    int row, col, value;
    SparseNode next;

    public SparseNode(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.next = null;
    }
}

public class SparseMatrix {
    private SparseNode head;
    private int maxRows, maxCols;

    public SparseMatrix(int rows, int cols) {
        this.maxRows = rows;
        this.maxCols = cols;
        this.head = null;
    }

    // Insert only non-zero values to save memory
    public void insert(int row, int col, int value) {
        if (value == 0) return; 
        
        SparseNode newNode = new SparseNode(row, col, value);
        if (head == null) {
            head = newNode;
        } else {
            SparseNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void display() {
        System.out.println("Sparse Matrix Data (Row, Col) = Value:");
        SparseNode temp = head;
        while (temp != null) {
            System.out.println("[" + temp.row + ", " + temp.col + "] = " + temp.value);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        // Simulating a 4x5 matrix with mostly zeros
        SparseMatrix matrix = new SparseMatrix(4, 5);
        matrix.insert(0, 2, 3);
        matrix.insert(1, 4, 8);
        matrix.insert(3, 1, 5);
        
        matrix.display();
    }
}
