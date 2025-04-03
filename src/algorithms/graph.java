package algorithms;

import java.util.*;

/**
 * Implementasi Graph untuk manajemen rute penerbangan antar kota
 */
public class graph  {
    static class Graph {
        private int numVertices;
        private LinkedList<Integer>[] adjacencyList;
        
        public Graph(int vertices) {
            numVertices = vertices;
            adjacencyList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjacencyList[i] = new LinkedList<>();
            }
        }
        
        void addEdge(int src, int dest) {
            if (src != dest) {
                adjacencyList[src].add(dest);
            }
        }
        
        void addUndirectedEdge(int src, int dest) {
            if (src != dest) {
                adjacencyList[src].add(dest);
                adjacencyList[dest].add(src);
            }
        }
        
        void BFS(int startVertex) {
            boolean[] visited = new boolean[numVertices];
            LinkedList<Integer> queue = new LinkedList<>();
            visited[startVertex] = true;
            queue.add(startVertex);
            
            System.out.println("BFS Traversal:");
            while (!queue.isEmpty()) {
                int s = queue.poll();
                System.out.print(s + " ");
                for (int n : adjacencyList[s]) {
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
            System.out.println();
        }
        
        void DFSUtil(int vertex, boolean[] visited) {
            visited[vertex] = true;
            System.out.print(vertex + " ");
            for (int n : adjacencyList[vertex]) {
                if (!visited[n]) {
                    DFSUtil(n, visited);
                }
            }
        }
        
        void DFS(int startVertex) {
            boolean[] visited = new boolean[numVertices];
            System.out.println("DFS Traversal:");
            DFSUtil(startVertex, visited);
            System.out.println();
        }
        
        boolean hasPath(int source, int destination) {
            boolean[] visited = new boolean[numVertices];
            LinkedList<Integer> queue = new LinkedList<>();
            visited[source] = true;
            queue.add(source);
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (current == destination) {
                    return true;
                }
                for (int n : adjacencyList[current]) {
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
            return false;
        }
        
        void printAllPaths(int source, int destination) {
            boolean[] visited = new boolean[numVertices];
            ArrayList<Integer> pathList = new ArrayList<>();
            pathList.add(source);
            System.out.println("All paths from " + source + " to " + destination + ":");
            printAllPathsUtil(source, destination, visited, pathList);
        }
        
        private void printAllPathsUtil(int current, int destination, boolean[] visited, List<Integer> pathList) {
            if (current == destination) {
                System.out.println(pathList);
                return;
            }
            visited[current] = true;
            for (int n : adjacencyList[current]) {
                if (!visited[n]) {
                    pathList.add(n);
                    printAllPathsUtil(n, destination, visited, pathList);
                    pathList.remove(pathList.size() - 1);
                }
            }
            visited[current] = false;
        }
    }
    
    static class FlightRoutes {
        public static void main(String[] args) {
            Graph flightGraph = new Graph(5);
            System.out.println("Menambahkan rute penerbangan...");
            flightGraph.addEdge(0, 1);
            flightGraph.addEdge(0, 2);
            flightGraph.addEdge(1, 2);
            flightGraph.addEdge(2, 3);
            flightGraph.addEdge(3, 4);
            
            System.out.println("\nBFS dari kota 2:");
            flightGraph.BFS(2);
            
            System.out.println("\nDFS dari kota 2:");
            flightGraph.DFS(2);
            
            int src = 0, dest = 4;
            System.out.println("\nApakah ada rute dari kota " + src + " ke kota " + dest + "? " 
                              + flightGraph.hasPath(src, dest));
            
            src = 0; dest = 3;
            System.out.println("\nSemua kemungkinan rute dari kota " + src + " ke kota " + dest + ":");
            flightGraph.printAllPaths(src, dest);
        }
    }
    
    public static void main(String[] args) {
        FlightRoutes.main(args);
    }
}

