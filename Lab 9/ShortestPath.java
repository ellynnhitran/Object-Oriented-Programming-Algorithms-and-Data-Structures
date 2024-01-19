
package lab10;
import java.util.*;
class Edge implements Comparable<Edge> {
int node;
int weight;
// TYPE YOUR CODE HERE
//source = node;
//for (int u = 0; u <= node; u++) {
// adjacencyList[u];
//}
Edge(int node, int weight){
this.node = node;
this.weight = weight;
}
@Override
public int compareTo(Edge o) {
// TODO Auto-generated method stub
return
Integer.valueOf(this.weight).compareTo(Integer.valueOf(o.weight));
}
}
class Graph {
int nodes = 0;
// adjacencyList[u] contains all the edges to adjacent node v with following
// weight w.
LinkedList<Edge>[] adjacencyList;
// for (int u = 0; u <= node; u++) {
// Edge e = new Edge();
// adjacencyList[u].add(e);
//}
private Integer[] trace, dist;
private PriorityQueue<Edge> heap;
// Initializes the size of arrays.
Graph(int n) {
nodes = n;
adjacencyList = new LinkedList[nodes + 1];
dist = new Integer[nodes + 1];
trace = new Integer[nodes + 1];
for (int i = 0; i <= nodes; i++) {
adjacencyList[i] = new LinkedList<Edge>();
dist[i] = Integer.MAX_VALUE;
}
}
// Adds an undirected edge between node u and node v with weight w.
void addEdge(int u, int v, int w) {
// TYPE YOUR CODE HERE
Edge e = new Edge(v, w);
Edge e_rev = new Edge(u, w);
//if (w == nodes) {
adjacencyList[u].add(e);
adjacencyList[v].add(e_rev);
//}
}
// Performs ShortestPath algorithm to find the shortest path from S to T and
// print the result.
// The shortest integer distance should be saved in dist[T]. Otherwise, some
// part of the original template should be modified.
void findShortestPath(int S, int T) {
// TYPE YOUR CODE HERE
ArrayList<Integer> route = getShortestPath(S, T);
System.out.println(dist[T]);
System.out.println(route.size());
for (int i = route.size() - 1; i >= 0; i--) {
System.out.print(route.get(i) + " ");
}
}
// Returns the ordered sequence of nodes on the shortest path from S to T.
private ArrayList<Integer> getShortestPath(int S, int T) {
// TYPE YOUR CODE HERE
heap = new PriorityQueue<Edge>();
// this.adjacencyList = adjacencyList;
for (int i = 0; i <= T; i++) {
dist[T] = Integer.MAX_VALUE;
}
dist[S] = 0;
heap.add(new Edge(S, dist[S]));
while(!heap.isEmpty()) {
//int u = heap.remove().node;
Edge e = heap.remove();
int s = e.node;
for(Edge adj_e: adjacencyList[s]) {
int v = adj_e.node;
if(dist[s] + adj_e.weight < dist[v]) {
dist[v] = dist[s] + adj_e.weight;
trace[v] = s;
heap.add(new Edge(v, dist[v]));
}
}
}
ArrayList <Integer> route = new ArrayList <Integer>();
while(T != S) {
route.add(T);
T = trace[T];
}
route.add(T);
return route;
}
}
public class ShortestPath {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.println("Enter all the Input:");
int N = sc.nextInt();
int M = sc.nextInt();
int S = sc.nextInt();
int T = sc.nextInt();
Graph g = new Graph(N);
for (int i = 0; i < M; i++) {
int u = sc.nextInt();
int v = sc.nextInt();
int w = sc.nextInt();
g.addEdge(u, v, w);
}
System.out.println("Output:");
g.findShortestPath(S, T);
sc.close();
}
}
