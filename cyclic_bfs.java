import java.util.*;

public class cyclic_bfs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of vertices (n x n matrix): ");
        int n = scanner.nextInt();

        int[][] graph = new int[n][n];
        int[] inDegree = new int[n];

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                graph[i][j] = scanner.nextInt();
                if (graph[i][j] == 1)
                    inDegree[j]++;
            }

        System.out.println("\nAdjacency Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nGraph Edges:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    System.out.println(i + " -> " + j);
                }
            }
        }

        // Kahn’s Algorithm
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (inDegree[i] == 0)
                queue.offer(i);

        int visitedCount = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            visitedCount++;

            for (int j = 0; j < n; j++) {
                if (graph[node][j] == 1) {
                    inDegree[j]--;
                    if (inDegree[j] == 0)
                        queue.offer(j);
                }
            }
        }

        if (visitedCount != n)
            System.out.println("\nGraph contains a cycle.");
        else
            System.out.println("\nGraph does not contain a cycle.");

        scanner.close();
    }
}
