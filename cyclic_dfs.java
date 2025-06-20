import java.util.*;

public class cyclic_dfs {
    static boolean[] visited;
    static boolean[] recursionStack;
    static List<Integer> cycle = new ArrayList<>();
    static int[][] graph;
    static int nodes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of vertices (n x n matrix): ");
        nodes = scanner.nextInt();

        graph = new int[nodes][nodes];
        visited = new boolean[nodes];
        recursionStack = new boolean[nodes];

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < nodes; i++)
            for (int j = 0; j < nodes; j++)
                graph[i][j] = scanner.nextInt();

        System.out.println("\nAdjacency Matrix:");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nGraph Edges:");
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                if (graph[i][j] == 1) {
                    System.out.println(i + " -> " + j);
                }
            }
        }

        boolean hasCycle = false;
        for (int i = 0; i < nodes; i++) {
            if (dfs(i)) {
                hasCycle = true;
                break;
            }
        }

        if (hasCycle) {
            System.out.println("\nGraph contains a cycle.");
            System.out.print("Vertices in the cycle: ");
            for (int v : cycle)
                System.out.print(v + " ");
            System.out.println();
        } else {
            System.out.println("\nGraph does not contain a cycle.");
        }

        scanner.close();
    }

    static boolean dfs(int node) {
        if (recursionStack[node]) {
            cycle.add(node);
            return true;
        }
        if (visited[node]) return false;

        visited[node] = true;
        recursionStack[node] = true;

        for (int neighbor = 0; neighbor < nodes; neighbor++) {
            if (graph[node][neighbor] == 1) {
                if (dfs(neighbor)) {
                    if (!cycle.contains(node)) cycle.add(node);
                    return true;
                }
            }
        }

        recursionStack[node] = false;
        return false;
    }
}
