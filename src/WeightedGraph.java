import java.util.*;

class Edge {
    int destination;
    int weight;
    
    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

public class WeightedGraph {
    private int vertices;
    private LinkedList<Edge>[] adjacencyList;

    @SuppressWarnings("unchecked")
    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyList[source].add(new Edge(destination, weight));
    }

    // Dijkstra's algorithm to find the shortest path
    public void dijkstra(int startVertex) {
        int[] distances = new int[vertices];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startVertex] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(startVertex, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.destination;

            for (Edge edge : adjacencyList[u]) {
                int v = edge.destination;
                int weight = edge.weight;

                // Relaxation step
                if (distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.add(new Edge(v, distances[v]));
                }
            }
        }

        System.out.println("Shortest path distances from Start Vertex " + startVertex + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.println("To Vertex " + i + " -> Distance: " + distances[i]);
        }
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(5);
        
        // Add directed, weighted edges
        graph.addEdge(0, 1, 9);
        graph.addEdge(0, 2, 6);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 3);
        
        graph.dijkstra(0);
    }
}
